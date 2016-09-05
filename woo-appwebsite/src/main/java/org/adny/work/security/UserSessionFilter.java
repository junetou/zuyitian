package org.adny.work.security;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.adny.work.controller.helper.AccountHelper;
import org.adny.work.controller.helper.ShoppingCartHelper;
import org.adny.work.controller.helper.UserSessionHelper;
import org.andy.work.constant.WebConstants;
import org.andy.work.utils.StringUtil;

public class UserSessionFilter implements Filter
{
	@SuppressWarnings("unused")
	private FilterConfig config;
	@Resource
	private UserSessionHelper userSessionhelper;
	@Resource
	private ShoppingCartHelper shoppingCartHelper;
	@Resource
	private AccountHelper accountHelper;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException
	{
		this.config = filterConfig;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
	{
		HttpServletRequest httpReq = (HttpServletRequest) request;
		HttpServletResponse httpResp = (HttpServletResponse) response;
		
		if (this.userSessionhelper.isLogined())
		{
			UserSession userSession = this.userSessionhelper.getUserSession(httpReq);
			if (!userSession.isHasInit())
			{
				this.accountHelper.refreshAccountBalance(userSession.getAccountId());
				
				this.shoppingCartHelper.addCookieCartItemsToAccountCartAfterLogin(httpReq, httpResp);
				userSession.setHasInit(true);
			}
			
			HttpSession httpSession = httpReq.getSession();
			String redirectUrl = (String) httpSession.getAttribute(WebConstants.REDIRECT_URL);
			if (StringUtil.hasValue(redirectUrl))
			{
				httpSession.removeAttribute(WebConstants.REDIRECT_URL);
				httpResp.sendRedirect(redirectUrl);
			}
		}
		
		chain.doFilter(request, response);
	}

	@Override
	public void destroy()
	{
		this.config = null;
		this.userSessionhelper = null;
		this.shoppingCartHelper = null;
	}
	
	public void setShoppingCartHelper(ShoppingCartHelper shoppingCartHelper)
	{
		this.shoppingCartHelper = shoppingCartHelper;
	}
	public void setUserSessionhelper(UserSessionHelper userSessionhelper)
	{
		this.userSessionhelper = userSessionhelper;
	}
	public void setAccountHelper(AccountHelper accountHelper)
	{
		this.accountHelper = accountHelper;
	}
}
