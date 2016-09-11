package org.andy.work.appserver.model;

public interface INeed {

	public void setNeed(Integer needid);
	public void setDescs(String desc);
	public void setName(String name);
	public void setAddr(String addr);
	public void setPrice(Double price);
	public void setNumber(Integer number); //物品发布主人
	public void setPhone(String phone);
	public void setDate(String date);
	public void setKind(String kind);
	public void setOveranalyzed(Integer overanalyzed);
    public void setLng(Double lng);
    public void setLat(Double lat);
    
	public Integer getNeed();
	public String getDescs();
	public String getName();
	public String getAddr();
	public Double getPrice();
	public Integer getNumber(); //物品发布主人
	public String getPhone();
	public String getDate();
	public String getKind();
	public Integer getOveranalyzed();
    public Double getLng();
    public Double getLat();
    
	
	
}
