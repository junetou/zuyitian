<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@	taglib uri="/WEB-INF/classes/woo.tld" prefix="woo"%>

<script type="text/javascript" src="http://api.map.baidu.com/library/CityList/1.2/src/CityList_min.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/library/SearchInfoWindow/1.5/src/SearchInfoWindow_min.js"></script>
<link rel="stylesheet" href="http://api.map.baidu.com/library/SearchInfoWindow/1.5/src/SearchInfoWindow_min.css" />
<title>百度地图的Hello, World</title>

<div id="container"></div>

<script type="text/javascript">
var map = new BMap.Map("allmap");  
map.centerAndZoom(new BMap.Point(104.071216,30.666031), 15);
map.enableScrollWheelZoom(); 
</script>