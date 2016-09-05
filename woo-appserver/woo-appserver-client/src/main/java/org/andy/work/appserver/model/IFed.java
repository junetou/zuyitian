package org.andy.work.appserver.model;

public interface IFed {

	public void setFedid(Integer fedid);
	public void setThingid(Integer thingid);
	public void setDesc(String desc);
	public void setName(String names);
	
	public Integer getFedid();
	public Integer getThingid();
	public String getDesc();
	public String getName();
	
}
