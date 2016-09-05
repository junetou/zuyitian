package org.andy.work.appserver.dao.impl;


import java.util.List;

import org.andy.work.appserver.dao.ITradeDAO;
import org.andy.work.appserver.dao.obj.QueryHelper;
import org.andy.work.appserver.model.ITrade;
import org.andy.work.appserver.model.impl.Detailmessage;
import org.andy.work.appserver.model.impl.Trade;
import org.andy.work.criteria.AcctUserSearchCriteria;
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
public class TradeDAO  extends GenericDAO implements ITradeDAO {
	
	@SuppressWarnings("unchecked")
	@Override
	public SearchResponse<ITrade> searchborrow(SearchRequest<AcctUserSearchCriteria> searchReq,Integer userid){
		SearchResponse<ITrade> searchResp = new SearchResponse<ITrade>();
		QueryHelper queryhelper=QueryHelper.getInstance();
		AcctUserSearchCriteria criteria = searchReq.getCriteria();
		PagingManagement pgm = searchReq.getPgm();
		StringBuffer hql = new StringBuffer("select count(u.trade) from Trade u ");
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql.toString());
		queryhelper.setToQuery(query, null);
		Long count=(Long) query.uniqueResult();
		searchResp.setTotalRecords(count);
	    
		if(count>0){
	      hql=new StringBuffer("from Trade u where 1=1 and u.borrow = :borrow and u.ensure = :ensure and u.assign = :assign ");
	      queryhelper.addParameter("borrow", userid);
	      queryhelper.addParameter("ensure", 0);
	      queryhelper.addParameter("assign", 1);
		  query = session.createQuery(hql.toString());
		  queryhelper.setToQuery(query, pgm);
		  List<ITrade> list=query.list();
		  int counts=query.list().size();
		  count=(long)counts;
		  searchResp.setTotalRecords(counts);
		  searchResp.setResults(list);
		}
        return searchResp;	
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public SearchResponse<ITrade> searchseller(SearchRequest<AcctUserSearchCriteria> searchReq,Integer userid){
		SearchResponse<ITrade> searchResp = new SearchResponse<ITrade>();
		QueryHelper queryhelper=QueryHelper.getInstance();
		AcctUserSearchCriteria criteria = searchReq.getCriteria();
		PagingManagement pgm = searchReq.getPgm();
		StringBuffer hql = new StringBuffer("select count(u.trade) from Trade u ");
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql.toString());
		queryhelper.setToQuery(query, null);
		Long count=(Long) query.uniqueResult();
		searchResp.setTotalRecords(count);
	    
		if(count>0){
	      hql=new StringBuffer("from Trade u where 1=1 and u.seller = :seller and u.assign = :assign and u.ensure = :ensure ");
	      queryhelper.addParameter("seller", userid);	
	      queryhelper.addParameter("assign", 1);
	      queryhelper.addParameter("ensure", 0);
		  query = session.createQuery(hql.toString());
		  queryhelper.setToQuery(query, pgm);
		  List<ITrade> list=query.list();
		  int counts=query.list().size();
		  count=(long)counts;
		  searchResp.setTotalRecords(counts);
		  searchResp.setResults(list);
		}
        return searchResp;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public SearchResponse<ITrade> searchsuccess(SearchRequest<AcctUserSearchCriteria> searchReq,Integer userid){
		SearchResponse<ITrade> searchResp = new SearchResponse<ITrade>();
		QueryHelper queryhelper=QueryHelper.getInstance();
		AcctUserSearchCriteria criteria = searchReq.getCriteria();
		PagingManagement pgm = searchReq.getPgm();
		StringBuffer hql = new StringBuffer("select count(u.trade) from Trade u ");
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql.toString());
		queryhelper.setToQuery(query, null);
		Long count=(Long) query.uniqueResult();
		searchResp.setTotalRecords(count);	    
		if(count>0){
			  hql=new StringBuffer("from Trade u where 1=1 and u.borrow = :borrow and u.success = :success");
		      queryhelper.addParameter("borrow", userid);
		      queryhelper.addParameter("success", 1);
		      query = session.createQuery(hql.toString());
		  queryhelper.setToQuery(query, pgm);
		  int counts=query.list().size();
	      count=(long)counts;
		  List<ITrade> list=query.list();
		  searchResp.setTotalRecords(counts);
		  searchResp.setResults(list);
		}
        return searchResp;	
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public SearchResponse<ITrade> searchsuccessSeller(SearchRequest<AcctUserSearchCriteria> searchReq,Integer userid){
		SearchResponse<ITrade> searchResp = new SearchResponse<ITrade>();
		QueryHelper queryhelper=QueryHelper.getInstance();
		AcctUserSearchCriteria criteria = searchReq.getCriteria();
		PagingManagement pgm = searchReq.getPgm();
		StringBuffer hql = new StringBuffer("select count(u.trade) from Trade u ");
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql.toString());
		queryhelper.setToQuery(query, null);
		Long count=(Long) query.uniqueResult();
		searchResp.setTotalRecords(count);	    
		if(count>0){
			  hql=new StringBuffer("from Trade u where 1=1 and u.seller = :seller and u.success = :success");
		      queryhelper.addParameter("seller", userid);
		      queryhelper.addParameter("success", 1);
		      query = session.createQuery(hql.toString());
		  queryhelper.setToQuery(query, pgm);
		  int counts=query.list().size();
	      count=(long)counts;
		  List<ITrade> list=query.list();
		  searchResp.setTotalRecords(counts);
		  searchResp.setResults(list);
		}
        return searchResp;	
	}
	
	@Override
	public String updatemessage(Trade trade){
		sessionFactory.getCurrentSession().update(trade);
		return "success";
	}

	
	@Override
	public String addmessage(Trade trade){
		sessionFactory.getCurrentSession().save(trade);
		return "success";
	}
	
	@Override
	public Trade getTrade(Integer trade){
		String hql="from Trade t where t.id=:id";
		Query query=super.sessionFactory.getCurrentSession().createQuery(hql).setParameter("id", trade);	
		return (Trade)query.uniqueResult();
	}
	
}
