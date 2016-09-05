package org.andy.work.appserver.service;

import org.andy.work.appserver.model.ITrade;
import org.andy.work.appserver.model.impl.Trade;
import org.andy.work.criteria.AcctUserSearchCriteria;
import org.andy.work.paging.SearchRequest;
import org.andy.work.paging.SearchResponse;

public interface ITradeMain {

	 SearchResponse<ITrade> searchborrow(SearchRequest<AcctUserSearchCriteria> searchReq,Integer userid);
		
	 SearchResponse<ITrade> searchseller(SearchRequest<AcctUserSearchCriteria> searchReq,Integer userid);
	 
	 SearchResponse<ITrade> searchsuccess(SearchRequest<AcctUserSearchCriteria> searchReq,Integer userid);
	 
	 SearchResponse<ITrade> searchsuccessSeller(SearchRequest<AcctUserSearchCriteria> searchReq,Integer userid);
	 
	 String updatemessage(Trade trade);
	 
	 String addmessage(Trade trade);
	 
	 Trade getTrade(Integer tradeid);
	 
}
