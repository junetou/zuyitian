package org.adny.work.controller.helper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.adny.work.property.SystemProperiesConfigure;
import org.andy.work.appserver.model.IAccount;
import org.andy.work.appserver.model.IAccountSecurity;
import org.andy.work.appserver.model.ISaaccount;
import org.andy.work.utils.AuthorityUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.template.Template;

/**
 *
 *developer
 *2015年1月24日下午3:17:39
 *
 */
@Component
public class AccountEmailHelper {
	
	private static final Logger log = Logger.getLogger(AccountEmailHelper.class);
	private SimpleDateFormat sf = new SimpleDateFormat("yyyy年MM月dd日");
	@Resource
	private EmailHelper emailHelper;
	@Resource
	private FreeMarkerConfigurer freeMarkerConfigurer;
	
	/**
	 * 找回密码
	 * @param account
	 * @param request
	 */
	public void sendResetPasswordEmail(IAccount account, HttpServletRequest request) {
		IAccountSecurity accountSecrity = account.getAccountSecurity();
		String email = accountSecrity.getEmail();
		String subject = SystemProperiesConfigure.get("mail.reset.subject");
		Map<String, String> model = new HashMap<String, String>();
		try {
			model.put("link", this.getResetLine(request, accountSecrity));
			model.put("toDay", this.sf.format(new Date()));
			Template template = this.freeMarkerConfigurer.getConfiguration().getTemplate("email/reset-password.ftl", "utf-8");
			String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
			this.emailHelper.send(email, subject, text);
		} catch (Exception e) {
			log.error("Send Account registration email error !", e);
		}
	}
	
	/**
	 * 更改邮箱
	 * @param account
	 * @param request
	 */
	public void sendUpdateEmail(IAccount account, HttpServletRequest request) {
		IAccountSecurity accountSecrity = account.getAccountSecurity();
		String email = accountSecrity.getEmail();
		String subject = SystemProperiesConfigure.get("mail.update.subject");
		Map<String, String> model = new HashMap<String, String>();
		try {
			model.put("link", this.getUpdMailLine(request, accountSecrity));
			model.put("toDay", this.sf.format(new Date()));
			Template template = this.freeMarkerConfigurer.getConfiguration().getTemplate("email/update-mail.ftl", "utf-8");
			String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
			this.emailHelper.send(email, subject, text);
		} catch (Exception e) {
			log.error("Send Account registration email error !", e);
		}
	}
	
	public void sendVerificationEmail(String email, IAccount account, HttpServletRequest request) {
		IAccountSecurity accountSecrity = account.getAccountSecurity();
		String subject = SystemProperiesConfigure.get("mail.update.subject");
		Map<String, String> model = new HashMap<String, String>();
		try {
			model.put("link", this.getVerificationLine(email, request, accountSecrity));
			model.put("toDay", this.sf.format(new Date()));
			Template template = this.freeMarkerConfigurer.getConfiguration().getTemplate("email/update-mail.ftl", "utf-8");
			String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
			this.emailHelper.send(email, subject, text);
		} catch (Exception e) {
			log.error("Send Account registration email error !", e);
		}
	}
	
	private String getVerificationLine(String email, HttpServletRequest request, IAccountSecurity accountSecrity) {
		return "http://" + request.getServerName() + "/person-central/update-verification" + "?code=" + accountSecrity.getSecurityCode() + "&email=" + email;
	}

	/**
	 * 注册账号
	 * @param saaccount
	 * @param request
	 */
	public void sendAccountRegistrationEmail(ISaaccount saaccount, HttpServletRequest request) {
		try {
			String emailAddr = saaccount.getEmail();
			String subject = SystemProperiesConfigure.get("mail.subject");
				
			Map<String, String> model = new HashMap<String, String>();
			model.put("link", this.genActiveAccountLink(request, saaccount));
				
			Template template = this.freeMarkerConfigurer.getConfiguration().getTemplate("email/register.ftl", "utf-8");
			String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
				
			this.emailHelper.send(emailAddr, subject, text);
		} catch (Exception e) {
			log.error("Send Account registration email error !", e);
		}
	}
	
	private String getResetLine(HttpServletRequest request, IAccountSecurity accountSecrity) {
		String mailEncode = AuthorityUtil.hmac(accountSecrity.getEmail(), accountSecrity.getSalt());
		return "http://" + request.getServerName() + SystemProperiesConfigure.get("mail.reset.active") + "?code=" + accountSecrity.getSecurityCode() + "&m=" + mailEncode;
	}
	
	private String getUpdMailLine(HttpServletRequest request, IAccountSecurity accountSecrity) {
		String mailEncode = AuthorityUtil.hmac(accountSecrity.getEmail(), accountSecrity.getSalt());
		return "http://" + request.getServerName() + SystemProperiesConfigure.get("mail.update.active") + "?code=" + accountSecrity.getSecurityCode() + "&m=" + mailEncode;
	}

	private String genActiveAccountLink(HttpServletRequest request, ISaaccount saaccount) {
		String mailEncode = AuthorityUtil.hmac(saaccount.getEmail(), saaccount.getSalt());
		String link = "http://" + request.getServerName() + SystemProperiesConfigure.get("mail.active") + "?code=" + saaccount.getSecureCode() + "&m=" + mailEncode;
		return link;
	}

}
