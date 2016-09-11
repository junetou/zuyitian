package org.andy.work.appserver.dao;

import org.andy.work.appserver.model.INeed;
import org.andy.work.appserver.model.impl.Detailmessage;
import org.andy.work.appserver.model.impl.Need;
import org.andy.work.criteria.AcctUserSearchCriteria;
import org.andy.work.paging.SearchRequest;
import org.andy.work.paging.SearchResponse;

public interface INeedDAO extends IGenericDAO {
	
	SearchResponse<INeed> SearchCustomerNeed(SearchRequest<AcctUserSearchCriteria> searchReq);
	
	SearchResponse<INeed> SearchMy(SearchRequest<AcctUserSearchCriteria> searchReq,Integer usernumber);

	Need Search(Integer id);
	
	int checkcount();
	
	String addmessage(Need message);
	 
	String updatemessage(Need message);


	
}
