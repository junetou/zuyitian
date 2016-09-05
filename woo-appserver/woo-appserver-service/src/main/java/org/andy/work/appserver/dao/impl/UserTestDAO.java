package org.andy.work.appserver.dao.impl;

import org.andy.work.appserver.dao.IUserDAO;
import org.andy.work.appserver.dao.IUserTestDAO;
import org.andy.work.appserver.model.IUser;
import org.andy.work.appserver.model.IUserTest;
import org.andy.work.appserver.model.impl.UserTest;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
public class UserTestDAO extends GenericDAO implements IUserTestDAO {

	@Override
	public void add(UserTest userTest) {
		super.sessionFactory.getCurrentSession().save(userTest);
	}

	
}
