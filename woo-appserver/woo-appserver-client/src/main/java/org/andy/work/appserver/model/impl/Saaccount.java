package org.andy.work.appserver.model.impl;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.andy.work.appserver.model.ISaaccount;

@Entity
@Table(name="saaccount")
public class Saaccount implements Serializable, ISaaccount
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3510306935296188497L;
	private Integer id;
	private String customerName;
	private String username;
	private String accountType;
	private String email;
	private String password;
	private String salt;
	private String secureCode;
	private Date expriedDate;
	private Date createdDate;
	
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
	@Column(name="cust_name", length=30)
	public String getCustomerName()
	{
		return customerName;
	}
	public void setCustomerName(String customerName)
	{
		this.customerName = customerName;
	}
	@Column(name="acc_usr_name", length=50, nullable=false, unique=true)
	public String getUsername()
	{
		return username;
	}
	public void setUsername(String username)
	{
		this.username = username;
	}
	@Column(name="acc_typ", nullable=false, length=10)
	public String getAccountType()
	{
		return accountType;
	}
	public void setAccountType(String accountType)
	{
		this.accountType = accountType;
	}
	@Column(name="email", length=60, nullable=false, unique=true)
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
	@Column(name="acc_pwd", length=60, nullable=false)
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	@Column(name="salt", length=30, nullable=false)
	public String getSalt()
	{
		return salt;
	}
	public void setSalt(String salt)
	{
		this.salt = salt;
	}
	@Column(name="sec_code", length=60, nullable=false, unique=true)
	public String getSecureCode()
	{
		return secureCode;
	}
	public void setSecureCode(String secureCode)
	{
		this.secureCode = secureCode;
	}
	@Column(name="expried_date", nullable=false)
	public Date getExpriedDate()
	{
		return expriedDate;
	}
	public void setExpriedDate(Date expriedDate)
	{
		this.expriedDate = expriedDate;
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
}
