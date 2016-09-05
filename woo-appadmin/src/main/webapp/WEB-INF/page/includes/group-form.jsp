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
<head><title>租一天</title></head>
<div class="panel panel-default">
    <div class="panel-heading">
        <ol class="breadcrumb">
		  	<li><a href="#">首页 </a></li>
		  	<li><a href="#">权限设置</a></li>
		  	<li class="active">编辑 ${command.gname }</li>
		</ol>
    </div>
    <!-- .panel-heading -->
    <div class="panel-body">
		
		<form:form id="group-form" method="post">
		
			<div class="btn-group" role="group" style="margin-bottom:20px;">
				<a href="javascript:history.go(-1)" class="btn btn-outline btn-info">返回</a>
		   		<button type="submit" class="btn btn-outline btn-info">保 存</button>
			</div>
			
            <div class="form-group">
                <label>名称<sup>*</sup></label>
				<form:input cssClass="form-control" path="gname"/>
				<p class="help-block">30个字以内</p> 
            </div>

			<!-- 系统管理 -->
			<div class="alert alert-info adminMenus">
				<label class="ui-label" for="adminMenus"><strong><spring:message code="menu.admin"/></strong></label><br> 
				<c:forEach var="menu" items="${adminMenus}" varStatus="indexs"> 
					<div class="form-group has-feedback has-success"><div class="btn-group">
						<label class="btn btn-default"><strong><spring:message code="menu.${fn:toLowerCase(menu.code)}"/></strong></label> 
						<c:forEach var="opt" items="${menu.opts}">
							<label class="btn btn-default">
								<form:checkbox path="opts" value="${menu.code}_${opt}"/> <spring:message code="menu.opt.${fn:toLowerCase(opt)}"/>
			                </label>
						</c:forEach>
					</div></div>
				</c:forEach>
				<input type="hidden" name="roles">
			</div>
			
			<!-- 地图栏 -->
			
			<div class="alert alert-info adminMenus">
				<label class="ui-label" for="mapMenus"><strong><spring:message code="menu.map"/></strong></label><br> 
				<c:forEach var="menu" items="${mapMenus}" varStatus="indexs"> 
					<div class="form-group has-feedback has-success"><div class="btn-group">
						<label class="btn btn-default"><strong><spring:message code="menu.${fn:toLowerCase(menu.code)}"/></strong></label> 
						<c:forEach var="opt" items="${menu.opts}">
							<label class="btn btn-default">
								<form:checkbox path="opts" value="${menu.code}_${opt}"/> <spring:message code="menu.opt.${fn:toLowerCase(opt)}"/>
			                </label>
						</c:forEach>
					</div></div>
				</c:forEach>
				<input type="hidden" name="roles">
			</div>
		
		  <!-- 评论 -->
		
			<div class="alert alert-info adminMenus">
				<label class="ui-label" for="commentMenus"><strong><spring:message code="menu.comment"/></strong></label><br> 
				<c:forEach var="menu" items="${commentMenus}" varStatus="indexs"> 
					<div class="form-group has-feedback has-success"><div class="btn-group">
						<label class="btn btn-default"><strong><spring:message code="menu.${fn:toLowerCase(menu.code)}"/></strong></label> 
						<c:forEach var="opt" items="${menu.opts}">
							<label class="btn btn-default">
								<form:checkbox path="opts" value="${menu.code}_${opt}"/> <spring:message code="menu.opt.${fn:toLowerCase(opt)}"/>
			                </label>
						</c:forEach>
					</div></div>
				</c:forEach>
				<input type="hidden" name="roles">
			</div>
		
			
			<!-- 账户管理 -->
			<%-- <div class="alert alert-info userMenus">
				<label class="ui-label" for="userMenus"><strong><spring:message code="menu.user"/></strong></label>
				<c:forEach var="menu" items="${userMenus}" varStatus="indexs">
					<div class="form-group has-feedback has-success"><div class="btn-group">
						<label class="btn btn-default"><strong><spring:message code="menu.${fn:toLowerCase(menu.code)}"/></strong></label> 
						<c:forEach var="opt" items="${menu.opts}">
							<label class="btn btn-default">
			                    <form:checkbox path="opts" value="${menu.code}_${opt}"/> <spring:message code="menu.opt.${fn:toLowerCase(opt)}"/>
			                </label>
						</c:forEach>
					</div></div>
				</c:forEach>
				<input type="hidden" name="roles">
			</div> --%>
			
			<!-- 内容管理 -->
			<div class="alert alert-info webMenus">
				<label class="ui-label" for="webMenus"><strong><spring:message code="menu.web"/></strong></label>
				<c:forEach var="menu" items="${webMenus}" varStatus="indexs">
					<div class="form-group has-feedback has-success"><div class="btn-group">
						<label class="btn btn-default"><strong><spring:message code="menu.${fn:toLowerCase(menu.code)}"/></strong></label> 
						<c:forEach var="opt" items="${menu.opts}">
							<label class="btn btn-default">
			                    <form:checkbox path="opts" value="${menu.code}_${opt}"/> <spring:message code="menu.opt.${fn:toLowerCase(opt)}"/>
			                </label>
						</c:forEach>
					</div></div>
				</c:forEach>
				<input type="hidden" name="roles">
			</div>
			
			<!-- 商品中心 -->
			<%-- <div class="alert alert-info productCentralMenus">
				<label class="ui-label" for="productCentralMenus"><strong><spring:message code="menu.product"/></strong></label>
				<c:forEach var="menu" items="${productCentralMenus}" varStatus="indexs">
					<div class="form-group has-feedback has-success"><div class="btn-group">
						<label class="btn btn-default"><strong><spring:message code="menu.${fn:toLowerCase(menu.code)}"/></strong></label> 
						<c:forEach var="opt" items="${menu.opts}">
							<label class="btn btn-default">
			                    <form:checkbox path="opts" value="${menu.code}_${opt}"/> <spring:message code="menu.opt.${fn:toLowerCase(opt)}"/>
			                </label>
						</c:forEach>
					</div></div>
				</c:forEach>
				<input type="hidden" name="roles">
			</div> --%>
			
			<!-- 订单管理 -->
			<%-- <div class="alert alert-info orderCentralMenus">
				<label class="ui-label" for="orderCentralMenus"><strong><spring:message code="menu.order_central"/></strong></label>
				<c:forEach var="menu" items="${orderCentralMenus}" varStatus="indexs">
					<div class="form-group has-feedback has-success"><div class="btn-group">
						<label class="btn btn-default"><strong><spring:message code="menu.${fn:toLowerCase(menu.code)}"/></strong></label> 
						<c:forEach var="opt" items="${menu.opts}">
							<label class="btn btn-default">
			                    <form:checkbox path="opts" value="${menu.code}_${opt}"/> <spring:message code="menu.opt.${fn:toLowerCase(opt)}"/>
			                </label>
						</c:forEach>
					</div></div>
				</c:forEach>
				<input type="hidden" name="roles">
			</div> --%>
			
			<!-- 营销管理 -->
			<%-- <div class="alert alert-info markMenus">
				<label class="ui-label" for="markMenus"><strong><spring:message code="menu.marketing"/></strong></label>
				<c:forEach var="menu" items="${markMenus}" varStatus="indexs">
					<div class="form-group has-feedback has-success"><div class="btn-group">
						<label class="btn btn-default"><strong><spring:message code="menu.${fn:toLowerCase(menu.code)}"/></strong></label> 
						<c:forEach var="opt" items="${menu.opts}">
							<label class="btn btn-default">
			                    <form:checkbox path="opts" value="${menu.code}_${opt}"/> <spring:message code="menu.opt.${fn:toLowerCase(opt)}"/>
			                </label>
						</c:forEach>
					</div></div>
				</c:forEach>
				<input type="hidden" name="roles">
			</div> --%>
			
			<!-- 财务管理 -->
			<%-- <div class="alert alert-info propertyMenus"> 
				<label class="ui-label" for="propertyMenus"><strong><spring:message code="menu.property"/></strong></label>
				<c:forEach var="menu" items="${propertyMenus}" varStatus="indexs">
					<div class="form-group has-feedback has-success"><div class="btn-group">
						<label class="btn btn-default"><strong><spring:message code="menu.${fn:toLowerCase(menu.code)}"/></strong></label> 
						<c:forEach var="opt" items="${menu.opts}">
							<label class="btn btn-default">
			                    <form:checkbox path="opts" value="${menu.code}_${opt}"/> <spring:message code="menu.opt.${fn:toLowerCase(opt)}"/>
			                </label>
						</c:forEach>
					</div></div>
				</c:forEach>
				<input type="hidden" name="roles">
			</div> --%>
			
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
        	
        	var hasMenus = $('.adminMenus').find('input[name=opts]:checked').length > 0;
        	if (hasMenus){
        		$('.adminMenus').find('input[name=roles]').val('admin');
        	}
        	
        	/* hasMenus = $('.userMenus').find('input[name=opts]:checked').length > 0;
        	if (hasMenus){
        		$('.userMenus').find('input[name=roles]').val('account');
        	} */
        	
        	hasMenus = $('.webMenus').find('input[name=opts]:checked').length > 0;
        	if (hasMenus){
        		$('.webMenus').find('input[name=roles]').val('web');
        	}
        	
        	/* hasMenus = $('.productCentralMenus').find('input[name=opts]:checked').length > 0;
        	if (hasMenus){
        		$('.productCentralMenus').find('input[name=roles]').val('prodcentral');
        	}
         	
        	hasMenus = $('.orderCentralMenus').find('input[name=opts]:checked').length > 0;
        	if (hasMenus){
        		$('.orderCentralMenus').find('input[name=roles]').val('ordercentral');
        	} */
        	
        	/* hasMenus = $('.markMenus').find('input[name=opts]:checked').length > 0;
        	if (hasMenus){
        		$('.markMenus').find('input[name=roles]').val('mark');
        	}
        	
        	hasMenus = $('.propertyMenus').find('input[name=opts]:checked').length > 0;
        	if (hasMenus){
        		$('.propertyMenus').find('input[name=roles]').val('property');
        	} */
        	
        	var id = $("#id").val();
        	var methet = "";
        	if(id != null && id != "") {
        		methet = "update";
        	} else {
        		methet = "add";
        	}
         	var datas = $("#group-form").toJson();
         	$.post("<c:url value='/portal/admin/system-role/"+methet+"'/>", datas, function(resp){
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
         	$('#register-form').slideUp('fast');
        },
        fields: {
        	gname: {
                message: '名称无效',
                validators: {
                    notEmpty: {
                        message: '名称是必需的，并且不能是空的'
                    },
                    stringLength: {
                        max: 30,
                        message: '名称必须小于30个字符长'
                    }
                }
            }
        }
    });
});
</script>
