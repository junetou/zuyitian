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

import org.andy.work.appserver.model.ICartDetail;
import org.andy.work.appserver.model.ICartHead;
import org.andy.work.appserver.model.IProduct;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name="cart_detail")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region="org.andy.work.appserver.model.impl.CartDetail")
public class CartDetail implements Serializable, ICartDetail {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6080918214368575878L;
	private Integer id;
	private Double quantity;		//数量
	private String selected;		//是否选中  CommonState DEF_STATE
	private IProduct product;			//产品
	private ICartHead cartHead;		//购物车
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="qty", nullable=false)
	public Double getQuantity() {
		return quantity;
	}
	public void setQuantity(Double quantity)
	{
		this.quantity = quantity;
	}
	@Column(name="slted", length=2)
	public String getSelected() {
		return selected;
	}
	public void setSelected(String selected) {
		this.selected = selected;
	}
	@ManyToOne(fetch=FetchType.EAGER, targetEntity=Product.class, optional=false)
	@JoinColumn(name="prod_id")
	public IProduct getProduct()
	{
		return product;
	}
	public void setProduct(IProduct product)
	{
		this.product = product;
	}
	@ManyToOne(fetch=FetchType.LAZY, targetEntity=CartHead.class, optional=false)
	@JoinColumn(name="ch_id")
	public ICartHead getCartHead() {
		return cartHead;
	}
	public void setCartHead(ICartHead cartHead) {
		this.cartHead = cartHead;
	}
}
