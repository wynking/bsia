<%@page import="cn.com.pansky.otp5.baseplatform.dao.po.Dictionary"%>
<%@page import="cn.com.pansky.otp5.baseplatform.enums.DictionaryTypeEnum"%>
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
<title>企业信息管理</title>
	
</head>
<body>

	<div class="row" style="margin-bottom: 20px;border-bottom: 1px #CCCCCC solid;padding-bottom: 10px;">
		<div class="col-sm-2 pull-right">
			<button type="button" class="btn btn-primary" id="shx">筛选</button>
			<button type="button" class="btn btn-info" id="enterprise_add_btn">新增</button>
		</div>
	</div>

	<div id="searchArea" >
		<form class="form-horizontal" id="form-search">
			<div class="form-group">
				<input type="hidden" name="pageNumber" value="">
				<input type="hidden" name="pageSize" value="">
				<label class="col-sm-1 control-label" for="name">名称</label>
				<div class="col-sm-3">
					<input class="form-control" name="name" type="text"
						placeholder="请输入" />
				</div>
				<%-- <label class="col-sm-1 control-label" for="">行业领域</label>
				<div class="col-sm-3">
					<select class="form-control" name="" id="industry" 
						multiple="multiple">
						<option value="">-请选择-</option>
						<c:forEach var="dic" items="${DIC005}" varStatus="status">
							<option value="${dic.getCode()}">${dic.getName()}</option>
						</c:forEach>
					</select>
				</div> --%>
				<label class="col-sm-1 control-label" for="isPublic">是否上市</label>
				<div class="col-sm-3">
					<select class="form-control" name="isPublic"  >
						<option value="">-请选择-</option>
						<option value="0">未上市</option>
						<option value="1">已上市</option>
					</select>
				</div>
				
				<label for="dtp_input2" class="col-sm-1 control-label">入会时间</label>
				<div class="col-sm-3">
					<div  class="input-group date form_date" data-date=""  data-date-format="" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
						<input id="basic_add_form_form_date_firstTime" class="form-control" size="10" type="text" value="" readonly>
						<span class="input-group-addon"><span
							class="glyphicon glyphicon-remove"></span></span> <span
							class="input-group-addon"><span
							class="glyphicon glyphicon-calendar"></span></span>
					</div>
					<input type="hidden" id="dtp_input2" name="firstTime" value="" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label" for="infoSources">信息来源</label>
				<div class="col-sm-3">
					<select class="form-control" name="infoSources" >
						<option value="">-请选择-</option>
						<c:forEach var="dic" items="${DIC003}" varStatus="status">
							<option value="${dic.getCode()}">${dic.getName()}</option>
						</c:forEach>
					</select>
				</div>
				<label class="col-sm-1 control-label" for="">是否会员</label>
				<div class="col-sm-3">
					<select class="form-control" name="isMember" id="isMember" >
						<option value="">-请选择-</option>
						<option value="0">非会员</option>
						<option value="1">会员</option>
					</select>
				</div>
				<label class="col-sm-1 control-label" for="hyLevel">会员级别</label>
				<div class="col-sm-3">
					<select class="form-control" name="hyLevel">
						<option value="">-请选择-</option>
						<c:forEach var="dic" items="${DIC001}" varStatus="status">
							<option value="${ dic.getCode()}">${dic.getName()}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label" for="techs">技术领域</label>
				<div class="col-sm-3">
					<select class="form-control" name="techs" id="techFields"
						multiple="multiple">
						<c:forEach var="dic" items="${DIC002}" varStatus="status">
							<option value="${dic.getCode()}">${dic.getName()}</option>
						</c:forEach>
					</select>
				</div>
				<label class="col-sm-1 control-label" for="industrys">行业领域</label>
				<div class="col-sm-3">
					<select class="form-control" name="industrys" id="industrys" multiple="multiple">
						<c:forEach var="dic" items="${DIC005}" varStatus="status">
							<option value="${dic.getCode()}">${dic.getName()}</option>
						</c:forEach>
					</select>
				</div>
				<label class="col-sm-1 control-label" for="">通讯地址</label>
				<div class="col-sm-3">
					<div id="basic_add_form_address" class="bs-chinese-region flat dropdown" data-min-level="1" data-max-level="3" data-def-val="[name=address]">
							<input type="text" class="form-control" id="" placeholder="请选择" data-toggle="dropdown" readonly="">
								<input type="hidden" class="form-control" name="address" value="">
								<div class="dropdown-menu" role="menu" aria-labelledby="dLabel">
									<div>
										<ul class="nav nav-tabs" role="tablist">
											<li role="presentation" class="active"><a href="#province2" data-next="city" role="tab" data-toggle="tab">省份</a></li>
											<li role="presentation"><a href="#city2" data-next="district" role="tab" data-toggle="tab">城市</a></li>
											<li role="presentation"><a href="#district2" data-next="street" role="tab" data-toggle="tab">县区</a></li>
										</ul>
										<div class="tab-content">
											<div role="tabpanel" class="tab-pane active" id="province2">--</div>
											<div role="tabpanel" class="tab-pane" id="city2">--</div>
											<div role="tabpanel" class="tab-pane" id="district2">--</div>
										</div>
									</div>
								</div>	
					</div>
				</div>
			</div>
			
			
			<div class="form-group">
				<label class="col-sm-1 control-label" for="">注册资金（元）</label>
				<div class="col-sm-3">
					<input class="form-control" name="registeredCapital_start" type="text"
						placeholder="请输入注册资金下限" /> --
					<input class="form-control" name="registeredCapital_end" type="text"
						placeholder="请输入注册资金上限" />
				</div>
				<label class="col-sm-1 control-label" >年收入总额（元）</label>
				<div class="col-sm-3">
					<input class="form-control" name="totalIncome_start" type="text"
						placeholder="请输入年收入总额下限" /> --
					<input class="form-control" name="totalIncome_end" type="text"
						placeholder="请输入年收入总额上限" />
				</div>
				<label class="col-sm-1 control-label" >研发费用总额（元）</label>
				<div class="col-sm-3">
					<input class="form-control" name="developExpenses_start" type="text"
						placeholder="请输入研发费用总额下限" /> --
					<input class="form-control" name="developExpenses_end" type="text"
						placeholder="请输入研发费用总额上限" />
				</div>
			</div>
			
			
			
			
			
			<div class="form-group">
				<label class="col-sm-1 control-label" >当年（月平均）职工总数</label>
				<div class="col-sm-3">
					<input class="form-control" name="avgNum_start" type="text"
						placeholder="请输入当年（月平均）职工总数下限" /> --
					<input class="form-control" name="avgNum_end" type="text"
						placeholder="请输入当年（月平均）职工总数上限" />
				</div>
				<div class="col-sm-3 col-sm-offset-1">
					<button type="button" class="btn btn-primary" id="btn-search" style="width: 100px;">查询</button>
					<!-- <button type="button" class="btn btn-primary" id="btn-export" style="width: 100px;">导出excel</button> -->
					<a class="btn btn-primary" href="export/exportSearchBasicList">导出excel</a>
				</div>
			</div>
		</form>
	</div>
	
	<div class="modal fade" id="modal-map" role="dialog" aria-hidden="true" >
		<div class="modal-dialog"  style="width: 750px;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">地址定位</h4>
				</div>
				<div class="modal-body" id="modal-map-content" style="">
						 <iframe id="modal-map-iframe" src="" height="400px" width="100%"  scrolling="no"  frameborder="no"></iframe>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
	
	
	<table id="enterprise-table" class="col-xs-12" data-toolbar="#toolbar"></table>
	
	
	<script type="text/javascript">
		$(document).ready(function() {
			//初始化表格
			initTable();
			
			initRegion();

			$("#techFields").select2({
				placeholder : "请选择",
				language : "zh-CN", //设置 提示语言
				tags : true,
				maximumSelectionLength : 3
			//最多能够选择的个数
			});
			
			/* $("#industry").select2({
				placeholder : "请选择",
				language : "zh-CN", //设置 提示语言
				tags : true,
				maximumSelectionLength : 3
			//最多能够选择的个数
			}); */
			
			$("#industrys").select2({
				placeholder : "请选择",
				language : "zh-CN", //设置 提示语言
				tags : true,
				maximumSelectionLength : 3
			//最多能够选择的个数
			});
			
			$('.form_date').datetimepicker({
				 format: "yyyy-mm-dd",
				 autoclose: true,
				 todayBtn: true,
				 pickerPosition: "bottom-left",
				 minView:2,
			     language: "zh-CN" //设置 提示语言
			});
			
			$("#searchArea").hide();
			$("#shx").click(function(){
				  $("#searchArea").is(":visible");
					if($("#searchArea").is(":visible")){
						$("#searchArea").hide();
					}else{
						$("#searchArea").show();
					}
				 
			});
			
			$("#btn-search").click(function() {
				/*  alertPansky(JSON.stringify($("#form-search").serialize()));
					return ;  */
				$.ajax({
					url : "enterprise/findByPage",
					type : 'POST',
					dataType : "JSON",
					data : $("#form-search").serialize(),
					success : function(resp) {
						$("#enterprise-table").bootstrapTable('destroy');
						initTable();
						//$('#enterprise-table').bootstrapTable('load', resp);
						
						//TableObj.oTableInit();
					},
					error : function(){
						alertPansky("查询失败！[系统异常]");
					}
				});
			});
			
			
			
		});
		
		
		//新增企业信息
		$("#enterprise_add_btn").click(function(){
			//main-content
			//alertPansky("aaaa");
			$main = $(".main-content" , parent.document);
			$main.load(
					"./enterprise/toAdd",
					function(response,
							status, xhr) {
						// $(this).unmask();
					});
		});
		

		
		
		$("#enterprise_add_submit").click(function(){
			//alertPansky(JSON.stringify($("#resource_add_form").serialize()));
			$.ajax({
				  url: "enterprise/add",
				  type: 'POST',
				  dataType: "JSON",
				  data: $("#enterprise_add_form").serialize(),
				  success: function(resp){
					  alertPansky("提交成功");
					 // $("#user_add").modal('hide');
				  }
				});
		});
		
		
		function initTable() {
			$('#enterprise-table').bootstrapTable({
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
				url : "enterprise/findByPage",//这个接口需要处理bootstrap table传递的固定参数
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
					title : '企业名称',
					align : 'center'
				},  {
					field : 'isPublic',
					title : '是否上市',
					align : 'center',
					formatter : function(value, row, index) {
						return value==1?"是":"否";
					}
				}, {
					field : 'isMember',
					title : '是否会员',
					align : 'center',
					formatter : function(value, row, index) {
						return value==1?"是":"否";
					}
				}, {
					field : 'hyLevelName',
					title : '会员级别',
					align : 'center'
				}, {
					field : 'infoSourcesName',
					title : '信息来源',
					align : 'center'
				},{
					field : 'url',
					title : '企业网址',
					align : 'center'
				}, {
					field : '',
					title : '操作',
					align : 'center',
					formatter : function(value, row, index) {

						//通过formatter可以自定义列显示的内容
						//value：当前field的值，即id
						//row：当前行的数据
						//var a = '<button class="update-enterprise" data-id="'+row.id+'" onClick="toUpdate(\''+row.id+'\')">修改</button>';
						var edit = '<button title="修改" class="update-enterprise" data-id="'+row.id+'" onClick="toUpdate(\''+row.id+'\')"><span  class="glyphicon glyphicon-pencil"></button>';
						var detail  = '<button title="详情" class="update-enterprise" data-id="'+row.id+'" onClick="detail(\''+row.id+'\')"><span  class="glyphicon glyphicon-file"></button>';
						var del  = '<button title="删除" class="update-enterprise" data-id="'+row.id+'" onClick="deleteEnterpriseById(\''+row.id+'\')"><span  class="glyphicon glyphicon-trash"></button>';
						var posi  = '<button title="定位" class="update-enterprise" data-id="'+row.id+'" onClick="toMap(\''+row.id+'\', \''+row.name+'\')"><span  class="glyphicon glyphicon-home"></button>';
						
						return edit + detail + del + posi;
					}
				} ]
			});
		};
		
		//设置传入参数
		function queryParams(params) {
		   // var obj = $("#form-search").serialize();
		   /*  var obj = {
		    		pageSize: params.pageSize,
		            pageNumber: params.pageNumber
	            }; */
			//alertPansky("form-search========>>>>" + $("#form-search").serialize());
	        $("#form-search").find("input[name='pageSize']").val(params.pageSize);
	        $("#form-search").find("input[name='pageNumber']").val(params.pageNumber);
			var obj = $("#form-search").serialize();
			//obj.put('pageSize',params.pageSize);
			//obj.pageSize = params.pageSize;
			//obj.pageNumber = params.pageNumber;
		    //extend(obj,);
			//alertPansky("obj========>>>>" + JSON.stringify(obj));
			//alertPansky("obj========>>>>" + obj);
			//alertPansky("obj========>>>>" + obj['pageNumber']);
		    return obj;
		}
		
		/* function extend(destination, source) {
		    for (var property in source){
				destination[property] = source[property];
		    }
		    return destination;
		}  */
		
		/* $(".update-enterprise").click(function(){
			//alertPansky(JSON.stringify($("#resource_add_form").serialize()));
			alertPansky($(this).attr("data-id"));
		}); */
		//修改企业信息
		function toUpdate(eId){
			//alertPansky("bbbbbbb==========>>>>" + eId);
			$main = $(".main-content" , parent.document);
			$main.load(
					"./enterprise/toUpdate",
					{eId : eId},
					function(response,
							status, xhr) {
						// $(this).unmask();
					});
		}
		
		
		function detail(eId){
			$main = $(".main-content" , parent.document);
			$main.load(
					"./enterprise/toUpdate",
					{
						eId : eId,
						isDetail:'1'
					},
					function(response,
							status, xhr) {
						// $(this).unmask();
					});
		}
		
		

		function deleteEnterpriseById(id){
			$.ajax({
				  url: "enterprise/deleteEnterpriseById",
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
					  alertPansky("删除产品信息失败！[系统异常]");
				  }
				});
		}
		
		
		//刷新表格
		function refreshTable(parms){
			//alertPansky(parms);
			var obj =  {url: 'enterprise/findByPage',pageNumber:1};
			if(parms!=undefined){
				obj = parms;
			}
			$('#enterprise-table').bootstrapTable('refresh',obj);
		}
		
		/* $("div").delegate(".update-enterprise","click",function(){
			//alertPansky($(this).attr("data-id"));
			var eId = $(this).attr("data-id");
			alertPansky("bbbbbbb==========>>>>" + eId);
			$main = $(".main-content" , parent.document);
			$main.load(
					"./enterprise/toUpdate",
					{eId : eId},
					function(response,
							status, xhr) {
						// $(this).unmask();
					});
		}); */
		
		function initRegion(){
			
			 $.getJSON('./resources/js/bootstrap-chinese-region-master/example/lib/bootstrap-chinese-region/sql_areas.json',function(data){
				 for (var i = 0; i < data.length; i++) {
						var area = {id:data[i].id,name:data[i].cname,level:data[i].level,parentId:data[i].upid};
						data[i] = area;
					}

					$('#basic_add_form_address').chineseRegion('source',data).on('completed.bs.chinese-region',function(e,areas){
						$(this).find('[name=address]').val(areas[areas.length-1].id);
					});
				 
			    }); 
			
		}
		
		function toMap(id,name){
			//alert(id + "----" + name);
			//var address = "长天科技有限公司";
			 $("#modal-map-iframe").attr("src","./map/mapPosi?name="+name +"&id="+id);
			$("#modal-map").modal('show'); 
			
			/* $("#map_posi").load("./map/mapPosi",
					{
						name: name,
						id: id
					},
					function(response, status, xhr) {
						
					});
			$("#modal-map").modal('show');  */
		}
		
		
		/* $("#userName").click(function(){
		
		}); */
		
		
	</script>
</body>
</html>