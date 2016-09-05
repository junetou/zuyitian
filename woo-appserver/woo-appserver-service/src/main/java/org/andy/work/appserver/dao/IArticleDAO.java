package org.andy.work.appserver.dao;

import java.util.List;

import org.andy.work.appserver.model.IArticle;
import org.andy.work.appserver.model.IArticleCategory;
import org.andy.work.criteria.ArticleSearchCriteria;
import org.andy.work.criteria.CategoryCriteria;
import org.andy.work.paging.SearchRequest;
import org.andy.work.paging.SearchResponse;

public interface IArticleDAO extends IGenericDAO {

	List<IArticle> getArticleALL();

	SearchResponse<IArticle> searchArticle(SearchRequest<ArticleSearchCriteria> searchRequest);

	List<IArticleCategory> getCategoryList();

	List<IArticleCategory> getCategoryListById(Integer id);

	List<IArticle> getArticleListByCategoryId(Integer id);

	SearchResponse<IArticleCategory> searchCategory(SearchRequest<CategoryCriteria> searchRequest);

	List<IArticleCategory> getCategoryListByState();

	List<IArticleCategory> getImmunityCagetoryById(Integer categoryId);
	
	List<IArticleCategory> getArticleCategoryByParayId(Integer categoryId);

	List<IArticle> findWinFiveYearsArticleImages(Integer categoryId);

}
