package org.adny.work.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsImpl implements UserDetails {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3709787375381028823L;
	private String username;
	private String password;
	private String salt;
	private boolean enabled;
	private Collection<GrantedAuthority> authorities;
	
	public UserDetailsImpl(String username, String password, String salt
			, boolean enabled, Collection<GrantedAuthority> authorities)
	{
		this.username = username;
		this.password = password;
		this.salt = salt;
		this.enabled = enabled;
		this.authorities = authorities;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities()
	{
		return authorities;
	}

	@Override
	public String getPassword()
	{
		return password;
	}

	@Override
	public String getUsername()
	{
		return username;
	}

	@Override
	public boolean isAccountNonExpired()
	{
		return enabled;
	}

	@Override
	public boolean isAccountNonLocked()
	{
		return enabled;
	}

	@Override
	public boolean isCredentialsNonExpired()
	{
		return enabled;
	}

	@Override
	public boolean isEnabled()
	{
		return enabled;
	}

	public String getSalt()
	{
		return salt;
	}
}
