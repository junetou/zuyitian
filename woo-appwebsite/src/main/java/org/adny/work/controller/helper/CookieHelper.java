package org.adny.work.controller.helper;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

@Component
public class CookieHelper
{
	public String getCookieValue(HttpServletRequest request, String cookieName)
	{
		Cookie cookie = getCookie(request, cookieName);
		if (cookie != null)
		{
			try
			{
				String cookieValue = URLDecoder.decode(cookie.getValue(), "UTF-8");
				return cookieValue;
			}
			catch (UnsupportedEncodingException e)
			{
				e.printStackTrace();
			}
		}
		
		return null;
	}
	
	public Cookie getCookie(HttpServletRequest request, String cookieName)
	{
		Cookie[] cookies = request.getCookies();
		if (cookies != null && cookies.length > 0)
		{
			for (int i = 0; i < cookies.length; i++)
			{
				Cookie cookie = cookies[i];
				if (!isCookieExpired(cookie) && cookie.getName().equalsIgnoreCase(cookieName))
				{
					return cookie;
				}
			}
		}
		
		return null;
	}
	
	private boolean isCookieExpired(Cookie cookie)
	{
		return cookie == null || (cookie != null && cookie.getMaxAge() == 0);
	}
}
