<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@	taglib prefix="woo" uri="/WEB-INF/classes/woo.tld" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<style>
<!--
.cart-list div {
	border-bottom: 2px solid #ccc;
}
-->
</style>

<div class="cart-list"></div>

<div>
	合计金额：<span id="totalAmount"></span>元，
	合计数量：<span id="totalItems"></span>件
	<a href="javascript:void(0)" class="list_oreder_2 btnCartClean">清空购物车</a>
</div>

<div>　</div>
<form class="addToForm" onsubmit="return false;">
	<input type="text" class="num" name="_qty1"/>
	<input type="text" class="num" name="_qty2"/>
	<input type="text" class="num" name="_qty3"/>
</form>
<a href="javascript:void(0)" class="list_oreder_2 btnBuyCart">加入购物车</a>
<script type="text/javascript">
$(document).ready(function() { 
	
	//购物车列表
	$.get("/cart/list", function(resp){
		var html = "";
		for (var i = 0; i < resp.cartItems.length; i++) {
			html += "<div> <p>"+resp.cartItems[i].productName+"</p> <p>"+resp.cartItems[i].price+"</p> <p>"+resp.cartItems[i].orderQty+"</p> <p>"+resp.cartItems[i].id+"</p> </div>";
			html += "<a href='javascript:void(0)' data-prodid='"+resp.cartItems[i].id+"' class='list_oreder_2 btnDel'>删除</a>";
		}
		$(".cart-list").append(html);
		$("#totalAmount").text(resp.totalAmount);
		$("#totalItems").text(resp.totalItems);
	});
	
	//加入购物车
	$(".btnBuyCart").on("click", function(){
		var $form = $(".addToForm"),
			ajaxUrl = '/cart/add',
			type ='get';
		
		$form.ajaxSubmit({
			url: ajaxUrl,
			type: type,
			dataType: 'json',
			success: function(resp){
				if (resp.r == 1) {
					alert("成功");
				}
			}
		});
	});
	
	//清空购物车
	$(".btnCartClean").on("click", function(){
		$.get("/cart/clear", function(resp){
			if (resp.r == 1) {
				alert("成功");
			}
		})
	});
	
	//删除
	$(".cart-list").delegate(".btnDel", "click", function(){
		var prodId = $(this).data("prodid");
		$.get("/cart/del", {"p": prodId}, function(resp){
			if (resp.r == 1) {
				alert("成功");
			}
		})
	});
	
});
</script>