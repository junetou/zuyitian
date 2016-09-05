package org.adny.work.controller.cart;

public class CartItemDisplay {
	
	private Integer id;
	private Integer prodId;
	private String selected; 
	private String productName;
	private Integer orderQty;
	private Double price;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getProdId() {
		return prodId;
	}
	public void setProdId(Integer prodId) {
		this.prodId = prodId;
	}
	public String getSelected() {
		return selected;
	}
	public void setSelected(String selected) {
		this.selected = selected;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getOrderQty() {
		return orderQty;
	}
	public void setOrderQty(Integer orderQty) {
		this.orderQty = orderQty;
	}
}
