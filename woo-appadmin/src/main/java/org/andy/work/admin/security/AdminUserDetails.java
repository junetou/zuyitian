package org.andy.work.admin.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class AdminUserDetails implements UserDetails
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2296234415501988115L;
	private String username;
	private String password;
	private String salt;
	private String mobile;
	private String name;
	private boolean enabled;
	private Collection<GrantedAuthority> grantedAuthorities;
	private List<String> permissions;
	
	public AdminUserDetails(String username, String password, String mobile, String name
			, boolean enabled, Collection<GrantedAuthority> grantedAuthorities
			, List<String> permissions) {
		super();
		this.name = name;
		this.username = username;
		this.password = password;
		this.mobile = mobile;
		this.enabled = enabled;
		this.grantedAuthorities = grantedAuthorities;
		this.permissions = permissions;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.grantedAuthorities;
	}

	public String getPassword() {
		return this.password;
	}

	public String getUsername() {
		return this.username;
	}
	
	public String getName() {
		return this.name;
	}
	
	public boolean isAccountNonExpired() {
		return this.enabled;
	}

	public boolean isAccountNonLocked() {
		return this.enabled;
	}

	public boolean isCredentialsNonExpired() {
		return this.enabled;
	}

	public boolean isEnabled() {
		return this.enabled;
	}
	
	public String getSalt() {
		return this.salt;
	}

	public List<String> getPermissions() {
		return permissions;
	}
	
	public String getMobile() {
		return this.mobile;
	}
}
