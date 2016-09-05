<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@	taglib uri="/WEB-INF/classes/woo.tld" prefix="woo"%>

<head><title>租一天</title></head>

<div class="panel panel-default"  style="background-color:#FFFFCC">
    <div class="panel-heading"  style="background-color:#FFFFCC">
        <ol class="breadcrumb"  style="background-color:#FFFFCC">
		  	<li class="active">个人信息查看</li>
		</ol>
    </div>
    <div class="panel-body"  style="background-color:#FFFFCC">
        
        <div class="row toolbar"  style="background-color:#FFFFCC">
        
			<div class="col-sm-7"  style="background-color:#FFFFCC">
				<div class="btn-group" role="group"  style="background-color:#FFFFCC">
         			<a href="javascript:location.reload();" class="btn btn-outline btn-info">刷新</a>
				</div>
			</div>   			
        </div>
        
        <div class="table-responsive">
		<table class="table table-hover" style="height:600px;">
		        <thead>
		            <tr>  
	                    <th width="13%">姓名</th>
	                    <th width="13%">登陆账号</th>
	                    <th width="20%">操作</th> 
		            </tr>
		        </thead>
		        <tbody>
			            <tr> 
			                <td>${name}</td>
			                <td>${usename}</td>
			                <td>
						    <a href="<c:url value="/portal/admin/updatemessage/show"/>" class="btnEdit" >
							[编辑]
						    </a>
							<a href="javascript:void(0);" class="btnReset" data-url="admin/updatemessage/resetpass">
							[重置密码]
							</a>
							<a href="<c:url value="/portal/admin/fed/showfed" />" class="btnEdit">
							[反馈信息]
							</a>
							</td>
			            </tr>
		        </tbody>
		     </table> 
		</div>
    </div>
    
</div>

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  	<div class="modal-dialog" role="document">
	    <div class="modal-content">
	    	<form id="reset-form" method="post">
		      	<div class="modal-header">
		        	<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        	<h4 class="modal-title" id="myModalLabel">重置密码</h4>
		      	</div>
		      	<div class="modal-body">
		      		<div class="form-group">
			            <label for="recipient-name" class="control-label">登陆密码<sup>*</sup></label>
			            <input type="password" class="form-control" id="password" name="password">
			            <p class="help-block">密码是必需的，并且不能是空的</p> 
		          	</div>
		      	</div>
		      	<div class="modal-footer">
		      		<input type="hidden" name="userId" id="userId" value="">
		        	<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		        	<button type="submit" class="btn btn-primary">提交</button>
		      	</div>
	      	</form>
	    </div>
  	</div>
</div>

