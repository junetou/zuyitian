package org.andy.work.appserver.model.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import org.andy.work.appserver.model.IUserTest;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


@Entity
@Table(name="usertest")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region="org.andy.work.appserver.model.impl.UserTest")
public class UserTest implements IUserTest{
	
	private int id;
	
	private String name;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	@Version
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name="usr_name", length=30, unique=true, nullable=false)
	public String getUsername() {
		return name;
	}
	public void setUsername(String name) {
		this.name = name;
	}

}
