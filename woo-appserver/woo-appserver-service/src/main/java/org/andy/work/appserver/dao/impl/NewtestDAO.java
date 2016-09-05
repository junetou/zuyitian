package org.andy.work.appserver.dao.impl;

import java.util.List;

import org.andy.work.appserver.dao.INewtestDAO;
import org.andy.work.appserver.model.INewtest;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
public class NewtestDAO extends GenericDAO implements INewtestDAO  {

	@Override
	public INewtest test(int id) {
	    String hql="from Newtest n where n.id=:id";
		Query query = super.sessionFactory.getCurrentSession().createQuery(hql).setParameter("id", id);
		return (INewtest) query.uniqueResult();
	}
	
	@Override
	public int count(){
		String hql="select count(*) from Newtest";
		Query query=super.sessionFactory.getCurrentSession().createQuery(hql);
		int count = ((Long) query.iterate().next()).intValue();
		return count;
	}
	
	
}
