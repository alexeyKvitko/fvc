/**
 * 
 */
package net.xrm.fvc.dao.hibernate;

import net.xrm.fvc.dao.HistoryDAO;
import net.xrm.fvc.dao.pojo.History;
import net.xrm.fvc.exception.DAOException;
import net.xrm.fvc.model.HistoryModel;

import org.hibernate.Query;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author alexey.kvitko
 * @date Sep 7, 2014 12:00:00 PM
 * @comment create HistoryHibernate class
 */
@Repository("history")
@Transactional
@Lazy
public class HistoryDAOHibernate extends BaseDAOHibernate< History, Integer> implements HistoryDAO{

	@Override
	@Transactional
	public int getCurrentRevision() throws DAOException {
		Integer revision = 0;
		try {			
			String hqlQuery = "SELECT max(revision) FROM " + getModelClass().getName();
			Query query = sessionFactory.getCurrentSession().createQuery( hqlQuery );
			query.setMaxResults( 1 );
			revision = ( Integer ) query.uniqueResult();
		} catch (Exception e) {
			throw new DAOException( e.getLocalizedMessage() );
		}
		return revision;
	}

	@Override
	public History getHistoryModel(HistoryModel model) throws DAOException {
		History result = null;
		try {			
			String hqlQuery = "FROM " + getModelClass().getName()+ " WHERE file_name = :file_name and author = :author "
					+ "and mod_date = :mod_date and comment = :comment and cls_mtd = :cls_mtd";
			Query query = sessionFactory.getCurrentSession().createQuery( hqlQuery );
			query.setParameter( "file_name", model.getFileName() );
			query.setParameter( "author", model.getAuthor() );
			query.setParameter( "mod_date",  model.getModDate() == null ? "" : model.getModDate());
			query.setParameter( "comment", model.getComment() == null ? ""  :  model.getComment());
			query.setParameter( "cls_mtd", model.getClassMethod() );
			query.setMaxResults( 1 );
			result = ( History ) query.uniqueResult();
		} catch (Exception e) {
			throw new DAOException( e.getLocalizedMessage() );
		}
		return result;
	}

}
