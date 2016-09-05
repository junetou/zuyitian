package org.andy.work.appserver.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.andy.work.appserver.dao.IGenericDAO;
import org.andy.work.appserver.dao.obj.QueryHelper;
import org.andy.work.paging.BasePaging;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
public class GenericDAO implements IGenericDAO {
	
	@Resource
	protected SessionFactory sessionFactory;

	@Override
	public Object makePersist(Object obj) {
		this.sessionFactory.getCurrentSession().save(obj);
		return obj;
	}

	@Override
	public void makeUpdate(Object obj) {
		this.sessionFactory.getCurrentSession().update(obj);
	}

	@Override
	public Object getEntityById(Class<?> clazz, Serializable id) {
		return (Serializable) this.sessionFactory.getCurrentSession().get(clazz, id);
	}
	

	@Override
	public void makeSaveOrUpdate(Object obj) {
		this.sessionFactory.getCurrentSession().saveOrUpdate(obj);
	}
	
	@Override
	public void deleteEntity(Object obj) {
		this.sessionFactory.getCurrentSession().delete(obj);
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	
	public void searchPaging(BasePaging paging, QueryHelper queryHelper) {
		
		StringBuffer hql = new StringBuffer("select count(w.id) from ").append(paging.getClassName());
		hql.append(" w ").append(paging.getWhereHql());
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql.toString());
		queryHelper.setToQuery(query, null);
		
		Long count = (Long) query.uniqueResult();
		paging.getSearchResponse().setTotalRecords(count);
		
		if (count > 0) {
			hql = new StringBuffer("from ").append(paging.getClassName()).append(" w ");
			hql.append(paging.getWhereHql()).append(" ").append(paging.getSortMethod());
			query = session.createQuery(hql.toString());
			queryHelper.setToQuery(query, paging.getPgm());
			
			@SuppressWarnings("unchecked")
			List<Object> list = query.list();
			paging.getSearchResponse().setResultsObj(list);
		}
		
	}
}
