package org.andy.work.utils;

import java.math.BigDecimal;
import java.security.MessageDigest;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.SortedMap;

public class WechatPayUtil {
	
	/**   
     * 将元为单位的转换为分 替换小数点，支持以逗号区分的金额  
     *   
     * @param amount  
     * @return  
     */    
    public static String changeY2F(Double amount){
    	Double amt = Double.valueOf(BigDecimal.valueOf(amount).multiply(new BigDecimal(100)).toString());
    	Integer amLong = amt.intValue();
        return amLong.toString();    
    }
	
	/**
	 * 将请求参数转换为 xml 格式的 string
	 * 
	 * @param parameters  请求参数
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String getRequestXml(SortedMap<Object,Object> parameters){
		StringBuffer sb = new StringBuffer();
		sb.append("<xml>");
		Set es = parameters.entrySet();
		Iterator it = es.iterator();
		while(it.hasNext()) {
			Map.Entry entry = (Map.Entry)it.next();
			String k = (String)entry.getKey();
			String v = (String)entry.getValue();
			if ("attach".equalsIgnoreCase(k)||"body".equalsIgnoreCase(k)||"sign".equalsIgnoreCase(k)) {
				sb.append("<"+k+">"+"<![CDATA["+v+"]]></"+k+">");
			}else {
				sb.append("<"+k+">"+v+"</"+k+">");
			}
		}
		sb.append("</xml>");
		return sb.toString();
	}
	
	/**
	 * 获取随机数字字符串
	 * @param num 几位数
	 * @return
	 */
	public static String getRandomString(Integer num) {
		Random random = new Random();
		String randomString = "";
		for (int i = 0; i < num; i++) {
			randomString += random.nextInt(10);
		}
		return randomString;
	}
	
	/**
	 * 创建签名
	 * 
	 * @param characterEncoding 编码格式
	 * @param parameters 请求参数
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String createSign(String characterEncoding, SortedMap<Object,Object> parameters, String wxKey){
		StringBuffer sb = new StringBuffer();
		Set es = parameters.entrySet();
		Iterator it = es.iterator();
		while(it.hasNext()) {
			Map.Entry entry = (Map.Entry)it.next();
			String k = (String)entry.getKey();
			Object v = entry.getValue();
			if(null != v && !"".equals(v) 
					&& !"sign".equals(k) && !"key".equals(k)) {
				sb.append(k + "=" + v + "&");
			}
		}
		sb.append("key=" + wxKey);
		String sign = WechatPayUtil.MD5Encode(sb.toString(), characterEncoding).toUpperCase();
		return sign;
	}
	
	/**
	 * 返回给微信的参数
	 * 
	 * @param return_code 返回编码
	 * @param return_msg  返回信息
	 * @return
	 */
	public static String setXML(String return_code, String return_msg) {
		return "<xml><return_code><![CDATA[" + return_code
				+ "]]></return_code><return_msg><![CDATA[" + return_msg
				+ "]]></return_msg></xml>";
	}
	
	public static String MD5Encode(String origin, String charsetname) {
		String resultString = null;
		try {
			resultString = new String(origin);
			MessageDigest md = MessageDigest.getInstance("MD5");
			if (charsetname == null || "".equals(charsetname))
				resultString = byteArrayToHexString(md.digest(resultString
						.getBytes()));
			else
				resultString = byteArrayToHexString(md.digest(resultString
						.getBytes(charsetname)));
		} catch (Exception exception) {
		}
		return resultString;
	}
	
	private static String byteArrayToHexString(byte b[]) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++)
			resultSb.append(byteToHexString(b[i]));

		return resultSb.toString();
	}
	
	private static final String hexDigits[] = { "0", "1", "2", "3", "4", "5",
		"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
	
	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n += 256;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}
	
}
