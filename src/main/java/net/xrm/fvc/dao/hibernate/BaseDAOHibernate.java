/**
 * 
 */
package net.xrm.fvc.dao.hibernate;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import net.xrm.fvc.dao.BaseDAO;
import net.xrm.fvc.exception.DAOException;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author alexey.kvitko
 * @date Sep 1, 2014 3:14:15 PM
 * @comment create BaseDAO 
 */
public class BaseDAOHibernate<Model, PK extends Serializable>  implements BaseDAO< Model,PK >{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BaseDAOHibernate.class);
	
	protected Class<Model> model;
	protected SessionFactory sessionFactory = null;


	/**
	 * @return the sessionFactory
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * @param sessionFactory the sessionFactory to set
	 */
	@Resource(name="sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	
	@SuppressWarnings("unchecked")
	public BaseDAOHibernate() {
		model = (Class<Model>)(( ParameterizedType)getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}

	public BaseDAOHibernate(Class<Model> p_model) {
		model = p_model;
	}

	public Class<Model> getModelClass() {
		return model;
	}

	
		
	@SuppressWarnings("unchecked")
	@Transactional
	public Model findByFieldName( String fieldName,Object param ) throws DAOException {
		Model model = null;
		try {			
			String hqlQuery = "FROM " + getModelClass().getName()+ " WHERE "+fieldName+" = :param";
			Query query = sessionFactory.getCurrentSession().createQuery( hqlQuery );
			query.setParameter( "param", param );
			query.setMaxResults( 1 );
			model = ( Model ) query.uniqueResult();
		} catch (Exception e) {
			throw new DAOException( e.getLocalizedMessage() );
		}
		return model;
	}

	@Transactional
	public Model insertRecord(Model p_newInstance) throws DAOException {
		try{
			sessionFactory.getCurrentSession().save( p_newInstance );	
		} catch ( Exception e ){
			LOGGER.error( "DAOException: "+e.getMessage()) ;
			throw new DAOException( e.getLocalizedMessage() );
		}
		return p_newInstance;
	}


	@Transactional
	public void deleteRecord(Model p_transientObject) throws DAOException {
		try {
			sessionFactory.getCurrentSession().delete( p_transientObject );	
		} catch (Exception e) {
			LOGGER.error( "DAOException: "+e.getMessage()) ;
			throw new DAOException( e.getLocalizedMessage() );
		}
	}
	
	@Transactional
	public void updateRecord(Model p_transientObject) throws DAOException {
		try {
			sessionFactory.getCurrentSession().update( p_transientObject );	
		} catch (Exception e) {
			LOGGER.error( "DAOException: "+e.getMessage()) ;
			throw new DAOException( e.getLocalizedMessage() );
		}
		
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Model> getAllByFieldName(String fieldName, Object param) throws DAOException {
		List< Model > models = null;
		try {			
			String hqlQuery = "FROM " + getModelClass().getName()+ " WHERE "+fieldName+" = :param";
			Query query = sessionFactory.getCurrentSession().createQuery( hqlQuery );
			query.setParameter( "param", param );
			models = (List <Model> ) query.list();
		} catch (Exception e) {
			throw new DAOException( e.getLocalizedMessage() );
		}
		return models;
	}
	
}
