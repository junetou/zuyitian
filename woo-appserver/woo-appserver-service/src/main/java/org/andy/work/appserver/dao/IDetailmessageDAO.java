package org.andy.work.appserver.dao;


import org.andy.work.appserver.model.IDetailmessage;
import org.andy.work.appserver.model.IUser;
import org.andy.work.appserver.model.impl.Detailmessage;
import org.andy.work.criteria.AcctUserSearchCriteria;
import org.andy.work.paging.SearchRequest;
import org.andy.work.paging.SearchResponse;

public interface IDetailmessageDAO extends IGenericDAO{
    
	 Detailmessage getmessage(Integer thingsid);
     
	 String addmessage(Detailmessage message);
	 
	 String updatemessage(Detailmessage message);
	 
	 String deletemessage(Integer thingid);
	 
	 SearchResponse<IDetailmessage> searchUsers(SearchRequest<AcctUserSearchCriteria> searchReq);
	 
	 int checkcount();
	 
	 SearchResponse<IDetailmessage> searchmessage(SearchRequest<AcctUserSearchCriteria> searchReq,Integer usernumber,String keyword);
	 
}
