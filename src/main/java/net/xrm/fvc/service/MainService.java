/**
 * 
 */
package net.xrm.fvc.service;

import net.xrm.fvc.model.MFIModel;
import net.xrm.fvc.service.response.BaseResponse;
import net.xrm.fvc.service.response.FileResponse;
import net.xrm.fvc.service.response.HistoryResponse;
import net.xrm.fvc.service.response.MFIResponse;


/**
 * @author alexey.kvitko
 * @date Aug 22, 2014 1:19:32 PM
 * @comment Create Service
 */
public interface MainService {
	
	/**
	 * Get All Dirs And files Path
	 * @param String path - root dir
	 * @return FileResponse - response which contains directories and files
	 */
	public FileResponse getFiles(String path) ;
	
	
	/**
	 * Get supprorted and modidied file info from dir 
	 * @param String path - root dir
	 * @return MFIResponse - response which contains supported files
	 */
	public MFIResponse getSupportedModifiedFiles(String path) ;
	
	
	/**
	 * Parse array of files and if it has any changes save them to db
	 * @param Array mfiModels - array of MfiModel
	 * @retrun BaseResponse
	 * @author alexey.kvitko
	 * @date 09/07/2014
	 * @comment check how it work
	 */
	public BaseResponse parseFileAndSaveHistoryToDB(MFIModel[] mfiModels) ;
	
	/**
	 * Get history for seleted file 
	 * @param String path - file
	 * @return HistoryResponse - response which contains supported files
	 */
	public HistoryResponse getHistoryForFile(String path) ;

}
