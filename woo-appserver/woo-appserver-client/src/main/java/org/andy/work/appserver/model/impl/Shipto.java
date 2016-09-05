package org.andy.work.appserver.model.impl;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.andy.work.appserver.model.IAccount;
import org.andy.work.appserver.model.IAddress;
import org.andy.work.appserver.model.IShipto;

/**
 * 收货地址主表
 * @author Administrator
 *
 */
@Entity
@Table(name="shipto")
public class Shipto implements Serializable, IShipto
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8437156886913313853L;
	private Integer id;
	private String contactName;
	private String cellphone;
	private String telephone;
	private IAccount account;
	private String shipNum;
	private String shipKey;
	private IAddress address;
	private String defaulted;  // 是否为默认地址，（Y为默认地址）
	private Date createdDate;
	private Date updatedDate;
	
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

	@Column(name="ctt_name", length=60, nullable=false)
	public String getContactName()
	{
		return contactName;
	}
	public void setContactName(String contactName)
	{
		this.contactName = contactName;
	}
	@Column(length=18)
	public String getCellphone()
	{
		return cellphone;
	}
	public void setCellphone(String cellphone)
	{
		this.cellphone = cellphone;
	}
	@Column(name="tel", length=30)
	public String getTelephone()
	{
		return telephone;
	}
	public void setTelephone(String telephone)
	{
		this.telephone = telephone;
	}
	@ManyToOne(fetch=FetchType.EAGER, optional=false, targetEntity=Account.class)
	@JoinColumn(name="acc_id")
	public IAccount getAccount()
	{
		return account;
	}
	public void setAccount(IAccount account)
	{
		this.account = account;
	}
	@Column(name="shp_num", length=10)
	public String getShipNum()
	{
		return shipNum;
	}
	public void setShipNum(String shipNum)
	{
		this.shipNum = shipNum;
	}
	@Column(name="shp_key", length=10, unique=true)
	public String getShipKey()
	{
		return shipKey;
	}
	public void setShipKey(String shipKey)
	{
		this.shipKey = shipKey;
	}
	@OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL, optional=false, targetEntity=Address.class)
	@JoinColumn(name="addr_id")
	public IAddress getAddress()
	{
		return address;
	}
	public void setAddress(IAddress address)
	{
		this.address = address;
	}
	@Column(length=2)
	public String getDefaulted()
	{
		return defaulted;
	}
	public void setDefaulted(String defaulted)
	{
		this.defaulted = defaulted;
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
}
