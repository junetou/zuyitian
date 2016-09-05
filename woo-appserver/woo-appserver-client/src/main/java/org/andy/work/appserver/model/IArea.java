package org.andy.work.appserver.model;

public interface IArea {
	
	Integer getId();
	
	void setId(Integer id);
	
	String getName();
	
	void setName(String name);
	
	String getGeocode();
	
	void setGeocode(String geocode);
	
	ICity getCity();
	
	void setCity(ICity city);
	
}
