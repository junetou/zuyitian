$.fn.toJson = function (){
    var jsonObj = {};
    var objs = $(this).serializeArray();
    $.each(objs, function(key, obj){
    	var val = jsonObj[obj.name];
    	if (val){
    		val = val + "," + obj.value;
    	}
    	else{
    		val = obj.value;
    	}
        jsonObj[obj.name] = val;
    });
    return jsonObj;
}

 $.ajaxSetup({
    contentType:"application/x-www-form-urlencoded;charset=utf-8",
    complete:function(XMLHttpRequest,textStatus){
    var sessionstatus=XMLHttpRequest.getResponseHeader("sessionstatus");
        if(sessionstatus=="NoAuthority"){
            $.dialog({
                title: "提示!",
                content: "抱歉，您没有操作权限！"
            });
        }
        if(sessionstatus=="NoOperationAuthority"){
             $.dialog({
                title: "提示!",
                content: "抱歉，您没有操作权限！"
            });
        }
     }
});

function getRootPath(){  
    //获取当前网址，如： http://localhost:8083/proj/meun.jsp  
    var curWwwPath = window.document.location.href;  
    //获取主机地址之后的目录，如： proj/meun.jsp  
    var pathName = window.document.location.pathname;  
    var pos = curWwwPath.indexOf(pathName);  
    //获取主机地址，如： http://localhost:8083  
    var localhostPath = curWwwPath.substring(0, pos);  
    //获取带"/"的项目名，如：/proj  
    var projectName = pathName.substring(0, pathName.substr(1).indexOf('/')+1);  
    return(localhostPath + projectName);  
}


