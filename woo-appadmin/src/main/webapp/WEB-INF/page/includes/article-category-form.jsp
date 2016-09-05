<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@	taglib uri="/WEB-INF/classes/woo.tld" prefix="woo"%>

<style>
	.btnWidth{width: 200px;}
</style>

<div class="panel panel-default">
	<div class="container-fluid">
	
		<div class="row" style="margin-top: 20px; margin-bottom: 20px; width: 90%;">
			<form:form id="categoryForm" cssClass="form-horizontal">
				<div class="tab-content">
					<div class="tab-pane active" id="info-tab">
						<div class="form-group myHeight">
							<label class="col-sm-2 control-label">父级分类<sup>*</sup></label>
							<div class="col-sm-10">
								 <form:select path="parentId" items="${categories }" itemLabel="name" itemValue="id" cssClass="form-control" htmlEscape="false" />
							</div>
						</div>
						<div class="form-group myHeight">
							<label class="col-sm-2 control-label">分类名称<sup>*</sup></label>
							<div class="col-sm-10">
								<form:input cssClass="form-control" path="name" />
							</div>
						</div>
						<div class="form-group myHeight">
							<label class="col-sm-2 control-label">分类描述<sup>*</sup></label>
							<div class="col-sm-10">
								<form:input cssClass="form-control" path="description" />
							</div>
						</div>
						<div class="form-group myHeight">
							<label class="col-sm-2 control-label">分类序号<sup>*</sup></label>
							<div class="col-sm-10">
								<form:input cssClass="form-control" path="sort" />
							</div>
						</div>
						<div class="form-group myHeight">
							<label class="col-sm-2 control-label">URL链接<sup></sup></label>
							<div class="col-sm-10">
								<form:input cssClass="form-control" path="likeUrl" />
							</div>
						</div>
						<div class="form-group myHeight">
			                <label class="col-sm-2 control-label">状态<sup>*</sup></label>
			                <div class="col-sm-10">
				                <form:select path="state" cssClass="form-control">
				                	<form:option value="Active" label="活动"/>
				                	<form:option value="Deprecated" label="禁用"/>
				                </form:select>
			                </div>
			            </div>
						
						<div class="form-group myHeight">
							<label class="col-sm-2 control-label">ICON图标<sup></sup></label>
							<div class="col-sm-10">
								<input id="input-1a" type="file" name="uploadImage" multiple value="${command.iconUrl }"  class="file-loading">
								<c:if test="${empty command.iconUrl }">
				                	<div id="kv-success-2" class="alert alert-success fade in" style="margin-top:10px; display: none;"></div>
				                </c:if>
				                <c:if test="${not empty command.iconUrl }">
				                	<div id="kv-success-2" class="alert alert-success fade in" style="margin-top:10px;">
				                		<h4>ICON图标</h4>
				                		<ul><li><img alt="" src="<woo:url value="${command.iconUrl }"/>" height="80"></li></ul>
				                	</div>
				                </c:if>
							</div>
						</div>
						
					</div>
				</div>

				<div class="form-group" style="text-align: center;">
					<div class="col-sm-offset-2 col-sm-10">
						<form:hidden path="id" />
						<form:hidden path="iconUrl"/>
						<button type="submit" class="btn btn-primary btnWidth">保存</button>
					</div>
				</div>
			</form:form>
		</div>
		
	</div>
</div>

<script>
	$(document).ready(function() {
		
		$("#input-1a").fileinput({
		    uploadUrl: "<c:url value='/portal/upload/upload-image?folderType=icon'/>", // server upload action
		    uploadAsync: false,
		    showPreview: false,
		    allowedFileExtensions: ['png','jpg', 'jpge', 'gif'],
		    maxFileCount: 5,
		    elErrorContainer: '#kv-error-2'
		}).on('filebatchpreupload', function(event, data, id, index) {
		    $('#kv-success-2').html('<h4>上传状态</h4><ul></ul>').hide();
		}).on('filebatchuploadsuccess', function(event, data) {
			$("#iconUrl").val(data.response.msgs);
		    var out = '';
		    $.each(data.files, function(key, file) {
		        var fname = file.name;
		        out = out + '<li>' + '上传的文件 # ' + (key + 1) + ' - '  +  fname + ' 成功.' + '</li>';
		    });
		    $('#kv-success-2 ul').append(out);
		    $('#kv-success-2').fadeIn('slow');
		});
		
		$('#categoryForm').bootstrapValidator({
			excluded : [ ':disabled' ],
			feedbackIcons : {
				valid : 'glyphicon glyphicon-ok',
				invalid : 'glyphicon glyphicon-remove',
				validating : 'glyphicon glyphicon-refresh'
			},
		 	submitHandler: function(form) {
	        	var datas = $("#categoryForm").toJson();
	         	$.post("<c:url value='/portal/web/article/category/saveOrUpdate'/>", datas, function(resp){
	         		if (resp.r == 1){
	         			$.dialog({
	         				title: "提示",
	         			    content: "保存成功！"
	         			});
	        			setTimeout(function(){parent.location.reload(); }, 1500); 
	        		} else {
	        			$.dialog({
	         				title: "提示",
	         			    content: "操作失败，" + resp.msgs
	         			});
	        		}
	         	}, "json");
	         	$('#register-form').slideUp('fast');
	        },
			fields : {
				name : {
					validators : {
						notEmpty : {
							message : '分类名称不能为空'
						}
					}
				},
				description : {
					validators : {
						notEmpty : {
							message : '分类描述不能为空'
						}
					}
				},
				sort : {
					validators : {
						notEmpty : {
							message : '分类序号不能为空'
						},
						regexp: {
	                        regexp: /^[0-9]+$/,
	                        message: '请输入数字'
	                    }
					}
				}
			}
		});
	});
</script>