package org.andy.work.appserver.dao.impl;

import java.util.List;
import java.util.regex.Pattern;

import org.andy.work.appserver.dao.INeedDAO;
import org.andy.work.appserver.dao.obj.QueryHelper;
import org.andy.work.appserver.model.INeed;
import org.andy.work.appserver.model.impl.Detailmessage;
import org.andy.work.appserver.model.impl.Need;
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
public class NeedDAO  extends GenericDAO implements INeedDAO {
	
	
	
	@Override
	public Need Search(Integer id){
		String hql="from Need u where  u.need = :need";
		Query query=super.sessionFactory.getCurrentSession().createQuery(hql).setParameter("need",id);
		return (Need)query.uniqueResult();
	}

	@Override
	public int checkcount(){
		String hql="select count(*) from Need";
		Query query=super.sessionFactory.getCurrentSession().createQuery(hql);
		int count = ((Long) query.iterate().next()).intValue();
		return count;
	}
	
	@Override
	public String addmessage(Need message){
		sessionFactory.getCurrentSession().save(message);
		return "success";
	}
	
	@Override
	public String updatemessage(Need message){
		sessionFactory.getCurrentSession().update(message);
		return "success";
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public SearchResponse<INeed> SearchCustomerNeed(SearchRequest<AcctUserSearchCriteria> searchReq){
		
		SearchResponse<INeed> searchResp = new SearchResponse<INeed>();
		QueryHelper queryhelper=QueryHelper.getInstance();
		AcctUserSearchCriteria criteria = searchReq.getCriteria();
		PagingManagement pgm = searchReq.getPgm();
		
		StringBuffer hql = new StringBuffer("select count(u.need) from Need u where 1=1 and u.overanalyzed = 1 ");
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql.toString());
		queryhelper.setToQuery(query, null);
		Long count=(Long) query.uniqueResult();
		searchResp.setTotalRecords(count);
	    
		if(count>0){
			if(StringUtil.hasValue(criteria.getKeyWord())){
	    		String judgment=criteria.getKeyWord();
	    		Pattern pattern=Pattern.compile("^[0-9]+([.]{0,1}[0-9]+){0,1}$");
	    		  if(!pattern.matcher(judgment).matches())
	    		  {
				  hql=new StringBuffer("from Need u where 1=1 and u.name = :name and u.overanalyzed = :overanalyzed  ");
		  		  queryhelper.addParameter("name", criteria.getKeyWord());
	              queryhelper.addParameter("overanalyzed", 1);
		  		  query = session.createQuery(hql.toString());
			      queryhelper.setToQuery(query, pgm);
				  int counts=query.list().size();
				  count=(long)counts;
				  searchResp.setTotalRecords(count);
				  List<INeed> list = query.list();
				  searchResp.setResults(list);
	    		  }
	    		  else if(!judgment.matches("^[0-9]+$")){
	    		  hql=new StringBuffer("from Need u where 1=1 and u.price = :price and u.overanalyzed = :overanalyzed ");
	    		  Double it=Double.valueOf(criteria.getKeyWord());
	    	  	  queryhelper.addParameter("price", it);
	    	  	  queryhelper.addParameter("overanalyzed", 1);
	  			  query = session.createQuery(hql.toString());
	  			  queryhelper.setToQuery(query, pgm);
	  			  int counts=query.list().size();
			      count=(long)counts;
			      searchResp.setTotalRecords(count);
				  List<INeed> list = query.list();
				  searchResp.setResults(list);
	    		  }
	    		  else{
	    		  hql=new StringBuffer("from Need u where 1=1 and u.date = :date and u.overanalyzed = :overanalyzed ");
	        	  queryhelper.addParameter("date", criteria.getKeyWord());
	        	  queryhelper.addParameter("overanalyzed", 1);
	  			  query = session.createQuery(hql.toString());
	  			  queryhelper.setToQuery(query, pgm);
	  			  int counts=query.list().size();
				  count=(long)counts;
				  searchResp.setTotalRecords(count);	
				  List<INeed> list = query.list();
				  searchResp.setResults(list);
	    		  }
		  		} 
			    else{
			    hql = new StringBuffer("from Need u where 1=1 and u.overanalyzed = :overanalyzed");
			    queryhelper.addParameter("overanalyzed", 1);
			  	query = session.createQuery(hql.toString());
			    queryhelper.setToQuery(query, pgm);
				List<INeed> list = query.list();
				searchResp.setResults(list);
			    }		
		}
        return searchResp;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public SearchResponse<INeed> SearchMy(SearchRequest<AcctUserSearchCriteria> searchReq,Integer usernumber){
		SearchResponse<INeed> searchResp = new SearchResponse<INeed>();
		QueryHelper queryhelper=QueryHelper.getInstance();
		AcctUserSearchCriteria criteria = searchReq.getCriteria();
		PagingManagement pgm = searchReq.getPgm();
		StringBuffer hql = new StringBuffer("select count(u.need) from Need u ");
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql.toString());
		queryhelper.setToQuery(query, null);
		Long count=(Long) query.uniqueResult();
		searchResp.setTotalRecords(count);
	    
		if(count>0){
			if(StringUtil.hasValue(criteria.getKeyWord())){
				hql=new StringBuffer("from Need u where 1=1 and u.name = :name and u.number=:number and u.overanalyzed = :overanalyzed ");
				queryhelper.addParameter("name", criteria.getKeyWord());
				queryhelper.addParameter("number",usernumber);
				queryhelper.addParameter("overanalyzed", 1);
			}
			else{
				hql=new StringBuffer("from Need u where 1=1 and u.number = :number and u.overanalyzed = :overanalyzed ");
				queryhelper.addParameter("number",usernumber);
				queryhelper.addParameter("overanalyzed", 1);
			}
			query = session.createQuery(hql.toString());
			queryhelper.setToQuery(query, pgm);
			List<INeed> list=query.list();
			int counts=query.list().size();
			count=(long)counts;
			searchResp.setTotalRecords(counts);
			searchResp.setResults(list);
		}
        return searchResp;
		
		
	}
	
	
}
