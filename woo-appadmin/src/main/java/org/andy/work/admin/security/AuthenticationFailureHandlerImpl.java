package org.andy.work.admin.security;

import java.io.IOException;
import java.util.Arrays;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.andy.work.admin.helper.UserHelper;
import org.andy.work.appserver.model.IUser;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class AuthenticationFailureHandlerImpl implements AuthenticationFailureHandler {
	
	@Resource
	private UserHelper userHelper;
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		String username = request.getParameter("usrname");
		IUser user = this.userHelper.findUserByUsername(username);
		String error = "";
		if (user != null) {
			if ("Y".equals(user.getLocked())) {
				error = "账号已被锁定";
			} else {
				error = "登录失败,请重新输入密码";
			}
		} else {
			error = "登录失败，账号不存在";
		}
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("SPRING_SECURITY_LAST_EXCEPTION", error);
		
		String url = request.getContextPath() + "/secure/login";

		response.sendRedirect(url);
	}

}
