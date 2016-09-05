package org.andy.work.appserver.service;

import java.util.List;

import org.andy.work.appserver.model.IArticle;
import org.andy.work.appserver.model.IArticleCategory;
import org.andy.work.criteria.ArticleSearchCriteria;
import org.andy.work.criteria.CategoryCriteria;
import org.andy.work.paging.SearchRequest;
import org.andy.work.paging.SearchResponse;

public interface IArticleMaintenanceService {

	SearchResponse<IArticle> searchArticle(SearchRequest<ArticleSearchCriteria> searchRequest);

	List<IArticleCategory> getCategoryFormatList();

	SearchResponse<IArticleCategory> searchCategory(SearchRequest<CategoryCriteria> searchRequest);

	IArticleCategory getCategoryById(Integer id);

	void saveOrUpdateCategory(IArticleCategory category);

	boolean deleteCategory(Integer id);

}
