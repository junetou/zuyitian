package org.andy.work.admin.tag;

import java.util.Collection;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.andy.work.admin.security.AdminUserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

public class PermissionTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2317512100352719974L;
	
	private String roleType;
	private String operationType;
	
	public int doStartTag() throws JspException {
		AdminUserDetails detail = (AdminUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		boolean falg = false;
		if (!this.isAuthoritie(detail.getAuthorities(), roleType)) {
			falg = true;
		} else if (!detail.getPermissions().contains(operationType)) {
			falg = true;
		}
		if (falg) {
			return SKIP_BODY;
		}
		return EVAL_BODY_INCLUDE;
	}
	
	private boolean isAuthoritie(Collection<? extends GrantedAuthority> collection, String operationType) {
		for (GrantedAuthority grantedAuthority : collection) {
			if (grantedAuthority.toString().equals(operationType)) {
				return true;
			}
		}
		return false;
	}
	
	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}
	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}
}
