package org.andy.work.appserver.model.impl;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.andy.work.appserver.model.IArticle;
import org.andy.work.appserver.model.IArticleCategory;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 文章分类
 * @author Administrator
 *
 */
@Entity
@Table(name = "article_category")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region="org.andy.work.appserver.model.impl.ArticleCategory")
public class ArticleCategory implements Serializable, IArticleCategory {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6827568762694251668L;
	private Integer id;
	private String name;//名称
	private String description;//描述
	private Integer sort;//排序
	private String likeUrl;//链接
	private String iconUrl;//ICON图片
	private String state;//状态    CommonState
	private IArticleCategory parent;//父级
	private Set<IArticle> articles;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="category_name", length=60, nullable=false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="category_desc", length=500)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name="category_sort")
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	@Column(name="like_url", length=120)
	public String getLikeUrl() {
		return likeUrl;
	}
	public void setLikeUrl(String likeUrl) {
		this.likeUrl = likeUrl;
	}
	@Column(name="icon_url", length=160)
	public String getIconUrl() {
		return iconUrl;
	}
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}
	@Column(name="state", length = 30)
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@ManyToMany(mappedBy = "articleCategorys", cascade = CascadeType.ALL, targetEntity=Article.class)
	public Set<IArticle> getArticles() {
		return articles;
	}
	public void setArticles(Set<IArticle> articles) {
		this.articles = articles;
	}
	
	@ManyToOne(fetch = FetchType.EAGER, targetEntity=ArticleCategory.class)
	@JoinColumn(name = "category_id", nullable = true)
	public IArticleCategory getParent() {
		return parent;
	}
	public void setParent(IArticleCategory parent) {
		this.parent = parent;
	}
}
