package org.adny.work.security;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.adny.work.controller.helper.AccountHelper;
import org.adny.work.property.SystemProperiesConfigure;
import org.andy.work.appserver.model.IAccount;
import org.apache.log4j.Logger;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;


public class AuthenticationFailureHandlerImpl implements AuthenticationFailureHandler
{
	private final static Logger log = Logger.getLogger(AuthenticationFailureHandlerImpl.class);
	@Resource
	private AccountHelper accountHelper;

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException arg2) throws IOException,
			ServletException {
		if (log.isDebugEnabled()) {
			log.debug("-----------------Member login failure !");
		}
		
		Exception exception = null;
		try {
			String username = request.getParameter("l_name");
			IAccount account = this.accountHelper.findAccountByUsername(username);
			if (account != null) {
				if ("Y".equals(account.getLocked())) {
					exception = new LockedException(SystemProperiesConfigure.get("AccountStatusUserDetailsChecker.locked"));
				} else {
					exception = new BadCredentialsException(SystemProperiesConfigure.get("account.loginfailure.pwdInvaild"));
				}
			} else {
				exception = new BadCredentialsException(SystemProperiesConfigure.get("account.loginfailure.notExsit"));
			}
		} catch (Exception e) {
			log.error("------------>Insert Member login success log error !", e);
			e.printStackTrace();
		}
		
		if (exception == null) {
			exception = new BadCredentialsException("Bad credentials");
		}
		
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("SPRING_SECURITY_LAST_EXCEPTION", exception);
		
		response.sendRedirect(SystemProperiesConfigure.get("login.fail.redirect"));
	}

}
