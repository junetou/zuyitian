package org.andy.work.appserver.model.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.andy.work.appserver.model.IArticle;
import org.andy.work.appserver.model.IArticleCategory;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 新闻文章
 * @author Administrator
 *
 */
@Entity
@Table(name = "article")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region="org.andy.work.appserver.model.impl.Article")
public class Article implements Serializable, IArticle{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3951510025315238331L;
	private Integer id;
	private String title;//标题
	private String summary;//摘要
	private String images;//图片
	private String columnImages;//栏目图片
	private String author;//作者
	private String source;//来源
	private Integer viewNumber;//浏览次数
	private String content;//内容
	private String state;//状态   见  NewsRecordStatus
	private String examineState;//审核状态   见  NewsRecordStatus
	private String hotspot;//热点文章  见  NewsRecordStatus
	private String pageTitle;//页面标题
	private String pageKeyword;//页面关键字
	private String pageDescription;//页面描述
	private IArticleCategory category;//文章分类
	private Set<IArticleCategory> articleCategorys;
	private Date createDate;//创建日期
	private String createBy;//创建者
	private Date updateDate;//修改日期
	private String updateBy;//修改者
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="article_title", length=200, nullable=false)
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Column(name="article_summary", length=500)
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	@Column(name="article_images", length=120)
	public String getImages() {
		return images;
	}
	public void setImages(String images) {
		this.images = images;
	}
	@Column(name="column_images", length=120)
	public String getColumnImages() {
		return columnImages;
	}
	public void setColumnImages(String columnImages) {
		this.columnImages = columnImages;
	}
	@Column(name="article_author", length=30)
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	@Column(name="article_source", length=30)
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	@Column(name="article_view_number")
	public Integer getViewNumber() {
		return viewNumber;
	}
	public void setViewNumber(Integer viewNumber) {
		this.viewNumber = viewNumber;
	}
	@Column(name="article_content", columnDefinition="TEXT")
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Column(name="article_state", length=30)
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Column(name="article_examine_state", length=30)
	public String getExamineState() {
		return examineState;
	}
	public void setExamineState(String examineState) {
		this.examineState = examineState;
	}
	@Column(name="article_hotspot", length=30)
	public String getHotspot() {
		return hotspot;
	}
	public void setHotspot(String hotspot) {
		this.hotspot = hotspot;
	}
	@Column(name="article_pagetitle", length=200)
	public String getPageTitle() {
		return pageTitle;
	}
	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}
	@Column(name="article_pagekeyword", length=200)
	public String getPageKeyword() {
		return pageKeyword;
	}
	public void setPageKeyword(String pageKeyword) {
		this.pageKeyword = pageKeyword;
	}
	@Column(name="article_pagedesc", length=500)
	public String getPageDescription() {
		return pageDescription;
	}
	public void setPageDescription(String pageDescription) {
		this.pageDescription = pageDescription;
	}
	@ManyToOne(fetch = FetchType.EAGER, targetEntity=ArticleCategory.class)
	@JoinColumn(name = "category_id", nullable = true)
	public IArticleCategory getCategory() {
		return category;
	}
	public void setCategory(IArticleCategory category) {
		this.category = category;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	
	@ManyToMany(cascade={CascadeType.MERGE}, fetch=FetchType.EAGER, targetEntity=ArticleCategory.class)  
    @JoinTable(name="t_article_category", joinColumns={@JoinColumn(name="t_article_id")}, inverseJoinColumns={@JoinColumn(name="t_category_id")})
	public Set<IArticleCategory> getArticleCategorys() {
		return articleCategorys;
	}
	public void setArticleCategorys(Set<IArticleCategory> articleCategorys) {
		this.articleCategorys = articleCategorys;
	}
}
