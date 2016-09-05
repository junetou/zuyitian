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

<head>	
<title>租一天</title>
<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.0.js"></script>
</head>

<div class="panel panel-default" style="background-color:#FFFFCC ">
    <div class="panel-heading" style="background-color:#FFFFCC ">
        <ol class="breadcrumb" style="background-color:#FFFFCC ">
		  	<li><a href="javascript:history.go(-1)">地图</a></li>
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
    <div class="table-responsive" >
    <table class="table table-striped">
    <thead>
    <tr>
    <th></th>
    <th>信息</th>
    </tr>
    </thead>
    <tr class="success">
    <th>物品详细信息</th>
    <th>${thingsdesc}</th>
    </tr>
    <tr class="success">
    <th>物品价格</th>
    <th>${thingsprice}</th>
    </tr>
    <tr class="warning">
    <th>物品名字</th>
    <th>${thingsname}</th>
    </tr>
    <tr class="success">
    <th>电话</th>
    <th>${thingsphone}</th>
    </tr>
    <tr class="warning">
    <th>微信</th>
    <th>${useweixin}</th>
    </tr>
    <tr class="success">
    <th>物品照片</th>
    <th><img src="<woo:url value="/static/picture/${picname}"/>"  width="100px" height="100px" ></th>
    </tr>
    </table>
    </div>  
    <div style="background-color:#FFFFCC "></div>
 </div>
 

 <script type="text/javascript">

</script>
 
 


