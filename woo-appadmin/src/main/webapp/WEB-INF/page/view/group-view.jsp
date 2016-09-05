<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@	taglib prefix="woo" uri="/WEB-INF/classes/woo.tld" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="panel panel-default">
    <div class="panel-heading">
        <ol class="breadcrumb">
		  	<li><a href="#">系统管理</a></li>
		  	<li class="active">角色管理</li>
		</ol>
    </div>
    <div class="panel-body">
        
        <div class="row toolbar">
        
			<div class="col-sm-7">
				<div class="btn-group" role="group">
         			<a href="javascript:location.reload();" class="btn btn-outline btn-info">刷新</a>
         			<woo:permission operationType="SYSTEM_ROLE_ADD" roleType="ROLE_ADMIN">
						<a href="<c:url value="/portal/admin/system-role/form/0	"/>" class="btn btn-outline btn-info">新建</a>
					</woo:permission>
				</div>
			</div>
			
			<div class="col-sm-5">
				<div id="dataTables-example_filter" class="dataTables_filter">
					<form method="post">
						<div class="input-group">
							<input type="text" name="keyword" id="keyword" value="${param.keyword }" class="form-control" placeholder="Search for...">
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
		                <th width="15%">组名称</th>
		                <th width="70%">组成员</th>
		                <th width="15%">操作</th>
		            </tr>
		        </thead>
		        <tbody>
		        	<c:forEach items="${grid.datas }" varStatus="index" var="data">
			            <tr>
			                <td>${data.gname }</td>
			                <td>${data.usrs }</td>
			                <td>
						      	<woo:permission operationType="SYSTEM_ROLE_EDIT" roleType="ROLE_ADMIN">
						      		<a href="javascript:void(0);" data-id="${data.id }" class="btnEdit" data-url="admin/system-role/form">
										[编辑]
									</a>
								</woo:permission>
								<woo:permission operationType="SYSTEM_ROLE_DELETE" roleType="ROLE_ADMIN">
									<a href="javascript:void(0);" data-id="${data.id }" class="btnDel" data-url="admin/system-role/delete">
										[删除]
									</a>
								</woo:permission>
							</td>
			            </tr>
		            </c:forEach>
		            <c:if test="${grid.datas == null}">
						<tr>
							<td colspan="3">暂无相关记录！</td>
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