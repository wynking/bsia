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
<title>企业年报</title>


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
						<!-- <button type="button" class="btn btn-primary">查询</button> -->
						<button type="button" class="btn btn-info" id="contact_add_btn"  data-toggle="modal" data-target="#annualReport-add-modal">新增</button>
					</div>
				</div>
			</form>
	</div>
	
	
	<table id="annualReport-table" class="col-xs-12" data-toolbar="#toolbar"></table>
	
	
	<div class="modal fade" id="annualReport-add-modal" role="dialog" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">年报信息</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" id="annualReport_add_form">
					
					<input type="hidden" name="eId" id="annualReport_eId" value="">
					<input type="hidden" name="id" >
					
						<div class="form-group">
							<label class="col-sm-3 control-label" for="year">年份</label>
							<div class="col-sm-8">
								<select class="form-control" name="year">	
										<option value="">-请选择-</option>
											
										<%
											for(int i=2000;i<2050;i++){
											    %>
											    <option value="<%=i%>" ><%=i%></option>
											    <%
											}
										%>
											
										
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label" for="totalIncome">年收入总额(元)</label>
							<div class="col-sm-8">
								<input class="form-control" name="totalIncome" type="text"
									placeholder="请输入" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label" for="totalAssets">资产总额(元)</label>
							<div class="col-sm-8">
								<input class="form-control" name="totalAssets" type="text"
									placeholder="请输入" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label" for="totalProfit">利润总额(元)</label>
							<div class="col-sm-8">
								<input class="form-control" name="totalProfit" type="text"
									placeholder="请输入" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label" for="netProfit">净利润(元)</label>
							<div class="col-sm-8">
								<input class="form-control" name="netProfit" type="text"
									placeholder="请输入" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label" for="developExpenses">研发费用总额(元)</label>
							<div class="col-sm-8">
								<input class="form-control" name="developExpenses" type="text"
									placeholder="请输入" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label" for="scale">研发费用占收入比例(%)</label>
							<div class="col-sm-8">
								<input class="form-control" name="scale" type="text"
									placeholder="请输入" />
							</div>
						</div>
					</form>


				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" id="annualReport-add-btn">提交</button>
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
	
	$('#annualReport-add-modal').on('hide.bs.modal', function () {
		$("#annualReport_add_form")[0].reset();
		setEId(getEId());
		$("#annualReport_add_form").find("input[name='id']").val("");
		resetValid();
	})
	
	annualReport_add_form_bind_valid();
});


