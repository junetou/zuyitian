package org.andy.work.appserver.model.impl;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import org.andy.work.appserver.model.IUser;
import org.andy.work.appserver.model.IUserGroup;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 管理员
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="usr")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region="org.andy.work.appserver.model.impl.User")
public class User implements Serializable, IUser {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3777655431317415578L;
	private Integer id;
	private Integer version;
	private String username;
	private String displayName;
	private String staffNum;
	private String depart;
	private String mobile;
	private String phone;
	private String faxNum;
	private String email;
	private String address;
	private String weixin;
	private String qqNum;
	private String remark;
	private String password;
	private Integer failLoginCount;
	private Date failLoginTime;
	private String locked;
	private Date lastLogin;
	private Date createdDate;
	private String ext1;
	private String ext2;
	private String ext3;
	private Integer userType; // See UserType class
	private IUserGroup userGroup;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	@Version
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	@Column(name="usr_name", length=30, unique=true, nullable=false)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Column(name="dis_name", length=30, nullable=false)
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	@Column(name="staffno", length=30, nullable=false)
	public String getStaffNum() {
		return staffNum;
	}
	public void setStaffNum(String staffNum) {
		this.staffNum = staffNum;
	}
	@Column(name="depart", length=30)
	public String getDepart() {
		return depart;
	}
	public void setDepart(String depart) {
		this.depart = depart;
	}
	@Column(length=60, nullable=false)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Column(name="faillogincount")
	public Integer getFailLoginCount() {
		return failLoginCount;
	}
	public void setFailLoginCount(Integer failLoginCount) {
		this.failLoginCount = failLoginCount;
	}
	@Column(name="failLogintime")
	public Date getFailLoginTime() {
		return failLoginTime;
	}
	public void setFailLoginTime(Date failLoginTime) {
		this.failLoginTime = failLoginTime;
	}
	@Column(length=2)
	public String getLocked() {
		return locked;
	}
	public void setLocked(String locked) {
		this.locked = locked;
	}
	@Column(name="last")
	public Date getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}
	@Column(name="crtdate", nullable=false)
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	@Column(name="mobile", length=30)
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	@Column(name="phone", length=30)
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Column(name="faxno", length=30)
	public String getFaxNum() {
		return faxNum;
	}
	public void setFaxNum(String faxNum) {
		this.faxNum = faxNum;
	}
	@Column(name="email", length=60)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Column(name="addr", length=250)
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Column(name="weixin", length=20)
	public String getWeixin() {
		return weixin;
	}
	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}
	@Column(name="qqno", length=25)
	public String getQqNum() {
		return qqNum;
	}
	public void setQqNum(String qqNum) {
		this.qqNum = qqNum;
	}
	@Column(name="remark", length=500)
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Column(name="ext1", length=60)
	public String getExt1() {
		return ext1;
	}
	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}
	@Column(name="ext2", length=60)
	public String getExt2() {
		return ext2;
	}
	public void setExt2(String ext2) {
		this.ext2 = ext2;
	}
	@Column(name="ext3", length=60)
	public String getExt3() {
		return ext3;
	}
	public void setExt3(String ext3) {
		this.ext3 = ext3;
	}
	@Column(name="usr_type")
	public Integer getUserType() {
		return userType;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	
	@ManyToOne(fetch=FetchType.EAGER, targetEntity=UserGroup.class)
	@JoinColumn(name="group_id")
	public IUserGroup getUserGroup() {
		return userGroup;
	}
	public void setUserGroup(IUserGroup userGroup) {
		this.userGroup = userGroup;
	}
}
