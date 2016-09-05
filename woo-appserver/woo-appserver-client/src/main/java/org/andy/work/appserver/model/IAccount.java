package org.andy.work.appserver.model;


import java.util.Date;


public interface IAccount
{
	public Integer getId();
	public void setId(Integer id);
	
	public String getUsername();
	public void setUsername(String username);
	
	public String getLocked();
	public void setLocked(String locked);
	
	String getStatus();
	void setStatus(String status);
	
	Integer getLoginedCount();
	
	void setLoginedCount(Integer loginedCount);
	
	Integer getSex();
	void setSex(Integer sex);
	
	Integer getLoginFailCount();
	void setLoginFailCount(Integer loginFailCount);
	
	Date getLastLoginedDate();
	void setLastLoginedDate(Date lastLoginedDate);
	
	String getKfOpenId();
	void setKfOpenId(String kfOpenId);
	
	Date getLastLoginFailDate();
	void setLastLoginFailDate(Date lastLoginFailDate);
	
	public Date getCreatedDate();
	void setCreatedDate(Date createdDate);
	
	Date getLastUpdatedDate();
	void setLastUpdatedDate(Date lastUpdatedDate);
	
	public Date getBirthday();
	public void setBirthday(Date birthday);
	
	IAccountSecurity getAccountSecurity();
	void setAccountSecurity(IAccountSecurity accountSecurity);
	
	IAccountBalance getAccountBalance();
	void setAccountBalance(IAccountBalance accountBalance);
	
	String getOpenId();
	void setOpenId(String openId);

	String getNickname();
	void setNickname(String nickname);

	String getIconHead();
	void setIconHead(String iconHead);

	String getUnionID();
	void setUnionID(String unionID);

	String getAcctType();
	void setAcctType(String acctType);
}
