package org.andy.work.appserver.model.impl;

import java.io.Serializable;





import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.andy.work.appserver.model.ITrade;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@SuppressWarnings("serial")
@Entity
@Table(name="trade")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region="org.andy.work.appserver.model.impl.Trade")
public class Trade  implements Serializable,ITrade{

	private static final long serialVersionUID = 3777655431317415578L;
	private Integer tradeid;
	private Integer thingid;
	private Integer borrowid;
	private Integer sellerid;
	private Integer assign;
	private Integer ensure;
	private Integer success;
	private String borrowname;
	private String sellername;
	private String goodsname;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getTrade() {
		return tradeid;
	}
	public void setTrade(Integer tradeid) {
		this.tradeid = tradeid;
	}
	
	@Column(name="thingid")
	public Integer getThing() {
		return thingid;
	}
	public void setThing(Integer thingid) {
		this.thingid = thingid;
	}
	
	@Column(name="borrowid")
	public Integer getBorrow() {
		return borrowid;
	}
	public void setBorrow(Integer borrowid) {
		this.borrowid = borrowid;
	}
	
	@Column(name="seller")
	public Integer getSeller() {
		return sellerid;
	}
	public void setSeller(Integer sellerid) {
		this.sellerid = sellerid;
	}
	
	@Column(name="assign")
	public Integer getAssign() {
		return assign;
	}
	public void setAssign(Integer assign) {
		this.assign = assign;
	}
	
	@Column(name="ensure")
	public Integer getEnsure() {
		return ensure;
	}
	public void setEnsure(Integer ensure) {
		this.ensure = ensure;
	}
	
	@Column(name="success")
	public Integer getSuccess() {
		return success;
	}
	public void setSuccess(Integer success) {
		this.success = success;
	}
	
	@Column(name="borrowname")
	public String getBorrowname() {
		return borrowname;
	}
	public void setBorrowname(String borrowname) {
		this.borrowname = borrowname;
	}
	
	@Column(name="sellername")
	public String getSellername() {
		return sellername;
	}
	public void setSellername(String sellername) {
		this.sellername = sellername;
	}
	
	@Column(name="goodsname")
	public String getGoodsname() {
		return goodsname;
	}
	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}
	
}
