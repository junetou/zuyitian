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



<div class="panel panel-default" style="background-color:#FFFFCC ">
    <div class="panel-heading" style="background-color:#FFFFCC ">
        <ol class="breadcrumb" style="background-color:#FFFFCC ">
		  	<li><a href="javascript:history.go(-1)"><b><i>地图</i></b></a></li>
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
    <div class="panel-body">
		<form:form id="group-form" method="post">
			<div class="btn-group" role="group" style="margin-bottom:20px;">
				<a href="#" class="btn btn-outline btn-info" onclick="post()">我要与卖家联系</a>
				<a href="#" class="btn btn-outline btn-info" onclick="tijiao()">我要租借</a>
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
    <th>物品名字</th>
    <th>${thingsname}</th>
    </tr>
    <tr class="warnning">
    <th>物品详细信息</th>
    <th>${thingsdesc}</th>
    </tr>
    <tr class="success">
    <th>物品价格</th>
    <th>${thingsprice}</th>
    </tr>
    </table>
    </div>  
    <div style="background-color:#FFFFCC "></div>
 </div>
 

 <script type="text/javascript">
function post(){
$('#post').trigger("click");
}
function tijiao(){
	alert("租借成功");
	$('#tijiao').trigger("click");
}
</script>
 
 


