<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@	taglib uri="/WEB-INF/classes/woo.tld" prefix="woo"%>

<head><title>租一天</title></head>

<div class="panel panel-default">
    <div class="panel-heading">
        <ol class="breadcrumb">
		  	<li class="active"><b><i>我发布的物品</i></b></li>
		</ol>
    </div>
    <div class="panel-body">
        
        <div class="row toolbar">
        
			
			
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
	                    <th width="5%">价格(￥/天)</th>
	                    <th width="10%">最长租借日期(以天为单位)</th>
	                    <th width="5%">种类</th>
	                    <th width="7%">详细信息</th>
		            </tr>
		        </thead>
		        <tbody>
		        	<c:forEach items="${grid.datas }" varStatus="index" var="data">
			              <c:if test="${data.thingsoveranalyzed == 1}">
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
								<a href="#" data-id="${data.thingsId}" class="btnEdit" data-url="admin/comment/newupdate">
								[修改信息]
								</a>
								<a href="#" data-id="${data.thingsId}" class="btnEdit" data-url="admin/comment/delete">
								[删除]
							    </a>
			                </td>
			            </tr>
			            </c:if>
		            </c:forEach>
		            <c:if test="${grid.datas == null}">
						<tr>
							<td colspan="7">亲，你还没发布东西！</td>
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


