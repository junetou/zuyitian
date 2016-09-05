package org.andy.work.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularExpressUtil {
	
	public static boolean isExcelFile(String fileType) {
		Pattern pattern = Pattern.compile("^.*\\.(?:xls|xlsx)$");
		Matcher matcher = pattern.matcher(fileType);
		
		return matcher.matches();
	}
	
	public static boolean isCorrectPassword(String pwd, int min, int max) {
		Pattern pwdPattern = Pattern.compile("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{" + min + "," + max + "}$");
		Matcher matcher = pwdPattern.matcher(pwd);
		
		return matcher.matches();
	}
	
	
}
