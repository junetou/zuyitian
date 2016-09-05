package org.andy.work.appserver.dao.impl;

import java.util.List;
import java.util.regex.Pattern;

import org.andy.work.appserver.dao.IUserDAO;
import org.andy.work.appserver.dao.obj.QueryHelper;
import org.andy.work.appserver.model.IUser;
import org.andy.work.appserver.model.IUserGroup;
import org.andy.work.criteria.AcctUserSearchCriteria;
import org.andy.work.paging.BasePaging;
import org.andy.work.paging.PagingManagement;
import org.andy.work.paging.SearchRequest;
import org.andy.work.paging.SearchResponse;
import org.andy.work.utils.StringUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
public class UserDAO extends GenericDAO implements IUserDAO {

	@Override
	public IUser finUserByusername(String username) {
		String hql = "from User u where u.username=:username and u.locked <> 'Y'";
		Query query = super.sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("username", username);
		return (IUser) query.uniqueResult();
	}

	@Override
	public SearchResponse<IUserGroup> searchSearchResponse(SearchRequest<String> searchReq) {
		SearchResponse<IUserGroup> searchResp = new SearchResponse<IUserGroup>();
		String keyword = searchReq.getCriteria();
		QueryHelper queryHelper = QueryHelper.getInstance();
		StringBuffer whereHql = new StringBuffer("where 1=1");
		if (StringUtil.hasValue(keyword)) {
			whereHql.append(" and w.name = :name");
			queryHelper.addParameter("name", keyword);
		}
		BasePaging paging = new BasePaging(searchReq.getPgm(), whereHql.toString(), "UserGroup", "", searchResp);
		this.searchPaging(paging, queryHelper);
		return searchResp;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getGroupUsers(Integer groupId) {
		String hql = "select u.displayName from User u where u.userGroup.id=:groupId";
		return this.sessionFactory.getCurrentSession().createQuery(hql).setParameter("groupId", groupId).list();
	}

	@Override
	public IUserGroup getUserGroupByName(String name) {
		String hql = "from UserGroup g where g.name=:name";
		return (IUserGroup) this.sessionFactory.getCurrentSession().createQuery(hql).setParameter("name", name).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public SearchResponse<IUser> searchUsers(SearchRequest<AcctUserSearchCriteria> searchReq) {
		SearchResponse<IUser> searchResp = new SearchResponse<IUser>();
		AcctUserSearchCriteria criteria = searchReq.getCriteria();
		PagingManagement pgm = searchReq.getPgm();
		QueryHelper queryHelper = QueryHelper.getInstance();
		StringBuffer whereHql = this.buildSecureUserHql(criteria, queryHelper);
		StringBuffer hql = new StringBuffer("select count(u.id) from User u");
		hql.append(whereHql);
		
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql.toString());
		queryHelper.setToQuery(query, null);
		
		Long count = (Long) query.uniqueResult();
		searchResp.setTotalRecords(count);
		if (count > 0) {
    		if(StringUtil.hasValue(criteria.getKeyWord())){
    		String judgment=criteria.getKeyWord();
    		Pattern pattern=Pattern.compile("[0-9]*");
    		  if(!pattern.matcher(judgment).matches())
    		  {
			  hql=new StringBuffer("from User w where 1=1 and w.displayName = :displayName ");
	  		  queryHelper.addParameter("displayName", criteria.getKeyWord());
    		  }
    		  else{
    		  hql=new StringBuffer("from User w where 1=1 and w.staffNum = :staffNum ");
    	  	  queryHelper.addParameter("staffNum", criteria.getKeyWord());	
    		  } 
	  		} 
		    else{
		    hql = new StringBuffer("from User u");
		    hql.append(whereHql);
	  		hql.append(" order by u.id");
		     }
			query = session.createQuery(hql.toString());
			queryHelper.setToQuery(query, pgm);
			
			List<IUser> list = query.list();
			searchResp.setResults(list);
		}
		return searchResp;
	}

	private StringBuffer buildSecureUserHql(AcctUserSearchCriteria criteria, QueryHelper queryHelper) {
		StringBuffer hql = new StringBuffer();
		hql.append(" where 1=1");
	//	hql.append(" where t.id=:id");
//		if(StringUtil.hasValue(criteria.getKeyWord())) {
	//	    hql.append(" where t.id=:id");
		//	hql.append(" and (u.username like :keyword or u.displayName like :keyword or u.staffNum like :keyword or u.depart like :keyword)");
		//	queryHelper.addParameter("keyword", "%"+criteria.getKeyWord()+"%");
	//	}
		return hql;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<IUserGroup> getUserGroup() {
		String hql = "from UserGroup u order by u.id";
		return this.sessionFactory.getCurrentSession().createQuery(hql).list();
	}

	@Override
	public boolean isAccount(String account) {
		String hql = "select count(u.id) from User u where u.username = :account";
		Long count = (Long) this.sessionFactory.getCurrentSession().createQuery(hql).setParameter("account", account).uniqueResult();
		return count  > 0;
	}
	
	@Override
	public boolean isUserGroup(Integer id) {
		String hql = "select count(u.id) from User u where u.userGroup.id = :id";
		Long count = (Long) this.sessionFactory.getCurrentSession().createQuery(hql).setParameter("id", id).uniqueResult();
		return count  > 0;
	}
	
	@Override
	public boolean hasusrname(String usrname){
		String hql=" from User t where t.username=:username";
		Query query=this.sessionFactory.getCurrentSession().createQuery(hql).setParameter("username", usrname);
		if(query.uniqueResult()!=null)
		{return false;}
		else
		{return true;}
	}
	
}
