<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@	taglib prefix="woo" uri="/WEB-INF/classes/woo.tld" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <meta name="format-detection" content="telephone=no" />
	<title>${title}</title>
	
	<link href="<woo:url value='/static/bootstrap/css/bootstrap.css'/>" rel="stylesheet">
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
	<script src="<woo:url value='/static/bootstrap/js/fileinput.js'/>" type="text/javascript"></script>
	<script src="<woo:url value='/static/bootstrap/js/fileinput_locale_zh.js'/>" type="text/javascript"></script>
	<script src="<woo:url value='/static/bootstrap/js/jquery-confirm.js'/>" type="text/javascript"></script>
	<script src="<woo:url value='/static/bootstrap/js/jquery.autocomplete.js'/>" type="text/javascript"></script>
	
</head>
<body>
	
	<tiles:insertAttribute name="content"/>
    
</body>
</html>