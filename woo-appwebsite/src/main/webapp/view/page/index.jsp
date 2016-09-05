<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@	taglib prefix="woo" uri="/WEB-INF/classes/woo.tld" %>

<form action="/search" method="get">
	<input type="text"  name="title" id="title" maxlength="100" value="${param.title }" >
	<input type="submit" value="百度一下">
</form>
