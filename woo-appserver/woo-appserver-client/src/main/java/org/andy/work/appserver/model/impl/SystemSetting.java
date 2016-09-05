package org.andy.work.appserver.model.impl;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.andy.work.appserver.model.ISystemSetting;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name="sys_setting")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region="org.andy.work.appserver.model.impl.SystemSetting")
public class SystemSetting implements Serializable, ISystemSetting {

	private static final long serialVersionUID = 2054077422257044748L;
	private String key;
	private String value;
	
	@Id
	@Column(name="s_key", length=20)
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	@Column(name="val", length=250, nullable=false)
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
