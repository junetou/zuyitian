package org.andy.work.appserver.service;

import org.andy.work.criteria.AcctUserSearchCriteria;
import org.andy.work.paging.SearchRequest;
import org.andy.work.paging.SearchResponse;

public interface ISolrPublishMaintenanceService {
	
	void startProduct();
	
	void removePublish();

	SearchResponse<AcctUserSearchCriteria> keywordSearch(SearchRequest<AcctUserSearchCriteria> searchRequest);
	
}
