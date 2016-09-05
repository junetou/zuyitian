package org.andy.work.criteria;

import java.io.Serializable;

public class CategoryCriteria implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2785727203450792484L;
	private Integer id;
	private String keyword;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
}
