package org.andy.work.appserver.service;

import org.andy.work.appserver.model.IFed;
import org.andy.work.appserver.model.impl.Fed;
import org.andy.work.criteria.AcctUserSearchCriteria;
import org.andy.work.paging.SearchRequest;
import org.andy.work.paging.SearchResponse;

public interface IFedMain {


	SearchResponse<IFed> searchUsers(SearchRequest<AcctUserSearchCriteria> searchReq);
	
	String addfed(Fed feds);
	
}
