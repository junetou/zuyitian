package org.andy.work.appserver.service.impl;

import javax.annotation.Resource;

import org.andy.work.appserver.manager.SolrManager;
import org.andy.work.appserver.service.ISolrSearchMaintenanceService;
import org.springframework.stereotype.Service;

@Service
public class SolrSearchMaintenanceService implements ISolrSearchMaintenanceService {
	
	@Resource
	private SolrManager solrManager;
	
}
