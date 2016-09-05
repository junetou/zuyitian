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
		  	<li>评论</li>
		</ol>
    </div>
    <!-- .panel-heading -->
    <div class="panel-body">
		<form:form id="group-form" method="post">
			<div class="btn-group" role="group" style="margin-bottom:20px;">
				<a href="javascript:history.go(-1)" class="btn btn-outline btn-info" >返回</a>
			</div>
        </form:form>
    </div>
    <div class="table-responsive">
    <table class="table table-striped">
    <thead>
    <tr>
    <th></th>
    <th>物品名字：${thingsname}</th>
    </tr>
    </thead>
    <tbody>
		        	<c:forEach items="${grid.datas}" varStatus="index" var="data">
			            <tr class="success">
			                <td>评论</td>
			                <td>${data.comment}</td>
			            </tr>
		            </c:forEach>
		            <c:if test="${grid.datas == null}">
						<tr>
							<td colspan="7">此物品暂时没有评论</td>
						</tr>
					</c:if>
    </tbody>
    </table>
    </div>
 </div>
 

 
 


