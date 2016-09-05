package org.adny.work.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.adny.work.controller.helper.AccountEmailHelper;
import org.adny.work.controller.helper.AccountHelper;
import org.adny.work.controller.helper.AdditionalCodeHelper;
import org.andy.work.appserver.model.IAccount;
import org.andy.work.appserver.model.IAccountSecurity;
import org.andy.work.utils.AjaxResponse;
import org.andy.work.utils.AuthorityUtil;
import org.andy.work.utils.CommonUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/retrieve")
public class RetrievePasswordController
{
	@Resource
	private AccountHelper accountHelper;
	@Resource
	private AdditionalCodeHelper additionalCodeHelper;
	@Resource
	private AccountEmailHelper accountEmailHelper;
	
	/**
	 * 找回密码第一步，填写账户名
	 * @param model
	 * @return
	 */
	@RequestMapping(value="reset")
	public ModelAndView retrievePassword(ModelAndView model) {
		model.setViewName("find-password/step-1");
		return model;
	}
	
	/**
	 * 找回密码第二步，身份验证
	 * @param model
	 * @return
	 */
	@RequestMapping(value="step-one")
	public ModelAndView stepOne(ModelAndView model, @RequestParam(value="username",  required=false) String username, 
			 HttpServletRequest request) {
		String viewName = "find-password/step-2";
		IAccount account = this.accountHelper.findAccountByUsername(username);
		if(account == null) {
			viewName = "find-password/step-1";
			model.addObject("errors","对不起注册账户名不存在.");
		} else {
			model.addObject("username", account.getUsername()).addObject("email",account.getAccountSecurity().getEmail()).addObject("question",account.getAccountSecurity().getSecurityQuestion());
		}
		model.setViewName(viewName);
		return model;
	}
	
	/**
	 * 找回密码第三步，通过电子邮件设置密码
	 * @param username
	 * @param request
	 * @return
	 */
	@RequestMapping(value="send-email")
	@ResponseBody
	public AjaxResponse sendEamil(@RequestParam(value="username",  required=false) String username,
			 HttpServletRequest request) {
		IAccount account = this.accountHelper.findAccountByUsername(username);
		if(account != null) {
			String verifyCode = AuthorityUtil.hmac(account.getUsername() + new Date().getTime(), account.getAccountSecurity().getSalt());
			IAccountSecurity accountSecurity = account.getAccountSecurity();
			Date date = new Date();
			/*if(accountSecurity.getExpirationDate() != null && !"".equals(accountSecurity.getExpirationDate()) && accountSecurity.getSecurityCode() != null && !"".equals(accountSecurity.getSecurityCode())) {
				if(this.accountHelper.isExpirationDate(date, accountSecurity.getId())) {
					return AjaxResponse.fail("邮件已发送至您的邮箱中，请您在48小时内登录您的邮箱并点击激活链接完成账户密码重置。");
				}
			}*/
			date = CommonUtils.parseToDate(2);
			accountSecurity.setExpirationDate(date);
			accountSecurity.setSecurityCode(verifyCode);
			account.setAccountSecurity(accountSecurity);
			this.accountHelper.updateAccount(account);
			this.accountEmailHelper.sendResetPasswordEmail(account, request);
		} else {
			return AjaxResponse.fail("网络连接超时，请稍后重试");
		}
		return AjaxResponse.success();
	}
	
	/**
	 * 用户通过邮件地址访问，找回密码
	 * @param model
	 * @param request
	 * @param code
	 * @return
	 */
	@RequestMapping(value="checkemail")
	public ModelAndView checkeMail(ModelAndView model, HttpServletRequest request, 
			@RequestParam(value="code",  required=false) String code) {
		IAccount account = this.accountHelper.getAccountBySecurityCode(code);
		String viewName = "find-password/step-3";
		String result = "success";
		if (account == null) {
			viewName = "find-password/error";
			result = "error-not-account";
		} else if (!this.accountHelper.isExpirationDate(new Date(), account.getAccountSecurity().getId())) {
			viewName = "find-password/error";
			result = "beoverdue";
		} else {
			model.addObject("username", account.getUsername());
		}
		if(account != null) {
			IAccountSecurity security = account.getAccountSecurity();
			security.setExpirationDate(null);
			security.setSecurityCode(null);
			account.setAccountSecurity(security);
			this.accountHelper.updateAccount(account);
		}
		model.addObject("result", result).setViewName(viewName);
		return model;
	}
	
	/**
	 * 找回密码第三步，通过安全问题设置密码
	 * @param model
	 * @param username
	 * @param request
	 * @return
	 */
	@RequestMapping(value="step-two")
	public ModelAndView stepTwo(ModelAndView model, @RequestParam(value="username",  required=false) String username,
			 HttpServletRequest request) {
		String viewName = "find-password/step-3";
		IAccount account = this.accountHelper.findAccountByUsername(username);
		if(account == null) {
			viewName = "find-password/step-1";
			model.addObject("errors","对不起，注册账户名不存在.");
		} else {
			model.addObject("username", account.getUsername());
		}
		model.setViewName(viewName);
		return model;
	}
	
	/**
	 * 找回密码第四步，完成
	 * @param username
	 * @param password
	 * @param request
	 * @return
	 */
	@RequestMapping(value="step-three")
	@ResponseBody
	public AjaxResponse stepThree(@RequestParam(value="username",  required=false) String username,
			@RequestParam(value="password",  required=false) String password, HttpServletRequest request) {
		IAccount account = this.accountHelper.findAccountByUsername(username);
		IAccountSecurity accountSecurity = account.getAccountSecurity();
		String passwordEncode = AuthorityUtil.hashPassword(password, accountSecurity.getSalt());
		accountSecurity.setAccountPassword(passwordEncode);
		account.setAccountSecurity(accountSecurity);
		this.accountHelper.updateAccount(account);
		return AjaxResponse.success();
	}
	
	/**
	 * 查询用户是否存在
	 * @param username
	 * @return
	 */
	@RequestMapping(value="findAccountUsername")
	@ResponseBody
	public AjaxResponse findAccountByUsername(@RequestParam(value="username") String username) {
		IAccount account = this.accountHelper.findAccountByUsername(username);
		if(account != null) {
			return AjaxResponse.success();
		} else {
			return AjaxResponse.fail("Error");
		}
	}
	
	/**
	 * 重置密码成功
	 * @param model
	 * @param username
	 * @return
	 */
	@RequestMapping(value="success")
	public ModelAndView success(ModelAndView model, @RequestParam(value="username") String username) {
		model.addObject("username",username);
		model.setViewName("find-password/success");
		return model;
	}
	
	/**
	 * 查询安全问题答案是否匹配
	 * @param answer
	 * @param username
	 * @return
	 */
	@RequestMapping(value="findAnswer")
	@ResponseBody
	public AjaxResponse findAnswer(@RequestParam(value="answer") String answer, @RequestParam(value="username") String username) {
		IAccount account = this.accountHelper.findAccountByUsername(username);
		if(account != null) {
			answer = AuthorityUtil.hmac(answer, account.getAccountSecurity().getSalt());
			if(account.getAccountSecurity().getSecurityAnswer().equals(answer)) {
				return AjaxResponse.success();
			} else {
				return AjaxResponse.fail("这是一个无效的答案.");
			}
		} else {
			return AjaxResponse.fail("用户不存在.");
		}
	}
	
	@RequestMapping(value="message")
	public ModelAndView showMessage(ModelAndView model) {
		model.setViewName("find-password/message");
		return model;
	}
	
}
