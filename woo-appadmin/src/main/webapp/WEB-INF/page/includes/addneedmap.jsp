<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@	taglib uri="/WEB-INF/classes/woo.tld" prefix="woo"%>
<style>
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


<div class="panel panel-default" style="width:530px;"   style="background-color:#FFFFCC">
   <div class="panel-heading" style="width:530px; background-color:#FFFFCC; "  >
    <ol class="breadcrumb" style="background-color:#FFFFCC" >
     <li><b><i>求租地图</i></b></li>
     <li><input type="text" id="keyWord" placeholder="Search for..." name="keyWord" style="background-color:#FFFFCC" />
     <span><input type="button"  onclick="javascript:seachMap()" value="搜索" ></span>
     </li>
    </ol>
   </div>
   <div width="auto;">
     <table class="table table-striped" width="auto;" >
     <form action="<c:url value='/portal/admin/need/addmessage'/>" method="post" >
      <thead>
      <tr>
      <th></th>
      <th>请在下面填写信息</th>
      </tr>
      </thead>
      <tr class="success">
      <th>求租物品名称(必填)</th>
      <th><input id="thingsname" type="text" name="thingsname"  required /></th>
      </tr>
      <tr class="warning">
      <th>物品详细信息(必填)</th>
      <th><input id="thingsdesc" type="text" name="thingsdesc"  required /></th>
      </tr>
      <tr class="success">
      <th>物品价格(￥/天)(必填)</th>
      <th><input id="thingsprice" type="text" name="thingsprice" required /></th>
      </tr>
      <tr class="warning">
      <th>物品最长可租借时间(以一天为单位)(必填)</th>
      <th><input id="thingsdate" type="text" name="thingsdate" required /></th>
      </tr>
      <tr class="success">
      <th>物品种类(必填，不清楚填无)</th>
      <th><input id="thingskind" type="text" name="thingskind" required /></th>
      </tr>
      <tr class="warning">
      <th>电话号码(必填)</th>
      <th><input id="thingsphone" type="text" name="thingsphone" required /></th>
      </tr>
      <tr class="success">
      <th>物品位置(必填)</th>
      <th><input id="thingsaddr" type="text" name="thingsaddr" required /></th>
      </tr>
      <tr class="success">
      <th><p>经度(点击地图即可获得)(必填)</p><input id="lng" name="lng" type="text" readonly="readonly" required /></th>
      <th><p>纬度</p><input id="lat" name="lat" type="text" readonly="readonly" required /></th>
      </tr>
      <tr>
      <th></th>
      <th><input type="submit" id="post" name="post" value="我要发布"/></th>
      </tr>
     </form>
     </table>
   <h1>错误提醒：${error}</h1>
   </div>
   <div id="container" style="width:530px; top:600px;" ></div>
</div>

<script>
var maker,map=new AMap.Map('container',{resizeEnable:true,
    zoom:10,
    center:[121.498586, 31.239637]});
//比例尺插件
map.plugin(["AMap.Scale"],function(){
    var scale = new AMap.Scale();
    map.addControl(scale);  
});
//地图操作工具条插件
map.plugin(["AMap.ToolBar"],function(){
    var tool = new AMap.ToolBar();
    map.addControl(tool);   
});
//浏览器定位插件
map.plugin(["AMap.Geolocation"],function(){
geolocation123=new AMap.Geolocation(
{
enableHighAccuracy: true,
timeout: 10000,  
buttonOffset: new AMap.Pixel(5,5),
zoomToAccuracy: true,  
buttonPosition:'RB'
});
map.addControl(geolocation123);
});
//搜索控件
function seachMap(){
	var mapaddr=$("#keyWord").val();
	AMap.service(["AMap.PlaceSearch"],function()
	{
	var placeSearch=new AMap.PlaceSearch({
	pageSize:1,
	pageIndex:0,
	city:"010",
	map:map                                     
	});
	placeSearch.search(mapaddr,function(status,result){});
	});
}
//获取经纬度
var clickEventListener=map.on('click', function(e){
var lng=e.lnglat.getLng();
var lat=e.lnglat.getLat();
showlnglat(lng,lat);
});

function showlnglat(lng,lat){
	document.getElementById("lng").value=lng;
	document.getElementById("lat").value=lat;
}
</script>


 