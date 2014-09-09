/**
 * 
 */
package net.xrm.fvc.dao;

import java.io.Serializable;
import java.util.List;

import net.xrm.fvc.exception.DAOException;

/**
 * @author alexey.kvitko
 * @date Sep 1, 2014 3:12:15 PM
 * @comment create BASEDAO interface
 */
public interface BaseDAO < Model, PK extends Serializable> {
	
	/**
	 * Find unique record from table
	 * @param String - fileName ( file name to find)
	 * @return Model 
	 */
	public Model findByFieldName( String fieldName,Object param ) throws DAOException;
	
	/**
	 * Get all record from table by fileName
	 * @param String - fileName ( file name to find)
	 * @return List of  models 
	 */
	public List< Model > getAllByFieldName( String fieldName,Object param ) throws DAOException;
	
	/**
	 * Create new record in table 
	 * @param Model p_instance - class to mapping the structure of table in database
	 * @return Model - new record in table
	 */	
	public Model insertRecord(Model p_newInstance) throws DAOException;
	
	/**
	 * Delete record from table 
	 * @param Model p_instance - class to mapping the structure of table in database
	 */	
	public void deleteRecord(Model p_transientObject) throws DAOException;
	
	/**
	 * Update record in table 
	 * @param Model p_instance - class to mapping the structure of table in database
	 */	
	public void updateRecord(Model p_transientObject) throws DAOException;

}
