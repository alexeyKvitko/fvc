/**
 * 
 */
package net.xrm.fvc.dao;

import net.xrm.fvc.dao.pojo.History;
import net.xrm.fvc.exception.DAOException;
import net.xrm.fvc.model.HistoryModel;

/**
 * @author alexey.kvitko
 * @date Sep 7, 2014 1:02:20 PM
 * @comment new  interface
 */
public interface HistoryDAO extends BaseDAO< History, Integer >{
	
	
	/**
	 * Get current commit revision
	 * @return int - current revision 
	 */
	public int getCurrentRevision() throws DAOException;
	
	/**
	 * Get HistoryModel form database
	 * @param History model
	 * @return HistoryModel 
	 */
	public History getHistoryModel( HistoryModel model) throws DAOException;

}
