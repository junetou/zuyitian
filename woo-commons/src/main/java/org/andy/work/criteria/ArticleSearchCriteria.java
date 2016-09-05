package org.andy.work.criteria;

import java.io.Serializable;

public class ArticleSearchCriteria implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5396221242215169190L;
	private String keyword;
	private Integer categoryId;
	private String state;
	private String applyState;
	private String dateStart;
	private String dateEnd;
	private String orderMode;
	
	private String hotId;
	
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getApplyState() {
		return applyState;
	}
	public void setApplyState(String applyState) {
		this.applyState = applyState;
	}
	public String getDateStart() {
		return dateStart;
	}
	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}
	public String getDateEnd() {
		return dateEnd;
	}
	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}
	public String getOrderMode() {
		return orderMode;
	}
	public void setOrderMode(String orderMode) {
		this.orderMode = orderMode;
	}
	public String getHotId() {
		return hotId;
	}
	public void setHotId(String hotId) {
		this.hotId = hotId;
	}
}
