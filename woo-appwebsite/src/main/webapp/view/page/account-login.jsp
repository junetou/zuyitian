<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@	taglib prefix="woo" uri="/WEB-INF/classes/woo.tld" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<style>
.hxsj_login_name{
	margin-top: 30px
}
.hxsj_login_name p{
	margin: 0 0 25px 80px; 
	font-size: 18px;
	color: orange
}
.hxsj_login_name .icon-checkmark{
	color: green
}
.hxsj_login_name .icon-cancel-circle{
	color: red;
	
}
.login-error{text-align: center;line-height: 35px}
.findPwd{margin-top: 15px}
.findPwd a{text-decoration: underline;color:#e60012}
</style>

<div class="container">
	<div class="col-lg-8 col-lg-offset-2">
		<form id="loginForm" action="<c:url value='/member/login/check'/>" method="post">
			<div class="row">
				<div class="form-group">
					<span class="new_user">登录</span>    
					<span class="new_account">还没有帐号？去 <a href="<c:url value='/member/reg.htm'/>">注册</a>  > </span>
				</div>
				<c:if test="${not empty requestScope.errorMessage}">
					<p class="login-error">${requestScope.errorMessage.message}</p>
				</c:if>
				<c:if test="${ACC_ACTIVATE}">
					<p class="login-error">您的账号已激活成功！</P>
				</c:if>
				<div class="form-group">
					<label>账户名：</label>
					<input id="accName" class="form-control" name="l_name" placeholder="邮箱地址" style="width: 280px"/>
					<p class="help-block"></p> 
		       	</div>
		   		<div class="form-group">
		  			<label>密码：</label>
					<input id="accPwd" class="form-control" name="l_pwd" type="password" style="width: 280px"/>
					<p class="help-block"></p> 
		        </div>
		        <div class="form-group">
		  			<label>验证码：</label>
					<input id="verifyCode" maxlength="4" class="form-control" name="verifyCode" style="width:100px"/>
					<span class=""><img id="verifyCodeImg" src="/secure/verify_code"/></span>
					<p class="help-block"></p> 
		        </div>
				<div class="form-group">
		            <button type="submit" class="btn btn-primary" name="signup" value="登 录">登 录</button>
		            <div class="col-lg-9 col-lg-offset-3">忘记登录密码？点击<a href="/retrieve/reset.htm">找回密码</a></div>
		        </div>
			</div>
		</form>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function() {
	
    $('#loginForm').bootstrapValidator({
        message: '此值无效',
        focusInvalid:false,
        submitHandler: function(validator, form, submitButton) {
        	validator.defaultSubmit();
        	$('.btn-primary').text('登陆中，请稍后...');
        },
        fields: {
        	l_name: {
                validators: {
                	notEmpty: {
                        message: '账户名不能为空，请输入'
                    },
                    emailAddress: {
                        message: '请输入正确的邮箱地址'
                    }
                }
            },
            l_pwd: {
                validators: {
                	notEmpty: {
                        message: '登陆密码不能为空，请输入'
                    }
                }
            },
            verifyCode: {
                validators: {
                    notEmpty: {
                        message: '验证码不能为空，请输入'
                    },
		            stringLength: {
		                min: 4,
		                max: 4,
		                message: '验证码必须4个字符'
		            },
                    remote: {
                        message: '验证码不正确',
                        url: '<c:url value="/secure/verify_code/check"/>',
                        data:{
                        	code: function() {
                     			return $("#verifyCode").val();
                    		}
                        }
                    }
                }
            }
        }
    });
});
</script>
<script type="text/javascript">
<!--
$('#refreshVerifyCode').on('click', function(){
	$('#verifyCodeImg').attr('src',"/secure/verify_code?t="+Math.random());
});
//-->
</script>
