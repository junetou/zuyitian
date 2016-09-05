package org.andy.work.appserver.model.impl;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.andy.work.appserver.model.IAccountBalance;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 账户余额
 * @author Administrator
 *
 */
@Entity
@Table(name="account_balance")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region="org.andy.work.appserver.model.impl.AccountBalance")
public class AccountBalance implements Serializable, IAccountBalance
{
	private static final long serialVersionUID = 901994406920774947L;
	private Integer id;
	private Double balanceAmount; //账户余额
	private Double creditLimit;	//信用额度
	private Date createdDate;	//创建日期
	private Date updatedDate;	//更新日期
	
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
	@Column(name="bal_amt")
	public Double getBalanceAmount()
	{
		return balanceAmount;
	}
	public void setBalanceAmount(Double balanceAmount)
	{
		this.balanceAmount = balanceAmount;
	}
	@Column(name="crtlimit")
	public Double getCreditLimit()
	{
		return creditLimit;
	}
	public void setCreditLimit(Double creditLimit)
	{
		this.creditLimit = creditLimit;
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
