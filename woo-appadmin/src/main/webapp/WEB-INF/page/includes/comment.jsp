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
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width">
    <title>租一天</title>
<link rel="stylesheet" href="http://cache.amap.com/lbs/static/main1119.css"/>
<script src="http://cache.amap.com/lbs/static/es5.min.js"></script>
<script src="http://webapi.amap.com/maps?v=1.3&key=3be8235f1cb10f423e1fdbdd0c9be773"></script>
<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.0.js"></script>
</head>


<div class="panel panel-default">
    <div class="panel-heading">
        <ol class="breadcrumb">
		  	<li><a href="#"><b><i>地图</i></b></a></li>
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
    <tr class="success">
    <th>物品名字</th>
    <th>${thingsname}</th>
    </tr>
    <tr class="warning">
    <th>物品详细信息</th>
    <th>${thingsdesc}</th>
    </tr>
    <tr class="success">
    <th>物品价格(￥/天)</th>
    <th>${thingsprice}</th>
    </tr>
    <tr class="warning">
    <th>最长租借日期</th>
    <th>${thingsdate}</th>
    </tr>
    <tr class="success">
    <th>物品评论</th>
    <th>${thingscomment}</th>
    </tr>
    <tr classp="warning">
    <th>评论时间</th>
    <th>${commentdate}</th>
    </tr>
    </table>
    </div>
 </div>
 

 
 


