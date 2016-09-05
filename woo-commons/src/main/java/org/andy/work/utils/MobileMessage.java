package org.andy.work.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MobileMessage {
	private static final String DX_URL = "http://sms.1xinxi.cn/asmx/smsservice.aspx";
	private static final String DX_NAME = "13416374134";
//	private static final String DX_PWD = "A057B14EDE460C1210D47E76D1DD";
	private static final String DX_PWD = "2188D8BF7613BBA3D266B3DAA201";
	private static final String DX_SIGN = "大佑科技";
	
	/**
	 * 发送短信通知手机验证码
	 * @param mobile 要发送的手机号码
	 * @param mobileCode 验证码
	 * @return 0：短信提交成功
	 * 		   2：余额不足
	 * 		   3：手机号码错误
	 */
	public static String sendMobileCode(String mobile, String mobileCode) {
		try {
			StringBuffer buff = new StringBuffer(DX_URL);
			buff.append("?name=").append(DX_NAME);
			buff.append("&pwd=").append(DX_PWD);
			buff.append("&mobile=").append(mobile);
			buff.append("&content=").append(URLEncoder.encode("您的手机验证码为：", "UTF-8"));
			buff.append(mobileCode).append(URLEncoder.encode("，切勿泄漏给其他人，有效期为5分钟", "UTF-8"));
			buff.append("&stime=").append("&sign=").append(URLEncoder.encode(DX_SIGN, "UTF-8"));
			buff.append("&type=pt&extno=");
			URL url = new URL(buff.toString());
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			String inputline = in.readLine().substring(0, 1);
			return inputline;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 发送短信通知订单
	 * @param mobile 要发送的手机号码
	 * @param name 客户的名称
	 * @param order 订单号
	 * @return 0：短信提交成功
	 * 		   2：余额不足
	 * 		   3：手机号码错误
	 */
	public static String notifyOrder(String mobile, String name, String order) {
		try {
			StringBuffer buff = new StringBuffer(DX_URL);
			buff.append("?name=").append(DX_NAME);
			buff.append("&pwd=").append(DX_PWD);
			buff.append("&mobile=").append(mobile);
			buff.append("&content=").append(URLEncoder.encode("客户", "UTF-8"));
			buff.append(URLEncoder.encode(name, "UTF-8"));
			buff.append(URLEncoder.encode(" 于", "UTF-8"));
			buff.append(URLEncoder.encode(getDateTime(), "UTF-8"));
			buff.append(URLEncoder.encode("下单成功，订单号为：", "UTF-8"));
			buff.append(URLEncoder.encode(order, "UTF-8"));
			buff.append(URLEncoder.encode("， 请登录后台系统查看", "UTF-8"));
			buff.append("&stime=").append("&sign=").append(URLEncoder.encode(DX_SIGN, "UTF-8"));
			buff.append("&type=pt&extno=");
			URL url = new URL(buff.toString());
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			String inputline = in.readLine().substring(0, 1);
			return inputline;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 发送短信通知发货
	 * @param mobile 要发送的手机号码
	 * @param name 客户的名称
	 * @param order 订单号
	 * @return 0：短信提交成功
	 * 		   2：余额不足
	 * 		   3：手机号码错误
	 */
	public static String notifyDeliverGoods(String mobile, String name, String order) {
		try {
			StringBuffer buff = new StringBuffer(DX_URL);
			buff.append("?name=").append(DX_NAME);
			buff.append("&pwd=").append(DX_PWD);
			buff.append("&mobile=").append(mobile);
			buff.append("&content=").append(URLEncoder.encode("客户", "UTF-8"));
			buff.append(URLEncoder.encode(name, "UTF-8"));
			buff.append(URLEncoder.encode(" 于", "UTF-8"));
			buff.append(URLEncoder.encode(getDateTime(), "UTF-8"));
			buff.append(URLEncoder.encode("付款成功，订单号为：", "UTF-8"));
			buff.append(URLEncoder.encode(order, "UTF-8"));
			buff.append(URLEncoder.encode("， 请登录后台系统查看并发货", "UTF-8"));
			buff.append("&stime=").append("&sign=").append(URLEncoder.encode(DX_SIGN, "UTF-8"));
			buff.append("&type=pt&extno=");
			URL url = new URL(buff.toString());
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			String inputline = in.readLine().substring(0, 1);
			return inputline;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 发送短信通知，提交分销商申请
	 * @param mobile 要发送的手机号码
	 * @param name 客户的名称
	 * @return 0：短信提交成功
	 * 		   2：余额不足
	 * 		   3：手机号码错误
	 */
	/*public static String notifyDeliverGoods(String mobile, String name) {
		try {
			StringBuffer buff = new StringBuffer(DX_URL);
			buff.append("?name=").append(DX_NAME);
			buff.append("&pwd=").append(DX_PWD);
			buff.append("&mobile=").append(mobile);
			buff.append("&content=").append(URLEncoder.encode("客户", "UTF-8"));
			buff.append(URLEncoder.encode(name, "UTF-8"));
			buff.append(URLEncoder.encode(" 于", "UTF-8"));
			buff.append(URLEncoder.encode(getDateTime(), "UTF-8"));
			buff.append(URLEncoder.encode("申请分销商", "UTF-8"));
			buff.append(URLEncoder.encode("， 请登录后台系统查看并审核", "UTF-8"));
			buff.append("&stime=").append("&sign=").append(URLEncoder.encode(DX_SIGN, "UTF-8"));
			buff.append("&type=pt&extno=");
			URL url = new URL(buff.toString());
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			String inputline = in.readLine().substring(0, 1);
			return inputline;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}*/
	
	/**
	 * 发送短信通知审核
	 * @param mobile 要发送的手机号码
	 * @param name 客户的名称
	 * @return 0：短信提交成功
	 * 		   2：余额不足
	 * 		   3：手机号码错误
	 */
	public static String notify2Examine(String mobile, String name) {
		try {
			StringBuffer buff = new StringBuffer(DX_URL);
			buff.append("?name=").append(DX_NAME);
			buff.append("&pwd=").append(DX_PWD);
			buff.append("&mobile=").append(mobile);
			buff.append("&content=").append(URLEncoder.encode("客户", "UTF-8"));
			buff.append(URLEncoder.encode(name, "UTF-8"));
			buff.append(URLEncoder.encode(" 于", "UTF-8"));
			buff.append(URLEncoder.encode(getDateTime(), "UTF-8"));
			buff.append(URLEncoder.encode("申请分销商", "UTF-8"));
			buff.append(URLEncoder.encode("， 请登录后台系统查看并审核", "UTF-8"));
			buff.append("&stime=").append("&sign=").append(URLEncoder.encode(DX_SIGN, "UTF-8"));
			buff.append("&type=pt&extno=");
			URL url = new URL(buff.toString());
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			String inputline = in.readLine().substring(0, 1);
			return inputline;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 发送短信通知审核
	 * @param mobile 要发送的手机号码
	 * @param name 客户的名称
	 * @return 0：短信提交成功
	 * 		   2：余额不足
	 * 		   3：手机号码错误
	 */
	public static String notifyCommission(String mobile, String name, String amount) {
		try {
			StringBuffer buff = new StringBuffer(DX_URL);
			buff.append("?name=").append(DX_NAME);
			buff.append("&pwd=").append(DX_PWD);
			buff.append("&mobile=").append(mobile);
			buff.append("&content=").append(URLEncoder.encode("客户", "UTF-8"));
			buff.append(URLEncoder.encode(name, "UTF-8"));
			buff.append(URLEncoder.encode(" 于", "UTF-8"));
			buff.append(URLEncoder.encode(getDateTime(), "UTF-8"));
			buff.append(URLEncoder.encode("提交了佣金申请提现,金额为:", "UTF-8"));
			buff.append(URLEncoder.encode(amount, "UTF-8")).append(URLEncoder.encode("元", "UTF-8"));
			buff.append(URLEncoder.encode("， 请登录后台系统查看并审核", "UTF-8"));
			buff.append("&stime=").append("&sign=").append(URLEncoder.encode(DX_SIGN, "UTF-8"));
			buff.append("&type=pt&extno=");
			URL url = new URL(buff.toString());
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			String inputline = in.readLine().substring(0, 1);
			return inputline;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 发送短信通知发红包验证码
	 * @param mobile 要发送的手机号码
	 * @param mobileCode 验证码
	 * @return 0：短信提交成功
	 * 		   2：余额不足
	 * 		   3：手机号码错误
	 */
	public static String notifyToRedEnvelope(String mobile, String mobileCode) {
		try {
			StringBuffer buff = new StringBuffer(DX_URL);
			buff.append("?name=").append(DX_NAME);
			buff.append("&pwd=").append(DX_PWD);
			buff.append("&mobile=").append(mobile);
			buff.append("&content=").append(URLEncoder.encode("您正在使用大佑科技后台派发红包，验证码为：", "UTF-8"));
			buff.append(mobileCode);
			buff.append(URLEncoder.encode("，如非本人操作，请忽略", "UTF-8"));
			buff.append("&stime=").append("&sign=").append(URLEncoder.encode(DX_SIGN, "UTF-8"));
			buff.append("&type=pt&extno=");
			URL url = new URL(buff.toString());
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			String inputline = in.readLine().substring(0, 1);
			return inputline;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String getDateTime() {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分");
		return format.format(date);
	}
	
	/**
	 * 发送短信通知微信打款验证码
	 * @param mobile 要发送的手机号码
	 * @param mobileCode 验证码
	 * @return 0：短信提交成功
	 * 		   2：余额不足
	 * 		   3：手机号码错误
	 */
	public static String notifyToEnterprisePay(String mobile, String mobileCode) {
		try {
			StringBuffer buff = new StringBuffer(DX_URL);
			buff.append("?name=").append(DX_NAME);
			buff.append("&pwd=").append(DX_PWD);
			buff.append("&mobile=").append(mobile);
			buff.append("&content=").append(URLEncoder.encode("您正在使用大佑科技后台微信打款，验证码为：", "UTF-8"));
			buff.append(mobileCode);
			buff.append(URLEncoder.encode("，如非本人操作，请忽略", "UTF-8"));
			buff.append("&stime=").append("&sign=").append(URLEncoder.encode(DX_SIGN, "UTF-8"));
			buff.append("&type=pt&extno=");
			URL url = new URL(buff.toString());
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			String inputline = in.readLine().substring(0, 1);
			return inputline;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 短信通知操作安全支付密码
	 * @param mobile 要发送的手机号码
	 * @param mobileCode 验证码
	 * @return 0：短信提交成功
	 * 		   2：余额不足
	 * 		   3：手机号码错误
	 */
	public static String notifyToSetPasswordPay(String mobile, String mobileCode) {
		try {
			StringBuffer buff = new StringBuffer(DX_URL);
			buff.append("?name=").append(DX_NAME);
			buff.append("&pwd=").append(DX_PWD);
			buff.append("&mobile=").append(mobile);
			buff.append("&content=").append(URLEncoder.encode("您正在操作大佑云商支付密码，本次验证码为：", "UTF-8"));
			buff.append(mobileCode);
			buff.append(URLEncoder.encode("，如有疑问，请拨打客服电话", "UTF-8"));
			buff.append("&stime=").append("&sign=").append(URLEncoder.encode(DX_SIGN, "UTF-8"));
			buff.append("&type=pt&extno=");
			URL url = new URL(buff.toString());
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			String inputline = in.readLine().substring(0, 1);
			return inputline;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
