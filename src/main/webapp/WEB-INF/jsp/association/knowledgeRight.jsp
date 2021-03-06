<%@page import="cn.com.pansky.otp5.baseplatform.dao.po.Dictionary"%>
<%@page
	import="cn.com.pansky.otp5.baseplatform.enums.DictionaryTypeEnum"%>
<%@page import="cn.com.pansky.otp5.common.DictionaryUtil"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%-- <%@ page import="cn.com.pansky.otp5.baseplatform.enums.ResourceTypeEnum"%>	 --%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>知识产权信息</title>

<style type="text/css">

.datepicker {  
z-index: 9999;  
top: 0;  
left: 0;  
padding: 4px;  
margin-top: 1px;  
-webkit-border-radius: 4px;  
-moz-border-radius: 4px;  
border-radius: 4px;  
}  

</style>


</head>
<body>
	<div id="searchArea" style="margin-top: 20px;">
			<form class="form-horizontal" id="form-search">
				<div class="form-group">
					<!-- <label class="col-sm-1 control-label" for="name">姓名</label>
					<div class="col-sm-3">
						<input class="form-control" id="name" type="text"
							placeholder="请输入" />
					</div> -->
					<div class="col-sm-3">
					<!-- 	<button type="button" class="btn btn-primary">查询</button> -->
						<button type="button" class="btn btn-info" id="contact_add_btn"  data-toggle="modal" data-target="#knowledgeRight-add-modal">新增</button>
	
					</div>
				</div>
			</form>
	</div>
	
	
	<table id="knowledgeRight-table" class="col-xs-12" data-toolbar="#toolbar"></table>
	
	
	<div class="modal fade" id="knowledgeRight-add-modal" role="dialog" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">知识产权信息</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" id="knowledgeRight_add_form">
					
					<input type="hidden" name="eId" id="knowledgeRight_eId" value="">
					<input type="hidden" name="id" >
					
						<div class="form-group">
							<label class="col-sm-3 control-label" for="name">名称</label>
							<div class="col-sm-8">
								<input class="form-control" name="name" type="text"
									placeholder="请输入" />
							</div>
						</div>
					
						<div class="form-group">
							<label class="col-sm-3 control-label" for="code">受理号/授权号/登记号</label>
							<div class="col-sm-8">
								<input class="form-control" name="code" type="text"
									placeholder="请输入" />
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-3 control-label" >知识产权类型</label>
							<div class="col-sm-8">
								<select class="form-control" name="type" id="type">
									<option value="">-请选择-</option>
									<c:forEach var="dic" items="${DIC008}" varStatus="status">
										<option value="${dic.getCode()}">${dic.getName()}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						
						
						<div class="form-group ">
							<label for="dtp_input2" class="col-sm-3 control-label">通过时间</label>
							<div class="col-sm-8">
								<div  class="input-group date form_date" data-date=""  data-date-format="" data-link-field="passTime" data-link-format="yyyy-mm-dd">
									<input id="knowledge_form_date_passTime" class="form-control" size="10" type="text" value="" readonly>
									<span class="input-group-addon"><span
										class="glyphicon glyphicon-remove"></span></span> <span
										class="input-group-addon"><span
										class="glyphicon glyphicon-calendar"></span></span>
								</div>
								<input type="hidden" id="passTime" name="passTime" value="" />
							</div>
						</div>
						
						
						
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" id="knowledgeRight-add-btn">提交</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
	
	


<script type="text/javascript">

setEId(getEId());

$(document).ready(function() {
	//初始化表格
	initTable();
	
	$('#knowledgeRight-add-modal').on('hide.bs.modal', function () {
		$("#knowledgeRight_add_form")[0].reset();
		setEId(getEId());
		$("#knowledgeRight_add_form").find("input[name='id']").val("");
		resetValid();
	})
	
	$('.form_date').datetimepicker({
				 format: "yyyy-mm-dd",
				 autoclose: true,
				 todayBtn: true,
				 pickerPosition: "bottom-left",
				 minView:2,
			     language: "zh-CN" //设置 提示语言
			});
	
	$('.form_date').datetimepicker().on('changeDate', function(ev) {
		//
		}).on('hide', function(event) {
		event.preventDefault();
		event.stopPropagation();
		});
	
	knowledgeRight_add_form_bind_valid();
});


