package org.andy.work.appserver.service.impl;

import javax.annotation.Resource;

import org.andy.work.appserver.dao.IDetailmessageDAO;
import org.andy.work.appserver.model.IDetailmessage;
import org.andy.work.appserver.model.impl.Detailmessage;
import org.andy.work.appserver.service.IDetailmessageMain;
import org.andy.work.criteria.AcctUserSearchCriteria;
import org.andy.work.paging.SearchRequest;
import org.andy.work.paging.SearchResponse;
import org.springframework.stereotype.Service;

@Service
public class DetailmessageMain implements IDetailmessageMain {

	@Resource
	private IDetailmessageDAO detailmessage;
	
	@Override
	public Detailmessage getmessage(Integer thingsid){
		
		Detailmessage message=this.detailmessage.getmessage(thingsid);
		
		return message;
		
	}
	
	@Override
	public String addmessage(Detailmessage message){
		detailmessage.addmessage(message);
		return "success";
	}
	
	@Override
	public String updatemessage(Detailmessage message){
		detailmessage.updatemessage(message);
		return "success";
	}
	
	@Override
	public SearchResponse<IDetailmessage> searchUser(SearchRequest<AcctUserSearchCriteria> searchReq){
		return this.detailmessage.searchUsers(searchReq);
	}
	
	@Override
	public String deletemessage(Integer thingid){
		detailmessage.deletemessage(thingid);
		return "success";
	}
	
	@Override
	public int checkcount(){
		return this.detailmessage.checkcount();
	}
	
	@Override
	public SearchResponse<IDetailmessage> searchmessage(SearchRequest<AcctUserSearchCriteria> searchReq,Integer usernumber,String keyword){
		return this.detailmessage.searchmessage(searchReq, usernumber, keyword);
	}
	
	
}
