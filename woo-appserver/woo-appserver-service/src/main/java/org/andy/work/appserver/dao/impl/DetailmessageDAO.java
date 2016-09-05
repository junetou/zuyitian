package org.andy.work.appserver.dao.impl;

import java.util.List;
import java.util.regex.Pattern;

import org.andy.work.appserver.dao.IDetailmessageDAO;
import org.andy.work.appserver.dao.obj.QueryHelper;
import org.andy.work.appserver.model.IDetailmessage;
import org.andy.work.appserver.model.IUser;
import org.andy.work.appserver.model.impl.Detailmessage;
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
public class DetailmessageDAO extends GenericDAO implements IDetailmessageDAO {
   
	@Override
	public Detailmessage getmessage(Integer thingsid){
		String hql="from Detailmessage t where t.id=:id";
		Query query=super.sessionFactory.getCurrentSession().createQuery(hql).setParameter("id", thingsid);
		
		return (Detailmessage)query.uniqueResult();
	}
	
	
	@Override
	public String addmessage(Detailmessage message){
		sessionFactory.getCurrentSession().save(message);
		return "success";
	}
	
	@Override
	public String updatemessage(Detailmessage message){
		sessionFactory.getCurrentSession().update(message);
		return "success";
	}
	
	@Override
	public String deletemessage(Integer thingid){
		sessionFactory.getCurrentSession().delete(thingid);
		return "success";
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public SearchResponse<IDetailmessage> searchUsers(SearchRequest<AcctUserSearchCriteria> searchReq){
		SearchResponse<IDetailmessage> searchResp = new SearchResponse<IDetailmessage>();
		AcctUserSearchCriteria criteria = searchReq.getCriteria();
		PagingManagement pgm = searchReq.getPgm();
		QueryHelper queryHelper = QueryHelper.getInstance();
		StringBuffer whereHql = new  StringBuffer(" where 1=1 ");
		StringBuffer hql = new StringBuffer("select count(u.thingsId) from Detailmessage u where 1=1 and u.overanalyzed = 1 ");
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql.toString());
		queryHelper.setToQuery(query, null);
		
		Long count = (Long) query.uniqueResult();
		searchResp.setTotalRecords(count);
		if (count > 0) {
    		if(StringUtil.hasValue(criteria.getKeyWord())){
    		String judgment=criteria.getKeyWord();
    		Pattern pattern=Pattern.compile("^[0-9]+([.]{0,1}[0-9]+){0,1}$");
    		  if(!pattern.matcher(judgment).matches())
    		  {
			  hql=new StringBuffer("from Detailmessage u where 1=1 and u.name = :name and u.overanalyzed = :overanalyzed  ");
	  		  queryHelper.addParameter("name", criteria.getKeyWord());
              queryHelper.addParameter("overanalyzed", 1);
	  		  query = session.createQuery(hql.toString());
		      queryHelper.setToQuery(query, pgm);
			  int counts=query.list().size();
			  count=(long)counts;
			  searchResp.setTotalRecords(count);
			  List<IDetailmessage> list = query.list();
			  searchResp.setResults(list);
    		  }
    		  else if(!judgment.matches("^[0-9]+$")){
    		  hql=new StringBuffer("from Detailmessage u where 1=1 and u.price = :price and u.overanalyzed = :overanalyzed ");
    		  Double it=Double.valueOf(criteria.getKeyWord());
    	  	  queryHelper.addParameter("price", it);
    	  	  queryHelper.addParameter("overanalyzed", 1);
  			  query = session.createQuery(hql.toString());
  			  queryHelper.setToQuery(query, pgm);
  			  int counts=query.list().size();
		      count=(long)counts;
		      searchResp.setTotalRecords(count);
			  List<IDetailmessage> list = query.list();
			  searchResp.setResults(list);
    		  }
    		  else{
    		  hql=new StringBuffer("from Detailmessage u where 1=1 and u.date = :date and u.overanalyzed = :overanalyzed ");
        	  queryHelper.addParameter("date", criteria.getKeyWord());
        	  queryHelper.addParameter("overanalyzed", 1);
  			  query = session.createQuery(hql.toString());
  			  queryHelper.setToQuery(query, pgm);
  			  int counts=query.list().size();
			  count=(long)counts;
			  searchResp.setTotalRecords(count);	
			  List<IDetailmessage> list = query.list();
			  searchResp.setResults(list);
    		  }
	  		} 
		    else{
		    hql = new StringBuffer("from Detailmessage u where 1=1 and u.overanalyzed = :overanalyzed");
		    queryHelper.addParameter("overanalyzed", 1);
		  	query = session.createQuery(hql.toString());
		    queryHelper.setToQuery(query, pgm);
			List<IDetailmessage> list = query.list();
			searchResp.setResults(list);
		    }		
		}
		
		return searchResp;
	}
	
	@Override
	public int checkcount(){
		String hql="select count(*) from Detailmessage";
		Query query=super.sessionFactory.getCurrentSession().createQuery(hql);
		int count = ((Long) query.iterate().next()).intValue();
		return count;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public SearchResponse<IDetailmessage> searchmessage(SearchRequest<AcctUserSearchCriteria> searchReq,Integer usernumber,String keyword){
		SearchResponse<IDetailmessage> searchResp = new SearchResponse<IDetailmessage>();
		QueryHelper queryhelper=QueryHelper.getInstance();
		AcctUserSearchCriteria criteria = searchReq.getCriteria();
		PagingManagement pgm = searchReq.getPgm();
		StringBuffer hql = new StringBuffer("select count(u.thingsId) from Detailmessage u ");
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql.toString());
		queryhelper.setToQuery(query, null);
		Long count=(Long) query.uniqueResult();
		searchResp.setTotalRecords(count);
	    
		if(count>0){
			if(StringUtil.hasValue(keyword)){
				hql=new StringBuffer("from Detailmessage u where 1=1 and u.name = :name and u.number=:number and u.overanalyzed = :overanalyzed ");
				queryhelper.addParameter("name", keyword);
				queryhelper.addParameter("number",usernumber);
				queryhelper.addParameter("overanalyzed", 1);
			}
			else{
				hql=new StringBuffer("from Detailmessage u where 1=1 and u.number = :number and u.overanalyzed = :overanalyzed ");
				queryhelper.addParameter("number",usernumber);
				queryhelper.addParameter("overanalyzed", 1);
			}
			query = session.createQuery(hql.toString());
			queryhelper.setToQuery(query, pgm);
			List<IDetailmessage> list=query.list();
			int counts=query.list().size();
			count=(long)counts;
			searchResp.setTotalRecords(counts);
			searchResp.setResults(list);
		}
        return searchResp;
	}
	
	
}
