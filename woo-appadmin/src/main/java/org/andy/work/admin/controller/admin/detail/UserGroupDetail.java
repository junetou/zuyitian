package org.andy.work.admin.controller.admin.detail;

public class UserGroupDetail {
	
	private Integer id;
	private String gname;
	private String[] opts;
	private String usrs;
	
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
	public String getUsrs() {
		return usrs;
	}
	public void setUsrs(String usrs) {
		this.usrs = usrs;
	}
}
