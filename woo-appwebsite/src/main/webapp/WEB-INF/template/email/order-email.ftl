<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" Content="text/html; Charset=utf-8"></meta>
<title>打印订单</title>
<style>

body {
	margin-top:0px;
	padding-top: 0px;
	position: relative;
	font-size: 12px;
	font-family: SimHei;
}
div,ul,li,dl,dt,dd {
	margin: 0px;
	padding: 0px;
}

h1,h2,h3,h4,h5,h6,.h1,.h2,.h3,.h4,.h5,.h6 {
	font-family: inherit;
	font-weight: 500;
	line-height: 1.1;
	color: inherit;
}
a{
	text-decoration: none;
}
h4,.h4 {
	font-size: 18px;
}
h4,.h4,h5,.h5,h6,.h6 {
	margin-top: 10px;
	margin-bottom: 10px;
}
.order-det .order-info h3 {
	margin: 0;
}
h3,.h3 {
	font-size: 24px;
}
ul {
	list-style: none;
}

.order-det .summary {
	font-size: 14px;
	background: #fffdee;
	border: 1px solid #ffda8b;
	line-height: 40px;
	padding-left: 10px;
}

.order-det .summary li {
	display: inline-block;
	width: 23%;
}

.order-det .order-info {
	background: #f3f3f3;
	border: 1px solid #e3e3e3;
	padding: 5px;
	margin-top: 30px;
}

.order-det .order-info .ui-dlist {
	width: 100%;
}

.ui-dlist {
	display: inline-block;
	color: #808080;
	font-size: 12px;
	line-height: 2.2;
}

.ui-dlist-tit {
	float: left;
	width: 20%;
	text-align: right;
	margin: 0;
}

.ui-dlist-det {
	float: left;
	width: 80%;
	text-align: left;
	margin: 0;
}

.order-det .order-info div {
	border-bottom: 1px solid #e3e3e3;
	padding: 10px;
	background: #fff;
}

.order-det .itm-ls table {
	width: 100%;
	border: 1px solid #d1d1d1;
}

table {
	background-color: transparent;
}

th {
	text-align: left;
}

.order-det .itm-ls table th {
	background: #f3f3f3;
	line-height: 30px;
	padding-left: 5px;
}

.order-det .itm-ls table td {
	padding: 10px 0 0px 5px;
}

.unit-price {
	color: red;
	font: 12px "Arial";
}

.order-det .itm-ls .ui-dlist {
	width: 100%;
}

.order-det .itm-ls .ui-dlist-tit {
	width: 80%;
}

.order-det .itm-ls .ui-dlist-det {
	width: 120px;
	text-align: right;
}

.amt {
	color: red;
	font: 16px "Arial";
	font-weight: bold;
}
.logo_head{
	padding:5px;
	border-bottom:2px solid #ccc;
	height:30px;
	position:relative;
}
.logo_head img{
	display:inline-block;
	width:200px;
	height:50px;
	position:absolute;
	left:0px;
	border:none;
	background-color: white;
}
.logo_head a{
	display:inline-block;
	color:#428bca;
	position:absolute;
	right:0px;
	bottom:0px;
	font-size:14px;
}
</style>
</head>

<body>
	<div class="logo_head" id="header">
		<div>
			<img src="${host!''}resources/images/hxsj_logo.jpg"/>
			<a href="http://www.nj-reagent.com/" style="text-decoration: none;">http://www.nj-reagent.com</a>
		</div>
	</div>
	<div class="order-det">
		<div class="order-info">
			<div>
				<h4>尊敬的 ${username!''} 您好：</h4>
				<p>
					您于${order.orderDate}在南京试剂网( <a href="http://www.nj-reagent.com/">http://www.nj-reagent.com/</a>) 已成功提交订单，
					订单编号：<a href="${host!''}order-central/detail/${order.orderNum!'0'}.htm">${order.orderNum!''}</a>，
				</p>
			</div>
			
			<div class="itm-ls">
				<h4>订单信息</h4>
				<table>
					<tbody>
						<tr>
							<th>产品</th>
							<th>单价</th>
							<th>购买数量</th>
							<th>金额</th>
						</tr>
						<#if order.items??>
							<#list order.items as itm>
								<tr>
									<td>${itm.prodNum!''}</td>
									<td>￥ ${itm.actualPrice!''}</td>
									<td>${itm.orderQty!''}</td>
									<td><span class="unit-price">${itm.totalAmount!''}</span> 元</td>
								</tr>
								<tr class="desc">
									<td colspan="4" style="border-bottom:1px solid #ccc;">${itm.specification!''}</td>
								</tr>
							</#list>
						</#if>
					</tbody>
				</table>
				<div class="subamt">
					<dl class="ui-dlist">
						<dt class="ui-dlist-tit">2件商品，共计商品金额：</dt>
						<dd class="ui-dlist-det">
							<span class="amt"> ${order.subAmount!''}</span> 元
						</dd>
						<dt class="ui-dlist-tit">运费：</dt>
						<dd class="ui-dlist-det">
							<span class="amt"> ${order.freightAmount!''}</span> 元
						</dd>
						<dt class="ui-dlist-tit">总金额：</dt>
						<dd class="ui-dlist-det">
							<span class="amt"> ${order.totalAmount!''}</span> 元
						</dd>
					</dl>
				</div>
				<div style="text-align:right;">Copyright © 2014 M.MAOCHONG Co,Ltd All rights reserved</div>
			</div>
			
		</div>
		
	</div>
</body>
</html>

