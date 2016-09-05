package org.adny.work.security;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.adny.work.controller.helper.AccountHelper;
import org.adny.work.controller.helper.ShoppingCartHelper;
import org.adny.work.controller.helper.UserSessionHelper;
import org.adny.work.property.SystemProperiesConfigure;
import org.andy.work.constant.WebConstants;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.SavedRequest;

public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler
{
	//private static final Logger log = Logger.getLogger(AuthenticationSuccessHandlerImpl.class);
	
	@Resource
	private UserSessionHelper userSessionHelper;
	@Resource
	private AccountHelper accountHelper;
	@Resource
	private ShoppingCartHelper shoppingCartHelper;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authen) throws IOException, ServletException
	{
		this.initUserSession(request, response);
		
		//IAccount account = this.accountHelper.getCurrenctUserAccount();
		
		String targetUrl = this.getTargetUrl(request);
		response.sendRedirect(targetUrl);
	}
	
	private void initUserSession(HttpServletRequest request, HttpServletResponse response)
	{
		this.userSessionHelper.createUserSession(request);
			
		this.shoppingCartHelper.addCookieCartItemsToAccountCartAfterLogin(request, response);
	}
	
	private String getTargetUrl(HttpServletRequest request)
	{
		String targetUrl;
		HttpSession httpSession = request.getSession();
		Object savedRequestObject = httpSession.getAttribute("SPRING_SECURITY_SAVED_REQUEST"); 
		
    if(savedRequestObject != null) 
    {  
    	targetUrl = ((SavedRequest) savedRequestObject).getRedirectUrl();  
      httpSession.removeAttribute("SPRING_SECURITY_SAVED_REQUEST");
    }
    else if (httpSession.getAttribute(WebConstants.REDIRECT_URL) != null)
    {
    	targetUrl = (String) httpSession.getAttribute(WebConstants.REDIRECT_URL);
    }
    else
    {
    	targetUrl = SystemProperiesConfigure.get("login.success.redirect");
    }
 
    return targetUrl;
	}
	

}
