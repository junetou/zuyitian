package org.andy.work.appserver.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.andy.work.appserver.dao.IArticleDAO;
import org.andy.work.appserver.model.IArticle;
import org.andy.work.appserver.model.IArticleCategory;
import org.andy.work.appserver.model.impl.ArticleCategory;
import org.andy.work.appserver.service.IArticleMaintenanceService;
import org.andy.work.appserver.service.ISolrPublishMaintenanceService;
import org.andy.work.appserver.service.ISolrSearchMaintenanceService;
import org.andy.work.criteria.ArticleSearchCriteria;
import org.andy.work.criteria.CategoryCriteria;
import org.andy.work.paging.SearchRequest;
import org.andy.work.paging.SearchResponse;
import org.springframework.stereotype.Service;

@Service
public class ArticleMaintenanceService implements IArticleMaintenanceService {
	
	@Resource IArticleDAO articleDAO;
	@Resource
	private ISolrPublishMaintenanceService solrService;
	@Resource
	private ISolrSearchMaintenanceService solrSearch;

	@Override
	public SearchResponse<IArticle> searchArticle(SearchRequest<ArticleSearchCriteria> searchRequest) {
		return this.articleDAO.searchArticle(searchRequest);
	}

	@Override
	public List<IArticleCategory> getCategoryFormatList() {
		List<IArticleCategory> categoryList = this.getCategoryList();
		List<IArticleCategory> orderList = new ArrayList<IArticleCategory>();
		List<IArticleCategory> firstList = new ArrayList<IArticleCategory>();
		for (int i = 0; i < categoryList.size(); i++) {
			IArticleCategory firstCategory = categoryList.get(i);
			if(firstCategory.getParent() == null) {
				firstList.add(firstCategory);
			}
		}
		this.recursionCategory(categoryList, firstList, "", orderList);
		
		return orderList;
	}
	
	@Override
	public SearchResponse<IArticleCategory> searchCategory(SearchRequest<CategoryCriteria> searchRequest) {
		return this.articleDAO.searchCategory(searchRequest);
	}
	
	private List<IArticleCategory> getCategoryList() {
		return articleDAO.getCategoryList();
	}
	
	private void recursionCategory(List<IArticleCategory> categoryList, List<IArticleCategory> firstList, String separator, List<IArticleCategory> orderList) {
		for (IArticleCategory articleCategory : firstList) {
			IArticleCategory category = new ArticleCategory();
			category.setId(articleCategory.getId());
			category.setName(separator + articleCategory.getName());
			category.setDescription(articleCategory.getDescription());
			category.setSort(articleCategory.getSort());
			category.setParent(articleCategory.getParent());
			orderList.add(category);
			this.recursionCategory(categoryList, this.getChildredList(articleCategory.getId(), categoryList), "&nbsp;&nbsp;&nbsp;&nbsp;" + separator, orderList);
		}
	}
	
	private List<IArticleCategory> getChildredList(Integer parentId, List<IArticleCategory> categoryList) {
		List<IArticleCategory> children = new ArrayList<IArticleCategory>();
		for (int i = 0; i < categoryList.size(); i++) {
			IArticleCategory child = categoryList.get(i);
			if(child.getParent() != null && child.getParent().getId() == parentId) {
				children.add(child);
			}
		}
		return children;
	}

	@Override
	public IArticleCategory getCategoryById(Integer id) {
		return (IArticleCategory) this.articleDAO.getEntityById(ArticleCategory.class, id);
	}
	
	@Override
	public void saveOrUpdateCategory(IArticleCategory category) {
		this.articleDAO.makeSaveOrUpdate(category);
	}
	
	@Override
	public boolean deleteCategory(Integer id) {
		IArticleCategory category = (IArticleCategory) articleDAO.getEntityById(ArticleCategory.class, id);
		List<IArticleCategory> categories = this.articleDAO.getCategoryListById(id);
		List<IArticle> articles = this.articleDAO.getArticleListByCategoryId(id);
		boolean hasOther = categories.size() > 0 || articles.size() > 0;
		if(category != null && !hasOther) {
			this.articleDAO.deleteEntity(category);
			return true;
		}
		return false;
	}
}
