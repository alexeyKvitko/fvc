/**
 * 
 */
package net.xrm.fvc.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.CRC32;

import net.xrm.fvc.AppConstants;
import net.xrm.fvc.dao.pojo.ModifiedFileInfo;
import net.xrm.fvc.exception.BusinessLogicException;
import net.xrm.fvc.model.FileNode;
import net.xrm.fvc.model.HistoryModel;
import net.xrm.fvc.model.MFIModel;
import net.xrm.fvc.service.HistoryService;
import net.xrm.fvc.service.MFIService;
import net.xrm.fvc.service.MainService;
import net.xrm.fvc.service.response.BaseResponse;
import net.xrm.fvc.service.response.FileResponse;
import net.xrm.fvc.service.response.HistoryResponse;
import net.xrm.fvc.service.response.MFIResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * @author alexey.kvitko
 *
 */
@Service
@Lazy
public class MainServiceImpl implements MainService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MainServiceImpl.class);
	
	@Autowired
	private MFIService  mfiService;
	
	@Autowired
	private HistoryService  historyService;
	
	@Autowired
	private MessageSource messageSource;
	
	private int currentRevision;

	@Override
	public FileResponse getFiles(String path) {
		String fileTypes = messageSource.getMessage( AppConstants.SUPPORTED_FILE_TYPES, null,null) ;
		FileResponse response = new FileResponse();
		if  ( path.equals("root") ){
			File[] roots = File.listRoots();
			if ( roots != null && roots.length != 0 ){
				path = roots[0].getPath();	
			}
		 else {
			response.setSuccess( false );
			response.setMessage( messageSource.getMessage( AppConstants.ERROR_GET_ROOT, null,null) );
			return response;
		 }
		}
		response.setSuccess( true );
		List< FileNode > files = new ArrayList< FileNode >();
		try {
			Path dir = Paths.get( path ).toRealPath();
			DirectoryStream< Path > stream = Files.newDirectoryStream( dir );
			for( Path entry : stream ){
				FileNode node = new FileNode();
				node.setId( entry.toRealPath().toString() );
				node.setText( entry.getFileName().toString() );
				if ( !entry.toFile().isDirectory() ){
					node.setExpanded( false );
					node.setLeaf( !entry.toFile().isDirectory() );
					if ( entry.toString().endsWith( fileTypes ) ){
						ModifiedFileInfo mfi = null;
						try {
							mfi = mfiService.getFileByName(  entry.toRealPath().toString().trim() );
						} catch (BusinessLogicException e) {
							String errorMsg = messageSource.getMessage( AppConstants.ERROR_CANNOT_GET_FILES, null,null)+" path: "+e.getMessage();
							LOGGER.error( errorMsg );
							response.setSuccess( false );
							response.setMessage( errorMsg );
							return response;
						}
						if ( mfi != null ){
							node.setRevision( mfi.getRevision() );
							node.setAuthor( mfi.getAuthor() );
							String commitDate = AppConstants.DATE_FORMAT.format( mfi.getCommitDate() )  ;
							node.setModifiedDate( commitDate );
						}
					}
				} 
			
				files.add( node );
				
			}
		} catch ( IOException e) {
			String errorMsg = messageSource.getMessage( AppConstants.ERROR_CANNOT_GET_FILES, null,null)+" path: "+e.getMessage();
			LOGGER.error( errorMsg );
			response.setSuccess( false );
			response.setMessage( errorMsg );
			return response;
		}
		
		response.setFileNode( files );
		return response;	
	}

	@Override
	public MFIResponse getSupportedModifiedFiles(String path) {
		MFIResponse response = new MFIResponse();
		response.setSuccess( true );
		String fileTypes = messageSource.getMessage( AppConstants.SUPPORTED_FILE_TYPES, null,null) ;
		List< MFIModel > mfiList = new ArrayList< MFIModel >();
		try {
			Path dir = Paths.get( path ).toRealPath();
			FindFilesVisitor fileVisitors = new FindFilesVisitor( fileTypes );
			Files.walkFileTree( dir, fileVisitors );
			List < Path > files = fileVisitors.getFiles();
			if ( files != null && !files.isEmpty() ){
				for( Path file : files ) {
					ModifiedFileInfo mfiPojo = mfiService.getFileByName( file.toRealPath().toString() );
					long crc32 = getCRCForFile( file );
					if ( mfiPojo == null || mfiPojo.getCrc32().longValue() != crc32 ){
						String status = mfiPojo == null ? AppConstants.FILE_STATUS_NEW : AppConstants.FILE_STATUS_MODIFIED;  
						MFIModel mfiModel = new MFIModel();
						mfiModel.setFileName( file.toRealPath().toString() );
						mfiModel.setStatus( status );
						mfiList.add( mfiModel );
					}
				}
			} else {
				response.setSuccess( false );
				response.setMessage( messageSource.getMessage( AppConstants.MSG_NOT_FOUND, null,null)+" "+messageSource.getMessage( AppConstants.SUPPORTED_FILE_TYPES, null,null) );
				return response;
			}
		} catch (IOException | BusinessLogicException e) {
			response.setSuccess( false );
			response.setMessage( messageSource.getMessage( AppConstants.ERROR_CANNOT_GET_FILES, null,null)+": "+path+e.getMessage() );
			return response;
		}
		if( !mfiList.isEmpty() ){
			response.setMfiList( mfiList );
		} else {
			response.setSuccess( false );
			response.setMessage( messageSource.getMessage( AppConstants.MSG_NOT_CHANGED, null,null) );
		}
		
		return response;
	}
	
	
	/**
	 *  Calculate CRC for File
	 *  @param Path file -  file to calculate CRC
	 *  @return long - CRC value
	 */
	private long getCRCForFile(Path file) throws IOException {
		FileInputStream source = null;
		FileChannel channel = null;
		CRC32 crc = null;
		try{
			 source = new FileInputStream( file.toFile() ); 
			 channel = source.getChannel();
			 crc = new CRC32();
			long length = channel.size();
			MappedByteBuffer buffer = channel.map( FileChannel.MapMode.READ_ONLY, 0, length );
			for (long p = 0; p < length; p++) {
				int c = buffer.get((int) p);
				crc.update(c);
			}
		} finally {
			if ( source != null ){
				source.close();
			}
			if ( channel != null ){
				channel.close();
			}
		}
		
		return crc.getValue();
	}
	

	@Override
	public BaseResponse parseFileAndSaveHistoryToDB(MFIModel[] mfiModels) {
		BaseResponse response = new BaseResponse();
		int count = 0;
		for(MFIModel mfiModel : mfiModels ){
			String author =  parseFileAndReturnLastAuthor( mfiModel.getFileName() );
			if ( author != null ){
				count++;
				Date now = new Date();
				mfiModel.setAuthor( author );
				mfiModel.setCommitDate( new Timestamp( now.getTime() ) );
				mfiModel.setRevision( getCurrentRevision()+1 );
				ModifiedFileInfo mfi = mapModelToPojo( mfiModel );
				try {
					if ( AppConstants.FILE_STATUS_NEW.equals( mfiModel.getStatus() ) ){
						mfiService.insertModifiedFileInfo( mfi );
					} else {
						ModifiedFileInfo mfiForId = mfiService.getFileByName( mfiModel.getFileName() );
						mfi.setId( mfiForId.getId() );
						mfiService.updateModifiedFileInfo( mfi );
					}
				} catch (BusinessLogicException e) {
					LOGGER.error( messageSource.getMessage( AppConstants.ERROR_CANNOT_UPDATE_FILE, null,null) );
				}
			}
		}
		if ( count == 0 ){
			response.setSuccess( false );
			response.setMessage( messageSource.getMessage( AppConstants.WARN_NO_COMMIT_FILES, null,null) );
		} else {
			response.setSuccess( true );
			response.setMessage( messageSource.getMessage( AppConstants.MSG_SUCCESS_COMMITTED , null,null)+ count );
		}
		return response;
	}
	
	/**
	 * @return the currentRevison
	 */
	public int getCurrentRevision() {
		return currentRevision;
	}

	/**
	 * @param currentRevison the currentRevison to set
	 */
	public void setCurrentRevision() {
		this.currentRevision = 0;
		try {
			this.currentRevision = historyService.getCurrentRevision();
		} catch (BusinessLogicException e) {
			LOGGER.error( messageSource.getMessage( AppConstants.ERROR_CANNOT_GET_REVISION, null,null) );
		}
		
	}

	private String parseFileAndReturnLastAuthor(String fileName){
		String lastAuthor = null;
		Path commitFile = Paths.get( fileName );
		int revision = getCurrentRevision()+1;
		List< HistoryModel > histories = new ArrayList< HistoryModel >();
		String anAuthor =  messageSource.getMessage( AppConstants.AN_AUTHOR, null,null);
		String anDate =  messageSource.getMessage( AppConstants.AN_DATE, null,null);
		String anComment =  messageSource.getMessage( AppConstants.AN_COMMENT, null,null);
		try {
			List<String> rows = Files.readAllLines( commitFile, StandardCharsets.UTF_8 );
			for ( int idx = 0 ; idx < rows.size(); idx++ ){
				String row = rows.get( idx ).trim();
				if ( row.contains( anAuthor ) ){
					HistoryModel historyModel = new HistoryModel();
					historyModel.setFileName( fileName );
					historyModel.setRevision( revision );
					int pos = row.indexOf( anAuthor )+anAuthor.length();
					historyModel.setAuthor( row.substring(pos, row.length()).trim() );
					idx++;
					while ( !rows.get( idx ).contains( AppConstants.SYMBOL_PARENTHESIS)  &&  !rows.get( idx ).contains(AppConstants.SYMBOL_SEMICOLON)  ){
						String innerRow = rows.get( idx ).trim();
						if ( innerRow.contains( anDate ) ){
							int datePos = row.indexOf( anDate )+anDate.length()+3;
							historyModel.setModDate( innerRow.substring(datePos, innerRow.length()).trim() );
						} else if ( innerRow.contains( anComment ) ){
							int commentPos = row.indexOf( anComment )+anComment.length()+3;
							historyModel.setComment( innerRow.substring(commentPos, innerRow.length()).trim() );
						}
						idx++;
					}
					String classMethod = rows.get( idx ).substring(0, rows.get( idx ).length()-1).trim();
					historyModel.setClassMethod( classMethod );
					histories.add( historyModel );
				}
			}
		} catch (IOException e) {
			LOGGER.error( messageSource.getMessage( AppConstants.ERROR_CANNOT_READ_FILE, null,null)+": "+commitFile+e.getMessage() );
		}
		try {
		 for ( HistoryModel model : histories ){
			HistoryModel oldModel = historyService.getHistoryModel( model );
			if ( oldModel == null ){
				historyService.insertHistory( model );
				lastAuthor = model.getAuthor();
			}
		}
		} catch (BusinessLogicException e) {
			LOGGER.error( e.getMessage());
		}
		return lastAuthor;
	}
	
	private ModifiedFileInfo mapModelToPojo(MFIModel mfiModel){
		ModifiedFileInfo mfi = new ModifiedFileInfo();
		mfi.setFileName( mfiModel.getFileName() );
		Path file = null;
		Long crs32 = null;
		try {
			file = Paths.get( mfiModel.getFileName() ).toRealPath();
			crs32 = getCRCForFile( file );
		} catch (IOException e) {
			LOGGER.error( messageSource.getMessage( AppConstants.ERROR_CANNOT_READ_FILE, null,null)+": "+file+e.getMessage() );
		}
		mfi.setCrc32(crs32 );
		mfi.setAuthor( mfiModel.getAuthor() );
		mfi.setRevision( mfiModel.getRevision() );
		mfi.setCommitDate( mfiModel.getCommitDate() );
		return mfi;
	}
	
	
	@Override
	public HistoryResponse getHistoryForFile(String path) {
		HistoryResponse response = new HistoryResponse();
		List<HistoryModel> historyModels = null;
		try {
			historyModels = historyService.getHistoryForFile( path );
		} catch (BusinessLogicException e) {
			response.setSuccess( false );
			response.setMessage( messageSource.getMessage( AppConstants.ERROR_CANNOT_GET_HISTORY, null,null)+": "+path+e.getMessage() );
			return response;
		}
		if ( historyModels.isEmpty() ){
			response.setSuccess( false );
			response.setMessage( messageSource.getMessage( AppConstants.ERROR_HISTORY_EMPTY, null,null));
			return response;
		}
		response.setHistories( historyModels );
		response.setSuccess( true );	
	    return response;
	}
	
	
	/**
	 * 
	 * @author alexey.kvitko
	 * @date Sep 1, 2014 2:21:35 PM
	 * @comment This class for getting supported files from start dir
	 */
	class FindFilesVisitor extends SimpleFileVisitor< Path >{
		List< Path > files;
		String fileTypes;
		
		public FindFilesVisitor( String fileTypes ){
			this.fileTypes = fileTypes;
			this.files = new ArrayList< Path >();
		}
		
		@Override
		public FileVisitResult visitFile( Path file, BasicFileAttributes attrs){
			if ( file.toString().endsWith( fileTypes ) ){
				files.add( file );
			}
			return FileVisitResult.CONTINUE;
		}

		/**
		 * @return the files
		 */
		public List<Path> getFiles() {
			return files;
		}
	}


	
	
	
}
