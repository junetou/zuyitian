package org.adny.work.controller;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.adny.work.annotation.Secure;
import org.adny.work.annotation.Secure.RequestType;
import org.adny.work.controller.form.RegisterForm;
import org.adny.work.controller.helper.AccountEmailHelper;
import org.adny.work.controller.helper.AccountHelper;
import org.adny.work.controller.helper.CookieHelper;
import org.andy.work.appserver.model.ISaaccount;
import org.andy.work.constant.WebConstants;
import org.andy.work.exception.IllegalRequestException;
import org.andy.work.utils.AjaxResponse;
import org.andy.work.utils.AuthorityUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="member/reg")
public class MemberRegistrationController {
	
	@Resource
	private AccountHelper accountHelper;
	@Resource
	private CookieHelper cookieHelper;
	@Resource
	private AccountEmailHelper accountEmailHelper;
	
	private static final Logger log = Logger.getLogger(MemberRegistrationController.class);
	
	@RequestMapping
	public ModelAndView index(ModelAndView model) {
		model.addObject("title", "会员登录").setViewName("member/registration");
		return model;
	}
	
	@RequestMapping(value="/regAcc", method=RequestMethod.POST)
	@Secure(type=RequestType.AJAX)
	@ResponseBody
	public AjaxResponse register(@Valid RegisterForm form, BindingResult bindingResult,  HttpServletRequest request, HttpServletResponse response) throws IllegalRequestException {		
		if (bindingResult.hasErrors()) {
			throw new IllegalRequestException(bindingResult);
		}
		
		if (log.isDebugEnabled()) {
			log.debug("--------------------> Account password ---> " + form.getPwd());
		}
		ISaaccount saaccount = this.accountHelper.registAccount(form);
		
		this.accountEmailHelper.sendAccountRegistrationEmail(saaccount, request);
		
		String key = AuthorityUtil.hashPassword(saaccount.getUsername(), saaccount.getSalt());
		AjaxResponse ajaxResponse = AjaxResponse.success();
		ajaxResponse.setObj(key);
		return ajaxResponse;
	}
	
	@RequestMapping(value="/active")
	public ModelAndView accountActivate(@RequestParam("code") String secureCode,
			@RequestParam("m") String mailCode, HttpServletRequest request, HttpServletResponse response, ModelAndView model) {
		model.setViewName("member/registration-activate");
		ISaaccount saaccount = this.accountHelper.getSaaccountBySecureCode(secureCode);
		if (saaccount == null) {
			model.addObject("result", false);
			return model;
		}
		
		String emailEncodeStr = AuthorityUtil.hmac(saaccount.getEmail(), saaccount.getSalt());
		if (!emailEncodeStr.equals(mailCode)) {
			model.addObject("result", false);
			return model;
		}
		
		if (this.accountHelper.isSaacountExpried(saaccount)) {
			this.accountHelper.deleteSaaccount(saaccount);
			model.addObject("result", "expried");
			return model;
		}
		
		try {
			this.accountHelper.accountActivation(saaccount);
			this.putUsernameToCookie(request, response, saaccount.getUsername());
			model.setViewName("redirect:/member/login.htm?action=ACTIVATE");
			return model;
		} catch (Exception e) {
			log.error("Active account error ! id " + saaccount.getId(), e);
			model.addObject("result", "exception");
		}
		
		return model;
	}
	
	private void putUsernameToCookie(HttpServletRequest request, HttpServletResponse response, String username) {
		Cookie cookie = this.cookieHelper.getCookie(request, WebConstants.USERNAME_COOKIE);
		if (cookie != null) {
			try  {
				cookie.setValue(URLEncoder.encode(username, "UTF-8"));
			} catch (java.io.UnsupportedEncodingException e)  {
				cookie.setValue(username);
			}
			cookie.setPath("/login");
			cookie.setMaxAge(30 * 24 * 60 * 60 ); // 30 days
			response.addCookie(cookie);
		} else  {
			Cookie newCookie = new Cookie(WebConstants.USERNAME_COOKIE, username);
			newCookie.setPath("/login");
			newCookie.setMaxAge(30 * 24 * 60 * 60 ); // 30 days
			response.addCookie(newCookie);	
		}
	}
	
	@RequestMapping(value="/success")
	private String showSuccess(@RequestParam("k") String key) {
		return "member/registration-success";
	}
	
	@RequestMapping(value="/accCheck")
	@ResponseBody
	@Secure(type=RequestType.AJAX)
	public Map<String, Boolean> accountCheck(@RequestParam String regName, HttpServletRequest request) {
		boolean isExist = this.accountHelper.isUsernameExist(regName);
		Map<String, Boolean> valid = new HashMap<String, Boolean>();
		if (!isExist) {
			valid.put("valid", true);
		} else {
			valid.put("valid", false);
		}
		return valid;
	}
}
