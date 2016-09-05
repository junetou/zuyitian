package org.andy.work.appserver.model;

import java.util.Date;

public interface IDetailmessage {

	public void setthingsId(Integer thingsId);
	public void setName(String name);
	public void setthingsDesc(String desc);
	public void setPrice(Double price);
	public void setDate(String date);
    public void setthingsLng(Double lng);
    public void setthingsLat(Double lat);
    public void setkind(String kind);
    public void setnumber(Integer number);
    public void setphone(String phone);
    public void setaddr(String addr);
    public void setoveranalyzed(Integer overanalyzed);
    public void setpicname(String picname);
    
    
    public Integer getthingsId();
    public String getName();
    public String getthingsDesc();
    public Double getPrice();
    public String getDate();
    public Double getthingsLng();
    public Double getthingsLat();
    public String getkind();
    public Integer getnumber();
    public String getaddr();
    public String getphone();
    public Integer getoveranalyzed();
    public String getpicname();
}
