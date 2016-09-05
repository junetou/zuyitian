package org.andy.work.admin.controller.content.detail;

public class ArticleDetail {
	private Integer id;
	private String title;//标题
	private String summary;//摘要
	private String summaryImage;//摘要图片
	private String images;//图片
	private String columnImages;
	private String author;//作者
	private String source;//来源
	private Integer viewNumber;//浏览次数
	private String content;//内容
	private String state;//状态   见  NewsRecordStatus
	private String examineState;//审核状态   见  NewsRecordStatus
	private String hotspot;
	private String pageTitle;//页面标题
	private String pageKeyword;//页面关键字
	private String pageDescription;//页面描述
	private String categoryName;//文章分类
	private String categoryNames;//文章分类组
	private String createDate;//创建日期
	private String createBy;//创建者
	private String updateDate;//修改日期
	private String updateBy;//修改者
	
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
	public String getSummaryImage() {
		return summaryImage;
	}
	public void setSummaryImage(String summaryImage) {
		this.summaryImage = summaryImage;
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
	public String getHotspot() {
		return hotspot;
	}
	public void setHotspot(String hotspot) {
		this.hotspot = hotspot;
	}
	public String getCategoryNames() {
		return categoryNames;
	}
	public void setCategoryNames(String categoryNames) {
		this.categoryNames = categoryNames;
	}
}
