<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@	taglib uri="/WEB-INF/classes/woo.tld" prefix="woo"%>

<head><title>租一天</title></head>

<div class="panel panel-default" style="background-color:#FFFFCC;width:auto; ">
    <div class="panel-heading" style="background-color:#FFFFCC ">
        <ol class="breadcrumb" style="background-color:#FFFFCC ">
		  	<li class="active" style="background-color:#FFFFCC "><b><i>我发布的需求</i></b></li>
		</ol>
    </div>
    <div class="panel-body">
        
        <div class="row toolbar">
        
			<div class="col-sm-7" style="background-color:#FFFFCC ">
				<div class="btn-group" role="group">
         			<a href="javascript:location.reload();" class="btn btn-outline btn-info">刷新</a> 
				</div>
			</div>
			
			<div class="col-sm-5" style="background-color:#FFFFCC " >
				<div id="dataTables-example_filter" class="dataTables_filter" style="background-color:#FFFFCC ">
					<form method="post">
						<div class="input-group">
							<input type="text" name="keyword" id="keyword" class="form-control" placeholder="Search for..." style="background-color:#FFFFCC" >
							<span class="input-group-btn">
								<button type="submit" class="btn btn-default btnSearchSubmit">搜索</button>
							</span>
			      		</div>
			 		</form>
              	</div>
   			</div>
   			
        </div>
        
        <div class="table-responsive" style="background-color:#FFFFCC ">
		    <table class="table table-hover" style="background-color:#FFFFCC ">
		        <thead>
		            <tr>
	                    <th width="10%">名字</th>
	                    <th width="12%">价格(￥/天)</th>
	                    <th width="20%">最长租借日期(以天为单位)</th>
	                    <th width="16%">种类</th>
	                    <th width="22%">详细信息</th>
		            </tr>
		        </thead>
		        <tbody>
		        	<c:forEach items="${grid.datas }" varStatus="index" var="data">
			              <c:if test="${data.overanalyzed == 1}">
			            <tr class="success">
			                <td>${data.name }</td>
			                <td>${data.price }</td>
			                <td>${data.date }</td> 
			                <td>${data.kind }</td>
			                <td>
								<a href="#" data-id="${data.need}" class="btnEdit" data-url="admin/need/newupdate">
								[修改信息]
								</a>
								<a href="#" data-id="${data.need}" class="btnEdit" data-url="admin/need/delete">
								[下架]
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
    
    <div class="panel-footer" style="background-color:#FFFFCC; width:auto;" >
        <div class="row" style="background-color:#FFFFCC;">
        	<div class="dataTables_paginate paging_simple_numbers" id="dataTables-example_paginate" style="background-color:#FFFFCC; ">
        		<woo:pager pgm="${grid.pgm }"/> 
   			</div>
    	</div>
    </div>
</div>


