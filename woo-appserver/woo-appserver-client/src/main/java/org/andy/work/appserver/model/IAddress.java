package org.andy.work.appserver.model;

import java.util.Date;

public interface IAddress
{

	Integer getId();

	void setId(Integer id);

	String getStreet();

	void setStreet(String street);

	String getPostcode();

	void setPostcode(String postcode);

	Date getCreatedDate();
	
	void setCreatedDate(Date createdDate);
	
	Date getUpdatedDate();
	
	void setUpdatedDate(Date updatedDate);
	
	ICountry getCountry();

	void setCountry(ICountry country);

	IProvince getProvince();

	void setProvince(IProvince province);

	ICity getCity();

	void setCity(ICity city);

	IArea getArea();

	void setArea(IArea area);

}