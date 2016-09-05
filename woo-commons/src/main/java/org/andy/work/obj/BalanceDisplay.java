package org.andy.work.obj;

import java.io.Serializable;

public class BalanceDisplay implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8351851500602519949L;
	private double totalAmount; //总金额
	private String balanceAmount = "0"; // 余额
	private String creditLimit = "0"; //信用额度
	private String totalAmountFormatted = "0"; // 格式化总金额
	private String onlinePayAmount; //在线支付金额
	
	private boolean haveBalance;
	private boolean haveCreditLimit;
	private boolean havePoints;
	private boolean haveDebt;
	
	private String payStatus;

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getBalanceAmount() {
		return balanceAmount;
	}

	public void setBalanceAmount(String balanceAmount) {
		this.balanceAmount = balanceAmount;
	}

	public String getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(String creditLimit) {
		this.creditLimit = creditLimit;
	}

	public String getTotalAmountFormatted() {
		return totalAmountFormatted;
	}

	public void setTotalAmountFormatted(String totalAmountFormatted) {
		this.totalAmountFormatted = totalAmountFormatted;
	}

	public String getOnlinePayAmount() {
		return onlinePayAmount;
	}

	public void setOnlinePayAmount(String onlinePayAmount) {
		this.onlinePayAmount = onlinePayAmount;
	}

	public boolean isHaveBalance() {
		return haveBalance;
	}

	public void setHaveBalance(boolean haveBalance) {
		this.haveBalance = haveBalance;
	}

	public boolean isHaveCreditLimit() {
		return haveCreditLimit;
	}

	public void setHaveCreditLimit(boolean haveCreditLimit) {
		this.haveCreditLimit = haveCreditLimit;
	}

	public boolean isHavePoints() {
		return havePoints;
	}

	public void setHavePoints(boolean havePoints) {
		this.havePoints = havePoints;
	}

	public boolean isHaveDebt() {
		return haveDebt;
	}

	public void setHaveDebt(boolean haveDebt) {
		this.haveDebt = haveDebt;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

}
