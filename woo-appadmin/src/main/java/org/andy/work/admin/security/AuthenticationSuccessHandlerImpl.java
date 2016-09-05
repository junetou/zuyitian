package org.andy.work.admin.security;

import java.io.IOException;
import java.net.URLEncoder;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.andy.work.admin.helper.CookieHelper;
import org.andy.work.admin.helper.UserHelper;
import org.andy.work.appserver.model.IUser;
import org.andy.work.constant.WebConstants;
import org.json.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.SavedRequest;

public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {
	
	@Resource
	private CookieHelper cookieHelper;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		
		String remember = request.getParameter("remember");
		String username = request.getParameter("usrname");
		if ("remember".equals(remember)) {
			JSONObject json = new JSONObject();
			json.put("username", username);
			json.put("checkusername", "checked");
			
			this.putItemsIntoCookieAccount(request, response, json.toString());
		} else {
			this.removeCookieAccount(request, response);
		}
		
		String targetUrl = this.getTargetUrl(request);
	    String b=usemessage(request,response,username);
	    response.sendRedirect(targetUrl);
	}

	private String getTargetUrl(HttpServletRequest request) {
		String targetUrl;
		HttpSession httpSession = request.getSession();
		Object savedRequestObject = httpSession.getAttribute("SPRING_SECURITY_SAVED_REQUEST"); 
		
		if(savedRequestObject != null)  {  
			targetUrl = ((SavedRequest) savedRequestObject).getRedirectUrl();  
			httpSession.removeAttribute("SPRING_SECURITY_SAVED_REQUEST");
		} else if (httpSession.getAttribute(WebConstants.REDIRECT_URL) != null) {
			targetUrl = (String) httpSession.getAttribute(WebConstants.REDIRECT_URL);
		}  else {
			targetUrl = request.getContextPath() + "/portal";
		}
		return targetUrl;
	}
	
	private void removeCookieAccount(HttpServletRequest request, HttpServletResponse response) {
		Cookie cookie = this.cookieHelper.getCookie(request, WebConstants.USERNAME_COOKIE);
		if (cookie != null) {
			cookie.setMaxAge(0);
			cookie.setPath("/");
			response.addCookie(cookie);
		}
	}
	
	private void putItemsIntoCookieAccount(HttpServletRequest request, HttpServletResponse response, String cookieValue)  {
		Cookie cookie = this.cookieHelper.getCookie(request, WebConstants.USERNAME_COOKIE);
		if (cookie != null) {
			try  {
				cookie.setValue(URLEncoder.encode(cookieValue, "UTF-8"));
			} catch (java.io.UnsupportedEncodingException e)  {
				cookie.setValue(cookieValue);
			}
			cookie.setPath("/");
			cookie.setMaxAge(30 * 24 * 60 * 60);// 30 days
			response.addCookie(cookie);
		} else  {
			Cookie newCookie = new Cookie(WebConstants.USERNAME_COOKIE, cookieValue);
			newCookie.setPath("/");
			newCookie.setMaxAge(30 * 24 * 60 * 60);// 30 days
			response.addCookie(newCookie);	
		}
	}
	
	@Resource
	private UserHelper userHelper;
	
	public String usemessage (HttpServletRequest request, HttpServletResponse response,String name ){
		String usename=name;
		IUser user=this.userHelper.findUserByUsername(usename);
		String a=user.getEmail();
		String b=user.getAddress();
		request.setAttribute("email",a);
		request.setAttribute("addr", b);
		return "yse";
	}
	
	
	
}
