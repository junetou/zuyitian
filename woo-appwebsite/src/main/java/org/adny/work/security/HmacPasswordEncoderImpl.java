package org.adny.work.security;

import org.andy.work.utils.AuthorityUtil;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class HmacPasswordEncoderImpl implements PasswordEncoder {

	@Override
	public String encodePassword(String rawPass, Object salt) {
		return AuthorityUtil.hashPassword(rawPass, salt.toString());
	}

	@Override
	public boolean isPasswordValid(String encPass, String rawPass, Object salt) {
		return encPass.equals(this.encodePassword(rawPass, salt));
	}
	

}
