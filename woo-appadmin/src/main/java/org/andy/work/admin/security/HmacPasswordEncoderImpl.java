package org.andy.work.admin.security;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class HmacPasswordEncoderImpl implements PasswordEncoder {

	@Override
	public String encodePassword(String rawPass, Object salt) {
		return DigestUtils.md5Hex(rawPass);
	}

	@Override
	public boolean isPasswordValid(String encPass, String rawPass, Object salt) {
		boolean flag = encPass.equals(this.encodePassword(rawPass, salt));
		return flag;
	}
	
}
