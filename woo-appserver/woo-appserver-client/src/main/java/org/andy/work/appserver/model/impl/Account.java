package org.andy.work.appserver.model.impl;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.andy.work.appserver.model.IAccount;
import org.andy.work.appserver.model.IAccountBalance;
import org.andy.work.appserver.model.IAccountSecurity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * Web 用户
 * @author Administrator
 *
 */
@Entity
@Table(name="account")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region="org.andy.work.appserver.model.impl.Account")
public class Account implements Serializable, IAccount {
	
	private static final long serialVersionUID = -6057172194299595000L;

	private Integer id;
	private String username;						//用户名
	private String locked;							//是否锁定 Y 锁定
	private String status;							//状态 见 UserDepartState
	private Integer loginedCount;					//登录次数
	private Integer loginFailCount;					//登录失败的次数
	
	private String openId;							//微信用户的 OpenId
	private String kfOpenId;						//开放平台的OpenId
	private String nickname;						//微信用户的昵称
	private String iconHead;						//微信用户的头像
	private String unionID;							//微信用户的 UnionID
	private Integer sex;							//微信性别	SexType
	private String acctType;						//分销类型 见 DistributorState
	private Date birthday;							//出生日期
	
	private Date lastLoginFailDate;					//最后登录失败日期
	private Date lastLoginedDate;					//最后登录日期
	private Date lastUpdatedDate;					//最后更新日期
	private Date createdDate;						//创建日期
	private IAccountSecurity accountSecurity;		//帐户安全信息
	private IAccountBalance accountBalance;			//帐户余额
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId()
	{
		return id;
	}
	public void setId(Integer id)
	{
		this.id = id;
	}
	
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	
	@Column(name="acc_usr_name", length=50, nullable=false, unique=true)
	public String getUsername()
	{
		return username;
	}
	public void setUsername(String username)
	{
		this.username = username;
	}
	@Column(name="acc_locked", length=2)
	public String getLocked()
	{
		return locked;
	}
	public void setLocked(String locked)
	{
		this.locked = locked;
	}

	@Column(name="acc_stat", nullable=false, length=10)
	public String getStatus()
	{
		return status;
	}
	public void setStatus(String status)
	{
		this.status = status;
	}
	@Column(name="logined_cnt")
	public Integer getLoginedCount()
	{
		return loginedCount;
	}
	public void setLoginedCount(Integer loginedCount)
	{
		this.loginedCount = loginedCount;
	}
	@Column(name="last_logined_date")
	public Date getLastLoginedDate()
	{
		return lastLoginedDate;
	}
	public void setLastLoginedDate(Date lastLoginedDate)
	{
		this.lastLoginedDate = lastLoginedDate;
	}
	@Column(name="lfail_count")
	public Integer getLoginFailCount()
	{
		return loginFailCount;
	}
	public void setLoginFailCount(Integer loginFailCount)
	{
		this.loginFailCount = loginFailCount;
	}
	
	@Column(length=50, unique=true)
	public String getKfOpenId() {
		return kfOpenId;
	}
	public void setKfOpenId(String kfOpenId) {
		this.kfOpenId = kfOpenId;
	}
	@Column(name="lastlfail_date")
	public Date getLastLoginFailDate()
	{
		return lastLoginFailDate;
	}
	public void setLastLoginFailDate(Date lastLoginFailDate)
	{
		this.lastLoginFailDate = lastLoginFailDate;
	}
	@Column(name="lastupdate_date")
	public Date getLastUpdatedDate()
	{
		return lastUpdatedDate;
	}
	public void setLastUpdatedDate(Date lastUpdatedDate)
	{
		this.lastUpdatedDate = lastUpdatedDate;
	}
	@Column(name="crtdate", nullable=false)
	public Date getCreatedDate()
	{
		return createdDate;
	}
	public void setCreatedDate(Date createdDate)
	{
		this.createdDate = createdDate;
	}
	
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	@OneToOne(cascade={CascadeType.ALL}, optional=false, fetch=FetchType.EAGER, targetEntity=AccountSecurity.class)
	@JoinColumn(name="sec_id")
	public IAccountSecurity getAccountSecurity()
	{
		return accountSecurity;
	}
	public void setAccountSecurity(IAccountSecurity accountSecurity)
	{
		this.accountSecurity = accountSecurity;
	}
	@OneToOne(cascade={CascadeType.ALL}, optional=false, fetch=FetchType.EAGER, targetEntity=AccountBalance.class)
	@JoinColumn(name="bal_id")
	public IAccountBalance getAccountBalance()
	{
		return accountBalance;
	}
	public void setAccountBalance(IAccountBalance accountBalance)
	{
		this.accountBalance = accountBalance;
	}
	@Column(name="openid", length=50, unique=true)
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	@Column(name="nick_name", length=50, nullable=false)
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	@Column(name="icon_head", columnDefinition="TEXT")
	public String getIconHead() {
		return iconHead;
	}
	public void setIconHead(String iconHead) {
		this.iconHead = iconHead;
	}
	@Column(name="unionid", length=50)
	public String getUnionID() {
		return unionID;
	}
	public void setUnionID(String unionID) {
		this.unionID = unionID;
	}
	@Column(name="acct_type", length=10)
	public String getAcctType() {
		return acctType;
	}
	public void setAcctType(String acctType) {
		this.acctType = acctType;
	}

}
