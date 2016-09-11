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
.hei{
    height:100px;
    width:1080px;
    maxlength:300;
}
</style>

<head><title>租一天</title></head>

<script type="text/javascript">
function change()
{
    $(":text").each(function() {
        $("<textarea rows='3' clos='5' />").val($(this).val()).insertAfter($(this));
        $(this).remove();
    });
}
change();
</script>

<div class="panel panel-default" style="background-color:#FFFFCC; width:420px;" >
   <div class="panel-heading" style="background-color:#FFFFCC; width:420px;">
    <ol class="breadcrumb" style="background-color:#FFFFCC; width:420px;">
     <li><b><i>反馈</i></b></li>
     <li><a href="javascript:history.go(-1)" class="btn btn-outline btn-info" >返回</a></li>   
    </ol>
   </div>
   <div style="background-color:#FFFFCC">
     <table class="table table-striped" style="background-color:#FFFFCC;">
     <form action="<c:url value='/portal/admin/fed/fed'/>" method="post" >
      <thead>
      <tr>
      <th>请在下面填写信息</th>
      </tr>
      </thead>
      <tr style="background-color:#FFFFCC" >
      <th><textarea id="thingsname" cols="5" rows="2" name="thingsname" class="hei" style="background-color:#FFFFCC; width:400px; height:500px; " ></textarea></th>
 <!--   <input id="thingsname" type="text" name="thingsname"  class="hei" required /></th> -->   
      </tr>      
      <tr>
      <th><input type="submit" id="post" name="post" value="提交"/></th>
      </tr>
     </form>
     </table>
     <h1>${message}</h1>
   </div>
</div>

 