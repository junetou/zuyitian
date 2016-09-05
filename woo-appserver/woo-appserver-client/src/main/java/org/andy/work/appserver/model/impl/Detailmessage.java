package org.andy.work.appserver.model.impl;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.andy.work.appserver.model.IDetailmessage;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


@SuppressWarnings("serial")
@Entity
@Table(name="detailmessage")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region="org.andy.work.appserver.model.impl.Detailmessage")
public class Detailmessage implements Serializable,IDetailmessage{
	
	
	private static final long serialVersionUID = 3777655431317415578L;
	private Integer thingsId;
	private String name;
	private String desc;
	private Double price;
	private String date;
	private Double lng;
	private Double lat;
	private String king;
	private Integer number;
	private String addr;
	private String phone;
    private Integer overanalyzed;
	private String picname;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getthingsId() {
		return thingsId;
	}
	public void setthingsId(Integer thingsId) {
		this.thingsId = thingsId;
	}
	
	@Column(name="name",length=50)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="thingdesc",length=100, nullable=false, unique=false)
	public String getthingsDesc() {
		return desc;
	}
	public void setthingsDesc(String desc) {
		this.desc = desc;
	}
	
	@Column(name="price",scale=2,nullable=false,unique=false)
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
	@Column(name="date",nullable=false,unique=false)
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	@Column(name="getlng",nullable=false,unique=false,scale=6)
	public Double getthingsLng() {
		return lng;
	}
	public void setthingsLng(Double lng) {
		this.lng = lng;
	}
	
	@Column(name="getlat",nullable=false,unique=false,scale=6)
	public Double getthingsLat() {
		return lat;
	}
	public void setthingsLat(Double lat) {
		this.lat = lat;
	}
	
	@Column(name="kind",nullable=false,unique=false)
	public String getkind(){
		return this.king;
	}	
	public void setkind(String kind){
		this.king=kind;
	}
		
	 @Column(name="number")
	 public void setnumber(Integer number){
		 this.number=number;
	 }
	public Integer getnumber(){
		return this.number;
	}
	
	
	 @Column(name="addr")
	 public String getaddr(){
			return this.addr;
	 }
	 public void setaddr(String addr){
		 this.addr=addr;
	 }
	
	
	@Column(name="phone")
	public String getphone(){
		return this.phone;
	}
	public void setphone(String phone){
	this.phone=phone;	
	}

	@Column(name="overanalyzed")
	public Integer getoveranalyzed(){
		return this.overanalyzed;
	}
	public void setoveranalyzed(Integer overanalyzed){
	this.overanalyzed=overanalyzed;	
	}
	
	@Column(name="picname")
	public String getpicname(){
		return this.picname;
	}
	public void setpicname(String picname){
		this.picname=picname;
	}
	
}
