/**
 * 
 */
package net.xrm.fvc.service;

import net.xrm.fvc.dao.pojo.ModifiedFileInfo;
import net.xrm.fvc.exception.BusinessLogicException;

/**
 * @author alexey.kvitko
 * @date Sep 1, 2014 3:49:02 PM
 * @comment
 */
public interface MFIService {
	
	/**
	 * Get file info from table by name
	 * @param fileName
	 * @return ModifiedFile
	 */
	public ModifiedFileInfo getFileByName( String fileName ) throws BusinessLogicException;
	
	/**
	 * Insert new file info to table
	 * @param ModifiedFile
	 * 
	 */
	public void insertModifiedFileInfo( ModifiedFileInfo mfi ) throws BusinessLogicException;
	
	/**
	 * Delete file info from table
	 * @param ModifiedFile
	 * 
	 */
	public void updateModifiedFileInfo( ModifiedFileInfo mfi ) throws BusinessLogicException;

}
