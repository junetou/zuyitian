package org.andy.work.appserver.model;

public interface ITrade {

	public void setTrade(Integer tradeid);
	public void setThing(Integer thingid);
	public void setBorrow(Integer borrowid);
	public void setSeller(Integer sellerid);
	public void setAssign(Integer assgin);//要租者确认租借，1为确认租者，0为取消租者
	public void setEnsure(Integer ensure);//卖者确认外租，1为确认租借，0为不祖
	public void setSuccess(Integer success);//交易成功
	public void setBorrowname(String borrowname);
	public void setSellername(String sellername);
	public void setGoodsname(String goodsname);
	
	public Integer getTrade();
	public Integer getThing();
	public Integer getBorrow();
	public Integer getSeller();
	public Integer getAssign();
	public Integer getEnsure();
	public Integer getSuccess();
	public String getBorrowname();
	public String getSellername();
	public String getGoodsname();
}
