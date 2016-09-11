<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@	taglib uri="/WEB-INF/classes/woo.tld" prefix="woo"%>
<style>
input[type=checkbox], input[type=radio] {margin:0;}
.form-group {
    margin-bottom: 5px;
} 
.panel {
	margin-bottom:0;
}
</style>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width">
    <link rel="stylesheet" href="http://cache.amap.com/lbs/static/main1119.css"/>
    <script src="http://cache.amap.com/lbs/static/es5.min.js"></script>
    <title>租一天</title>
</head>


<div class="panel panel-default">
    <div class="panel-heading">
        <ol class="breadcrumb">
		  	<li><b><i>首页</i></b></li>
		  	<li><b><i>修改物品信息</i></b></li>
		  	<li><a href="javascript:history.go(-1)" class="btn btn-outline btn-info" >返回</a></li>
		</ol>
    </div>
    <!-- .panel-heading --> 
    <div class="panel-body">
		
		
		<form:form id="group-form" method="post">
			<div class="btn-group" role="group" style="margin-bottom:20px;">
	    		<button type="submit" class="btn btn-outline btn-info">保 存</button>
			</div>
			<div class="row">
               	<div class="col-md-6">
		            <div class="form-group">
		                <label>物品名称<sup>*</sup></label>
						<form:input cssClass="form-control" path="name"/>
						<p class="help-block">10个字以内</p> 
		            </div>
				    <div class="form-group">
		                <label>物品价格<sup>*</sup></label>
		                <form:input cssClass="form-control" path="price"/>
		            </div>
				</div>
				
               	<div class="col-md-6">
		            <div class="form-group">
		                <label>可以租借的最大日期</label>
		                <form:input cssClass="form-control" path="date"/>
		                <p class="help-block">以一天为单位</p>
		            </div>		            
                    <div class="form-group">
		                <label>手机号码</label>
		                <form:input cssClass="form-control" path="phone"/>
		                <p class="help-block">请输入正确的手机号</p>
		            </div>
               	</div> 
           	</div>
			
			 <div class="form-group">
	            <label for="remark" class="ui-label">物品详细信息</label>
	            <form:textarea class="form-control" path="descs"/>
	            <p class="help-block">500字符以内</p>
	        </div>		        
			<form:hidden path="need"/>
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
         	$.post(" <c:url value='/portal/admin/need/update' />", datas, function(resp){
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
            name: {
                validators: {
                    notEmpty: {
                        message: '你必须输入物品的姓名'
                    }
                }
            },
        }
    });
});
</script>


