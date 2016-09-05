package org.andy.work.admin.controller.admin.detail;

public class MessageDetail {

	private Integer thingsId;
	private String thingsdesc;
	private String thingsname;
	private String thingsprice;
	private String thingsdate;
	private String thingskind;
	public Integer thingsoveranalyzed;
	public Integer getThingsId() {
		return thingsId;
	}
	public void setThingsId(Integer thingsId) {
		this.thingsId = thingsId;
	}
	public String getThingsdesc() {
		return thingsdesc;
	}
	public void setThingsdesc(String thingsdesc) {
		this.thingsdesc = thingsdesc;
	}
	public String getThingsname() {
		return thingsname;
	}
	public void setThingsname(String thingsname) {
		this.thingsname = thingsname;
	}
	public String getThingsprice() {
		return thingsprice;
	}
	public void setThingsprice(String thingsprice) {
		this.thingsprice = thingsprice;
	}
	public String getThingsdate() {
		return thingsdate;
	}
	public void setThingsdate(String thingsdate) {
		this.thingsdate = thingsdate;
	}
	public void setThingskind(String kind){
		this.thingskind=kind;
	}
	public String getThingskind(){
		return this.thingskind;
	}
   
	public void setThingsoveranalyzed(Integer thingsoveranalyzed){
		this.thingsoveranalyzed=thingsoveranalyzed;
	}
	public Integer getThingsoveranalyzed(){
		return this.thingsoveranalyzed;
	}
	
}
