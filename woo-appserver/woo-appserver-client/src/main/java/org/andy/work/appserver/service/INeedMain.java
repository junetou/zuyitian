package org.andy.work.appserver.service;

import org.andy.work.appserver.model.INeed;
import org.andy.work.appserver.model.impl.Need;
import org.andy.work.criteria.AcctUserSearchCriteria;
import org.andy.work.paging.SearchRequest;
import org.andy.work.paging.SearchResponse;

public interface INeedMain {

    SearchResponse<INeed> SearchCustomerNeed(SearchRequest<AcctUserSearchCriteria> searchReq);
	
	SearchResponse<INeed> SearchMy(SearchRequest<AcctUserSearchCriteria> searchReq,Integer usernumber);

	int checkcount();
	
	String addmessage(Need message);
	 
	String updatemessage(Need message);
	
	Need Search(Integer id);
}
