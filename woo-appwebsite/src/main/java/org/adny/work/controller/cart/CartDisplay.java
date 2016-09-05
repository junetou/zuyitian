package org.adny.work.controller.cart;

import java.util.List;

public class CartDisplay {
	
	private String totalAmount;		//总金额
	private String totalItems;		//总项目数量
	private String selectedAll;		//是否全部选中
	private List<CartItemDisplay> cartItems;//购物车项
	
	public String getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getTotalItems() {
		return totalItems;
	}
	public void setTotalItems(String totalItems) {
		this.totalItems = totalItems;
	}

	public String getSelectedAll() {
		return selectedAll;
	}
	public void setSelectedAll(String selectedAll) {
		this.selectedAll = selectedAll;
	}
	public List<CartItemDisplay> getCartItems() {
		return cartItems;
	}
	public void setCartItems(List<CartItemDisplay> cartItems) {
		this.cartItems = cartItems;
	}
}
