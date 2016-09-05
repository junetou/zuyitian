package org.andy.work.utils;

import java.io.Serializable;

public class AjaxResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String n;
	private String r;
	private String s;
	private int code;
	private String msgs;
	private Object obj;
	

	public AjaxResponse(String n, String r, String s, String msgs)
	{
		this.n = n;
		this.r = r;
		this.s = s;
		this.msgs = msgs;
	}
	
	public AjaxResponse(String msg)
	{
		this(null, msg);
	}
	
	public AjaxResponse(int code)
	{
		this.code = code;
	}
	
	public AjaxResponse(String r, String msg)
	{
		this.r = r;
		this.msgs = msg;
	}
	

	public AjaxResponse()
	{
	
	}
	
	public String getR()
	{
		return r;
	}
	public void setR(String r)
	{
		this.r = r;
	}
	
	public String getMsgs()
	{
		return msgs;
	}

	public void setMsgs(String msgs)
	{
		this.msgs = msgs;
	}
	
	public int getCode()
	{
		return code;
	}

	public void setCode(int code)
	{
		this.code = code;
	}
	
	public String getN() {
		return n;
	}

	public void setN(String n) {
		this.n = n;
	}

	public String getS() {
		return s;
	}

	public void setS(String s) {
		this.s = s;
	}

	public Object getObj()
	{
		return obj;
	}

	public void setObj(Object obj)
	{
		this.obj = obj;
	}

	public static AjaxResponse success(String msg)
	{
		return new AjaxResponse("1", msg);
	}
	
	public static AjaxResponse success()
	{
		return new AjaxResponse("1", null);
	}
	
	public static AjaxResponse fail(String msg)
	{
		return new AjaxResponse(msg);
	}
	
	public static AjaxResponse fail(int code)
	{
		return new AjaxResponse(code);
	}
}
