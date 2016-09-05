package org.adny.work.security;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.adny.work.controller.helper.AccountHelper;
import org.andy.work.appserver.model.IAccount;
import org.andy.work.appserver.model.IAccountSecurity;
import org.andy.work.utils.StringUtil;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService
{
	@Resource
	private AccountHelper accountHelper;
	@Resource
	private HmacPasswordEncoderImpl passwordValidator;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		if (StringUtil.hasValue(username))
		{
			IAccount account = this.accountHelper.findAccountByUsername(username);
			if (account != null)
			{
				IAccountSecurity accountSecurity = account.getAccountSecurity();
				
				boolean isEnabled = !"Y".equals(account.getLocked());
				UserDetailsImpl userDetails = new UserDetailsImpl(
						account.getUsername(), accountSecurity.getAccountPassword(), accountSecurity.getSalt(), isEnabled, this.getGrantedAuthoritys(account));
				
				return userDetails;
			}
		}
		
		throw new UsernameNotFoundException("");
	}
	
	private List<GrantedAuthority> getGrantedAuthoritys(IAccount account)
	{
		List<GrantedAuthority> gas = new ArrayList<GrantedAuthority>();
		
		gas.add(new SimpleGrantedAuthority("ROLE_" + account.getAcctType()));
		
		return gas;
	} 

	public void setAccountHelper(AccountHelper accountHelper)
	{
		this.accountHelper = accountHelper;
	}
	public void setPasswordValidator(HmacPasswordEncoderImpl passwordValidator)
	{
		this.passwordValidator = passwordValidator;
	}
}
