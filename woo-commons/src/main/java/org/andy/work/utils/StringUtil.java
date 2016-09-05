package org.andy.work.utils;

public class StringUtil {
	
	public static boolean isBlank(String str) {
		return str == null || "".equals(str.trim());
	}
	
	public static boolean hasValue(String str) {
		return str != null && !str.trim().equals("");
	}
	
	public static String trim(String str) {
		if (str == null) {
			return "";
		}
		return str.trim();
	}
	
}
