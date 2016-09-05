package org.adny.work.controller.helper;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.adny.work.security.UserDetailsImpl;
import org.adny.work.security.UserSession;
import org.andy.work.appserver.model.IAccount;
import org.andy.work.appserver.model.IShipto;
import org.andy.work.appserver.service.IAccountMaintenanceService;
import org.andy.work.appserver.service.IAddressMaintenanceService;
import org.andy.work.constant.WebConstants;
import org.andy.work.obj.BalanceDisplay;
import org.andy.work.utils.CommonUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserSessionHelper {
	
	@Resource
	private IAccountMaintenanceService accountMaintenanceService;
	@Resource
	private IAddressMaintenanceService addressMaintenanceService;
	@Resource
	private AccountHelper accountHelper;
	
	private UserDetailsImpl getUserDetails()
	{
		Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (obj instanceof UserDetailsImpl)
		{
			return (UserDetailsImpl) obj;
		}
		
		return null;
	}
	
	public boolean isLogined()
	{
		UserDetailsImpl userDetails = this.getUserDetails();
		if (userDetails != null)
		{
			return true;
		}
		
		return false;
	}
	
	public String getUsername()
	{
		UserDetailsImpl userDetail = this.getUserDetails();
		
		return userDetail.getUsername();
	}
	
	public void createUserSession(HttpServletRequest request)
	{
		UserSession userSession = new UserSession();
		
		IAccount account = this.getUserAccount();
		account = this.accountHelper.refreshAccountBalance(account.getId());
		
		BalanceDisplay balance = this.accountHelper.getAccountBalanceDisplay(account);
		
		userSession.setAccountId(account.getId());
		userSession.setBalance(balance);
		
		if (account.getLastLoginedDate() != null)
		{
			userSession.setLastLoginedTime(CommonUtils.datetimeFormat(account.getLastLoginedDate()));
		}
		account.setLastLoginedDate(new Date());
		
		Integer loginedCount = account.getLoginedCount();
		if (loginedCount == null)
		{
			loginedCount = 1;
		}
		else
		{
			loginedCount++;
		}
		account.setLoginedCount(loginedCount);
		userSession.setLoginedTimes(loginedCount);
		
		IShipto shipto = this.addressMaintenanceService.getUserDefaultedShipto(account.getId());
		if (shipto != null)
		{
			userSession.setSelectedShipto(shipto.getId());
		}

		
		request.getSession().setAttribute(WebConstants.USER_SESSION, userSession);
		
		this.accountHelper.updateAccount(account);
	}
	
	public UserSession getUserSession(HttpServletRequest request)
	{
		UserDetailsImpl userDetails = this.getUserDetails();
		
		UserSession userSession = null;
		if (userDetails != null)
		{
			HttpSession httpSession = request.getSession();
			userSession = (UserSession) httpSession.getAttribute(WebConstants.USER_SESSION);
		}
		
		return userSession;
	}
	
	public IAccount getUserAccount()
	{
		Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (obj instanceof UserDetailsImpl)
		{
			String username = ((UserDetailsImpl) obj).getUsername();
			return this.accountMaintenanceService.findAccountByUsername(username);
		}
		
		return null;
	}
	
	public void refreshUserSession(UserSession userSession, HttpServletRequest request)
	{
		request.getSession().setAttribute(WebConstants.USER_SESSION, userSession);
	}
	
	public void setAccountMaintenanceService(IAccountMaintenanceService accountMaintenanceService)
	{
		this.accountMaintenanceService = accountMaintenanceService;
	}
	public void setAddressMaintenanceService(IAddressMaintenanceService addressMaintenanceService)
	{
		this.addressMaintenanceService = addressMaintenanceService;
	}
	public void setAccountHelper(AccountHelper accountHelper)
	{
		this.accountHelper = accountHelper;
	}
}
