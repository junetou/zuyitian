package org.andy.work.appserver.manager;

import java.io.IOException;

import org.andy.work.appserver.util.Config;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.ConcurrentUpdateSolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.springframework.stereotype.Component;

@Component
public class SolrManager {
	private HttpSolrServer httpServerZh;
	private ConcurrentUpdateSolrServer updateServerZh;
	
	public SolrManager() {
		String URL_ZH = Config.get("solr.url.zh");
		this.httpServerZh = this.initHttpServer(URL_ZH);
		
		this.updateServerZh = this.initUpdateServer(URL_ZH);
	}
	
	private HttpSolrServer initHttpServer(String url) {	
		HttpSolrServer httpServer = new HttpSolrServer(url);
		httpServer.setAllowCompression(true);
		httpServer.setConnectionTimeout(5000);
		httpServer.setMaxRetries(1);
		httpServer.setSoTimeout(10000);
		httpServer.setMaxTotalConnections(1000);
		httpServer.setFollowRedirects(false);
		httpServer.setAllowCompression(true);
		
		return httpServer;
	}
	
	private ConcurrentUpdateSolrServer initUpdateServer(String url) {
		ConcurrentUpdateSolrServer updateServer = new ConcurrentUpdateSolrServer(url, 1000, 1);
		updateServer.setConnectionTimeout(5000);
		updateServer.setSoTimeout(10000);
		
		return updateServer;
	}
	
	public HttpSolrServer getQueryServer(String lanCode) {
		if ("zh".equals(lanCode)) {
			return this.httpServerZh;
		}
		
		return null;
	}
	
	public ConcurrentUpdateSolrServer getUpdateServer(String lanCode) {
		if ("zh".equals(lanCode)) {
			return this.updateServerZh;
		}
		
		return null;
	}
	
	public void commint() throws SolrServerException, IOException {
		this.updateServerZh.commit(false, false);
	}
}
