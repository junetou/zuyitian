package org.andy.work.admin.controller.content.form;

import java.util.List;

import org.andy.work.state.NewsRecordStatus;

public class ArticleForm {
	private Integer id;
	private String title;
	private String summary;
	private String images;
	private String columnImages;
	private String author = "大佑";
	private String source = "大佑云商";
	private Integer viewNumber = 0;
	private String content;
	private String state;
	private String examineState;
	private String hotspot = NewsRecordStatus.NOTHOTSPOT;//热点文章  见  NewsRecordStatus
	private String pageTitle;
	private String pageKeyword;
	private String pageDescription;
	private List<String> categoryId;
	private List<String> labelId;
	private String categoryName;
	private String createDate;
	private String createBy;
	private String updateDate;
	private String updateBy;
	private String mediaPath;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getImages() {
		return images;
	}
	public void setImages(String images) {
		this.images = images;
	}
	public String getColumnImages() {
		return columnImages;
	}
	public void setColumnImages(String columnImages) {
		this.columnImages = columnImages;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public Integer getViewNumber() {
		return viewNumber;
	}
	public void setViewNumber(Integer viewNumber) {
		this.viewNumber = viewNumber;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getExamineState() {
		return examineState;
	}
	public void setExamineState(String examineState) {
		this.examineState = examineState;
	}
	public String getPageTitle() {
		return pageTitle;
	}
	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}
	public String getPageKeyword() {
		return pageKeyword;
	}
	public void setPageKeyword(String pageKeyword) {
		this.pageKeyword = pageKeyword;
	}
	public String getPageDescription() {
		return pageDescription;
	}
	public void setPageDescription(String pageDescription) {
		this.pageDescription = pageDescription;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public List<String> getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(List<String> categoryId) {
		this.categoryId = categoryId;
	}
	public List<String> getLabelId() {
		return labelId;
	}
	public void setLabelId(List<String> labelId) {
		this.labelId = labelId;
	}
	public String getMediaPath() {
		return mediaPath;
	}
	public void setMediaPath(String mediaPath) {
		this.mediaPath = mediaPath;
	}
	public String getHotspot() {
		return hotspot;
	}
	public void setHotspot(String hotspot) {
		this.hotspot = hotspot;
	}
	
}
