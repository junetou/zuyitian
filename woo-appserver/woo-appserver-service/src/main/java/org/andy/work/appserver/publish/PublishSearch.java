package org.andy.work.appserver.publish;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.andy.work.appserver.dao.IArticleDAO;
import org.andy.work.appserver.doc.AcctUserSearchDocument;
import org.andy.work.appserver.manager.SolrManager;
import org.andy.work.appserver.model.IArticle;
import org.andy.work.criteria.AcctUserSearchCriteria;
import org.andy.work.paging.PagingManagement;
import org.andy.work.paging.SearchRequest;
import org.andy.work.paging.SearchResponse;
import org.andy.work.utils.StringUtil;
import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.util.ClientUtils;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.stereotype.Component;

@Component
public class PublishSearch {
	
	private static final Logger log = Logger.getLogger(PublishSearch.class);
	@Resource
	private SolrManager solrManager;
	@Resource
	private PublishHelper publishHelper;
	@Resource
	private IArticleDAO articleDAO;
	
	/**
	 * 开始发布搜索引擎
	 * @param search
	 * @throws SolrServerException
	 * @throws IOException
	 */
	public void start() throws SolrServerException, IOException {
		
		List<IArticle> articles = this.articleDAO.getArticleALL();
		List<AcctUserSearchDocument> docs = new ArrayList<AcctUserSearchDocument>();
		for (IArticle article : articles) {
			AcctUserSearchDocument doc = new AcctUserSearchDocument();
			doc.setId(article.getId()+"");
			doc.setAuthor(article.getAuthor());
			doc.setComments(article.getId());
			doc.setDescription(article.getSummary());
			doc.setSubject(article.getSummary());
			doc.setTitle(article.getTitle());
			
			docs.add(doc);
		}
		this.solrManager.getUpdateServer("zh").addBeans(docs);
		this.solrManager.commint();
	}

	/**
	 * 清楚所有搜索引擎
	 */
	public void removePublish() {
		try {
			this.solrManager.getQueryServer("zh").deleteByQuery("*:*");
			this.solrManager.commint();
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	/**
	 * 检索关键字
	 * @param searchRequest
	 * @return
	 */
	public SearchResponse<AcctUserSearchCriteria> keywordSearch(SearchRequest<AcctUserSearchCriteria> searchRequest) {
		SearchResponse<AcctUserSearchCriteria> searchResp = new SearchResponse<AcctUserSearchCriteria>();
		AcctUserSearchCriteria criteria = searchRequest.getCriteria();
		PagingManagement pgm = searchRequest.getPgm();
		SolrQuery solrQuery = new SolrQuery();
		
		boolean flag = true;
		String keyword = criteria.getTitle();
		if (StringUtil.hasValue(keyword)) {
			solrQuery.setParam("qf", "title^20 subject^10 description^5 itmSearchName text_suggest")
			.setHighlight(true)
			.setParam("hl.fl", "title subject description");
			solrQuery.setParam("defType", "dismax").setParam("q", ClientUtils.escapeQueryChars(keyword));
		} else {
			solrQuery.setParam("q", "id:*");
			flag = false;
		}
		
		solrQuery.setFields("id title subject description comments author") 
		.setFacet(true)
		.setStart(pgm.getFirstResult())
		.setRows(pgm.getNumberPerPage());
		
		try {
			QueryResponse queryResponse = this.solrManager.getQueryServer("zh").query(solrQuery);
			SolrDocumentList result= queryResponse.getResults();
			List<AcctUserSearchCriteria> beans = null;
			if (flag) {
				beans = this.getBeansHighlight(result, queryResponse);
			} else {
				beans = this.getBeans(result, queryResponse);
			}
			
			searchResp.setTotalRecords(queryResponse.getResults().getNumFound());
			searchResp.setResults(beans);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return searchResp;
	}


	private List<AcctUserSearchCriteria> getBeansHighlight(SolrDocumentList result, QueryResponse queryResponse) {
		List<AcctUserSearchCriteria> beans = new ArrayList<AcctUserSearchCriteria>();
		for (SolrDocument doc : result) {
			AcctUserSearchCriteria bean = new AcctUserSearchCriteria();
			
			String id = (String) doc.getFieldValue("id");
        	if(queryResponse.getHighlighting().get(id).get("title") != null) {
        		bean.setTitle(queryResponse.getHighlighting().get(id).get("title").toString().replace("[", "").replace("]", ""));
        	} else {
        		bean.setTitle(doc.get("title").toString().replace("[", "").replace("]", ""));
        	}
        	
        	if(queryResponse.getHighlighting().get(id).get("subject") != null) {
        		bean.setSubject(queryResponse.getHighlighting().get(id).get("subject").toString().replace("[", "").replace("]", ""));
        	} else {
        		bean.setSubject(doc.get("subject").toString().replace("[", "").replace("]", ""));
        	}
        	
        	if(queryResponse.getHighlighting().get(id).get("description") != null) {
        		bean.setDescription(queryResponse.getHighlighting().get(id).get("description").toString().replace("[", "").replace("]", ""));
        	} else {
        		bean.setDescription(doc.get("description").toString().replace("[", "").replace("]", ""));
        	}
			
			bean.setAuthor(doc.get("author").toString());
			bean.setComments(Integer.valueOf(doc.get("comments").toString()));
			bean.setId(doc.get("id").toString());
			beans.add(bean);
		}
		return beans;
	}
	
	private List<AcctUserSearchCriteria> getBeans(SolrDocumentList result, QueryResponse queryResponse) {
		List<AcctUserSearchCriteria> beans = new ArrayList<AcctUserSearchCriteria>();
		for (SolrDocument doc : result) {
			AcctUserSearchCriteria bean = new AcctUserSearchCriteria();
			
			bean.setTitle(doc.get("title").toString().replace("[", "").replace("]", ""));
			bean.setSubject(doc.get("subject").toString().replace("[", "").replace("]", ""));
			bean.setDescription(doc.get("description").toString().replace("[", "").replace("]", ""));
			
			bean.setAuthor(doc.get("author").toString());
			bean.setComments(Integer.valueOf(doc.get("comments").toString()));
			bean.setId(doc.get("id").toString());
			beans.add(bean);
		}
		return beans;
	}
	
}
