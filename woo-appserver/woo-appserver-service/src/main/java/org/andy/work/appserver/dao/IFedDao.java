package org.andy.work.appserver.dao;

import org.andy.work.appserver.model.IFed;
import org.andy.work.appserver.model.impl.Fed;
import org.andy.work.criteria.AcctUserSearchCriteria;
import org.andy.work.paging.SearchRequest;
import org.andy.work.paging.SearchResponse;

public interface IFedDao extends IGenericDAO {
      
	
	SearchResponse<IFed> searchUsers(SearchRequest<AcctUserSearchCriteria> searchReq);
	
	String addfed(Fed fed);
	
}
