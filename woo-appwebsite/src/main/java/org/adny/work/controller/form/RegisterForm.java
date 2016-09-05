package org.adny.work.controller.form;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class RegisterForm {
	
	private String custName;
	@NotEmpty
	private String accName;
	@NotEmpty
	@Size(min=8)
	private String pwd;
	@NotEmpty
	private String policy;
	
	
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getAccName() {
		return accName;
	}
	public void setAccName(String accName) {
		this.accName = accName;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getPolicy() {
		return policy;
	}
	public void setPolicy(String policy) {
		this.policy = policy;
	}
	
}
