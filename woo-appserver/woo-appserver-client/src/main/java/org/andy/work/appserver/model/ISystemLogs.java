package org.andy.work.appserver.model;

import java.util.Date;

public interface ISystemLogs {
	
	Integer getId();
	void setId(Integer id);
	
	String getLogsType();
	void setLogsType(String logsType);
	
	String getContent();
	void setContent(String content);
	
	String getCreateBy();
	void setCreateBy(String createBy);
	
	Date getCreateDate();
	void setCreateDate(Date createDate);
	
	String getAddressIp();
	void setAddressIp(String addressIp);
}

