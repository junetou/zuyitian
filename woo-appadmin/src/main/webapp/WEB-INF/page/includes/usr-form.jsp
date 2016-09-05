<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@	taglib uri="/WEB-INF/classes/woo.tld" prefix="woo"%>
<style>
.form-group {
    margin-bottom: 5px;
} 
.panel {
	margin-bottom:0;
}
</style>

<head>
    <link rel="stylesheet" href="http://cache.amap.com/lbs/static/main1119.css"/>
    <script src="http://cache.amap.com/lbs/static/es5.min.js"></script>
    <script src="http://webapi.amap.com/maps?v=1.3&key=3be8235f1cb10f423e1fdbdd0c9be773"></script>
    <title>租一天</title>
</head>

<div class="panel panel-default">
    <div class="panel-heading">
        <ol class="breadcrumb">
		  	<li><a href="#">首页 </a></li>
		  	<li><a href="#">用户管理</a></li>
		  	<li class="active">编辑 </li>
		</ol>
    </div>
    <!-- .panel-heading --> 
    <div class="panel-body">
		
		
		<form:form id="group-form" method="post">
			<div class="btn-group" role="group" style="margin-bottom:20px;">
				<a href="javascript:history.go(-1)" class="btn btn-outline btn-info">返回</a>
	    		<button type="submit" class="btn btn-outline btn-info">保 存</button>
			</div>
			<div class="row">
			
               	<div class="col-md-6">
					<div class="form-group">
		                <label>登录账号<sup>*</sup></label>
						<form:input cssClass="form-control" path="account"/>
						<p class="help-block">30个字以内,唯一</p> 
		            </div>
		           <div class="form-group">
		                <label for="depart" class="ui-label">部门<sup>*</sup></label>
		                <form:input class="form-control" path="depart"/>
		                <p class="help-block">30字符以内</p>
		            </div>
		            <div class="form-group">
		                <label>姓名<sup>*</sup></label>
						<form:input cssClass="form-control" path="dname"/>
						<p class="help-block">10个字以内，中文或英文</p> 
		            </div>
		            <div class="form-group">
		                <label>工号<sup>*</sup></label>
						<form:input cssClass="form-control" path="staffno"/>
						<p class="help-block">10个字以内，全数字</p> 
		            </div>
		            <div class="form-group">
		                <label>用户组<sup>*</sup></label>
		                <form:select path="group" cssClass="form-control">
							<option value="">--请选择--</option>
						 	<form:options items="${groups}" itemValue="id" itemLabel="name"/>
						</form:select>
						<p class="help-block">请选择用户组</p> 
		            </div>
		            <div class="form-group">
		                <label for="qqNum" class="ui-label">qq号码：</label>
		                <form:input class="form-control" path="qqNum"/>
		                <p class="help-block">25字符以内</p>
		            </div>
				</div>
				
               	<div class="col-md-6">
              
		             <c:if test="${command.account==null}">
						<div class="form-group">
							<label>密码<sup>*</sup></label>
							<form:input cssClass="form-control" path="pwd"/>
							<p class="help-block">密码是必需的</p> 
						</div>
					</c:if>
				  
		            <div class="form-group">
		                <label>Email<sup>*</sup></label>
		                <form:input cssClass="form-control" path="email"/>
		                <p class="help-block">请输入（例如：39809901@qq.com）</p>
		            </div>
               		<div class="form-group">
		                <label>手机号码</label>
		                <form:input cssClass="form-control" path="mobile"/>
		                <p class="help-block">请输入正确的手机号</p>
		            </div>
		            <div class="form-group">
		                <label>电话</label>
		                <form:input cssClass="form-control" path="phone"/>
		                <p class="help-block">请输入（例如：020-34839285）</p>
		            </div>
		            <div class="form-group">
		                <label>通讯地址</label>
		                <form:input class="form-control" path="address"/>
		                <p class="help-block">250字符以内</p>
		            </div>
		            
		            <div class="form-group">
		                <label>微信号</label>
		                <form:input class="form-control" path="weixin"/>
		                <p class="help-block">20字符以内</p> 
		            </div>
			
               	</div> 
               	
           	</div>
			
			 <div class="form-group">
	            <label for="remark" class="ui-label">备注：</label>
	            <form:textarea class="form-control" path="remark"/>
	            <p class="help-block">500字符以内</p>
	        </div>
			
			
			<form:hidden path="id"/>
			<div class="btn-group" role="group" style="margin-bottom:20px;">
                <button type="submit" class="btn btn-outline btn-info">保 存</button>
                <a href="javascript:history.go(-1)" class="btn btn-outline btn-info">返回</a>
            </div>
			
		</form:form>
		
    </div>
    <!-- .panel-body -->
</div>


<script type="text/javascript">
$(document).ready(function() {
    $('#group-form').bootstrapValidator({
        message: '此值无效',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        submitHandler: function(form) {
        	
        	var datas = $("#group-form").toJson();
        	var methet = "";
        	if(datas.id != null && datas.id != "") {
        		methet = "update";
        	} else {
        		methet = "add";
        	}
         	$.post("<c:url value='/portal/admin/admin-account/"+methet+"'/>", datas, function(resp){
         		if (resp.r == 1){
         			$.dialog({
         				title: "提示",
         			    content: "保存成功！"
         			});
        			setTimeout(function(){
        				self.location=document.referrer;
        			}, 1500); 
        		} else {
        			$.dialog({
         				title: "提示",
         			    content: "操作失败，" + resp.msgs
         			});
        		}
         	}, "json");
        },
        fields: {
        	account: {
                message: '用户名称无效',
                validators: {
                    notEmpty: {
                        message: '用户名是必需的，并且不能是空的'
                    },
                    stringLength: {
                        min: 5,
                        max: 30,
                        message: '用户名必须大于5，小于30个字符长'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9_\.]+$/,
                        message: '用户名只能由字母、数字、点和下划线组成'
                    },
                    remote: {
                        message: '该用户名已存在,请更换',
                        url: '<c:url value="/portal/admin/admin-account/findAccount?id=${command.id}"/>',
                        data:{
                        	account: function() {
                     			return $("#account").val();
                    		}
                        }
                    }
                }
            },
            group: {
                validators: {
                    notEmpty: {
                        message: '用户组是必须的，不能为空'
                    }
                }
            },
            dname: {
                validators: {
                    notEmpty: {
                        message: '你必须输入用户的姓名'
                    }
                }
            },
            depart: {
                validators: {
                    notEmpty: {
                        message: '你必须输入部门'
                    }
                }
            },
            email: {
                validators: {
                    notEmpty: {
                        message: '该电子邮件地址是必需的，不能是空的'
                    },
                    emailAddress: {
                        message: '输入不是有效的电子邮件地址'
                    }
                }
            },
            staffno: {
                validators: {
                	notEmpty: {
                        message: '工号是必需的，并且不能是空的'
                    },
                    regexp: {
                        regexp: /^[0-9]+$/,
                        message: '工号只能输入数字'
                    }
                }
            },
            pwd: {
                validators: {
                    notEmpty: {
                        message: '密码是必需的，并且不能是空的'
                    },
                    stringLength: {
                        min: 8,
                        max: 16,
                        message: '密码必须大于8，小于16个字符长'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9_\.]+$/,
                        message: '密码只能由字母、数字、点和下划线组成'
                    }
                }
            }
        }
    });
});
</script>

