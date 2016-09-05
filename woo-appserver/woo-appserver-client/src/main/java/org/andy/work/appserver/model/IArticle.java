package org.andy.work.appserver.model;

import java.util.Date;
import java.util.Set;

public interface IArticle {
	
	Integer getId();
	
	void setId(Integer id);
	
	String getTitle();
	
	void setTitle(String title);
	
	String getSummary();
	
	void setSummary(String summary);
	
	String getImages();
	
	void setImages(String images);
	
	String getColumnImages();
	
	void setColumnImages(String columnImages);
	
	String getAuthor();
	
	void setAuthor(String author);
	
	String getSource();
	
	void setSource(String source);
	
	Integer getViewNumber();
	
	void setViewNumber(Integer viewNumber);
	
	String getContent();
	
	void setContent(String content);
	
	String getState();
	
	void setState(String state);
	
	String getExamineState();
	
	void setExamineState(String examineState);
	
	String getHotspot();
	
	void setHotspot(String hotspot);
	
	String getPageTitle();
	
	void setPageTitle(String pageTitle);
	
	String getPageKeyword();
	
	void setPageKeyword(String pageKeyword);
	
	String getPageDescription();
	
	void setPageDescription(String pageDescription);
	
	IArticleCategory getCategory();
	
	void setCategory(IArticleCategory category);
	
	Date getCreateDate();
	
	void setCreateDate(Date createDate);
	
	String getCreateBy();
	
	void setCreateBy(String createBy);
	
	Date getUpdateDate();
	
	void setUpdateDate(Date updateDate);
	
	String getUpdateBy();
	
	void setUpdateBy(String updateBy);
	
	Set<IArticleCategory> getArticleCategorys();
	
	void setArticleCategorys(Set<IArticleCategory> articleCategorys);
	
}
