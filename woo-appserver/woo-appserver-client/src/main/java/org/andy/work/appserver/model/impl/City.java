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

import org.andy.work.appserver.model.ICity;
import org.andy.work.appserver.model.IProvince;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 城市表
 * @author JIANG
 *
 */
@Entity
@Table(name = "city")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region="org.andy.work.appserver.model.impl.City")
public class City implements Serializable, ICity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1139323161326255069L;
	private Integer id;
	private String name;
	private String geocode;
	private IProvince province;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="city_name", length=30, nullable=false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="city_geocode", length=30)
	public String getGeocode() {
		return geocode;
	}
	public void setGeocode(String geocode) {
		this.geocode = geocode;
	}
	@ManyToOne(fetch=FetchType.LAZY, optional=false, targetEntity=Province.class)
	@JoinColumn(name="province_id")
	public IProvince getProvince() {
		return province;
	}
	public void setProvince(IProvince province) {
		this.province = province;
	}
}
