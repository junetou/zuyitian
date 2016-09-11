package org.andy.work.appserver.service.impl;

import javax.annotation.Resource;


import org.andy.work.appserver.dao.INeedDAO;
import org.andy.work.appserver.model.INeed;
import org.andy.work.appserver.model.impl.Need;
import org.andy.work.appserver.service.INeedMain;
import org.andy.work.criteria.AcctUserSearchCriteria;
import org.andy.work.paging.SearchRequest;
import org.andy.work.paging.SearchResponse;
import org.springframework.stereotype.Service;


@Service
public class NeedMain implements INeedMain{
	
	@Resource
	private INeedDAO detailmessage;
	
    public SearchResponse<INeed> SearchCustomerNeed(SearchRequest<AcctUserSearchCriteria> searchReq){
    	return this.detailmessage.SearchCustomerNeed(searchReq);
    }
	
	public SearchResponse<INeed> SearchMy(SearchRequest<AcctUserSearchCriteria> searchReq,Integer usernumber){
		return this.detailmessage.SearchMy(searchReq, usernumber);
	}

	public int checkcount(){
		return this.detailmessage.checkcount();
	}
	
	public String addmessage(Need message){
		String judge=this.detailmessage.addmessage(message);
		return judge;
	}
	 
	public String updatemessage(Need message){
		String judge=this.detailmessage.updatemessage(message);
		return judge;
	}
	
	public Need Search(Integer id){
		return this.detailmessage.Search(id);
	}

}
