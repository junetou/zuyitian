package org.adny.work.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.adny.work.annotation.Secure;
import org.adny.work.controller.helper.UserSessionHelper;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SecureHandlerInterceptor extends HandlerInterceptorAdapter {
	@Resource
	private UserSessionHelper userSessionHelper;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
		if (obj instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) obj;
			Secure secureOfClass = handlerMethod.getBean().getClass().getAnnotation(Secure.class);
			
			if (secureOfClass != null) {
				
			}
		} 
		
		return true;
	}
	
	public String getRedirectUrl(HttpServletRequest request, String action)
	{
		return request.getContextPath() + action;
	}

}
