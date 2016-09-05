package org.andy.work.appserver.dao.impl;

import java.util.Date;
import java.util.List;

import org.andy.work.appserver.dao.IAccountDAO;
import org.andy.work.appserver.model.IAccount;
import org.andy.work.appserver.model.ISaaccount;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@SuppressWarnings("unchecked")
@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
public class AccountDAO extends GenericDAO implements IAccountDAO {

	@Override
	public ISaaccount getSaaccountByUsername(String username) {
		String hql = "from Saaccount a where a.username=:username";
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		query.setParameter("username", username);
		return (ISaaccount) query.uniqueResult();
	}

	
	@Override
	public ISaaccount getSaaccountBySecureCode(String secureCode) {
		String hql = "from Saaccount a where a.secureCode=:code";
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		query.setParameter("code", secureCode);
		
		List<ISaaccount> list = query.list();
		if (!list.isEmpty()) {
			return list.get(0);
		}
		
		return null;
	}


	@Override
	public boolean isUsernameExist(String username) {
		String hql = "select a.id from Account a where a.username=:username";
		List<Integer> list = this.sessionFactory.getCurrentSession().createQuery(hql).setParameter("username", username).list();
		if (list.isEmpty()) {
			return false;
		}
		return true;
	}


	@Override
	public IAccount findAccountByUsername(String username) {
		String hql = "from Account a where a.username=:username";
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("username", username);
		return (IAccount) query.uniqueResult();
	}


	@Override
	public IAccount getAccountBySecurityCode(String secCode) {
		String hql = "from Account a where a.accountSecurity.securityCode=:code";
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		query.setParameter("code", secCode);
		
		List<IAccount> list = query.list();
		if (!list.isEmpty()) {
			return list.get(0);
		}
		
		return null;
	}
	
	@Override
	public boolean isExpirationDate(Date date, Integer id) {
		String hql = "select count(a.id) from AccountSecurity a where a.id = :id and a.expirationDate > :toDay";
		Query query = super.sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("id", id).setParameter("toDay", date);
		Long count = (Long) query.uniqueResult();
		return count > 0;
	}

}
