package org.andy.work.appserver.model.impl;

import java.io.Serializable;






import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.andy.work.appserver.model.INeed;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


@SuppressWarnings("serial")
@Entity
@Table(name="customerneed")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region="org.andy.work.appserver.model.impl.Need")
public class Need implements Serializable,INeed{

	private Integer need;
	private String descs;
	private String name;
	private Double price;
	private String addr;
	private Double lng;
	private Double lat;
	private String kind;
	private String date;
	private Integer number;
	private String phone;
    private Integer overanalyzed;
    
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getNeed() {
		return need;
	}
	public void setNeed(Integer needid) {
		this.need = needid;
	}
	
	@Column(name="descs")
	public String getDescs() {
		return descs;
	}
	public void setDescs(String desc) {
		this.descs = desc;
	}
	
	@Column(name="name",length=50)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="price",scale=2)
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
	@Column(name="addr")
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	
	@Column(name="lng",nullable=false,unique=false,scale=6)
	public Double getLng() {
		return lng;
	}
	public void setLng(Double lng) {
		this.lng = lng;
	}
	
	@Column(name="lat",nullable=false,unique=false,scale=6)
	public Double getLat() {
		return lat;
	}
	public void setLat(Double lat) {
		this.lat = lat;
	}
	
	@Column(name="kind")
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	
	@Column(name="date")
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	@Column(name="number")
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	
	@Column(name="phone")
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Column(name="overanalyzed")
	public Integer getOveranalyzed() {
		return overanalyzed;
	}
	public void setOveranalyzed(Integer overanalyzed) {
		this.overanalyzed = overanalyzed;
	}
    
	
	

}
