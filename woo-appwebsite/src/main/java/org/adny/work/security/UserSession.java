package org.adny.work.security;

import java.io.Serializable;

import org.andy.work.obj.BalanceDisplay;

public class UserSession implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7739525939434080749L;
	
	private Integer accountId;
	private Integer selectedShipto;
	private Integer selectedInvoice;
	private Integer remainCreateShiptoCount;
	private String realName;
	private String lastLoginedTime;
	private int loginedTimes;
	private boolean hasInit;
	
	private BalanceDisplay balance;
	
	private String selectedBank;
	
	public Integer getAccountId()
	{
		return accountId;
	}
	public void setAccountId(Integer accountId)
	{
		this.accountId = accountId;
	}
	public Integer getSelectedShipto()
	{
		return selectedShipto;
	}
	public void setSelectedShipto(Integer selectedShipto)
	{
		this.selectedShipto = selectedShipto;
	}
	public Integer getSelectedInvoice()
	{
		return selectedInvoice;
	}
	public void setSelectedInvoice(Integer selectedInvoice)
	{
		this.selectedInvoice = selectedInvoice;
	}
	public Integer getRemainCreateShiptoCount()
	{
		return remainCreateShiptoCount;
	}
	public void setRemainCreateShiptoCount(Integer remainCreateShiptoCount)
	{
		this.remainCreateShiptoCount = remainCreateShiptoCount;
	}
	public String getSelectedBank()
	{
		return selectedBank;
	}
	public void setSelectedBank(String selectedBank)
	{
		this.selectedBank = selectedBank;
	}
	public boolean isHasInit()
	{
		return hasInit;
	}
	public void setHasInit(boolean hasInit)
	{
		this.hasInit = hasInit;
	}
	public String getRealName()
	{
		return realName;
	}
	public void setRealName(String realName)
	{
		this.realName = realName;
	}
	public BalanceDisplay getBalance()
	{
		return balance;
	}
	public void setBalance(BalanceDisplay balance)
	{
		this.balance = balance;
	}
	public String getLastLoginedTime()
	{
		return lastLoginedTime;
	}
	public void setLastLoginedTime(String lastLoginedTime)
	{
		this.lastLoginedTime = lastLoginedTime;
	}
	public int getLoginedTimes()
	{
		return loginedTimes;
	}
	public void setLoginedTimes(int loginedTimes)
	{
		this.loginedTimes = loginedTimes;
	}
}
