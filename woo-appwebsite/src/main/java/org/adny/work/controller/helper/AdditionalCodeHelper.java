package org.adny.work.controller.helper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.andy.work.constant.WebConstants;
import org.andy.work.utils.StringUtil;
import org.springframework.stereotype.Component;

@Component
public class AdditionalCodeHelper {
	
	public void saveAdditionalCode(HttpServletRequest request, String code) {
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute(WebConstants.ADDITIONAL_CODE, code);
	}
	
	public void cleanAdditionalCode(HttpServletRequest request) {
		HttpSession httpSession = request.getSession();
		httpSession.removeAttribute(WebConstants.ADDITIONAL_CODE);
	}
	
	public boolean isValid(String code, HttpServletRequest request) {
		HttpSession httpSession = request.getSession();
		String codeInSession = (String) httpSession.getAttribute(WebConstants.ADDITIONAL_CODE);
		if (StringUtil.hasValue(code) && code.equalsIgnoreCase(codeInSession)) {
			return true;
		}
		return false;
	}
}	
