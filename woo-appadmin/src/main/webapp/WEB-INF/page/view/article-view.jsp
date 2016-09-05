<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@	taglib uri="/WEB-INF/classes/woo.tld" prefix="woo"%>

<div class="panel panel-default">
	<div class="panel-heading">
        <ol class="breadcrumb">
		  	<li><a href="#">内容管理</a></li>
		  	<li class="active" onclick="javascript:location.reload();">文章管理</li>
		</ol>
    </div>
    
	<div class="panel-body">
		
		<div class="row toolbar">
			<div class="col-sm-7">
				<div class="btn-group" role="group">
				  	<a href="javascript:location.reload();" class="btn btn-outline btn-info"><i class="glyphicon glyphicon-refresh"></i> 刷新</a>
				  	<woo:permission operationType="ARTICLE_ADD" roleType="ROLE_WEB">
                    	<a class="btn btn-outline btn-info" href="<c:url value='/portal/web/article/form/0' />"><i class="fa fa-plus-circle"></i> 新建</a>
               		</woo:permission>
				  	<div class="btn-group">
				    	<button type="button" class="btn btn-outline btn-info" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
				    		批量操作 <span class="caret"></span>
				    	</button>
				      	<ul class="dropdown-menu">
				      		<woo:permission operationType="ARTICLE_DELETE" roleType="ROLE_WEB">
                       			<li><a class="batchDeleteSomething" onclick="javascript:void(0);" data-url="portal/web/article/batchDelete"> 删除</a></li>
                       		</woo:permission>
                       		<woo:permission operationType="ARTICLE_EXAMINE" roleType="ROLE_WEB">
                       			<li><a class="batchExamineSomething" onclick="javascript:void(0);" data-url="portal/web/article/batchExamine"> 审核</a></li>
                       		</woo:permission>
                       		<woo:permission operationType="ARTICLE_RELEASE" roleType="ROLE_WEB">
                       			<li><a class="batchPublishSomething" onclick="javascript:void(0);" data-url="portal/web/article/batchPublish"> 发布</a></li>
                       		</woo:permission>
			     		</ul>
				    </div>
				  	
				  	<div class="btn-group">
				    	<button type="button" class="btn btn-outline btn-info" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
				    		文章分类 <span class="caret"></span>
				    	</button>
				      	<ul class="dropdown-menu">
				      		<li><a href="<c:url value="/portal/web/article"/>?applyState=${param.applyState }&state=${param.state }&categoryId=">全部</a></li>
						    <li role="separator" class="divider"></li>
				      		<c:forEach var="category" items="${categoryDisplays }">
				        		<li><a href="<c:url value='/portal/web/article' />?applyState=${param.applyState }&state=${param.state }&categoryId=${category.id }">${category.name }</a></li>
				      		</c:forEach>
			     		</ul>
				    </div>
				    <div class="btn-group">
				    	<button type="button" class="btn btn-outline btn-info" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
				    		文章状态 <span class="caret"></span>
				    	</button>
				      	<ul class="dropdown-menu">
				      		<li><a href="<c:url value="/portal/web/article"/>?applyState=${param.applyState }&state=&categoryId=${param.categoryId }">全部</a></li>
						    <li role="separator" class="divider"></li>
		        			<li><a href="<c:url value='/portal/web/article' />?applyState=${param.applyState }&state=10&categoryId=${param.categoryId }">等待发布</a></li>
		        			<li><a href="<c:url value='/portal/web/article' />?applyState=${param.applyState }&state=20&categoryId=${param.categoryId }">已发布</a></li>
			     		</ul>
				    </div>
				    <div class="btn-group">
				    	<button type="button" class="btn btn-outline btn-info dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
				    		审核状态 <span class="caret"></span>
				    	</button>
				      	<ul class="dropdown-menu">
				      		<li><a href="<c:url value="/portal/web/article"/>?applyState=&state=${param.state }&categoryId=${param.categoryId }">全部</a></li>
						    <li role="separator" class="divider"></li>
		        			<li><a href="<c:url value='/portal/web/article' />?applyState=10&state=${param.state }&categoryId=${param.categoryId }">等待审核</a></li>
		        			<li><a href="<c:url value='/portal/web/article' />?applyState=20&state=${param.state }&categoryId=${param.categoryId }">已审核</a></li>
			     		</ul>
				    </div>
				    <woo:permission operationType="ARTICLE_EXPORT" roleType="ROLE_WEB">
                		<a href="javascript:;" class="btn btn-outline btn-info btnExport"><i class="fa fa-file-excel-o"></i> Excel 导出</a>
                	</woo:permission>
				</div>
			</div>
			<div class="col-sm-5">
				<form method="get">
					<div class="input-group">
						<input type="text" name="keyword" id="keyword" value="${param.keyword }" class="form-control" placeholder="Search for...">
						<input type="hidden" name="state" value=${param.state }>
						<input type="hidden" name="applyState" value=${param.applyState }>
						<input type="hidden" name="categoryId" value=${param.categoryId }>
						<span class="input-group-btn">
							<button type="submit" class="btn btn-default btnSearchSubmit"><i class="fa fa-search"></i> 搜索</button>
							<button class="btn btn-default" type="button" data-toggle="modal" data-target="#exampleModal"><i class="glyphicon glyphicon-share-alt"></i> 更多</button>
						</span>
		      		</div>
		 		</form>
			</div>
		</div>
		
		<div class="table-responsive">
		    <table class="table table-hover">
		        <thead>
		            <tr>
	                    <th style="text-align:center;" width="3%"><input id="checkAll" type="checkbox"></th>
	                    <th width="20%">标题</th>
	                    <th width="8%">图片</th>
	                    <th width="10%">文章分类</th>
	                    <th width="9%">审核状态</th>
	                    <th width="8%">文章状态</th>
	                    <th width="7%">浏览次数</th>
	                    <th width="8%">创建日期</th>
	                    <th width="28%">操作</th>
		            </tr>
		        </thead>
	         	<tbody>
		       		<form id="tableForm">
			         	<c:forEach items="${grid.datas }" varStatus="index" var="data">
				            <tr>
				                <td style="text-align: center;"><input type="checkbox" name="articles" value="${data.id }" /></td>
				                <td <c:if test="${data.hotspot eq '10'}">style="color:#f00;"</c:if>>
				                	
				                	<c:choose>
				                		<c:when test="${fn:length(data.title) > 15}">${fn:substring(data.title, 0, 15)}...</c:when>
				                		<c:otherwise>${data.title }</c:otherwise>
				                	</c:choose>
				                </td>
				             	<td><img alt="" src="<woo:url value="${data.summaryImage }"/>" height="22"></td>
				                <td>${data.categoryNames }</td>
				                <td><spring:message code="article.examine.${data.examineState }"/></td>
				                <td><spring:message code="article.state.${data.state }"/></td>
				                <td>${data.viewNumber }</td>
				                <td>${data.createDate }</td>
				                <td>
	                        		<woo:permission operationType="ARTICLE_VIEW" roleType="ROLE_WEB">
	                        			<a href="<c:url value='/portal/web/article/show' />?id=${data.id }">[查看更多]</a> 
	                        		</woo:permission>
	                        		<woo:permission operationType="ARTICLE_EDIT" roleType="ROLE_WEB">
	                        			<a href="javascript:void(0);" data-id="${data.id }" class="btnEdit" data-url="portal/web/article/form">[编辑]</a> 
	                        		</woo:permission>
	                        		<woo:permission operationType="ARTICLE_DELETE" roleType="ROLE_WEB">
	                        			<a href="javascript:void(0);" data-id="${data.id }" class="btnDel" data-url="portal/web/article/delete">[删除]</a> 
	                        		</woo:permission>
	                        		<woo:permission operationType="ARTICLE_RELEASE" roleType="ROLE_WEB">
	                        			<c:choose>
								       		<c:when test="${data.state eq '10'}"> 
								             	<a class="ajaxUpdateSomething" data-id="${data.id }" data-url="portal/web/article/publish" href="javascript:void(0);">[发布]</a> 
								       		</c:when>
								       		<c:when test="${data.state eq '20'}"> 
								             	<a class="ajaxUpdateSomething" data-id="${data.id }" data-url="portal/web/article/unpublish" href="javascript:void(0);">[撤销发布]</a> 
								       		</c:when>
										</c:choose>
	                        		</woo:permission>
	                        		<woo:permission operationType="ARTICLE_EXAMINE" roleType="ROLE_WEB">
	                        			<c:if test="${data.examineState eq '10'}">
                        					 <a class="ajaxUpdateSomething" data-id="${data.id }" data-url="portal/web/article/examine" href="javascript:void(0);">[审核]</a> 
                        				</c:if>
                        				<c:if test="${data.examineState eq '20'}">
                        					 <span>[已审核]</span>
                        				</c:if>
	                        		</woo:permission>
								</td>
				            </tr>
			            </c:forEach>
					</form>
		            <c:if test="${grid.datas == null}">
						<tr>
							<td colspan="9">暂无相关记录！</td>
						</tr>
					</c:if>
		         </tbody>
			</table>
		</div>
		
	</div>
	
	<div class="panel-footer">
        <div class="row">
        	<div class="dataTables_paginate paging_simple_numbers" id="dataTables-example_paginate">
        		<woo:pager pgm="${grid.pgm }"/> 
   			</div>
    	</div>
    </div>
		
