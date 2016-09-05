package org.andy.work.appserver.model;


import java.util.Date;

public interface IAccountSecurity
{

	Integer getId();

	void setId(Integer id);

	String getSecurityQuestion();

	void setSecurityQuestion(String securityQuestion);

	String getSecurityAnswer();

	void setSecurityAnswer(String securityAnswer);

	String getSecurityPassword();

	void setSecurityPassword(String securityPassword);
	
	String getAccountPassword();
	
	void setAccountPassword(String accountPassword);
	
	String getSalt();

	void setSalt(String salt);
	
	String getSecurityCode();
	
	void setSecurityCode(String securityCode);

	Date getExpirationDate();

	void setExpirationDate(Date expirationDate);

	Date getCreatedDate();

	void setCreatedDate(Date createdDate);

	Date getUpdatedDate();

	void setUpdatedDate(Date updatedDate);
	
	String getPhone();
	
	void setPhone(String phone);
	
	String getCellphone();
	
	void setCellphone(String cellhone);
	
	String getAuthCellphone();
	
	void setAuthCellphone(String authCellphone);
	
	String getEmail();
	
	void setEmail(String email);
	
	String getAuthEmail();
	
	void setAuthEmail(String authEmail);
	
	String getIdCardNum();
	
	void setIdCardNum(String idCardNum);
	
	String getRealName();
	
	void setRealName(String realName);
	
	String getAuthRealName();
	
	void setAuthRealName(String authRealName);
	
	Date getAuthRealNameDate();
	
	void setAuthRealNameDate(Date authRealNameDate);
	
	String getWecharNum();
	void setWecharNum(String wecharNum);
	
	String getQqNum();
	void setQqNum(String qqNum);
	
	Date getAuthorizationDate();
	void setAuthorizationDate(Date authorizationDate);
	
}