$(function(){
    //删除信息
    $(".btnDel").on("click", function() {
        var id = $(this).data("id");
        var url = getRootPath() + "/" + $(this).data("url");
        $.confirm({
            title: '提示!', content: '确定删除吗？', confirmButton: '确定', cancelButton: '取消',
            confirm: function(){
                $.post(url, {"id": id}, function(resp){
                    if (resp.r == 1) {
                        $.dialog({title: "提示", content: "删除成功！" });
                        setTimeout(function(){ location.reload(); }, 1500); 
                    } else {
                        $.dialog({title: "提示", content: "操作失败，" + resp.msgs });
                    }
                });
            }
        });
    });
    //删除信息
    $(".btnDelPromotion").on("click", function() {
        var prodId = $(this).data("prod");
        var pid = $(this).data("pid");
        var url = getRootPath() + "/" + $(this).data("url");
        $.confirm({
            title: '提示!', content: '确定删除吗？', confirmButton: '确定', cancelButton: '取消',
            confirm: function(){
                $.post(url, {"prodId": prodId, "pid":pid}, function(resp){
                    if (resp.r == 1) {
                        $.dialog({title: "提示", content: "删除成功！" });
                        setTimeout(function(){ location.reload(); }, 1500); 
                    } else {
                        $.dialog({title: "提示", content: "操作失败，" + resp.msgs });
                    }
                });
            }
        });
    });
    //锁定账户
    $(".btnLock").on("click", function(){
        var id = $(this).data("id");
        var userName = $(this).data("name");
        var url = getRootPath() + "/" + $(this).data("url");
        $.confirm({
            title: '提示!', content: '您确定锁定“'+userName+'”用户？', confirmButton: '确定', cancelButton: '取消',
            confirm: function(){
                $.post(url, {"id": id}, function(resp){
                    if (resp.r == 1) {
                        $.dialog({title: "提示", content: "锁定‘"+userName+"’用户成功" });
                        setTimeout(function(){ location.reload(); }, 1500); 
                    } else {
                        $.dialog({title: "提示", content: "操作失败，" + resp.msgs });
                    }
                });
            }
        });
    });
    //激活账户
    $(".btnUnlock").on("click", function(){
        var id = $(this).data("id");
        var userName = $(this).data("name");
        var url = getRootPath() + "/" + $(this).data("url");
        $.confirm({
            title: '提示!', content: '您确定解锁“'+userName+'”用户？', confirmButton: '确定', cancelButton: '取消',
            confirm: function(){
                $.post(url, {"id": id}, function(resp){
                    if (resp.r == 1) {
                        $.dialog({title: "提示", content: "解锁‘"+userName+"’用户成功" });
                        setTimeout(function(){ location.reload(); }, 1500);
                    } else {
                        $.dialog({title: "提示", content: "操作失败，" + resp.msgs });
                    }
                });
            }
        });
    });
    //商品上架
    $(".btnUpper").on("click", function(){
        var id = $(this).data("id");
        var name = $(this).data("name");
        var url = getRootPath() + "/" + $(this).data("url");
        $.confirm({
            title: '提示!', content: '您确定上架“'+name+'”商品？', confirmButton: '确定', cancelButton: '取消',
            confirm: function(){
                $.post(url, {"id": id}, function(resp){
                    if (resp.r == 1) {
                        $.dialog({title: "提示", content: "上架‘"+name+"’商品成功" });
                        setTimeout(function(){ location.reload(); }, 1500); 
                    } else {
                        $.dialog({title: "提示", content: "操作失败，" + resp.msgs });
                    }
                });
            }
        });
    });
    //商品下架
    $(".btnLower").on("click", function(){
        var id = $(this).data("id");
        var name = $(this).data("name");
        var url = getRootPath() + "/" + $(this).data("url");
        $.confirm({
            title: '提示!', content: '您确定下架“'+name+'”商品？', confirmButton: '确定', cancelButton: '取消',
            confirm: function(){
                $.post(url, {"id": id}, function(resp){
                    if (resp.r == 1) {
                        $.dialog({title: "提示", content: "下架‘"+name+"’商品成功" });
                        setTimeout(function(){ location.reload(); }, 1500);
                    } else {
                        $.dialog({title: "提示", content: "操作失败，" + resp.msgs });
                    }
                });
            }
        });
    });
    //商品评论审核
    $(".btnExamine").on("click", function(){
        var id = $(this).data("id");
        var name = $(this).data("name");
        var url = getRootPath() + "/" + $(this).data("url");
        $.confirm({
            title: '提示!', content: '您确定审核“'+name+'”商品评论？', confirmButton: '确定', cancelButton: '取消',
            confirm: function(){
                $.post(url, {"id": id}, function(resp){
                    if (resp.r == 1) {
                        $.dialog({title: "提示", content: "审核‘"+name+"’商品成功" });
                        setTimeout(function(){ location.reload(); }, 1500);
                    } else {
                        $.dialog({title: "提示", content: "操作失败，" + resp.msgs });
                    }
                });
            }
        });
    });
    //编辑
    $(".btnEdit").on("click", function(){
        var id = $(this).data("id");
        var url = getRootPath() + "/" + $(this).data("url") + "/" + id;
        location.href = url;
    });
    //重置密码
    $(".btnReset").on("click", function() {
    	var id = $(this).data("id");
        var url = getRootPath() + "/" + $(this).data("url");
        $("#userId").val(id);
        $("#reset-form").attr("action", url);
    	$('#myModal').modal('show');
    });
    //提交重置密码的表单
    $('#reset-form').bootstrapValidator({
        message: '此值无效',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        submitHandler: function(form) {
        	var datas = $("#reset-form").toJson();
        	var url = $("#reset-form").attr("action");
         	$.post(url, datas, function(resp){
         		if (resp.r == 1){
         			$.dialog({
         				title: "提示",
         			    content: resp.msgs
         			});
        			$("#userId").val("");
        			$('#myModal').modal('hide');
        		} else {
        			$.dialog({
         				title: "提示",
         			    content: "操作失败，" + resp.msgs
         			});
        		}
         	}, "json");
         	$('#register-form').slideUp('fast');
        },
        fields: {
            password: {
                validators: {
                    notEmpty: {
                        message: '密码是必需的，并且不能是空的'
                    },
                    stringLength: {
                        min: 8,
                        max: 16,
                        message: '密码必须大于8，小于16个字符长'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9_\.]+$/,
                        message: '密码只能由字母、数字、点和下划线组成'
                    }
                }
            }
        }
    });
	//弹框编辑
	$(".btnEditBig").on("click", function() {
		$('#myWindow').modal('show');
		var id = $(this).data("id");
        var url = getRootPath() + "/" + $(this).data("url") + "/" + id;
		$("#window-form").attr("src", url)
	});
    //弹框编辑
    $(".btnPromotion").on("click", function() {
        $('#myWindow').modal('show');
        var prodId = $(this).data("prod");
        var pid = $(this).data("pid");
        var url = getRootPath() + "/" + $(this).data("url") + "/" + pid + "?prodId=" + prodId;
        $("#window-form").attr("src", url)
    });
    //弹框编辑
    $(".btnEditBigWindow").on("click", function() {
        $('#myWindow').modal('show');
        var url = getRootPath() + "/" + $(this).data("url");
        $("#window-form").attr("src", url)
    });
	
	
	//弹框编辑
	$(".btnEditRegion").on("click", function() {
		$('#myWindow').modal('show');
		var id = $(this).data("id");
		var grade = $(this).data("grade");
		var regionId = $(this).data("regionid");
        var url = getRootPath() + "/" + $(this).data("url") + "/" + id + "?grade=" + grade + "&regionId=" + regionId;
		$("#window-form").attr("src", url)
	});
	//删除区域
	$(".btnDelRegion").on("click", function() {
		var id = $(this).data("id");
		var grade = $(this).data("grade");
		var url = getRootPath() + "/" + $(this).data("url");
        $.confirm({
            title: '提示!', content: '确定删除吗？', confirmButton: '确定', cancelButton: '取消',
            confirm: function(){
                $.post(url, {"id": id, "grade":grade}, function(resp){
                    if (resp.r == 1) {
                        $.dialog({title: "提示", content: "删除成功！" });
                        setTimeout(function(){ location.reload(); }, 1500); 
                    } else {
                        $.dialog({title: "提示", content: "操作失败，" + resp.msgs });
                    }
                });
            }
        });
	});

	
	//添加
	$(".newSomething").on("click", function() {
		$('#myWindow').modal('show');
		var id = $(this).data("id");
        var url = getRootPath() + "/" + $(this).data("url") + "/" + id;
		$("#window-form").attr("src", url)
	});
	
	//选择成员
	$(".selectAccount").on("click", function() {
		$('#myWindow').modal('show');
        var url = getRootPath() + "/" + $(this).data("url");
		$("#window-form").attr("src", url)
	});
	
	//修改
	$(".ajaxUpdateSomething").on("click", function() {
        var id = $(this).data("id");
        var url = getRootPath() + "/" + $(this).data("url");
        $.confirm({
            title: '提示!', content: '确定修改吗？', confirmButton: '确定', cancelButton: '取消',
            confirm: function(){
                $.post(url, {"id": id}, function(resp){
                    if (resp.r == 1) {
                        $.dialog({title: "提示", content: "修改成功！" });
                        setTimeout(function(){ location.reload(); }, 1500); 
                    } else {
                        $.dialog({title: "提示", content: "操作失败，" + resp.msgs });
                    }
                });
            }
        });
    });
	
	//批量删除
	$(".batchDeleteSomething").on("click", function() {
		var datas = $("#tableForm").serialize();
		var url = getRootPath() + "/" + $(this).data("url");
		if(datas == null || datas == "") {
			 $.dialog({title: "提示", content: "请选中选项!" });
		} else {
			$.confirm({
				title: '提示!', content: '确定删除选中的选项吗？', confirmButton: '确定', cancelButton: '取消',
				confirm: function(){
					$.post(url, datas, function(resp){
						if (resp.r == 1) {
							$.dialog({title: "提示", content: "删除成功！" });
							setTimeout(function(){ location.reload(); }, 1500); 
						} else {
							$.dialog({title: "提示", content: "操作失败，" + resp.msgs });
						}
					});
				}
			});
		}
	});
	
	//批量选择
	$(".batchSelect").on("click", function() {
		window.parent.$("#hiddenArea").empty();
		var datas = $("#tableForm").serialize();
		var url = getRootPath() + "/" + $(this).data("url");
		$.post(url, datas, function(resp){
			if (resp.r == 1) {
				window.parent.$('#myWindow').modal('hide');
				window.parent.$('#accountNames').val(resp.accountNames);
				window.parent.$("#hiddenArea").append("<input type='hidden' name='accounts' value='" + resp.accountIds + "' />");
			} else {
				$.dialog({title: "提示", content: "操作失败，" + resp.msgs });
			}
		});
	});
	
	//选择全部
	$(".selectAllAccount").on("click", function() {
		$("#hiddenArea").empty();
		$('#accountNames').val("全体成员");
		$("#hiddenArea").append("<input type='hidden' name='accounts' value='0' />");
	});
	
	
    //右侧链接打开新的右侧
    $(".openNewRightPage").on("click", function(){
        var url = getRootPath() + "/" + $(this).data("url");
        location.href = url;
    });
	
	 //右侧链接打开新的右侧
    $(".openNewRightPage2").on("click", function(){
        var url = getRootPath() + "/" + $(this).data("url");
		var id = $(this).data("id");
        location.href = url + "?id=" + id;
    });
	
	
	//查看部分成员
	$(".viewSomeAccount").on("click", function() {
		$('#myWindow').modal('show');
		var id = $(this).data("id");
		var url = getRootPath() + "/" + $(this).data("url") + "/" + id;
		$("#window-form").attr("src", url)
	});
})
