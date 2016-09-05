package org.andy.work.tag;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.andy.work.utils.Config;
import org.apache.log4j.Logger;

public class WooTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger log = Logger.getLogger(WooTag.class);
	private static final String projectName = "/" + Config.get("static.project.name");
	private String secure;
	private String value;
	
	@Override
	public int doStartTag() throws JspException
	{
		StringBuffer sb = new StringBuffer();
		if ("true".equals(this.secure))
		{
			sb.append("https://");
		}
		else
		{
			sb.append("http://");
		}
		
		HttpServletRequest request = (HttpServletRequest) super.pageContext.getRequest();
		String serverName = request.getServerName();
		int serverPort = request.getServerPort();
		sb.append(serverName);
		if (80 != serverPort)
		{
			sb.append(":" + serverPort);
		}

		sb.append(projectName).append(this.value);
		
		try
		{
			super.pageContext.getOut().write(sb.toString());
		}
		catch (IOException e)
		{
			log.error(e);
		}
		
		return SKIP_BODY;
	}
	
	public void setSecure(String secure)
	{
		this.secure = secure;
	}
	public void setValue(String value)
	{
		this.value = value;
	}
	
}