function initTable() {
	$('#knowledgeRight-table').bootstrapTable({
		method : 'post',
		contentType : "application/x-www-form-urlencoded",
		//toolbar : '#toolbar', //工具按钮用哪个容器
		striped : true, //是否显示行间隔色
		//cache : false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
		pagination : true, //是否显示分页（*）
		sortable : false, //是否启用排序
		sortOrder : "asc", //排序方式
		pageNumber : 1, //初始化加载第一页，默认第一页
		pageSize : CONSTANT.pageSize, //每页的记录行数（*）
		pageList : CONSTANT.pageList, //可供选择的每页的行数（*）
		url : "knowledgeRight/findByPage",//这个接口需要处理bootstrap table传递的固定参数
		queryParamsType : '', //默认值为 'limit' ,在默认情况下 传给服务端的参数为：offset,limit,sort
		// 设置为 ''  在这种情况下传给服务器的参数为：pageSize,pageNumber

		queryParams: queryParams,//前端调用服务时，会默认传递上边提到的参数，如果需要添加自定义参数，可以自定义一个函数返回请求参数
		sidePagination : "server", //分页方式：client客户端分页，server服务端分页（*）
		//search: true,      //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
		//strictSearch : true,
		//showColumns: true,     //是否显示所有的列
		//showRefresh: true,     //是否显示刷新按钮
		//minimumCountColumns : 2, //最少允许的列数
		clickToSelect : true, //是否启用点击选中行
		searchOnEnterKey : true,
		pagination : true,
		columns : [ {
			field : 'SerialNumber',
			title : '序号',
			align : 'center',
			formatter : function(value, row, index) {
				return index + 1;
			}
		}, {
			field : 'id',
			title : 'ID',
			align : 'center',
			visible: false
		}, {
			field : 'name',
			title : '名称',
			align : 'center'
		}, {
			field : 'code',
			title : '受理号/授权号/登记号',
			align : 'center'
		},{
			field : 'typeName',
			title : '知识产权类型',
			align : 'center'
		},{
			field : 'passTime',
			title : '通过时间',
			align : 'center'
		}, {
			field : '',
			title : '操作',
			align : 'center',
			formatter : function(value, row, index) {

				//通过formatter可以自定义列显示的内容
				//value：当前field的值，即id
				//row：当前行的数据
				
				
				//var a = '</span><span class="glyphicon glyphicon-user"></span>';
				var edit = '<button title="修改" class="update-enterprise" data-id="'+row.id+'" onClick="showUpdate(\''+row.id+'\')"><span  class="glyphicon glyphicon-pencil"></button>';
				var del  = '<button title="删除" class="update-enterprise" data-id="'+row.id+'" onClick="deleteKnowledgeRight(\''+row.id+'\')"><span  class="glyphicon glyphicon-trash"></button>';
				return edit + del;
			}
		} ]
	});
};


//设置传入参数
function queryParams(params) {
    var obj = {  
		pageSize: params.pageSize,
        pageNumber: params.pageNumber,
        eId : getEId()
    };
    return obj;
}

//设置企业基本信息页面的企业ID
function setEId(eId){
	//alertPansky(eId);
	$("#knowledgeRight_eId").val(eId);
}

function getEId(){
	return window.parent.getEId_enterpriseSingle();
}


