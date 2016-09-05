<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@	taglib prefix="woo" uri="/WEB-INF/classes/woo.tld" %>

<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<link rel="icon" href="<woo:url value='/static/images/ico.ico'/>" type="image/x-icon">
	<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<link href="<woo:url value='/static/bootstrap/css/bootstrap.min.css'/>" rel="stylesheet">
	<link href="<woo:url value='/static/bootstrap/css/metisMenu.min.css'/>" rel="stylesheet">
	<link href="<woo:url value='/static/bootstrap/css/timeline.css'/>" rel="stylesheet">
	<link href="<woo:url value='/static/bootstrap/css/sb-admin-2.css'/>" rel="stylesheet">
	<link href="<woo:url value='/static/bootstrap/css/morris.css'/>" rel="stylesheet">
	<link href="<woo:url value='/static/bootstrap/css/font-awesome.min.css'/>" rel="stylesheet" type="text/css">
	<link href="<woo:url value='/static/bootstrap/css/style.css'/>" rel="stylesheet">
	<link href="<woo:url value='/static/bootstrap/css/bootstrap-multiselect.css'/>" rel="stylesheet" type="text/css">
	<link href="<woo:url value='/static/bootstrap/css/fileinput.css'/>" rel="stylesheet" type="text/css">
	<link href="<woo:url value='/static/bootstrap/css/jquery-confirm.css'/>" rel="stylesheet" type="text/css"/>
	
	<!--[if lt IE 9]>
	    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
	    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
	<![endif]-->
	
	<script src="<woo:url value='/static/bootstrap/jquery/jquery.min.js'/>"></script>
	<script src="<woo:url value='/static/bootstrap/jquery/jquery.form.js'/>"></script>
	<script src="<woo:url value='/static/bootstrap/js/bootstrap.min.js'/>"></script>
	<script src="<woo:url value='/static/bootstrap/js/bootstrap3-validation.js'/>"></script>
	<script src="<woo:url value='/static/bootstrap/js/bootstrapValidator.js'/>"></script>
	<script src="<woo:url value='/static/bootstrap/js/bootstrap-multiselect.js'/>"></script>
	<script src="<woo:url value='/static/bootstrap/js/metisMenu.min.js'/>"></script>
	<script src="<woo:url value='/static/bootstrap/js/raphael-min.js'/>"></script>
	<script src="<woo:url value='/static/bootstrap/js/morris.min.js'/>"></script>
	<script src="<woo:url value='/static/bootstrap/js/sb-admin-2.js'/>"></script>
	<script src="<woo:url value='/static/bootstrap/js/handlebars-1.0.0.beta.6.js'/>"></script>
	<script src="<woo:url value='/static/bootstrap/js/fileinput.js'/>" type="text/javascript"></script>
	<script src="<woo:url value='/static/bootstrap/js/fileinput_locale_zh.js'/>" type="text/javascript"></script>
	<script src="<woo:url value='/static/bootstrap/js/common.js'/>"></script>
	<script src="<woo:url value='/static/bootstrap/js/jquery-confirm.js'/>" type="text/javascript"></script>
	<script src="<woo:url value='/static/bootstrap/js/jquery.autocomplete.js'/>" type="text/javascript"></script>
</head>

<body>


<div class="mainContainer">
 <tiles:insertAttribute name="content"/> 	
</div>

<script type="text/javascript">
$(function(){
	$(document).ajaxError(function(e, xhr, settings, exception){
		if(xhr.status==401){
			window.parent.location.reload(false);
		}
	});
})
</script>
</body>
</html>