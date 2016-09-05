package org.andy.work.appserver.model;

public interface IProvince {
	
	Integer getId();
	
	void setId(Integer id);
	
	String getName();
	
	void setName(String name);
	
	ICountry getCountry();
	
	void setCountry(ICountry country);
	
}