</div>


<div class="modal fade" id="myWindow" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  	<div class="modal-dialog modal-lg" role="document">
	    <div class="modal-content">
	    	
	      	<div class="modal-header">
	        	<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        	<h4 class="modal-title" id="myModalLabel">编辑</h4>
	      	</div>
	      	
	      	<iframe id="window-form" name="window-form" frameborder="0" src="" height="400" width="100%" scrolling="no"></iframe>
	      	
	    </div>
  	</div>
</div>

<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
  	<div class="modal-dialog" role="document">
    	<div class="modal-content">
      		<div class="modal-header">
        		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        		<h4 class="modal-title" id="exampleModalLabel">更多条件...</h4>
      		</div>
      		<form id="seniorSearchKwyword" method="get">
      			<div class="modal-body">
          			
          			<div class="form-group">
            			<label for="recipient-name" class="control-label">起始日期</label>
            			<input id="dateStart" name="dateStart" value="${param.dateStart}" type="text" class="form-control" id="recipient-name"/>
          			</div>
          			
          			<div class="form-group">
            			<label for="recipient-name" class="control-label">截止日期</label>
            			<input id="dateEnd" name="dateEnd" value="${param.dateEnd}" type="text" class="form-control" id="recipient-name"/>
          			</div>
        		
      			</div>
	      		<div class="modal-footer">
					<input type="hidden" name="state" value=${param.state }>
					<input type="hidden" name="applyState" value=${param.applyState }>
					<input type="hidden" name="categoryId" value=${param.categoryId }>
			        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			        <button type="submit" class="btn btn-primary">搜索</button>
	      		</div>
      		</form>
    	</div>
  	</div>
