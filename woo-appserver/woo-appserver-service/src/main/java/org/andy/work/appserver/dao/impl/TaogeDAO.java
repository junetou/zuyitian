package org.andy.work.appserver.dao.impl;

import org.andy.work.appserver.dao.ITaogeDAO;
import org.andy.work.appserver.model.impl.Taoge;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
public class TaogeDAO extends GenericDAO implements ITaogeDAO{

	@Override
	public int add(Taoge taoge) {
		sessionFactory.getCurrentSession().save(taoge);
		return 1;
	}

}
