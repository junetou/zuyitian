package org.andy.work.admin.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class AdminAuthenticationFailureHandler implements AuthenticationFailureHandler
{

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException authen) throws IOException,
			ServletException {
		
		System.out.println("----------------------------onAuthenticationFailure");
		
		String header = request.getHeader("x-requested-with");	
		System.out.println("--------------------> " + header);
		if (!"XMLHttpRequest".equals(header)) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		} 	else {
			response.sendRedirect(request.getContextPath() + "/secure/login");
		}
		System.out.println("erro");
	}
}
