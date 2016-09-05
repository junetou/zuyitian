package org.andy.work.appserver.model;


public interface ICartDetail
{

	Integer getId();

	void setId(Integer id);

	Double getQuantity();

	void setQuantity(Double quantity);

	IProduct getProduct();

	void setProduct(IProduct product);

	ICartHead getCartHead();

	void setCartHead(ICartHead cartHead);

	String getSelected();
	
	void setSelected(String selected);
}