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
    .info{
            border: solid 1px silver;
    }
    div.info-top{
            position: relative;
            background: none repeat scroll 0 0 #F9F9F9;
            border-bottom: 1px solid #CCC;
            border-radius: 5px 5px 0 0;
    }
    div.info-top div{
            display: inline-block;
            color: #333333;
            font-size: 14px;
            font-weight: bold;
            line-height: 31px;
            padding: 0 10px;
    }
    div.info-top img{
            position: absolute;
            top: 10px;
            right: 10px;
            width:10px;
            height:10px;
            transition-duration: 0.25s;
    }
    div.info-top img:hover{
            box-shadow: 0px 0px 5px #000;
    }
    div.info-middle{
            font-size: 12px;
            padding: 6px;
            line-height: 20px;
    }  
    div.info-bottom{
            height: 0px;
            width: 100%;
            clear: both;
            text-align: center;
    }   
    div.info-bottom img{
            position: relative;
            z-index: 104;
              width:50px;
            height:50px;
    }
    span{
            margin-left: 5px;
            font-size: 11px;
    }
    .info-middle img {
            float: left;
            margin-right: 6px;
            width:80px;
            height:50px;
    }
</style>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width">
    <title>求租地图</title>
<link rel="stylesheet" href="http://cache.amap.com/lbs/static/main1119.css"/>
<script src="http://cache.amap.com/lbs/static/es5.min.js"></script>
<script src="http://webapi.amap.com/maps?v=1.3&key=3be8235f1cb10f423e1fdbdd0c9be773"></script>
<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.0.js"></script>
</head>

<body>

<div class="panel panel-default" style="background-color:#FFFFCC " >
    <div class="panel-heading" style="background-color:#FFFFCC ">
        <ol class="breadcrumb" style="background-color:#FFFFCC ">
		  <!--  	<li><a href="#">首页 </a></li> -->
		  	<li><a href="javascript:history.go(-1)" ><b><i>返回</i></b></a></li>
		  	<li style="text-align:center"><input type="text" name="keyWord" id="keyWord" placeholder="Search for..." style="background-color:#FFFFCC"  >
		  	<span><input type="button"  onclick="javascript:seachMap()" value="搜索" ></span>
		  	</li>
		</ol>
    </div>
</div>
 
<div style="display:none" >
<form action="<c:url value='/portal/admin/need/showmessage'/>" method="get" >
<input id="usrname" type="text" name="usrname" value="1" required >
<input type="submit" id="post" name="post" value="get"/>
</form>
</div>



<div id="container" style=" height:93%; width:auto; top:10%" >
<div><input type="checkbox" id="satellite" onclick="satellite(this)"/>卫星地图</div>
</div>




<script>
var maker,map=new AMap.Map('container',{resizeEnable:true,
    zoom:8,
    center:[112.498586, 22.239637]});
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
//移动
map.on('click',function(e){ 
var x=e.lnglat.getLng();
var y=e.lnglat.getLat();
map.panTo([x,y])});
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
//循环遍历
<c:forEach var="message" items="${usemessage}">
var lat=${message.getLng()};
var lng=${message.getLat()};
var usrid=${message.getNeed()};
var price=${message.getPrice()};
var addr='${message.getAddr()}';
var phone='${message.getPhone()}';
var name='${message.getName()}';
var judge='${message.getOveranalyzed()}';
if(judge==1){
addmarker(lng,lat,usrid,price,phone,addr,name);
}
</c:forEach>


function addmarker(jingdu,weidu,usrid,price,phone,addr,name){
marker = new AMap.Marker({                           //丅自定义图标
icon:new AMap.Icon({size:new AMap.Size(40,50),image:"http://webapi.amap.com/theme/v1.3/images/newpc/way_btn2.png",imageOffset:new AMap.Pixel(0,-60)}),
position:[jingdu,weidu]
});
marker.setMap(map);
//marker.on('click',openInfo());
marker.setTitle("主要信息");
AMap.event.addListener(marker, 'click', function(){openInfo(jingdu,weidu,usrid,price,phone,addr,name);});
}
function openInfo(lng1,lat1,useid,prices,phones,addrs,name){
    //构建信息窗体中显示的内容
    var maininformation="主要信息";
    var style3='<span style="font-size:11px;color:blue;">';
    var namestyle='物品名字:';
    var names=name;
    var style4='</span>';
    var style1='<span style="font-size:11px;color:#F00;">';
    var pricestyle='价格(￥/天):';
    var price=prices;
    var style2='</span>';
    var sum=maininformation+style3+namestyle+names;
    var content=[];
    useidnumber(useid);
    var img='<img src="<woo:url value="/static/images/4.png"/>">'; 
    var dizhi='地址:';
    var addr=addrs;
    var overlay=img+dizhi+addr;
    var p1="价格:";
    var p2=prices;
    var phone=p1+p2;
    content.push(overlay);
    content.push(phone);
    content.push('<a href="#" onclick="post()" >详细信息</a>');
    /*
    var info = [];
    info.push('<a href="#" onclick="post()" >详细信息</a>');
    info.push('<a href="<c:url value="/portal/admin/mapmessage"/>" onclick="post()" >详细信息</a>');
    */
    infoWindow = new AMap.InfoWindow({
    	isCustom:true,
        content:createInfoWindow(sum,content.join("<br/>")),  //使用默认信息窗体框样式，显示信息内容
        offset: new AMap.Pixel(28,-40)
    });
    infoWindow.open(map,[lng1,lat1]);
}
function createInfoWindow(title, content) {
	var info = document.createElement("div");
	info.className = "info";
	var top = document.createElement("div");
	var titleD = document.createElement("div");
	var closeX = document.createElement("img");
	top.className = "info-top";
	titleD.innerHTML = title;
	closeX.src = "http://webapi.amap.com/images/close2.gif";
	closeX.onclick = closeInfoWindow;

	top.appendChild(titleD);
	top.appendChild(closeX);
	info.appendChild(top);
	// 定义中部内容
	var middle = document.createElement("div");
	middle.className = "info-middle";
	middle.style.backgroundColor = 'white';
	middle.innerHTML = content;
	info.appendChild(middle);
    // 定义底部内容
	var bottom = document.createElement("div");
	bottom.className = "info-bottom";
	bottom.style.position = 'relative';
	bottom.style.top = '0px';
	bottom.style.margin = '0 auto';
	var sharp = document.createElement("img");
	sharp.src = "http://webapi.amap.com/images/sharp.png";
	bottom.appendChild(sharp);
	info.appendChild(bottom);
	return info;
}
//关闭信息窗体
function closeInfoWindow() {
map.clearInfoWindow();
}

function useidnumber(usrid){
	$("#usrname").val(usrid);
}
function post(){
	$('#post').trigger("click");
}
//搜索功能
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



</script>

</body>


