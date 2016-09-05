package org.andy.work.appserver.model.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.andy.work.appserver.model.IAccount;
import org.andy.work.appserver.model.ICartDetail;
import org.andy.work.appserver.model.ICartHead;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name="cart_head")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region="org.andy.work.appserver.model.impl.CartHead")
public class CartHead implements Serializable, ICartHead {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2125837060017381790L;
	private Integer id;
	private Integer itemCount;				//项目计数
	private Double totalAmount;				//总价格
	private Date lastUpdatedDate;			//最后更新日期
	private Date createdDate;				//创建时间
	private Set<ICartDetail> cartDetails;	//购物车列表
	private IAccount account;				//属于谁的购物车
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name="itm_count")
	public Integer getItemCount() {
		return itemCount;
	}
	public void setItemCount(Integer itemCount) {
		this.itemCount = itemCount;
	}
	@Column(name="amount")
	public Double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Double totalAmount)
	{
		this.totalAmount = totalAmount;
	}
	@Column(name="last_uptdate")
	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}
	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}
	@Column(name="crtdate", nullable=false)
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	@OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.LAZY, targetEntity=CartDetail.class, mappedBy="cartHead")
	public Set<ICartDetail> getCartDetails() {
		return cartDetails;
	}
	public void setCartDetails(Set<ICartDetail> cartDetails) {
		this.cartDetails = cartDetails;
	}
	@OneToOne(fetch=FetchType.LAZY, optional=false, targetEntity=Account.class)
	@JoinColumn(name="acc_id")
	public IAccount getAccount() {
		return account;
	}
	public void setAccount(IAccount account) {
		this.account = account;
	}
}
