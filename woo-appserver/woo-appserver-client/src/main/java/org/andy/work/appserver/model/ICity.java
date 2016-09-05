package org.andy.work.appserver.model;

public interface ICity {
	
	Integer getId();
	
	void setId(Integer id);
	
	String getName();
	
	void setName(String name);
	
	String getGeocode();
	
	void setGeocode(String geocode);
	
	IProvince getProvince();
	
	void setProvince(IProvince province);
	
}
