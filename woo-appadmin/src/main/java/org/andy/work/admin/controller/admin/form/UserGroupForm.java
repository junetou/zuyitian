package org.andy.work.admin.controller.admin.form;

public class UserGroupForm {
	
	private Integer id;
	private String gname;
	private String[] opts;
	private String[] roles;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getGname() {
		return gname;
	}
	public void setGname(String gname) {
		this.gname = gname;
	}
	public String[] getOpts() {
		return opts;
	}
	public void setOpts(String[] opts) {
		this.opts = opts;
	}
	public String[] getRoles() {
		return roles;
	}
	public void setRoles(String[] roles) {
		this.roles = roles;
	}
	
}
