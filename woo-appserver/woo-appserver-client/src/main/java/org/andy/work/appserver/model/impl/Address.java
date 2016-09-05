package org.andy.work.appserver.model.impl;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.andy.work.appserver.model.IAddress;
import org.andy.work.appserver.model.IArea;
import org.andy.work.appserver.model.ICity;
import org.andy.work.appserver.model.ICountry;
import org.andy.work.appserver.model.IProvince;

/**
 * 收货地址子表
 * @author Administrator
 *
 */
@Entity
@Table(name="address")
public class Address implements Serializable, IAddress
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7333894686092762544L;
	private Integer id;
	private String street;
	private String postcode;
	private Date createdDate;
	private Date updatedDate;
	private ICountry country;
	private IProvince province;
	private ICity city;
	private IArea area;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId()
	{
		return id;
	}
	public void setId(Integer id)
	{
		this.id = id;
	}
	@Column(length=250, nullable=false)
	public String getStreet()
	{
		return street;
	}
	public void setStreet(String street)
	{
		this.street = street;
	}
	@Column(length=10)
	public String getPostcode()
	{
		return postcode;
	}
	public void setPostcode(String postcode)
	{
		this.postcode = postcode;
	}
	@Column(name="crtdate", nullable=false)
	public Date getCreatedDate()
	{
		return createdDate;
	}
	public void setCreatedDate(Date createdDate)
	{
		this.createdDate = createdDate;
	}
	@Column(name="uptdate")
	public Date getUpdatedDate()
	{
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate)
	{
		this.updatedDate = updatedDate;
	}
	@ManyToOne(fetch=FetchType.EAGER, targetEntity=Country.class)
	@JoinColumn(name="ctry_id")
	public ICountry getCountry()
	{
		return country;
	}
	public void setCountry(ICountry country)
	{
		this.country = country;
	}
	@ManyToOne(fetch=FetchType.EAGER, targetEntity=Province.class)
	@JoinColumn(name="stat_id")
	public IProvince getProvince()
	{
		return province;
	}
	public void setProvince(IProvince province)
	{
		this.province = province;
	}
	@ManyToOne(fetch=FetchType.EAGER, targetEntity=City.class)
	@JoinColumn(name="city_id")
	public ICity getCity()
	{
		return city;
	}
	public void setCity(ICity city)
	{
		this.city = city;
	}
	@ManyToOne(fetch=FetchType.EAGER, targetEntity=Area.class)
	@JoinColumn(name="cty_id")
	public IArea getArea()
	{
		return area;
	}
	public void setArea(IArea area)
	{
		this.area = area;
	}
}
