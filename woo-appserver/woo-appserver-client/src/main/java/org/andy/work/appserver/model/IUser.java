package org.andy.work.appserver.model;

import java.util.Date;

public interface IUser {
	
	Integer getId();

	void setId(Integer id);

	Integer getVersion();

	void setVersion(Integer version);

	String getUsername();

	void setUsername(String username);

	String getDisplayName();

	void setDisplayName(String displayName);

	String getStaffNum();

	void setStaffNum(String staffNum);

	String getDepart();

	void setDepart(String depart);

	String getPassword();

	void setPassword(String password);

	Integer getFailLoginCount();

	void setFailLoginCount(Integer failLoginCount);

	Date getFailLoginTime();

	void setFailLoginTime(Date failLoginTime);

	String getLocked();

	void setLocked(String locked);

	Date getLastLogin();

	void setLastLogin(Date lastLogin);

	Date getCreatedDate();

	void setCreatedDate(Date createdDate);

	String getMobile();

	void setMobile(String mobile);

	String getPhone();

	void setPhone(String phone);

	String getFaxNum();

	void setFaxNum(String faxNum);

	String getEmail();

	void setEmail(String email);

	String getAddress();

	void setAddress(String address);

	String getWeixin();

	void setWeixin(String weixin);

	String getQqNum();

	void setQqNum(String qqNum);

	String getRemark();

	void setRemark(String remark);

	String getExt1();

	void setExt1(String ext1);

	String getExt2();

	void setExt2(String ext2);

	String getExt3();

	void setExt3(String ext3);

	Integer getUserType();
	
	void setUserType(Integer userType);
	
	IUserGroup getUserGroup();

	void setUserGroup(IUserGroup userGroup);
	
}
