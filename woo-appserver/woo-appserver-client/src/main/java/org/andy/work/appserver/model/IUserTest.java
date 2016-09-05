package org.andy.work.appserver.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

public interface IUserTest {

	public Integer getId();
	
	public void setId(Integer id);

	public String getUsername() ;
	
	public void setUsername(String name) ;
	
}
