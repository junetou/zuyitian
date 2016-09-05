<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri='http://www.springframework.org/security/tags' prefix='security'%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@	taglib uri="/WEB-INF/classes/woo.tld" prefix="woo"%>



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
     <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0; background-color:#FFFFCC; ">
         <div class="navbar-header"  >
             <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                 <span class="sr-only">Toggle navigation</span>
                 <span class="icon-bar"></span>
                 <span class="icon-bar"></span>
                 <span class="icon-bar"></span>
             </button> 
             <a href="<c:url value='/portal.htmls'/>"><img alt="" src="<woo:url value="\\static\\images\\2.png"/>"></a>
         </div>
        
         <!-- /.navbar-header -->
          <!-- 
         <ul class="nav navbar-top-links navbar-right" >   
          -->
             <!-- /.dropdown -->
           <!-- 
             <li class="dropdown">
                 <a href="<c:url value='/logout'/>" >
                                                                  注销
                 -->
                   <!--  <i class="fa fa-user fa-fw"></i>  <i class="fa fa-caret-down"></i> <i class="fa fa-sign-out fa-fw"></i>  -->
                 <!-- 
                 </a>
             </li>
         </ul>
         -->
         
         <div class="navbar-default sidebar main-menu" role="navigation" style="background-color:#FFFFCC" >
         
             <div class="sidebar-nav navbar-collapse" style="background-color:#FFFFCC">
			<ul class="nav" id="side-menu">
			 <li class="dropdown"> <a href="<c:url value='/logout'/>" >
             <i class="fa fa-user fa-fw"></i>注销</a></li>
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
		</ul>
	</div>

         </div> 
         <!-- /.navbar-static-side -->
     </nav>
      
    <div id="page-wrapper" style="background-color:#FFFFCC">
		<iframe id="mainFrame" name="mainFrame" frameborder="0"  width="100%" ></iframe>
    
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
</script>