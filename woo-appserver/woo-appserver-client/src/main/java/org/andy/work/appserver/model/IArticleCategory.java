package org.andy.work.appserver.model;

import java.util.Set;

public interface IArticleCategory {
	
	Integer getId();
	
	void setId(Integer id);
	
	String getName();
	
	void setName(String name);
	
	String getDescription();
	
	void setDescription(String description);
	
	Integer getSort();
	
	void setSort(Integer sort);
	
	String getLikeUrl();
	
	void setLikeUrl(String likeUrl);
	
	String getIconUrl();
	
	void setIconUrl(String iconUrl);
	
	String getState();
	void setState(String state);
	
	IArticleCategory getParent();
	
	void setParent(IArticleCategory parent);
	
	Set<IArticle> getArticles();
	
	void setArticles(Set<IArticle> articles);
	
}
