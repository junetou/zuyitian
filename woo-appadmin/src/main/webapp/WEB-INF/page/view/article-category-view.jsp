<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@	taglib uri="/WEB-INF/classes/woo.tld" prefix="woo"%>

<div class="panel panel-default">
	<div class="panel-heading">
        <ol class="breadcrumb">
		  	<li><a href="#">内容管理</a></li>
		  	<li class="active" onclick="javascript:location.reload();">文章分类</li>
		</ol>
    </div>
    
	<div class="panel-body">
		
		<div class="row toolbar">
			<div class="col-sm-7">
				<div class="btn-group" role="group">
					<c:if test="${!empty param.id }">
						<a href="javascript:history.go(-1)" class="btn btn-outline btn-info">返回</a>
					</c:if>
				  	<a class="btn btn-outline btn-info" onclick="javascript:location.reload();"><i class="glyphicon glyphicon-refresh"></i> 刷新</a>
				  	
			  		<woo:permission operationType="ARTICLE_CATEGORY_ADD" roleType="ROLE_WEB">
                    	<a class="btn btn-outline btn-info newSomething" href="javascript:" data-url="portal/web/article/category/form" data-id="0?paranId=${param.id }"><i class="fa fa-plus-circle"></i> 新建</a>
				  	</woo:permission>
				</div>
			</div>
			<div class="col-sm-5">
				<form method="get">
					<div class="input-group">
						<input type="text" name="keyword" id="keyword" value="${param.keyword }" class="form-control" placeholder="Search for...">
						<input type="hidden" name="opt" value=${param.opt }>
						<span class="input-group-btn">
							<button type="submit" class="btn btn-default btnSearchSubmit"><i class="fa fa-search"></i> 搜索</button>
						</span>
		      		</div>
		 		</form>
			</div>
		</div>
		
		<div class="table-responsive">
		    <table class="table table-hover">
			  	<thead>
		            <tr>
	                    <th width="15%">名称</th>
	                    <th width="35%">描述</th>
	                    <th width="10%">状态</th>
	                    <th width="5%">排序</th>
	                    <th width="15%">父级名称</th>
	                    <th width="20%">操作</th>
		            </tr>
		        </thead>
		         <tbody>
		         	<c:forEach items="${grid.datas }" varStatus="index" var="data">
			            <tr>
			                <td>${data.name }</td>
			                <td>${data.description }</td>
			                <td>${data.state }</td>
			                <td>${data.sort }</td>
			                <td>${data.parentName }</td>
			                <td>
			                	
			                	<woo:permission operationType="ARTICLE_CATEGORY_VIEW" roleType="ROLE_WEB">
			                		<a href='<c:url value="/portal/web/article/category?id=${data.id }"/>'>[查看]</a>
			                	</woo:permission>
			                
			                	<woo:permission operationType="ARTICLE_CATEGORY_EDIT" roleType="ROLE_WEB">
			                		<a href="javascript:void(0);" data-id="${data.id }" class="btnEditBig" data-url="portal/web/article/category/form">[编辑]</a>
			                	</woo:permission>
			                	
			                	<woo:permission operationType="ARTICLE_CATEGORY_DELETE" roleType="ROLE_WEB">
			                		<a href="javascript:void(0);" data-id="${data.id }" class="btnDel" data-url="portal/web/article/category/delete">[删除]</a>
			                	</woo:permission>
							</td>
			            </tr>
		            </c:forEach>
		            <c:if test="${grid.datas == null}">
						<tr>
							<td colspan="5">暂无相关记录！</td>
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


<div class="modal fade" id="myWindow" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  	<div class="modal-dialog modal-lg" role="document">
	    <div class="modal-content">
	    	
	      	<div class="modal-header">
	        	<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        	<h4 class="modal-title" id="myModalLabel">编辑</h4>
	      	</div>
	      	
	      	<iframe id="window-form" name="window-form" frameborder="0" src="" height="650" width="100%" scrolling="no"></iframe>
	      	
	    </div>
  	</div>
</div>