package org.andy.work.appserver.service.impl;

import java.io.IOException;

import javax.annotation.Resource;

import org.andy.work.appserver.publish.PublishSearch;
import org.andy.work.appserver.service.ISolrPublishMaintenanceService;
import org.andy.work.criteria.AcctUserSearchCriteria;
import org.andy.work.paging.SearchRequest;
import org.andy.work.paging.SearchResponse;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.stereotype.Service;

@Service
public class SolrPublishMaintenanceService implements ISolrPublishMaintenanceService {
	
	@Resource
	private PublishSearch publishSearch;

	@Override
	public void startProduct() {
		try {
			this.publishSearch.start();
		} catch (SolrServerException | IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void removePublish() {
		this.publishSearch.removePublish();
	}

	@Override
	public SearchResponse<AcctUserSearchCriteria> keywordSearch(SearchRequest<AcctUserSearchCriteria> searchRequest) {
		return this.publishSearch.keywordSearch(searchRequest);
	}
	
}
