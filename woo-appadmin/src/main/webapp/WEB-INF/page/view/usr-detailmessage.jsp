<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@	taglib uri="/WEB-INF/classes/woo.tld" prefix="woo"%>

<head><title>租一天</title></head>

<div class="panel panel-default"  style="background-color:#FFFFCC">
    <div class="panel-heading"  style="background-color:#FFFFCC">
        <ol class="breadcrumb"  style="background-color:#FFFFCC">
		  	<li class="active"><b><i>查询物品</i></b></li>
		</ol>
    </div>
    <div class="panel-body"  style="background-color:#FFFFCC">
        
        <div class="row toolbar"  style="background-color:#FFFFCC">
        
			<div class="col-sm-7"  style="background-color:#FFFFCC">
				<div class="btn-group" role="group"  style="background-color:#FFFFCC" >
         			<a href="javascript:location.reload();" class="btn btn-outline btn-info">刷新</a>
				</div>
			</div>
			
			<div class="col-sm-5"  style="background-color:#FFFFCC" >
				<div id="dataTables-example_filter" class="dataTables_filter" style="background-color:#FFFFCC">
					<form method="post"  style="background-color:#FFFFCC">
						<div class="input-group">
							<input type="text" name="keyWord" id="keyWord" value="${param.keyWord }" class="form-control" placeholder="Search for(请在第一页搜索)..." style="background-color:#FFFFCC" >
							<span class="input-group-btn">
								<button type="submit" class="btn btn-default btnSearchSubmit">搜索</button>
							</span>
			      		</div>
			 		</form>
              	</div>
   			</div>
   			
        </div>
        
        <div class="table-responsive"  style="background-color:#FFFFCC">
		    <table class="table table-hover"  style="background-color:#FFFFCC">
		        <thead>
		            <tr>
	                    <th width="5%">名字</th>
	                    <th width="30%">详情</th>
	                    <th width="12%">价格(￥/天)</th>
	                    <th width="5">最长租借日期</th>
	                    <th width="5">种类</th>
	                    <th width="7">详细信息</th>
		            </tr>
		        </thead>
		        <tbody>
		        	<c:forEach items="${grid.datas }" varStatus="index" var="data">

			            <tr class="success">
			                <td>${data.thingsname }</td>
			                <td>${data.thingsdesc }</td> 
			                <td>${data.thingsprice }</td>
			                <td>${data.thingsdate }</td> 
			                <td>${data.thingskind }</td>
			                <td>
						        <a href="#" data-id="${data.thingsId}" class="btnEdit" data-url="admin/thingsandmap/form">
								[详细信息]
								</a>
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
    
    <div class="panel-footer"  style="background-color:#FFFFCC">
        <div class="row" style="background-color:#FFFFCC">
        	<div class="dataTables_paginate paging_simple_numbers" id="dataTables-example_paginate">
        		<woo:pager pgm="${grid.pgm }"/> 
   			</div>
    	</div>
    </div>
</div>


