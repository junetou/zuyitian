<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@	taglib uri="/WEB-INF/classes/woo.tld" prefix="woo"%>


<div class="panel panel-default">
    <div class="panel-heading">
        <ol class="breadcrumb">
		  	<li class="active">用户管理</li>
		</ol>
    </div>
    <div class="panel-body">
        
        <div class="row toolbar">
        
			<div class="col-sm-7">
				<div class="btn-group" role="group">
         			<a href="javascript:location.reload();" class="btn btn-outline btn-info">刷新</a>
         			<woo:permission operationType="ADMIN_ACCOUNT_ADD" roleType="ROLE_ADMIN">
         				<a href="<c:url value="/portal/admin/admin-account/form/0"/>" class="btn btn-outline btn-info">新建</a>
         			</woo:permission>
				</div>
			</div>
			
			<div class="col-sm-5">
				<div id="dataTables-example_filter" class="dataTables_filter">
					<form method="post">
						<div class="input-group">
							<input type="text" name="keyWord" id="keyWord" value="${param.keyWord }" class="form-control" placeholder="Search for...(姓名或工号)">
							<span class="input-group-btn">
								<button type="submit" class="btn btn-default btnSearchSubmit">搜索</button>
							</span>
			      		</div>
			 		</form>
              	</div>
   			</div>
   			
        </div>
        
        <div class="table-responsive">
		    <table class="table table-hover">
		        <thead>
		            <tr>
		                <th width="10%">工号</th>
	                    <th width="13%">姓名</th>
	                    <th width="13%">登陆账户</th>
	                    <th width="12%">部门</th>
	                    <th width="12%">状态</th>
	                    <th width="13%">棣属组</th>
	                    <th width="20%">操作</th>
		            </tr>
		        </thead>
		        <tbody>
		        	<c:forEach items="${grid.datas }" varStatus="index" var="data">
			            <tr>
			                <td>${data.staffno }</td> 
			                <td>${data.dname }</td>
			                <td>${data.account }</td> 
			                <td>${data.depart }</td>
			                <td>${data.locked }</td> 
			                <td>${data.group }</td>
			                <td>
						      	<woo:permission operationType="ADMIN_ACCOUNT_EDIT" roleType="ROLE_ADMIN">
						      		<a href="#" data-id="${data.id }" class="btnEdit" data-url="admin/admin-account/form">
										[编辑]
									</a>
								</woo:permission>
								<woo:permission operationType="ADMIN_ACCOUNT_DELETE" roleType="ROLE_ADMIN">
									<a href="javascript:void(0);" data-id="${data.id }" class="btnDel" data-url="admin/admin-account/delete">
										[删除]
									</a>
								</woo:permission>
								<woo:permission operationType="ADMIN_ACCOUNT_RESET" roleType="ROLE_ADMIN">
									<a href="javascript:void(0);" data-id="${data.id }" class="btnReset" data-url="admin/admin-account/resetpass">
										[重置密码]
									</a>
								</woo:permission>
								<woo:permission operationType="ADMIN_ACCOUNT_FREEZE" roleType="ROLE_ADMIN">
									<c:choose>
								       	<c:when test="${data.lock}"> 
								             <a class="btnLock" data-id="${data.id }" data-name="${data.dname }" data-url="admin/admin-account/lock" href="javascript:void(0);">[锁定]</a> 
								       	</c:when>
								       	<c:otherwise>
								             <a class="btnUnlock" data-id="${data.id }" data-name="${data.dname }" data-url="admin/admin-account/unlock" href="javascript:void(0);">[解锁]</a> 
								       	</c:otherwise>
									</c:choose>
								</woo:permission>
							</td>
			            </tr>
		            </c:forEach>
		            <c:if test="${grid.datas == null}">
						<tr>
							<td colspan="7">暂无相关记录！</td>
						</tr>
					</c:if>
		        </tbody>
		    </table>
		</div>
    </div>
    
    <div class="panel-footer">
        <div class="row">
        	<div class="dataTables_paginate paging_simple_numbers" id="dataTables-example_paginate">
        		<woo:pager pgm="${grid.pgm }"/> 
   			</div>
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
			            <input type="text" class="form-control" id="password" name="password">
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

