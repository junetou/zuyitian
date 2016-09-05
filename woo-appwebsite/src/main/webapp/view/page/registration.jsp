<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@	taglib prefix="woo" uri="/WEB-INF/classes/woo.tld" %>
<%@ taglib prefix='security' uri='http://www.springframework.org/security/tags' %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<style>
.hxsj_register_name{
	margin-top: 25px;
}
</style>
<security:authorize ifAnyGranted="ROLE_B2C,ROLE_B2B">
	<script>
		location.href="<c:url value='/index.htm'/>"
	</script>
</security:authorize>

<div class="container">
	<div class="col-lg-8 col-lg-offset-2">
		<form id="reg-form" method="post">
			<div class="row">
				<div class="form-group">
					<span class="new_user">注册新用户</span>    
					<span class="new_account">已有帐号？去 <a href="<c:url value='/member/login.htm'/>">登录</a>  > </span>
				</div>
				<div class="form-group">
					<label>注册邮箱：</label>
					<input class="form-control" name="accName" id="accName" type="text" style="width:280px"/>
					<p class="help-block">登录账号就是注册的邮箱地址</p> 
		       	</div>
		   		<div class="form-group">
		  			<label>用户姓名：</label>
					<input class="form-control" name="custName" type="text" style="width: 280px"/>
					<p class="help-block"></p> 
		        </div>
		        <div class="form-group">
		  			<label>登录密码：</label>
					<input class="form-control" name="pwd" type="password" style="width: 280px"/>
					<p class="help-block">6~20位字符，必须包含数字和字母</p> 
		        </div>
		        <div class="form-group">
		  			<label>确认密码：</label>
					<input class="form-control" name="confirmPassword" type="password" style="width: 280px"/>
					<p class="help-block"></p> 
		        </div>
		        
		        <div class="form-group">
		  			<label>验证码：</label>
					<input id="verifyCode" maxlength="4" class="form-control" name="verifyCode" style="width:100px"/>
					<span class=""><img id="verifyCodeImg" src="/secure/verify_code"/></span>
					<a id="refreshVerifyCode" href="javascript:void(0)">看不清，换一张</a>
					<p class="help-block"></p> 
		        </div>
				<div class="form-group">
					<div>
						<input id="regAgreeTerm" class="ui-checkbox" name="policy" type="checkbox" checked="checked"/>
						我已阅读并且接收<a href="/static/private-term.htm" target="_blank">《用户协议》</a>
						<span class="ui-form-explain"></span>
					</div>
		            <button type="submit" class="btn btn-primary" name="signup" value="登 录">登 录</button>
		        </div>
			</div>
		</form>
	</div>
</div>

<script type="text/javascript">
$('#refreshVerifyCode').on('click', function(){
	$('#verifyCodeImg').attr('src',"/secure/verify_code?t="+Math.random());
});
$(document).ready(function() {
	
    $('#reg-form').bootstrapValidator({
        message: '此值无效',
        focusInvalid:false,
        submitHandler: function(form) {
        	var datas = $("#reg-form").serializeArray();
        	$('.btn-primary').text('正在提交，请稍后...');
         	$.post("<c:url value='/member/reg/regAcc'/>", datas, function(resp){
         		if (resp.r == 1){
        			setTimeout(function(){
        				location.href='/member/reg/success.html?k=' + resp.obj;
        			}, 1500); 
        		} else {
        			$.dialog({
         				title: "提示",
         			    content: "连接超时，请重试"
         			});
        		}
         	}, "json");
        	
        },
        fields: {
        	accName: {
                validators: {
                	notEmpty: {
                        message: '账户名不能为空，请输入'
                    },
                    emailAddress: {
                        message: '请输入正确的邮箱地址'
                    },
                    remote: {
                        message: '该邮箱已存在',
                        url: '<c:url value="/member/reg/accCheck"/>',
                        data:{
                        	regName: function() {
                     			return $("#accName").val();
                    		}
                        }
                    }
                }
            },
            custName: {
                validators: {
                	notEmpty: {
                        message: '用户姓名不能为空，请输入'
                    }
                }
            },
            pwd: {
                validators: {
                	notEmpty: {
                        message: '登录密码不能为空，请输入'
                    },
                    regexp: {
                        regexp: /^(?=.*?[a-zA-Z])(?=.*?[0-6])[!"#$%&'()*+,\-./:;<=>?@\[\\\]^_`{|}~A-Za-z0-9]{8,16}$/,
                        message: '密码至少同时包含数字和字母，且长度在8-16位之间'
                    }
                }
            },
            confirmPassword: {
                validators: {
                	notEmpty: {
                        message: '确认密码不能为空，请输入'
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