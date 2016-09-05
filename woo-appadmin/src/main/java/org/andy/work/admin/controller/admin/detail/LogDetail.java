package org.andy.work.admin.controller.admin.detail;

public class LogDetail {
	private Integer id;
	private String logsType;
	private String content;
	private String createBy;
	private String addressIp;
	private String createDate;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLogsType() {
		return logsType;
	}
	public void setLogsType(String logsType) {
		this.logsType = logsType;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public String getAddressIp() {
		return addressIp;
	}
	public void setAddressIp(String addressIp) {
		this.addressIp = addressIp;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
}
