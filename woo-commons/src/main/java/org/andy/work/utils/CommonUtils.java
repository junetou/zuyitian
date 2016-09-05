package org.andy.work.utils;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.support.RequestContextUtils;

public class CommonUtils {
	
	private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy/MM/dd");
	private static final DateFormat DATETIME_FORMAT = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
	private static WebApplicationContext wac;
	
	public static String getMessage(String key, Object[] args, Locale locale, HttpServletRequest request) 
	{
		try
		{
			if (wac == null)
			{
				wac = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
			}
     
			return wac.getMessage(key, args, locale);
		}
		catch (Exception ex)
		{
			//ignored
		}
		return "";
	}
	
	
	public static String getMessage(String key, Locale locale, HttpServletRequest request) 
	{
		return getMessage(key, null, locale, request);
	}
	
	public static String getMessage(String key, HttpServletRequest request)
	{
		Locale locale = RequestContextUtils.getLocaleResolver(request).resolveLocale(request);
		return getMessage(key, locale, request);
	}
	
	public static String getRequestURL(HttpServletRequest request) { 
		if (request == null) { 
			return ""; 
		} 
		String url = "http://"; 
		url = url + request.getServerName();
		url = url + request.getContextPath(); 
		url = url + request.getServletPath(); 
		if (request.getQueryString() != null && !"".equals(request.getQueryString())) { 
			url = url + "?" + request.getQueryString(); 
		} 
		return url; 
	}
	
	
	/**
	 * 字符串转换日期
	 * @param dateStr
	 * @return
	 */
	public static Date parseFromDate(String dateStr) {
		Date date = null;
		if (StringUtil.hasValue(dateStr)) {
			try {
				date = parseDate(dateStr);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return date;
	}
	
	/**
	 * 字符串转换日期
	 * @param dateStr
	 * @return
	 */
	public static Date parseToDate(String dateStr) {
		if (StringUtil.hasValue(dateStr)) {
			try {
				Date date = parseDate(dateStr);
				Calendar c = Calendar.getInstance();
				c.setTime(date);
				c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) + 1); // next date
				c.set(Calendar.HOUR_OF_DAY, 0);
				c.set(Calendar.MINUTE, 0);
				c.set(Calendar.SECOND, 0);
				return c.getTime();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public static Date parseToDate( int dayNum) {
		try {
			Calendar calendar = Calendar.getInstance();
		  calendar.add(Calendar.DAY_OF_MONTH, + dayNum);
			return calendar.getTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 格式化日期，日期转换字符串 (2015/10/20 16:15:20)
	 * @param date
	 * @return
	 */
	public static String datetimeFormat(Date date) {
		if (date != null) {
			return DATETIME_FORMAT.format(date);
		}
		return "";
	}
	
	/**
	 * 格式化日期，日期转换字符串 (2015/10/20 16:15:20)
	 * @param date
	 * @return
	 */
	public static String datetimeFormat(Date date, int dayNum) {
		if (date != null) {
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) + dayNum); // next date
			c.set(Calendar.HOUR_OF_DAY, 0);
			c.set(Calendar.MINUTE, 0);
			c.set(Calendar.SECOND, 0);
			return DATETIME_FORMAT.format(c.getTime());
		}
		return "";
	}
	
	public static String startDateAuthorization() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		return sdf.format(new Date());
	}
	
	public static String endDateAuthorization() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) + 365); // next date
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		return sdf.format(c.getTime());
	}
	
	/**
	 * 格式化日期，日期转换字符串(2015/10/20)
	 * @param date
	 * @return
	 */
	public static String dateFormat(Date date) {
		if (date != null) {
			return DATE_FORMAT.format(date);
		}
		return "";
	}
	
	/**
	 * 字符串转换日期
	 * @param dateStr
	 * @return 2015/11/30
	 * @throws ParseException
	 */
	public static Date parseDate(String dateStr) throws ParseException {
		return DATE_FORMAT.parse(dateStr);
	}
	
	/**
	 * 字符串转换日期
	 * @param dateStr
	 * @return  2015/11/30 11:22:23
	 * @throws ParseException
	 */
	public static Date parseDateTime(String dateStr) throws ParseException {
		return DATETIME_FORMAT.parse(dateStr);
	}
	
	/**
	 * 格式化数字，并保留两位小时
	 * 例如（12,123.00）
	 * @param amt
	 * @return
	 */
	public static String amountFormat(Double amt)
	{
		return format(amt, 2);
	}	
	
	/**
	 * 格式化数字
	 * 例如（12,123）
	 * @param amt
	 * @return
	 */
	public static String quantityFormat(Double qty)
	{
		return format(qty, 0);
	}
	
	/**
	 * 数据加密处理
	 * @param decript
	 * @return
	 */
	public static String SHA1(String decript) {
		return DigestUtils.md5Hex(decript);
        /*try {
            MessageDigest digest = java.security.MessageDigest
                    .getInstance("SHA-1");
            digest.update(decript.getBytes());
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();
 
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";*/
    }
	
	/**
	 * 格式化数字
	 * @param d1
	 * @param scale
	 * @return
	 */
	private static String format(Double d1, int scale)
	{
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(scale);
		nf.setMinimumFractionDigits(scale);
		
		if (d1 == null)
		{
			return nf.format(0);
		}
		
		return nf.format(d1);
	}
	
	/**
	 * 获取IP地址
	 * @return
	 */
	public static String getIp(HttpServletRequest request){
		/*if (request.getHeader("x-forwarded-for") == null) {
            return request.getRemoteAddr();    
        }     
        return request.getHeader("x-forwarded-for"); */
		String ip = request.getHeader("x-forwarded-for");
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getHeader("Proxy-Client-IP");
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getHeader("WL-Proxy-Client-IP");
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getRemoteAddr();
	    }
	    return ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;

	}
	
	/**
	 * 判断日期时分秒，多少小时前
	 * @param date 判断日期
	 * @return String 结果
	 * @throws ParseException 
	 */
	public static String isDateTime(Date riqi){
		//SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = null;
		try {
			now = DATETIME_FORMAT.parse(DATETIME_FORMAT.format(Calendar.getInstance().getTime()));
		} catch (Exception e) {
			e.printStackTrace();
		}

		long l = Math.abs(now.getTime() - riqi.getTime());
        long day = l / 86400000L;
        long hour = l / 3600000L - day * 24L;
        long min = l / 60000L - day * 24L * 60L - hour * 60L;
        long s = l / 1000L - day * 24L * 60L * 60L - hour * 60L * 60L - min * 60L;
        
        if ((hour <= 12L) && (day < 1L)) {
        	if (hour >= 1L) {
        		return hour + "小时前";
        	}
        	if (min >= 1L) {
        		return min + "分钟前";
        	}

        	return s + "秒前";
        }
        //SimpleDateFormat shijiangs = new SimpleDateFormat("yyyy-MM-dd");
        try {
        	 return DATE_FORMAT.format(riqi);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * 获取UUID
	 * @return String
	 */
	public static String getUUID() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}
	
	/**
	 * 隐藏部分
	 * @param idCardNum
	 * @return
	 */
	public static String maskIdCardNum(String idCardNum) {
		if (StringUtil.hasValue(idCardNum)) {
			String prefix = idCardNum.substring(0, 5);
			String subfix = idCardNum.substring(idCardNum.length() - 2);
			return prefix + "***********" + subfix;
		}
		return idCardNum;
	}
    
    /** 获取两个时间的时间查 如1天2小时30分钟 */
    public static String getDatePoor(Date endDate, Date nowDate) {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        return day + "天" + hour + "时";
    }
    
}