</div>


<script src="<woo:url value='/static/bootstrap/js/moment-with-locales.js'/>"></script> 
<script src="<woo:url value='/static/bootstrap/js/bootstrap-datetimepicker.min.js'/>"></script>
<script>
$(document).ready(function() {
	$(".batchExamineSomething").on("click", function() {
		var datas = $("#tableForm").serialize();
		var url = getRootPath() + "/" + $(this).data("url");
		if(datas == null || datas == "") {
			 $.dialog({title: "提示", content: "请选中选项!" });
		} else {
			$.confirm({
				title: '提示!', content: '确定审核选中的选项吗？', confirmButton: '确定', cancelButton: '取消',
				confirm: function(){
					$.post(url, datas, function(resp){
						if (resp.r == 1) {
							$.dialog({title: "提示", content: "审核成功！" });
							setTimeout(function(){ location.reload(); }, 1500); 
						} else {
							$.dialog({title: "提示", content: "操作失败，" + resp.msgs });
						}
					});
				}
			});
		}
	});
	

	$(".batchPublishSomething").on("click", function() {
		var datas = $("#tableForm").serialize();
		var url = getRootPath() + "/" + $(this).data("url");
		if(datas == null || datas == "") {
			 $.dialog({title: "提示", content: "请选中选项!" });
		} else {
			$.confirm({
				title: '提示!', content: '确定发布选中的选项吗？', confirmButton: '确定', cancelButton: '取消',
				confirm: function(){
					$.post(url, datas, function(resp){
						if (resp.r == 1) {
							$.dialog({title: "提示", content: "发布成功！" });
							setTimeout(function(){ location.reload(); }, 1500); 
						} else {
							$.dialog({title: "提示", content: "操作失败，" + resp.msgs });
						}
					});
				}
			});
		}
	});
	
	
	$(".btnExport").on("click", function() {
		var url = "<c:url value='/portal/web/article/export'/>?keyword=${param.keyword }&categoryId=${param.categoryId}&state=${param.state}&applyState=${param.applyState}";
		var datas = $("#seniorSearchKwyword").toJson();
		for(var key in datas) {
			url += "&" + key + "=" + datas[key];
 		}
		$.confirm({
            title: '提示~', content: '您确定导出数据吗？', confirmButton: '确定', cancelButton: '取消',
            confirm: function(){
            	location.href = url;
            }
        });
	});
	
	$('#dateStart').datetimepicker({
		locale: "zh-cn",
		format: 'YYYY/MM/DD'
    });
    $('#dateEnd').datetimepicker({
    	locale: "zh-cn",
    	format: 'YYYY/MM/DD'
    });
    $('#seniorSearchKwyword').bootstrapValidator({
        fields: {
        	dateStart: {
                validators: {
                    date: {
                        format: 'YYYY/MM/DD',
                        message: '此值无效，请选择一个日期'
                    }
                }
            },
            dateEnd: {
                validators: {
                    date: {
                        format: 'YYYY/MM/DD',
                        message: '此值无效，请选择一个日期'
                    }
                }
            }
        }
    });
});
</script>
<script>
$(function(){
	$("#checkAll").click(function() {
        $('input[name="articles"]').prop("checked", this.checked);
	}); 
    var $subBox = $("input[name='articles']");
    $subBox.click(function(){
        $("#checkAll").prop("checked",$subBox.length == $("input[name='articles']:checked").length ? true : false);
    });
})
</script>