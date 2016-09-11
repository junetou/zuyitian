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
     <li><b><i>地图</i></b></li>
     <li><input type="text" id="keyWord" placeholder="Search for..." name="keyWord" style="background-color:#FFFFCC" />
     <span><input type="button"  onclick="javascript:seachMap()" value="搜索" ></span>
     </li>
    </ol>
   </div>
   <div width="auto;">
     <table class="table table-striped" width="auto;" >
     <form action="<c:url value='/portal/admin/addmessage/message'/>" method="post" enctype="multipart/form-data" >
      <thead>
      <tr>
      <th></th>
      <th>请在下面填写信息</th>
      </tr>
      </thead>
      <tr class="success">
      <th>物品名称(必填)</th>
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
      <tr class="warning">
      <th>上传图片(必填,只能上传jpg、jpeg,gif格式，否则提交错误)<input type="file" name="file0" id="file0" multiple="true"  onchange="getPhotoSize(this)" required/></th>
      <th class="success">
      <img src="" id="img0"  width="192px" height="50px"  ></th>
      </tr>
      <tr class="success">
      <th><p>经度(点击地图即可获得)(必填)</p><input id="lng" name="lng" type="text" readonly="readonly" required /></th>
      <th><p>纬度</p><input id="lat" name="lat" type="text" readonly="readonly" required /></th>
      </tr>
      <tr>
      <th></th>
      <th><input type="submit" id="post" name="post" onclick="uploadFile()" value="我要发布"/></th>
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
//上传图片
/*
$("#file0").change(function(){
	var objUrl = getObjectURL(this.files[0]) ;
	console.log("objUrl = "+objUrl) ;
	if (objUrl) {
		$("#img0").attr("src", objUrl) ;
	}
	if(window.File && window.FileList) {
	      var fd = new FormData();
	      var files = document.getElementById('file0').files;
	      for (var i = 0; i < files.length; i++) {  
	        fd.append("file"+i, files[i]);
	      }
	      var xhr = new XMLHttpRequest();
	      xhr.open("POST", document.getElementById('file0').action);
	      xhr.send(fd);
	    } 
}) ;
*/
//建立一個可存取到該file的url
function getObjectURL(file) {
	var url = null ; 
	if (window.createObjectURL!=undefined) { // basic
		url = window.createObjectURL(file) ;
	} else if (window.URL!=undefined) { // mozilla(firefox)
		url = window.URL.createObjectURL(file) ;
	} else if (window.webkitURL!=undefined) { // webkit or chrome
		url = window.webkitURL.createObjectURL(file) ;
	}
	return url ;
}
//上传到后台
function uploadFile(){  
    var fileObj = document.getElementById("file0").files[0]; // 获取文件对象  
    var FileController = "entityServlet1"; // 接收上传文件的后台地址  
              
    if(fileObj){  
         // FormData 对象  
             var form = new FormData();   
             form.append("file", fileObj);// 文件对象     
      
             // XMLHttpRequest 对象  
             var xhr = new XMLHttpRequest();      
             xhr.open("post", FileController, true);      
             xhr.onload = function () {      
             };   
             xhr.send(form);  
                  
    }else{  
        alert("未选择文件");  
    }  
}  
//判断图片是否合格
function getPhotoSize(obj){
	photoExt=obj.value.substr(obj.value.lastIndexOf(".")).toLowerCase();
	if(photoExt!='.jpg' && photoExt!='.jpeg' && photoExt!='.bmp' && photoExt!='.gif'){
		alert("请重新上传，图片不符合规格");
		$("#img0").attr("src","") ;
		return false;
	}
	var fileSize=0;
	var isIE = /msie/i.test(navigator.userAgent) && !window.opera;  
	if (isIE && !obj.files) {          
         var filePath = obj.value;            
         var fileSystem = new ActiveXObject("Scripting.FileSystemObject");   
         var file = fileSystem.GetFile (filePath);               
         fileSize = file.Size;         
    }
	else {  
         fileSize = obj.files[0].size;     
    } 
	fileSize=Math.round(fileSize/1024*100)/100;
	var objUrl = getObjectURL(obj.files[0]) ;
	console.log("objUrl = "+objUrl) ;
	if (objUrl) {
		$("#img0").attr("src", objUrl) ;
	}
}
</script>


 