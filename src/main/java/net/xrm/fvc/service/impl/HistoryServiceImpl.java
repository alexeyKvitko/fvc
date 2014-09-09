/**
 * 
 */
package net.xrm.fvc.service.impl;

import java.util.ArrayList;
import java.util.List;

import net.xrm.fvc.AppConstants;
import net.xrm.fvc.dao.HistoryDAO;
import net.xrm.fvc.dao.pojo.History;
import net.xrm.fvc.exception.BusinessLogicException;
import net.xrm.fvc.exception.DAOException;
import net.xrm.fvc.model.HistoryModel;
import net.xrm.fvc.service.HistoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * @author alexey.kvitko
 * @date Sep 7, 2014 3:47:14 PM
 * @comment new HistoryServiceImpl
 */
@Service
@Lazy
public class HistoryServiceImpl implements HistoryService{
	
	@Autowired
	private HistoryDAO historyDAO;
	

	@Override
	public int getCurrentRevision()  throws BusinessLogicException {
		try {
			return historyDAO.getCurrentRevision();
		} catch (DAOException e) {
			throw new BusinessLogicException( e.getMessage() );
		}
	}


	@Override
	public HistoryModel getHistoryModel( HistoryModel model ) throws BusinessLogicException {
		try {
			return mapPojoToModel( historyDAO.getHistoryModel(model) );
		} catch (DAOException e) {
			throw new BusinessLogicException( e.getMessage() );
		}
	}


	@Override
	public void insertHistory(HistoryModel model) throws BusinessLogicException {
		try {
			historyDAO.insertRecord( mapModelToPojo( model ) );
		} catch (DAOException e) {
			throw new BusinessLogicException( e.getMessage() );
		}
		
	}
	
	private History mapModelToPojo( HistoryModel model ){
		History pojo = new History();
		pojo.setAuthor( model.getAuthor() );
		pojo.setRevision( model.getRevision() );
		pojo.setFileName( model.getFileName() );
		pojo.setModDate( model.getModDate() == null ? "" : model.getModDate() );
		pojo.setComment( model.getComment()  == null ? "" : model.getComment() );
		pojo.setClassMethod( model.getClassMethod() );
		return pojo;
	}
	
	private HistoryModel mapPojoToModel( History pojo ){
		if ( pojo == null ){
			return null;
		}
		HistoryModel model = new HistoryModel();
		model.setAuthor( pojo.getAuthor() );
		model.setRevision( pojo.getRevision() );
		model.setModDate( pojo.getModDate() );
		model.setFileName( pojo.getModDate() );
		model.setComment( pojo.getComment() );
		model.setClassMethod( pojo.getClassMethod() );
		return model;
	}


	@Override
	public List<HistoryModel> getHistoryForFile(String path) throws BusinessLogicException {
		List< History > histories = null;
		List< HistoryModel > historyModels = null;
		try {
			histories = historyDAO.getAllByFieldName( AppConstants.FIELD_FILE_NAME, path );
		} catch (DAOException e) {
			throw new BusinessLogicException( e.getMessage() );
		}
		if  ( histories != null ){
			historyModels = new ArrayList< HistoryModel >();
			for ( History history : histories ){
				historyModels.add( mapPojoToModel(history) );
			}
		}
		return historyModels;
	}
	

}
