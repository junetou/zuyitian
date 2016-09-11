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
    <title>详细信息</title>
<link rel="stylesheet" href="http://cache.amap.com/lbs/static/main1119.css"/>
<script src="http://cache.amap.com/lbs/static/es5.min.js"></script>
<script src="http://webapi.amap.com/maps?v=1.3&key=3be8235f1cb10f423e1fdbdd0c9be773"></script>
<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.0.js"></script>
</head>


<div class="panel panel-default"  style="background-color:#FFFFCC">
    <div class="panel-heading"  style="background-color:#FFFFCC">
        <ol class="breadcrumb"  style="background-color:#FFFFCC">
		  	<li><a href="#"><b><i>地图</i></b></a></li>
		</ol>
    </div>
    <div style="display:none" >
    <form action="<c:url value='/portal/admin/chat/get'/>" method="get" >
    <input id="usrid" type="text" name="usrid" value='${thingsnumber}' required >
    <input type="submit" id="post" name="post" value="get"/>
    </form>
    </div>
    <div style="display:none" >
    <form action="<c:url value='/portal/admin/trade/buy'/>" method="get" >
    <input id="thingsid" type="text" name="thingsid" value='${thingsid}' required >
    <input type="submit" id="tijiao" name="tijiao" value="get"/>
    </form>
    </div>
    
    <!-- .panel-heading -->
    <div class="panel-body"  style="background-color:#FFFFCC">
		<form:form id="group-form" method="post">
			<div class="btn-group" role="group"  style="background-color:#FFFFCC">
		    <a href="javascript:history.go(-1)" class="btn btn-outline btn-info" >返回</a>
			<a  href="#" onclick="post()" class="btn btn-outline btn-info">                            
		    [与求租者联系]
			</a>
			<a hre="#" onclick="tijiao()" class="btn btn-outline btn-info">
			[我要出租]
			</a>
			</div>
        </form:form>
    </div>
    <div class="table-responsive" >
    <table class="table table-striped" >
    <thead>
    <tr>
    <th></th>
    <th>物品信息</th>
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
    <div><input type="checkbox" id="satellite" onclick="satellite(this)"/>卫星地图</div>
    <tr class="warning">
    <th>物品放置地方</th>
    <th>${thingsplace}</th>
    </tr>
    </table>
    </div>
    <div id="container" style="width:auto; top:475px;" ></div> 
 </div>
 
 <script>
var maker,map=new AMap.Map('container',{resizeEnable:true,
    zoom:18,
    center:[112.498586, 22.239637]});
//展示卫星地图
var Satellite= new AMap.TileLayer.Satellite({zIndex:10});
Satellite.setMap(map);
Satellite.hide();
function satellite(checkbox){
  if(checkbox.checked){
     Satellite.show();
}
 else{
     Satellite.hide();
}
}
var lat=${thingslng};
var lng=${thingslat};
addmarker(lng,lat);
function addmarker(jingdu,weidu){
marker = new AMap.Marker({                           //丅自定义图标
icon:new AMap.Icon({size:new AMap.Size(40,50),image:"http://webapi.amap.com/theme/v1.3/images/newpc/way_btn2.png",imageOffset:new AMap.Pixel(0,-60)}),
position:[jingdu,weidu]
});
marker.setMap(map);
map.panTo([jingdu,weidu]);
}
function post(){
	$('#post').trigger("click");
}
function tijiao(){
	alert("租借成功");
	$('#tijiao').trigger("click");
}
</script>
 
 


