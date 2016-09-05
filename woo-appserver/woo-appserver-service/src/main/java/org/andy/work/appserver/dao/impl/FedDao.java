package org.andy.work.appserver.dao.impl;

import java.util.List;

import org.andy.work.appserver.dao.IFedDao;
import org.andy.work.appserver.dao.obj.QueryHelper;
import org.andy.work.appserver.model.IFed;
import org.andy.work.appserver.model.impl.Fed;
import org.andy.work.criteria.AcctUserSearchCriteria;
import org.andy.work.paging.PagingManagement;
import org.andy.work.paging.SearchRequest;
import org.andy.work.paging.SearchResponse;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;



@Repository
@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
public class FedDao extends GenericDAO implements IFedDao{
	
	@SuppressWarnings("unchecked")
	@Override
	public SearchResponse<IFed> searchUsers(SearchRequest<AcctUserSearchCriteria> searchReq){
		SearchResponse<IFed> searchResp = new SearchResponse<IFed>();
		AcctUserSearchCriteria criteria = searchReq.getCriteria();
		PagingManagement pgm = searchReq.getPgm();
		QueryHelper queryHelper = QueryHelper.getInstance();
		StringBuffer whereHql = new  StringBuffer(" where 1=1 ");
		StringBuffer hql = new StringBuffer("select count(u.fedid) from Fed u");
		
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql.toString());
		queryHelper.setToQuery(query, null);
		
		Long count = (Long) query.uniqueResult();
		searchResp.setTotalRecords(count);
		if (count > 0) {
		    hql = new StringBuffer("from Fed u");
		    hql.append(" where 1=1 ");
	  		hql.append(" order by u.id");
			query = session.createQuery(hql.toString());
			queryHelper.setToQuery(query, pgm);
			
			List<IFed> list = query.list();
			searchResp.setResults(list);
		}
		
		return searchResp;
	}
	
	
	@Override
	public String addfed(Fed fed){
		sessionFactory.getCurrentSession().save(fed);
		return "success";
	}

}