//保存知识产权信息
$("#knowledgeRight-add-btn").click(function(){
	
	if(form_isValid("knowledgeRight_add_form")){
		//alertPansky(JSON.stringify($("#contact_add_form").serialize()));
		var id = $("#knowledgeRight_add_form").find("input[name='id']").val();
		//alertPansky(id);
		//return ;
		if(id!=""){
			update();
		}else{
			$.ajax({
				  url: "knowledgeRight/add",
				  type: 'POST',
				  dataType: "JSON",
				  data: $("#knowledgeRight_add_form").serialize(),
				  success: function(resp){
					  if(resp.flag==1){
						$("#knowledgeRight-add-modal").modal('hide');
						  alertPansky("保存成功！");
						  refreshTable();
					  }else{
						  alertPansky("保存失败！");
					  }
					 // $("#user_add").modal('hide');
				  },
				  error:function(){
					  alertPansky("保存知识产权信息失败！[系统异常]");
				  }
				});
		}
	}

});


function update(){
	$.ajax({
		  url: "knowledgeRight/updateByPrimaryKey",
		  type: 'POST',
		  dataType: "JSON",
		  data: $("#knowledgeRight_add_form").serialize(),
		  success: function(resp){
			  if(resp.flag==1){

				$("#knowledgeRight-add-modal").modal('hide');
				alertPansky("修改成功");
				//$("#user_add").modal('hide');
				refreshTable();
			  }else{
				  alertPansky("修改失败！");
			  }
			 
		  },
		  error:function(){
			  alertPansky("修改知识产权信息失败！[系统异常]");
		  }
		});
}



//显示修改联系人model
function showUpdate(id){
	$.ajax({
		  url: "knowledgeRight/selectByPrimaryKey",
		  type: 'POST',
		  dataType: "JSON",
		  data: {
			  id : id
		  },
		  success: function(resp){
			 //alertPansky(JSON.stringify(resp.data));
			  if(resp.flag==1){
				  $("#knowledgeRight_add_form").setForm(resp.data);
				  $("#knowledge_form_date_passTime").val(resp.data.passTime); 
				  $("#knowledgeRight-add-modal").modal('show');
			  }else{
				  alertPansky("获取联系人信息失败！");
			  }
			  
		  },
		  error: function(XMLHttpRequest, textStatus, errorThrown) {
			  alertPansky(XMLHttpRequest.status);
			  alertPansky(XMLHttpRequest.readyState);
			  alertPansky(errorThrown);
			  alertPansky("获取联系人信息失败！[系统异常]");
		  }
	}); 
}



function delFun(id){
	$.ajax({
		  url: "knowledgeRight/deleteKnowledgeRightById",
		  type: 'POST',
		  dataType: "JSON",
		  data: {id:id},
		  success: function(resp){
			  if(resp.flag==1){
				  alertPansky("删除成功");
				  refreshTable();
			  }else{
				  alertPansky("删除失败！");
			  }
			 
		  },
		  error:function(){
			  alertPansky("删除知识产权信息失败！[系统异常]");
		  }
		});
}

function deleteKnowledgeRight(id){
	
	deletePansky(delFun,id);
}








//刷新表格
function refreshTable(parms){
	//alertPansky(parms);
	var obj =  {url: 'knowledgeRight/findByPage',pageNumber:1};
	if(parms!=undefined){
		obj = parms;
	}
	$('#knowledgeRight-table').bootstrapTable('refresh',obj);
}



//表单绑定校验
function knowledgeRight_add_form_bind_valid(){
	 $('#knowledgeRight_add_form').bootstrapValidator({
      message: '校验不通过',
      feedbackIcons: {/*输入框不同状态，显示图片的样式*/
          valid: 'glyphicon glyphicon-ok',
          invalid: 'glyphicon glyphicon-remove',
          validating: 'glyphicon glyphicon-refresh'
      },
      fields: {/*验证*/
    	  code: {/*键名username和input name值对应*/
              message: '校验不通过',
              validators: {
                  stringLength: {/*长度提示*/
                      min: 0,
                      max: 50,
                      message: '长度不可超过50个字符'
                  }
              }
          }
      }
  });
}


function resetValid(){
	$("#knowledgeRight_add_form").data('bootstrapValidator').destroy();
    $('#knowledgeRight_add_form').data('bootstrapValidator', null);
    knowledgeRight_add_form_bind_valid();
}

</script>



</body>
</html>