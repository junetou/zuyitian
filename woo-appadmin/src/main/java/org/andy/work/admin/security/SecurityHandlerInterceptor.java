package org.andy.work.admin.security;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Collection;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.andy.work.admin.auth.AuthOperation;
import org.andy.work.admin.helper.UserSessionHelper;
import org.andy.work.utils.CommonUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SecurityHandlerInterceptor extends HandlerInterceptorAdapter {
	
	@Resource
	private UserSessionHelper userSessionHelper;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (handler instanceof HandlerMethod) {
			
			if (!this.userSessionHelper.isUserLogined()) {
				//HandlerMethod handlerMethod = (HandlerMethod) handler;
				String header = request.getHeader("x-requested-with");	
				if ("XMLHttpRequest".equals(header)) {
					response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
					return false;
				}
			}
			if (request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")){
				return this.isAjaxOperation(handler, response, request);
			} else {
				this.isOperation(handler, response, request);
			}
		}
		return true;
	}

	/**
	 * 普通请求判断是否是有权限操作
	 * @param response
	 * @param request
	 * @throws IOException 
	 */
	private void isOperation(Object handler, HttpServletResponse response, HttpServletRequest request) throws IOException {
		AdminUserDetails detail = this.userSessionHelper.getUserDetails();
		String path = request.getContextPath();//得到项目路劲
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Method method = handlerMethod.getMethod();
		
		AuthOperation operation = method.getAnnotation(AuthOperation.class);
		if (operation != null) {
			if (!this.isAuthoritie(detail.getAuthorities(), operation.roleType())) {
				response.sendRedirect(path + "/operation/403");
			} else if (!detail.getPermissions().contains(operation.operationType())) {
				response.sendRedirect(path + "/operation/403");
			}
		}
	}
	
	/**
	 * Ajax请求判断是否是有权限操作
	 * @param handler
	 * @param response
	 * @param request
	 * @throws IOException 
	 */
	private boolean isAjaxOperation(Object handler, HttpServletResponse response, HttpServletRequest request) throws IOException {
		boolean flag = true;
		AdminUserDetails detail = this.userSessionHelper.getUserDetails();
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Method method = handlerMethod.getMethod();
		AuthOperation operation = method.getAnnotation(AuthOperation.class);
		
		if (operation != null) {
						
			if (!this.isAuthoritie(detail.getAuthorities(), operation.roleType())) {
				response.setHeader("sessionstatus", "NoOperationAuthority");//在响应头设置session状态
				response.getWriter().print(CommonUtils.getMessage("error.no.enough.operation", request));
			} else if (!detail.getPermissions().contains(operation.operationType())) {
				response.setHeader("sessionstatus", "NoOperationAuthority");//在响应头设置session状态
				response.getWriter().print(CommonUtils.getMessage("error.no.enough.operation", request));
			}
		}
		return flag;
	}
	
	private boolean isAuthoritie(Collection<? extends GrantedAuthority> collection, String operationType) {
		for (GrantedAuthority grantedAuthority : collection) {
			if (grantedAuthority.toString().equals(operationType)) {
				return true;
			}
		}
		return false;
	}
	
}
