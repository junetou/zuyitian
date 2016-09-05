<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@	taglib prefix="woo" uri="/WEB-INF/classes/woo.tld" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<style>
.hxsj_register h3{font-size: 18px}
.hxsj_register h4{margin: 25px 0 10px 0}
.icon-checkmark{color: green}
.hxsj_register_name{
	margin: 15px 0 0 30px;
	font-size: 14px
}
.hxsj_register a{
	text-decoration: underline;
	color: red;
}
</style>

<div class="hxsj_register">
	<div class="hxsj_register_left">
		<div class="hxsj_register_title">
			<h3><i class="icon-checkmark"></i> 账户注册成功！</h3>
		</div>
		<div class="hxsj_register_name">
			<p>感谢您注册南京试剂网站，系统已发送账户激活邮件至您注册的邮箱地址，请您在48小时内登录您的邮箱并点击激活链接完成账户激活。</p>
		
			<h4>如果没有收到激活邮件，怎么办？</h4>
			<p>1、看看是否在邮箱的回收站中、垃圾邮件中</p>
			<p>2、点击<a href="<c:url value='/member/reg.htm'/>">重新注册</a>，或使用其他邮箱地址注册</p>
			<p>3、如有其他问题，请&nbsp;<a href="#"><span>联系我们>></span></a></p>
		</div>
	</div>
	
</div>