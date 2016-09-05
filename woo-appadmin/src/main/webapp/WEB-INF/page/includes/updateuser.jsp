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

<div class="panel panel-default" style="background-color:#FFFFCC">
    <div class="panel-heading" style="background-color:#FFFFCC">
        <ol class="breadcrumb" style="background-color:#FFFFCC">
		  	<li>首页 </li>
		  	<li>编辑个人信息</li>
		  	<li><a href="javascript:history.go(-1)" class="btn btn-outline btn-info" >返回</a></li>
		</ol>
    </div>
    <!-- .panel-heading --> 
    <div class="panel-body" style="background-color:#FFFFCC" >
		
		
		<form:form id="group-form" method="post" style="background-color:#FFFFCC" >
			<div class="btn-group" role="group" style="margin-bottom:20px;">
	    		<button type="submit" class="btn btn-outline btn-info">保 存</button>
			<p>${judgement}</p>
			</div>
			<div class="row">
               	<div class="col-md-6">
		            <div class="form-group">
		                <label>姓名<sup>*</sup></label>
						<form:input cssClass="form-control" path="dname" style="background-color:#FFFFCC" />
						<p class="help-block">10个字以内，中文或英文或数字</p> 
		            </div>
		            <div class="form-group">
		                <label for="qqNum" class="ui-label">qq号码：</label>
		                <form:input class="form-control" path="qqNum" style="background-color:#FFFFCC" />
		                <p class="help-block">25字符以内</p>
		            </div>
				    <div class="form-group">
		                <label>Email<sup>*</sup></label>
		                <form:input cssClass="form-control" path="email" style="background-color:#FFFFCC" />
		                <p class="help-block">请输入（例如：123456789@qq.com）</p>
		            </div>
		            <div class="form-group">
		                <label>手机号码<sup>*</sup></label>
		                <form:input cssClass="form-control" path="mobile" style="background-color:#FFFFCC" />
		                <p class="help-block">请输入正确的手机号</p>
		            </div>
				</div>
				
               	<div class="col-md-6">
		            <div class="form-group">
		                <label>电话</label>
		                <form:input cssClass="form-control" path="phone" style="background-color:#FFFFCC" />
		                <p class="help-block">请输入（例如：020-34839285）</p>
		            </div>
		            <div class="form-group">
		                <label>通讯地址(请尽量详细)</label>
		                <form:input class="form-control" path="address" style="background-color:#FFFFCC" />
		                <p class="help-block">250字符以内</p>
		            </div>		            
		            <div class="form-group">
		                <label>微信号<sup>*</sup></label>
		                <form:input class="form-control" path="weixin" style="background-color:#FFFFCC" />
		                <p class="help-block">20字符以内</p> 
		            </div>
			
               	</div> 
           	</div>
			
			 <div class="form-group">
	            <label for="remark" class="ui-label">备注：</label>
	            <form:textarea class="form-control" path="remark" style="background-color:#FFFFCC" />
	            <p class="help-block">500字符以内</p>
	        </div>		
			<form:hidden path="id"/>
		</form:form>
		
    </div>
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
         	$.post(" <c:url value='/portal/admin/updatemessage/update' />", datas, function(resp){
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
            dname: {
                validators: {
                    notEmpty: {
                        message: '你必须输入用户的姓名'
                    }
                }
            },
            weixin: {
                validators: {
                    notEmpty: {
                        message: '微信号不能为空'
                    }
                }
            },
            mobile: {
                validators: {
                    notEmpty: {
                        message: '手机号必须输入'
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

        }
    });
});
</script>

