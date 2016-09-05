package org.andy.work.appserver.model.impl;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.andy.work.appserver.model.IArea;
import org.andy.work.appserver.model.ICity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 区县表
 * @author JIANG
 *
 */
@Entity
@Table(name = "area")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region="org.andy.work.appserver.model.impl.Area")
public class Area implements Serializable,IArea {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7328125457901854475L;
	
	private Integer id;
	private String name;
	private String geocode;
	private ICity city;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="area_name", length=50, nullable=false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="area_geocode", length=30)
	public String getGeocode() {
		return geocode;
	}
	public void setGeocode(String geocode) {
		this.geocode = geocode;
	}
	@ManyToOne(fetch=FetchType.LAZY, optional=false, targetEntity=City.class)
	@JoinColumn(name="city_id")
	public ICity getCity() {
		return city;
	}
	public void setCity(ICity city) {
		this.city = city;
	}
}
