package org.adny.work.controller.cart;

public class CartItemAdd {
	
	private Integer prodId;
	private String prodNum;
	private Double orderQty;
	private String selected;
	
	public Integer getProdId() {
		return prodId;
	}
	public void setProdId(Integer prodId) {
		this.prodId = prodId;
	}
	public String getProdNum() {
		return prodNum;
	}
	public void setProdNum(String prodNum) {
		this.prodNum = prodNum;
	}
	public Double getOrderQty() {
		return orderQty;
	}
	public void setOrderQty(Double orderQty) {
		this.orderQty = orderQty;
	}
	public String getSelected() {
		return selected;
	}
	public void setSelected(String selected) {
		this.selected = selected;
	}
}
