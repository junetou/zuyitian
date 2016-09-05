package org.andy.work.appserver.model;

import java.util.Date;

/**
 *
 *developer
 *2015年1月24日下午1:23:05
 *
 */
public interface ISaaccount
{

	Integer getId();

	void setId(Integer id);
	
	String getCustomerName();
	
	void setCustomerName(String customerName);

	String getUsername();

	void setUsername(String username);

	String getAccountType();

	void setAccountType(String accountType);
	
	String getPassword();
	
	void setPassword(String password);
	
	String getSalt();
	
	void setSalt(String salt);

	String getEmail();

	void setEmail(String email);

	String getSecureCode();

	void setSecureCode(String secureCode);

	Date getExpriedDate();

	void setExpriedDate(Date expriedDate);

	Date getCreatedDate();

	void setCreatedDate(Date createdDate);

}