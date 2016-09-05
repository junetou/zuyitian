package org.andy.work.admin.controller.admin.detail;

public class UserDetail {
	
	private Integer id;
	private String dname;
	private String depart;
	private String group;
	private String staffno;
	private String locked;
	private String account;
	private String pwd;
	private String last;
	private boolean lock;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public String getDepart() {
		return depart;
	}
	public void setDepart(String depart) {
		this.depart = depart;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getStaffno() {
		return staffno;
	}
	public void setStaffno(String staffno) {
		this.staffno = staffno;
	}
	public String getLocked() {
		return locked;
	}
	public void setLocked(String locked) {
		this.locked = locked;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getLast() {
		return last;
	}
	public void setLast(String last) {
		this.last = last;
	}
	public boolean isLock() {
		return lock;
	}
	public void setLock(boolean isLock) {
		this.lock = isLock;
	}
	
}
