package org.andy.work.appserver.model.impl;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.andy.work.appserver.model.INewtest;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
@SuppressWarnings("serial")
@Entity
@Table(name="newtest")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region="org.andy.work.appserver.model.impl.Newtest")

public class Newtest implements Serializable, INewtest{
	private float lng;
	private float lat;
	private int id;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	@Column(name="lng", length=50, nullable=false, unique=true)
	public float getlng() {
		return lng;
	}
	public void setlng(float lng) {
		this.lng = lng;
	}
	
	@Column(name="lat", length=50, nullable=false, unique=true)
	public float getlat() {
		return lat;
	}
	public void setlat(float lat){
		this.lat = lat;
	}
	
	
}
