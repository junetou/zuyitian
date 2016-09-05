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
		  	<li class="active">我发布的物品</li>
		</ol>
    </div>
    <div class="panel-body">
        
        <div class="row toolbar">
        
			<div class="col-sm-7">
				<div class="btn-group" role="group">
         			<a href="javascript:location.reload();" class="btn btn-outline btn-info">刷新</a>
				</div>
			</div>
		
			
			<div class="col-sm-5">
				<div id="dataTables-example_filter" class="dataTables_filter">
					<form method="post">
						<div class="input-group">
							<input type="text" name="keyword" id="keyword" class="form-control" placeholder="Search for...">
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
	                    <th width="5%">名字</th>
	                    <th width="30%">详情</th>
	                    <th width="12%">价格(￥/天)</th>
	                    <th width="5">最长租借日期</th>
	                    <th width="5">种类</th>
	                    <th width="7">操作</th>
		            </tr>
		        </thead>
		        <tbody>
		       
		        	<c:forEach items="${grid.datas}" varStatus="index" var="data">
			           
			            <tr class="success">
			                <td>${data.thingsname }</td>
			                <td>${data.thingsdesc }</td> 
			                <td>${data.thingsprice }</td>
			                <td>${data.thingsdate }</td> 
			                <td>${data.thingskind }</td>        
			                <td>
						       <a href="#" data-id="${data.thingsId}" class="btnEdit" data-url="admin/comment/form" >                            
								[查看评论]
							   </a>
							   <a href="#" data-id="${data.thingsId}" class="btnEdit" data-url="admin/comment/newupdate" >                            
								[修改信息]
							   </a>
							   <a href="#" data-id="${data.thingsId}" class="btnEdit" data-url="admin/comment/delete">
								[删除]
							   </a>
			                </td>
			            </tr>
			           
		            </c:forEach>
		           
		            <c:if test="${grid.datas == 0}">
						<tr>
							<td colspan="7">你还没出租过东西！</td>
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

