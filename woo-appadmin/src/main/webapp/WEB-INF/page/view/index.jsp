<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri='http://www.springframework.org/security/tags' prefix='security'%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@	taglib uri="/WEB-INF/classes/woo.tld" prefix="woo"%>

<head>	
<link rel="icon" href="<woo:url value='/static/images/02.ico'/>" type="image/x-icon">
<link rel="shortcut icon" href="<woo:url value='/static/images/favicon.ico'/>" type="image/x-icon" /> 
</head>

<style type="text/css">
	#page-wrapper {padding:0;}
	.tips-btn{
	position: absolute;
    top: 2px;
    right: 0;
    width: 16px;
    height: 16px;
    border-radius: 50%;
    background: #ff0033;
    text-align: center;
    line-height: 16px;
    font-size: 12px;
    color: #fff;
    z-index:99999;
	}
</style>


<div id="wrapper" style="background-color:#FFFFCC " >
     <!-- Navigation -->
     <!-- -->
     <nav class="navbar navbar-default navbar-static-top " role="navigation" style="margin-bottom: 0; background-color:#FFFFCC; ">
         <!--  
         <div class="navbar-header"  >
             <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                 <span class="sr-only">Toggle navigation</span>
                 <span class="icon-bar"></span>
                 <span class="icon-bar"></span>
                 <span class="icon-bar"></span>
             </button> 
             <a href="<c:url value='/portal.htmls'/>"><img alt="" src="<woo:url value="\\static\\images\\2.png"/>"></a>
         </div> 
         <div class="navbar-default sidebar main-menu" role="navigation" style="background-color:#FFFFCC" >
             <div class="sidebar-nav navbar-collapse" style="background-color:#FFFFCC">
			-->
			<ul class="nav" id="side-menu">
			 <li class="dropdown"><img alt="" src="<woo:url value="/static/images/4.png"/>" id="bgImg" </li>
                   <!--  <i class="fa fa-user fa-fw"></i>  <i class="fa fa-caret-down"></i> <i class="fa fa-sign-out fa-fw"></i>  -->
			<c:forEach var="menu" items="${menus}">
                   <li>
                   	<a href="#"><i class="${menu.classIcon}"></i> ${menu.name}<span class="fa arrow"></span></a>
                   	<ul class="nav nav-second-level">
						<c:forEach items="${menu.nodes }" var="m">
							<li><!--   target="mainFrame" -->
								<a href="${m.likeUrl }"  ><i class="${m.classIcon}"></i> ${m.name }</a>
							</li>
						</c:forEach>
						
					</ul>
                   </li>
                </c:forEach>
                <li><a href="<c:url value='/portal/admin/legal/showlegal'/>" > <i class="glyphicon glyphicon-exclamation-sign"></i>法律声明</a></li>
                <li><a href="<c:url value='/logout'/>" > <i class="fa fa-user fa-fw"></i>注销账户</a></li>
		</ul>
	<!--  </div> 

         </div>  --> 
         <!-- /.navbar-static-side -->
      </nav>  
     <!-- 
    <div id="page-wrapper" style="background-color:#FFFFCC">
		<iframe id="mainFrame" name="mainFrame" frameborder="0"  width="100%" ></iframe>
    
    </div> 
    -->
     <div style="background-color:#FFFFCC; width:auto; height:400px;">
     </div>
</div>



<script>
	$(document).ready(function(){
		var ifm = document.getElementById("mainFrame");
		var height = document.documentElement.clientHeight-60 || document.body.clientHeight-60;
		$(ifm).height(height);
		window.onresize = function () {
			var ifm= document.getElementById("mainFrame");
			var height = document.documentElement.clientHeight-60 || document.body.clientHeight-60;
			$(ifm).height(height);
		}
	});
	window.onload=function()
	{
	    var oImg=document.getElementById('bgImg');
	    oImg.style.width='100%';
	    oImg.style.height=document.documentElement.clientHeight/8+'px';
	    //高度为可视区的1/3
	}
</script>