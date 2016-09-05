package org.andy.work.utils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
/**
 *
 * 快递鸟物流轨迹即时查询接口
 *
 * @author: CQ
 * @qq: 1069712970
 * @see: http://www.kdniao.com/YundanChaxunAPI.aspx
 * @copyright: 深圳市快金数据技术服务有限公司
 *
 * DEMO中的电商ID与私钥仅限测试使用，正式环境请单独注册账号
 * 单日超过500单查询量，建议接入我方物流轨迹订阅推送接口
 * 
 * ID和Key请到官网申请：http://www.kdniao.com/ServiceApply.aspx
 * 测试ID和KEY已经关闭
 * ID:1237100
 * KEY:518a73d8-1f7f-441a-b644-33e77b49d846
 */

@SuppressWarnings("restriction")
public class KdApiSearchUtils {
	
	//电商ID
	private static final String EBusinessID="1255635";	
	//电商加密私钥，快递鸟提供，注意保管，不要泄漏
	private static final String AppKey="3c59de27-f0ae-41a3-9f31-1109d27478aa";	
	//请求url
	private static final String ReqURL="http://api.kdniao.cc/Ebusiness/EbusinessOrderHandle.aspx";	
	
	
	public static void main(String[] args) {
		try {
			System.out.println(KdApiSearchUtils.getOrderWiththeByJson("JYM", "2225862072"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
     * Json方式 查询订单物流轨迹
	 * @throws Exception 
     */
	public static String getOrderWiththeByJson(String shipperCode, String logisticCode) throws Exception {
		String requestData= "{'OrderCode':'','ShipperCode':'"+shipperCode+"','LogisticCode':'"+logisticCode+"'}";
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("RequestData", urlEncoder(requestData, "UTF-8"));
		params.put("EBusinessID", EBusinessID);
		params.put("RequestType", "1002");
		String dataSign=encrypt(requestData, AppKey, "UTF-8");
		params.put("DataSign", urlEncoder(dataSign, "UTF-8"));
		params.put("DataType", "2");
		
		String result = sendPost(ReqURL, params);	
		return result;
	}
	
	/**
     * Json方式 查询订单物流轨迹
	 * @throws Exception 
     */
	public static String getOrderTracesByJson() throws Exception{
		String requestData= "{'OrderCode':'','ShipperCode':'SF','LogisticCode':'918222234993'}";
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("RequestData", urlEncoder(requestData, "UTF-8"));
		params.put("EBusinessID", EBusinessID);
		params.put("RequestType", "1002");
		String dataSign=encrypt(requestData, AppKey, "UTF-8");
		params.put("DataSign", urlEncoder(dataSign, "UTF-8"));
		params.put("DataType", "2");
		
		String result=sendPost(ReqURL, params);	
		
		//根据公司业务处理返回的信息......
		result = formatJsonResult(result);
		
		return result;
	}
	
	private static String formatJsonResult(String result) {
		JSONObject  resJson = new JSONObject (result);
		StringBuffer resSb = new StringBuffer("<ul class='op_express_delivery_timeline_box'>");
		JSONArray list = resJson.getJSONArray("Traces");
		for (int i = list.length()-1; i >= 0; i--) {
			JSONObject obj = (JSONObject) list.get(i);
			if (i+1 == list.length()) {
				resSb.append("<li class='op_express_delivery_timeline_new c-clearfix'><div class='op_express_delivery_timeline_title'><div class='op_express_delivery_timeline_circle op_express_delivery_timeline_circle'><i class='c-icon op_express_delivery_timeline_circle_red'></i><i class='c-text c-text-danger c-text-mult c-gap-left-small'>最新</i></div><div class='op_express_delivery_timeline_info'>");
				resSb.append(obj.get("AcceptTime")).append("<br>").append(obj.get("AcceptStation")).append("</div></div></li>");
			} else {
				resSb.append("<li class='op_express_delivery_timeline_label c-clearfix'><div class='op_express_delivery_timeline_title'><div class='op_express_delivery_timeline_circle'><i class='c-icon op_express_delivery_timeline_circle_blue'></i></div><div class='op_express_delivery_timeline_info'>");
				resSb.append(obj.get("AcceptTime")).append("<br>").append(obj.get("AcceptStation")).append("</div></div></li>");
			}
		}
		resSb.append("</ul>");
		return resSb.toString();
	}
	
	private static String formatWechatJsonResult(String result) {
		JSONObject  resJson = new JSONObject (result);
		StringBuffer resSb = new StringBuffer("<ul class='lgtk-ul'>");
		JSONArray list = resJson.getJSONArray("Traces");
		for (int i = list.length()-1; i >= 0; i--) {
			JSONObject obj = (JSONObject) list.get(i);
			if (i+1 == list.length()) {
				resSb.append("<li class='borderLeft'><div class='divbgcolor'>").append(obj.get("AcceptStation"));
				resSb.append("<p>").append(obj.get("AcceptTime")).append("</p><i class='bgcolorI'></i>").append("</div><span class='bgcolor'><u></u></span></li>");
			} else {
				resSb.append("<li><div>").append(obj.get("AcceptStation"));
				resSb.append("<p>").append(obj.get("AcceptTime")).append("</p><i></i>").append("</div><span><u></u></span></li>");
			}
		}
		resSb.append("</ul>");
		return resSb.toString();
	}
	
	/**
     * Json方式 查询订单物流轨迹
	 * @throws Exception 
     */
	public static String getOrderWechatTracesByJson(String shipperCode, String logisticCode) throws Exception {
		String requestData= "{'OrderCode':'','ShipperCode':'"+shipperCode+"','LogisticCode':'"+logisticCode+"'}";
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("RequestData", urlEncoder(requestData, "UTF-8"));
		params.put("EBusinessID", EBusinessID);
		params.put("RequestType", "1002");
		String dataSign=encrypt(requestData, AppKey, "UTF-8");
		params.put("DataSign", urlEncoder(dataSign, "UTF-8"));
		params.put("DataType", "2");
		
		String result=sendPost(ReqURL, params);	
		
		//根据公司业务处理返回的信息......
		result = formatWechatJsonResult(result);
		
		return result;
	}

	/**
     * Json方式 查询订单物流轨迹
	 * @throws Exception 
     */
	public static String getOrderTracesByJson(String shipperCode, String logisticCode) throws Exception{
		String requestData= "{'OrderCode':'','ShipperCode':'"+shipperCode+"','LogisticCode':'"+logisticCode+"'}";
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("RequestData", urlEncoder(requestData, "UTF-8"));
		params.put("EBusinessID", EBusinessID);
		params.put("RequestType", "1002");
		String dataSign=encrypt(requestData, AppKey, "UTF-8");
		params.put("DataSign", urlEncoder(dataSign, "UTF-8"));
		params.put("DataType", "2");
		
		String result=sendPost(ReqURL, params);	
		
		//根据公司业务处理返回的信息......
		result = formatJsonResult(result);
		
		return result;
	}

	/**
     * XML方式 查询订单物流轨迹
	 * @throws Exception 
     */
	public static String getOrderTracesByXml() throws Exception{
		String requestData= "<?xml version=\"1.0\" encoding=\"utf-8\" ?>"+
							"<Content>"+
							"<OrderCode></OrderCode>"+
							"<ShipperCode>SF</ShipperCode>"+
							"<LogisticCode>918222234993</LogisticCode>"+
							"</Content>";
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("RequestData", urlEncoder(requestData, "UTF-8"));
		params.put("EBusinessID", EBusinessID);
		params.put("RequestType", "1002");
		String dataSign=encrypt(requestData, AppKey, "UTF-8");
		params.put("DataSign", urlEncoder(dataSign, "UTF-8"));
		params.put("DataType", "1");
		
		String result=sendPost(ReqURL, params);	
		
		//根据公司业务处理返回的信息......
		
		return result;
	}
	
	/**
     * XML方式 查询订单物流轨迹
	 * @throws Exception 
     */
	public static String getOrderTracesByXml(String shipperCode, String logisticCode) throws Exception{
		String requestData= "<?xml version=\"1.0\" encoding=\"utf-8\" ?>"+
							"<Content>"+
							"<OrderCode></OrderCode>"+
							"<ShipperCode>"+shipperCode+"</ShipperCode>"+
							"<LogisticCode>"+logisticCode+"</LogisticCode>"+
							"</Content>";
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("RequestData", urlEncoder(requestData, "UTF-8"));
		params.put("EBusinessID", EBusinessID);
		params.put("RequestType", "1002");
		String dataSign=encrypt(requestData, AppKey, "UTF-8");
		params.put("DataSign", urlEncoder(dataSign, "UTF-8"));
		params.put("DataType", "1");
		
		String result=sendPost(ReqURL, params);	
		
		//根据公司业务处理返回的信息......
		
		return result;
	}
 
	/**
     * MD5加密
     * @param str 内容       
     * @param charset 编码方式
	 * @throws Exception 
     */
	private static String MD5(String str, String charset) throws Exception {
	    MessageDigest md = MessageDigest.getInstance("MD5");
	    md.update(str.getBytes(charset));
	    byte[] result = md.digest();
	    StringBuffer sb = new StringBuffer(32);
	    for (int i = 0; i < result.length; i++) {
	        int val = result[i] & 0xff;
	        if (val <= 0xf) {
	            sb.append("0");
	        }
	        sb.append(Integer.toHexString(val));
	    }
	    return sb.toString().toLowerCase();
	}
	
	/**
     * base64编码
     * @param str 内容       
     * @param charset 编码方式
	 * @throws UnsupportedEncodingException 
     */
	private static String base64(String str, String charset) throws UnsupportedEncodingException{
		String encoded = Base64.encode(str.getBytes(charset));
		return encoded;    
	}	
	
	private static String urlEncoder(String str, String charset) throws UnsupportedEncodingException{
		String result = URLEncoder.encode(str, charset);
		return result;
	}
	
	/**
     * 电商Sign签名生成
     * @param content 内容   
     * @param keyValue Appkey  
     * @param charset 编码方式
	 * @throws UnsupportedEncodingException ,Exception
	 * @return DataSign签名
     */
	private static String encrypt (String content, String keyValue, String charset) throws UnsupportedEncodingException, Exception
	{
		if (keyValue != null)
		{
			return base64(MD5(content + keyValue, charset), charset);
		}
		return base64(MD5(content, charset), charset);
	}
	
	 /**
     * 向指定 URL 发送POST方法的请求     
     * @param url 发送请求的 URL    
     * @param params 请求的参数集合     
     * @return 远程资源的响应结果
     */
	private static String sendPost(String url, Map<String, String> params) {
        OutputStreamWriter out = null;
        BufferedReader in = null;        
        StringBuilder result = new StringBuilder(); 
        try {
            URL realUrl = new URL(url);
            HttpURLConnection conn =(HttpURLConnection) realUrl.openConnection();
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // POST方法
            conn.setRequestMethod("POST");
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.connect();
            // 获取URLConnection对象对应的输出流
            out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            // 发送请求参数            
            if (params != null) {
		          StringBuilder param = new StringBuilder(); 
		          for (Map.Entry<String, String> entry : params.entrySet()) {
		        	  if(param.length()>0){
		        		  param.append("&");
		        	  }	        	  
		        	  param.append(entry.getKey());
		        	  param.append("=");
		        	  param.append(entry.getValue());		        	  
		        	  
		          }
		          out.write(param.toString());
            }
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {            
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result.toString();
    }
}
