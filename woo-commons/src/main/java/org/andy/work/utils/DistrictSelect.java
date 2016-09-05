package org.andy.work.utils;

import java.io.Serializable;

public class DistrictSelect implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -63243576669484706L;
	private Integer id;
	private String name;
	
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
	
}
