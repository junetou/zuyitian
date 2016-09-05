package org.andy.work.appserver.model.impl;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.andy.work.appserver.model.IFed;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


@SuppressWarnings("serial")
@Entity
@Table(name="fed")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region="org.andy.work.appserver.model.impl.Fed")
public class Fed implements Serializable,IFed{
	private static final long serialVersionUID = 3777655431317415578L;
	public Integer Fedid;
	public Integer thingnumber;
	public String usedesc;
	public String name;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getFedid() {
		return Fedid;
	}
	public void setFedid(Integer fedid) {
		this.Fedid = fedid;
	}
	
	@Column(name="thingnumber")
	public Integer getThingid() {
		return thingnumber;
	}
	public void setThingid(Integer thingid) {
		this.thingnumber = thingid;
	}
	
	@Column(name="usedesc",length=100)
	public String getDesc() {
		return usedesc;
	}
	public void setDesc(String desc) {
		this.usedesc = desc;
	}
	
	@Column(name="name")
	public String getName() {
		return name;
	}
	public void setName(String names) {
		this.name = names;
	}
	
	
}
