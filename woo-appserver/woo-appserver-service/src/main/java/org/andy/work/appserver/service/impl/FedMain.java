package org.andy.work.appserver.service.impl;

import javax.annotation.Resource;

import org.andy.work.appserver.dao.IDetailmessageDAO;
import org.andy.work.appserver.dao.IFedDao;
import org.andy.work.appserver.model.IFed;
import org.andy.work.appserver.model.impl.Fed;
import org.andy.work.appserver.service.IFedMain;
import org.andy.work.criteria.AcctUserSearchCriteria;
import org.andy.work.paging.SearchRequest;
import org.andy.work.paging.SearchResponse;
import org.springframework.stereotype.Service;

@Service
public class FedMain implements IFedMain{
	
	@Resource
	private IFedDao fed;
	
	@Override
    public SearchResponse<IFed> searchUsers(SearchRequest<AcctUserSearchCriteria> searchReq){
    	return this.fed.searchUsers(searchReq);
    }
	
	@Override
	public String addfed(Fed feds){
		fed.addfed(feds);
		return "success";
	}
	

}
