package org.andy.work.utils;

import java.util.Random;

public class AuthorityUtil {
	private static Random wheel = new Random();
	
	public static String generateSalt() {
		return createAlmostRandomString(16);
	}
	
	private static String createAlmostRandomString(int length) {
		StringBuffer sb = new StringBuffer();
		while (sb.length() < length) {
			char ch = (char) wheel.nextInt('z' + 1);
			if (Character.isLetterOrDigit(ch)) {
				sb.append(ch);
			}
		}
		sb.setLength(length);
		return sb.toString();
	}
	
	public static String hashPassword(String password, String salt) {
		return HmacUtil.hmac(password, salt, "HmacSHA1");
	}
	
	public static String hmac(String raw, String salt) {
		return HmacUtil.hmac(raw, salt, "HmacSHA1");
	}
}
