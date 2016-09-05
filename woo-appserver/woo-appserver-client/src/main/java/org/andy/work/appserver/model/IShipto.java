package org.andy.work.appserver.model;

import java.util.Date;


public interface IShipto
{

	Integer getId();

	void setId(Integer id);
	
	String getContactName();
	
	void setContactName(String contactName);
	
	String getCellphone();
	
	void setCellphone(String cellphone);
	
	String getTelephone();
	
	void setTelephone(String telephone);

	IAccount getAccount();

	void setAccount(IAccount account);

	String getShipNum();

	void setShipNum(String shipNum);

	String getShipKey();

	void setShipKey(String shipKey);

	IAddress getAddress();

	void setAddress(IAddress address);

	String getDefaulted();

	void setDefaulted(String defaulted);

	Date getCreatedDate();
	
	void setCreatedDate(Date createdDate);
	
	Date getUpdatedDate();
	
	void setUpdatedDate(Date updatedDate);
}