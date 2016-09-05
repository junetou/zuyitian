package org.andy.work.appserver.dao.impl;

import org.andy.work.appserver.dao.ITestDAO;
import org.andy.work.appserver.model.ITest;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
public class TestDAO extends GenericDAO implements ITestDAO {

	@Override
	public ITest test(int id) {
		String hql = "from Test t where t.id=:id";
		Query query = super.sessionFactory.getCurrentSession().createQuery(hql).setParameter("id", id);

		return (ITest) query.uniqueResult();
	}

}
