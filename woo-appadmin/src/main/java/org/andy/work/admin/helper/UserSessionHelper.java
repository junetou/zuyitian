package org.andy.work.admin.helper;

import org.andy.work.admin.security.AdminUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserSessionHelper {
	
	public AdminUserDetails getUserDetails() {
		Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (obj instanceof AdminUserDetails) {
			return (AdminUserDetails) obj;
		}
		
		return null;
	}
	
	public String getCurrentUserName() {
		AdminUserDetails user = this.getUserDetails();
		return user.getUsername();
	}
	
	public String getCurrentUserMobile() {
		AdminUserDetails user = this.getUserDetails();
		return user.getMobile();
	}
	
	public String getCurrentName() {
		AdminUserDetails user = this.getUserDetails();
		return user.getName();
	}
	
	public boolean isUserLogined() {
		AdminUserDetails userDetails = this.getUserDetails();
		if (userDetails != null) {
			return true;
		}
		return false;
	}
}
