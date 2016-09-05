package org.andy.work.appserver.model;

public interface IComment {

	public void setcommentid(Integer commentid);
	public void setcomment(String comment);
	public void setthingsid(Integer thingid);
	public void settime(String time);
	public void seteverythingnumber(Integer everythingnumber);
	
	
	public Integer getcommentid();
	public String getcomment();
	public Integer getthingsid();
	public String gettime();
	public Integer geteverythingnumber();
	
}
