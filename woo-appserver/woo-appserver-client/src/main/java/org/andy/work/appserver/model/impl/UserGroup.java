package org.andy.work.appserver.model.impl;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.andy.work.appserver.model.IUserGroup;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 用户组
 * @author Administrator
 *
 */
@Entity
@Table(name="usr_group")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region="org.andy.work.appserver.model.impl.UserGroup")
public class UserGroup implements Serializable, IUserGroup {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8218694730156465943L;
	private Integer id;
	private String name;//名称
	private String role;//权限
	private String permission;//编辑菜单

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	@Column(length=30, nullable=false, unique=true)
	public String getName() {
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	@Column(name="r_typ", length=120)
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Column(name="perm", length=3000)
	public String getPermission() {
		return permission;
	}
	public void setPermission(String permission) {
		this.permission = permission;
	}
}
