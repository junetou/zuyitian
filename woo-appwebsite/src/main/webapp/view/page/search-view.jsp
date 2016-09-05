<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@	taglib prefix="woo" uri="/WEB-INF/classes/woo.tld" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<style>
body {
	font-size:12px;
	font-family: serif;
}
em {
	color:#a71d5d;
	font-weight: bold;
    font-style: normal;
}
.search {
	border-bottom: 2px solid #ccc;
}
</style>

<form action="/search" method="get">
	<input type="text"  name="title" id="title" maxlength="100" value="${param.title }" >
	<input type="submit" value="百度一下">
</form>

<c:forEach items="${grid.datas }" varStatus="index" var="data">
	<div class="search">
		<h3>${data.title }</h3>
		<p>${data.subject }</p>
		<p>${data.author }</p>
	</div>
</c:forEach>


<div class="panel-footer">
        <div class="row">
        	<div class="dataTables_paginate paging_simple_numbers" id="dataTables-example_paginate">
        		<woo:pager pgm="${grid.pgm }"/> 
   			</div>
    	</div>
    </div>