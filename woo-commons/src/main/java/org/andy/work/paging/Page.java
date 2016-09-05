package org.andy.work.paging;

import java.io.Serializable;

/**
 *
 *developer
 *2015年2月3日下午5:26:21
 *
 */
public class Page implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3808530048711345490L;
	private String num;
	private boolean active;
	public String getNum()
	{
		return num;
	}
	public void setNum(String num)
	{
		this.num = num;
	}
	public boolean isActive()
	{
		return active;
	}
	public void setActive(boolean active)
	{
		this.active = active;
	}
}
