package org.andy.work.admin.controller.content.detail;

public class ArticleCategoryDetail {
	private Integer id;
	private String name;
	private String description;
	private Integer sort;
	private String likeUrl;//链接
	private String iconUrl;//ICON图片
	private String state;//状态    CommonState
	private String parentName;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public String getLikeUrl() {
		return likeUrl;
	}
	public void setLikeUrl(String likeUrl) {
		this.likeUrl = likeUrl;
	}
	public String getIconUrl() {
		return iconUrl;
	}
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
}
