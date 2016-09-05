package org.andy.work.admin.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.andy.work.admin.helper.UserHelper;
import org.andy.work.admin.permission.RoleType;
import org.andy.work.appserver.model.IUser;
import org.andy.work.appserver.model.IUserGroup;
import org.andy.work.utils.StringUtil;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AdminAuthenticationService implements UserDetailsService {
	
	@Resource
	private UserHelper userHelper;
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		IUser user = this.userHelper.findUserByUsername(username);
       
   //     request.getAuthType();
        
        
        
		List<GrantedAuthority> grantedAuthorities = this.getGrantedAuthoritys(user);
		List<String> permissions = new ArrayList<String>();
		if (user.getUserGroup() != null && StringUtil.hasValue(user.getUserGroup().getPermission())) {
			permissions.addAll(Arrays.asList(user.getUserGroup().getPermission().split(",")));
		}
		
		AdminUserDetails userDetails = new AdminUserDetails(user.getUsername(), user.getPassword(), user.getMobile(), user.getDisplayName()
				,  true, grantedAuthorities, permissions);
	//    System.out.println(userDetails.getName());
		return userDetails;
	}
	
	private List<GrantedAuthority> getGrantedAuthoritys(IUser user) {
		
		List<GrantedAuthority> gas = new ArrayList<GrantedAuthority>();
		
		IUserGroup group = user.getUserGroup();
		if (group != null && StringUtil.hasValue(group.getRole())) {
			gas.add(new SimpleGrantedAuthority(RoleType.USER));
			for (String role : group.getRole().split(",")) {
				gas.add(new SimpleGrantedAuthority(role));
			}
		}
		
		return gas;
	}

}
