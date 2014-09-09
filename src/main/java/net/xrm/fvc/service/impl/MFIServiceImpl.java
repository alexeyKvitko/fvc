/**
 * 
 */
package net.xrm.fvc.service.impl;

import net.xrm.fvc.AppConstants;
import net.xrm.fvc.dao.MFIDAO;
import net.xrm.fvc.dao.pojo.ModifiedFileInfo;
import net.xrm.fvc.exception.BusinessLogicException;
import net.xrm.fvc.exception.DAOException;
import net.xrm.fvc.service.MFIService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * @author alexey.kvitko
 * @date Sep 1, 2014 4:00:09 PM
 * @comment New class MFIServiceImpl
 */
@Service
@Lazy
public class MFIServiceImpl implements MFIService{
	
	
	@Autowired
	private MFIDAO mfiDAO;
	
	@Override
	public ModifiedFileInfo getFileByName(String fileName) throws BusinessLogicException {
		ModifiedFileInfo mfi = null;
		try {
			mfi = mfiDAO.findByFieldName( AppConstants.FIELD_FILE_NAME, fileName );
		} catch (DAOException e) {
			throw new BusinessLogicException( e.getMessage() );
		}
		return mfi;
	}

	@Override
	public void insertModifiedFileInfo(ModifiedFileInfo mfi ) throws BusinessLogicException {
		try {
			mfiDAO.insertRecord( mfi );
		} catch (DAOException e) {
			throw new BusinessLogicException( e.getMessage() );
		}
	}

	@Override
	public void updateModifiedFileInfo(ModifiedFileInfo mfi)
			throws BusinessLogicException {
		try {
			mfiDAO.updateRecord( mfi );
		} catch (DAOException e) {
			throw new BusinessLogicException( e.getMessage() );
		}
	}

}
