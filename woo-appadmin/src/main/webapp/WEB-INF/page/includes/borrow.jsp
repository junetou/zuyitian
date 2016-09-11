<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@	taglib uri="/WEB-INF/classes/woo.tld" prefix="woo"%>

<head><title>租一天</title></head>

<div class="panel panel-default"  style="background-color:#FFFFCC">
    <div class="panel-heading"  style="background-color:#FFFFCC">
        <ol class="breadcrumb"  style="background-color:#FFFFCC">
		  	<li class="active"><b><i>我要租借</i></b></li>
		</ol>
    </div>
    <div class="panel-body"  style="background-color:#FFFFCC">
        
        <div class="row toolbar"  style="background-color:#FFFFCC">
        
			<div class="col-sm-7"  style="background-color:#FFFFCC">
				<div class="btn-group" role="group"  style="background-color:#FFFFCC" >
         			<a href="javascript:location.reload();" class="btn btn-outline btn-info">刷新</a>
				</div>
			</div>
			
   			
        </div>
        
        <div class="table-responsive"  style="background-color:#FFFFCC">
		    <table class="table table-hover"  style="background-color:#FFFFCC">
		        <thead>
		            <tr>
	                    <th width="33%">物品名称</th>
	                    <th width="33%">租者的名称</th>
	                    <th width="33%">操作</th>
		            </tr>
		        </thead>
		        <tbody>
		        	<c:forEach items="${grid.datas }" varStatus="index" var="data">		          
			            <c:if test="${data.success == 0}">
			            <c:if test="${data.assign==1}">
			            <tr class="success">
			                <td>${data.goodsname }</td>
			                <td>${data.sellername }</td> 
			                <td>
						        <a href="#" data-id="${data.trade}" class="btnEdit" data-url="admin/trade/cancelborrow">
								[取消租借]
								</a>
			                </td>
			            </tr>
			            </c:if>
			            </c:if>
		            </c:forEach>
		            <c:if test="${grid.datas == null}">
						<tr>
							<td colspan="7">亲，你还没租借！</td>
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


