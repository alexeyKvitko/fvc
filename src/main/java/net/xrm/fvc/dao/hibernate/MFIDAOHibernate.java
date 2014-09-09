/**
 * 
 */
package net.xrm.fvc.dao.hibernate;

import net.xrm.fvc.dao.MFIDAO;
import net.xrm.fvc.dao.pojo.ModifiedFileInfo;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author alexey.kvitko
 * @date Sep 1, 2014 3:39:23 PM
 * @comment New Class describe MFIDAOHibernate 
 */
@Repository("modifiedFile")
@Transactional
@Lazy
public class MFIDAOHibernate extends BaseDAOHibernate< ModifiedFileInfo, Integer> implements MFIDAO{

}
