package org.andy.work.appserver.model;

public interface IUserGroup {
	
	abstract Integer getId();

	abstract void setId(Integer id);

	abstract String getName();

	abstract void setName(String name);
	
	String getRole();
	
	void setRole(String role);

	abstract String getPermission();

	abstract void setPermission(String permission);

}
