/**
 * 
 */
package net.xrm.fvc.service;

import java.util.List;

import net.xrm.fvc.exception.BusinessLogicException;
import net.xrm.fvc.model.HistoryModel;

/**
 * @author alexey.kvitko
 * @date Sep 7, 2014 3:45:34 PM
 * @comment create HistoryService interface
 */
public interface HistoryService {
	
	/**
	 * Get current revision
	 * 
	 */
	public int getCurrentRevision() throws BusinessLogicException;
	
	/**
	 * Get History model 
	 * @param model - HistoryModel
	 * @return HistoryModel - if exist else null
	 */
	public HistoryModel getHistoryModel(HistoryModel model) throws BusinessLogicException;
	
	/**
	 * Get History for file  
	 * @param path - file
	 * @return list of HistoryModel 
	 */
	public List<HistoryModel> getHistoryForFile(String path) throws BusinessLogicException;
	
	/**
	 * Insert new record in history
	 * @param model - HistoryModel
	 * 
	 */
	public void insertHistory( HistoryModel model ) throws BusinessLogicException;

}