function initTable() {
	$('#annualReport-table').bootstrapTable({
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
		url : "annualReport/findByPage",//这个接口需要处理bootstrap table传递的固定参数
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
			field : 'totalIncome',
			title : '年收入总额',
			align : 'center'
		}, {
			field : 'totalAssets',
			title : '资产总额',
			align : 'center'
		},{
			field : 'totalProfit',
			title : '利润总额',
			align : 'center'
		},{
			field : 'netProfit',
			title : '净利润',
			align : 'center'
		},{
			field : 'developExpenses',
			title : '研发费用总额',
			align : 'center'
		},{
			field : 'scale',
			title : '研发费用占收入比例',
			align : 'center'
		},{
			field : 'year',
			title : '年度',
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
				var del  = '<button title="删除" class="update-enterprise" data-id="'+row.id+'" onClick="deleteAnnualReport(\''+row.id+'\')"><span  class="glyphicon glyphicon-trash"></button>';
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
	$("#annualReport_eId").val(eId);
}

function getEId(){
	return window.parent.getEId_enterpriseSingle();
}


//保存年报信息
$("#annualReport-add-btn").click(function(){
	
	if(form_isValid("annualReport_add_form")){
		//alertPansky(JSON.stringify($("#contact_add_form").serialize()));
		var id = $("#annualReport_add_form").find("input[name='id']").val();
		//alertPansky(id);
		//return ;
		if(id!=""){
			update();
		}else{
			$.ajax({
				  url: "annualReport/add",
				  type: 'POST',
				  dataType: "JSON",
				  data: $("#annualReport_add_form").serialize(),
				  success: function(resp){
					  if(resp.flag==1){
						  $("#annualReport-add-modal").modal('hide');
						  alertPansky("保存成功！");
						  refreshTable();
					  }else{
						  alertPansky("保存失败！");
					  }
					 // $("#user_add").modal('hide');
				  },
				  error:function(){
					  alertPansky("保存年报信息失败！[系统异常]");
				  }
				});
		}
	}
	
	
	

});


function update(){
	$.ajax({
		  url: "annualReport/updateByPrimaryKey",
		  type: 'POST',
		  dataType: "JSON",
		  data: $("#annualReport_add_form").serialize(),
		  success: function(resp){
			  if(resp.flag==1){
				  $("#annualReport-add-modal").modal('hide');
				  alertPansky("修改成功");
				 // $("#user_add").modal('hide');
				  refreshTable();
			  }else{
				  alertPansky("修改失败！");
			  }
			  
		  },
		  error:function(){
			  alertPansky("修改年报信息失败！[系统异常]");
		  }
		});
}



//显示修改联系人model
function showUpdate(id){
	$.ajax({
		  url: "annualReport/selectByPrimaryKey",
		  type: 'POST',
		  dataType: "JSON",
		  data: {
			  id : id
		  },
		  success: function(resp){
			 //alertPansky(JSON.stringify(resp.data));
			  if(resp.flag==1){
				  $("#annualReport_add_form").setForm(resp.data);
				  $("#annualReport-add-modal").modal('show');
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
		  url: "annualReport/deleteAnnualReportById",
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
			  alertPansky("删除年报信息失败！[系统异常]");
		  }
		});
}


function deleteAnnualReport(id){
	deletePansky(delFun,id);
}


//刷新表格
function refreshTable(parms){
	//alertPansky(parms);
	var obj =  {url: 'annualReport/findByPage',pageNumber:1};
	if(parms!=undefined){
		obj = parms;
	}
	$('#annualReport-table').bootstrapTable('refresh',obj);
}



//表单绑定校验
function annualReport_add_form_bind_valid(){
	 $('#annualReport_add_form').bootstrapValidator({
          message: '校验不通过',
          feedbackIcons: {/*输入框不同状态，显示图片的样式*/
              valid: 'glyphicon glyphicon-ok',
              invalid: 'glyphicon glyphicon-remove',
              validating: 'glyphicon glyphicon-refresh'
          },
          fields: {/*验证*/
        	  year: {/*键名username和input name值对应*/
                  message: '校验不通过',
                  validators: {
                      notEmpty: {/*非空提示*/
                          message: '请选择年份'
                      }
                  }
              },
        	  totalIncome: {/*键名username和input name值对应*/
        		  message: '校验不通过',
                  validators: {
                	  regexp: {
                          regexp:  REGEX.num2,
                          message: '请输入正确的格式，如12.34'
                      },
                      stringLength: {/*长度提示*/
                          min: 0,
                          max: 15,
                          message: '长度不可超过15个字符'
                      }
                  }
              },
              totalAssets: {/*键名username和input name值对应*/
        		  message: '校验不通过',
                  validators: {
                	  regexp: {
                          regexp:  REGEX.num2,
                          message: '请输入正确的格式，如12.34'
                      },
                      stringLength: {/*长度提示*/
                          min: 0,
                          max: 15,
                          message: '长度不可超过15个字符'
                      }
                  }
              },
              totalProfit: {/*键名username和input name值对应*/
        		  message: '校验不通过',
                  validators: {
                	  regexp: {
                          regexp:  REGEX.num2,
                          message: '请输入正确的格式，如12.34'
                      },
                      stringLength: {/*长度提示*/
                          min: 0,
                          max: 15,
                          message: '长度不可超过15个字符'
                      }
                  }
              },
              netProfit: {/*键名username和input name值对应*/
        		  message: '校验不通过',
                  validators: {
                	  regexp: {
                          regexp:  REGEX.num2,
                          message: '请输入正确的格式，如12.34'
                      },
                      stringLength: {/*长度提示*/
                          min: 0,
                          max: 15,
                          message: '长度不可超过15个字符'
                      }
                  }
              },
              developExpenses: {/*键名username和input name值对应*/
        		  message: '校验不通过',
                  validators: {
                	  regexp: {
                          regexp:  REGEX.num2,
                          message: '请输入正确的格式，如12.34'
                      },
                      stringLength: {/*长度提示*/
                          min: 0,
                          max: 15,
                          message: '长度不可超过15个字符'
                      }
                  }
              },
              scale: {/*键名username和input name值对应*/
        		  message: '校验不通过',
                  validators: {
                	  regexp: {
                          regexp:  REGEX.num2,
                          message: '请输入正确的格式，如12.34'
                      },
                      stringLength: {/*长度提示*/
                          min: 0,
                          max: 15,
                          message: '长度不可超过15个字符'
                      }
                  }
              }
          }
      });
}

function resetValid(){
	$("#annualReport_add_form").data('bootstrapValidator').destroy();
    $('#annualReport_add_form').data('bootstrapValidator', null);
    annualReport_add_form_bind_valid();
}

</script>



</body>
</html>