package org.andy.work.admin.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.andy.work.admin.helper.CookieHelper;
import org.andy.work.constant.WebConstants;
import org.andy.work.utils.StringUtil;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserLoginController {
	
	@Resource
	private CookieHelper cookieHelper;
	
	@RequestMapping(value="/secure/login", method=RequestMethod.GET)
	private String login(HttpServletRequest request) {
		String userNameValue = this.cookieHelper.getCookieValue(request, WebConstants.USERNAME_COOKIE);
		try {
			if (StringUtil.hasValue(userNameValue)) {
				JSONObject json = new JSONObject(userNameValue);
				request.setAttribute("username", json.get("username"));
				request.setAttribute("checkusername", json.get("checkusername"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Date date=new Date();
		return "login";
	}
	
}
