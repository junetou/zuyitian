package org.adny.work.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.adny.work.controller.helper.AccountHelper;
import org.adny.work.controller.helper.CookieHelper;
import org.andy.work.constant.WebConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="member/login")
public class MemberSigninController
{
	@Resource
	private AccountHelper accountHelper;
	@Resource
	private CookieHelper cookieHelper;
	
	
	@RequestMapping
	public ModelAndView index(@CookieValue(value=WebConstants.USERNAME_COOKIE, required=false) String username,
			@RequestParam(required=false) String action, ModelAndView model, HttpServletRequest request) {
		HttpSession httpSession = request.getSession();
		Object loginErrorMessage = httpSession.getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
		httpSession.removeAttribute("SPRING_SECURITY_LAST_EXCEPTION");
		
		if ("ACTIVATE".equals(action)) {
			model.addObject("ACC_ACTIVATE", true);
		}
		
		request.setAttribute("errorMessage", loginErrorMessage);
		model.addObject("username", username)
			.addObject("title", "会员登录")
			.addObject("errorMessage", loginErrorMessage)
			.setViewName("member/account-login");
		return model;
	}
}
