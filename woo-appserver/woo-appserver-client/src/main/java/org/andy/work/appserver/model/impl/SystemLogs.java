package org.andy.work.appserver.model.impl;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.andy.work.appserver.model.ISystemLogs;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 操作日期
 * @author Administrator
 *
 */
@Entity
@Table(name="sys_logs")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region="org.andy.work.appserver.model.impl.SystemLogs")
public class SystemLogs implements Serializable, ISystemLogs {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3072716045620052360L;
	private Integer id;
	private String logsType;
	private String content;
	private String createBy;
	private String addressIp;
	private Date createDate;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="logs_type", length=10, nullable=false)
	public String getLogsType() {
		return logsType;
	}
	public void setLogsType(String logsType) {
		this.logsType = logsType;
	}
	@Column(name="content", length=1000)
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Column(name="createBy", length=50)
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	@Column(name="crtdate", nullable=false)
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Column(name="address", length=30)
	public String getAddressIp() {
		return addressIp;
	}
	public void setAddressIp(String addressIp) {
		this.addressIp = addressIp;
	}

}
