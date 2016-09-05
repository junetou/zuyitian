package org.andy.work.appserver.model.impl;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.andy.work.appserver.model.IAccountSecurity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 帐户安全信息
 * @author Administrator
 *
 */
@Entity
@Table(name="account_security")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region="org.andy.work.appserver.model.impl.AccountSecurity")
public class AccountSecurity implements Serializable, IAccountSecurity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -64284812902461682L;
	private Integer id;
	private String securityQuestion; //安全问题
	private String securityAnswer;	//安全回答
	private String securityPassword; //支付口令
	private String accountPassword;	//帐户密码
	private String salt;
	private String securityCode; //验证码
	private String phone; // 电话号码
	private String cellphone; // 手机号码
	private String authCellphone; // 手机认证标记， Y：已认证
	private String email; 	//电子邮件
	private String authEmail; // 邮箱认证标记， Y：已认证
	private String idCardNum; // 实名认证身份证号码
	private String realName; // 实名认证姓名
	private String authRealName; //实名认证标记， Y：已认证
	private Date authRealNameDate; // 实名认证日期
	private Date expirationDate; // 验证码失效时间
	private Date authorizationDate;//授权书的有效日期
	
	private String wecharNum;						//微信号码
	private String qqNum;							//qq号码
	
	private Date createdDate;
	private Date updatedDate;

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
	@Column(name="sec_question", length=120)
	public String getSecurityQuestion()
	{
		return securityQuestion;
	}
	public void setSecurityQuestion(String securityQuestion)
	{
		this.securityQuestion = securityQuestion;
	}

	@Column(name="sec_answer", length=60)
	public String getSecurityAnswer()
	{
		return securityAnswer;
	}
	public void setSecurityAnswer(String securityAnswer)
	{
		this.securityAnswer = securityAnswer;
	}

	@Column(name="sec_pwd", length=60)
	public String getSecurityPassword()
	{
		return securityPassword;
	}
	public void setSecurityPassword(String securityPassword)
	{
		this.securityPassword = securityPassword;
	}
	@Column(name="acc_pwd", length=60, nullable=false)
	public String getAccountPassword()
	{
		return accountPassword;
	}
	public void setAccountPassword(String accountPassword)
	{
		this.accountPassword = accountPassword;
	}
	@Column(name="wechar_num", length=60)
	public String getWecharNum() {
		return wecharNum;
	}
	public void setWecharNum(String wecharNum) {
		this.wecharNum = wecharNum;
	}
	@Column(name="qq_num", length=60)
	public String getQqNum() {
		return qqNum;
	}
	public void setQqNum(String qqNum) {
		this.qqNum = qqNum;
	}
	
	@Column(name="sec_salt", length=30)
	public String getSalt()
	{
		return salt;
	}
	public void setSalt(String salt)
	{
		this.salt = salt;
	}
	
	@Column(name="sec_code", length=60)
	public String getSecurityCode()
	{
		return securityCode;
	}
	public void setSecurityCode(String securityCode)
	{
		this.securityCode = securityCode;
	}
	@Column(name="phone", length=30)
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Column(name="cellphone", length=11)
	public String getCellphone()
	{
		return cellphone;
	}
	public void setCellphone(String cellphone)
	{
		this.cellphone = cellphone;
	}
	@Column(name="email", length=30)
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
	@Column(name="authen_mobile", length=2)
	public String getAuthCellphone()
	{
		return authCellphone;
	}
	public void setAuthCellphone(String authCellphone)
	{
		this.authCellphone = authCellphone;
	}
	@Column(name="authen_email", length=2)
	public String getAuthEmail()
	{
		return authEmail;
	}
	public void setAuthEmail(String authEmail)
	{
		this.authEmail = authEmail;
	}
	@Column(name="idc_num", length=20)
	public String getIdCardNum()
	{
		return idCardNum;
	}
	public void setIdCardNum(String idCardNum)
	{
		this.idCardNum = idCardNum;
	}
	@Column(name="real_name", length=60)
	public String getRealName()
	{
		return realName;
	}
	public void setRealName(String realName)
	{
		this.realName = realName;
	}
	@Column(name="auth_name", length=2)
	public String getAuthRealName()
	{
		return authRealName;
	}
	public void setAuthRealName(String authRealName)
	{
		this.authRealName = authRealName;
	}
	@Column(name="auth_rndate")
	public Date getAuthRealNameDate()
	{
		return authRealNameDate;
	}
	public void setAuthRealNameDate(Date authRealNameDate)
	{
		this.authRealNameDate = authRealNameDate;
	}
	@Column(name="exp_date")
	public Date getExpirationDate()
	{
		return expirationDate;
	}
	public void setExpirationDate(Date expirationDate)
	{
		this.expirationDate = expirationDate;
	}

	@Column(name="crt_date", nullable=false)
	public Date getCreatedDate()
	{
		return createdDate;
	}
	public void setCreatedDate(Date createdDate)
	{
		this.createdDate = createdDate;
	}

	@Column(name="upt_date")
	public Date getUpdatedDate()
	{
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate)
	{
		this.updatedDate = updatedDate;
	}
	@Column(name="authorization_date")
	public Date getAuthorizationDate() {
		return authorizationDate;
	}
	public void setAuthorizationDate(Date authorizationDate) {
		this.authorizationDate = authorizationDate;
	}
}
