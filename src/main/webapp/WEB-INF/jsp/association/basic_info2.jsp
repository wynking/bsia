<%@page import="cn.com.pansky.otp5.baseplatform.dao.po.Dictionary,
				cn.com.pansky.otp5.common.DictionaryUtil
				"%>


<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<%-- <%@ page import="cn.com.pansky.otp5.baseplatform.enums.ResourceTypeEnum"%>	 --%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>企业基本信息</title>

<style type="text/css">
.input-append .add-on, .input-prepend .add-on {
	background-color: #eee;
	border: 1px solid #ccc;
	display: inline-block;
	font-size: 14px;
	font-weight: normal;
	height: 20px;
	line-height: 20px;
	min-width: 16px;
	padding: 4px 5px;
	text-align: center;
	text-shadow: 0 1px 0 #fff;
	width: auto;
}

.icon-remove {
	background-position: -312px 0;
}

[class^="icon-"], [class*=" icon-"] {
	/*  background-image: url("./resources/img/glyphicon-calendar.png"); */
	background-position: 14px 14px;
	background-repeat: no-repeat;
	display: inline-block;
	height: 14px;
	line-height: 14px;
	margin-top: 1px;
	vertical-align: text-top;
	width: 14px;
}

</style>

</head>
<body>

	<form class="form-horizontal" id="basic_add_form"
		style="margin-top: 20px;">
		
		<div class="row">
			<div class="form-group col-sm-6">
				<label class="col-sm-4 control-label" for="name">企业名称</label>
				<div class="col-sm-8">
					<input class="form-control" name="name" type="text"
						placeholder="请输入" />
				</div>
			</div>
			<div class="form-group col-sm-6">
				<label class="col-sm-4 control-label" for="code">统一社会信用代码</label>
				<div class="col-sm-8">
					<input class="form-control" name="code" type="text"
						placeholder="请输入" />
				</div>
			</div>
		</div>
		
		<div class="row">
			<div class="form-group col-sm-6">
				<label class="col-sm-4 control-label" for="url">企业网址</label>
				<div class="col-sm-8">
					<input class="form-control" name="url" type="text" placeholder="请输入" />
				</div>
			</div>
			<div class="form-group col-sm-6">
				<label class="col-sm-4 control-label" for="hyLevel">会员级别</label>
				<div class="col-sm-8">
					<select class="form-control" name="hyLevel">
						<option value="">-请选择-</option>
						<%-- <c:set var="salary" scope="session" value="<%=DictionaryUtil.getAllEnableDicByType(request, "DIC001");%>" /> --%>
						<c:forEach var="dic" items="${DIC001}" varStatus="status">
							<option value="${ dic.getCode()}">${dic.getName()}</option>
						</c:forEach>
					</select>
				</div>
			</div>
		</div>
		
		
		<div class="row">
			<div class="form-group col-sm-6">
				<label class="col-sm-4 control-label" for="techs">技术领域</label>
				<div class="col-sm-8">
					<select class="form-control" name="techs" id="techFields"
						multiple="multiple">
						<!-- <option value="">-请选择-</option> -->
						<c:forEach var="dic" items="${DIC002}" varStatus="status">
							<option value="${dic.getCode()}">${dic.getName()}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="form-group col-sm-6">
				<label class="col-sm-4 control-label" for="infoSources">信息来源</label>
				<div class="col-sm-8">
					<select class="form-control" name="infoSources" id="infoSources">
						<option value="">-请选择-</option>
						<c:forEach var="dic" items="${DIC003}" varStatus="status">
							<option value="${dic.getCode()}">${dic.getName()}</option>
						</c:forEach>
					</select>
				</div>
			</div>
		</div>
		
		<div class="row">
			<div class="form-group col-sm-6">
				<label class="col-sm-4 control-label" for="">行业领域</label>
				<div class="col-sm-8">
					<select class="form-control" name="industrys" id="industrys" multiple="multiple">
						<c:forEach var="dic" items="${DIC005}" varStatus="status">
							<option value="${dic.getCode()}">${dic.getName()}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="form-group col-sm-6">
				<label for="dtp_input2" class="col-sm-4 control-label">入会时间</label>
				<div class="col-sm-8">
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
		</div>
		
		<div class="row">
			<div class="form-group col-sm-6">
				<label class="col-sm-4 control-label" for="employeeNum">员工规模[可填写当前职工总人数]</label>
				<div class="col-sm-8">
					<input class="form-control" name="employeeNum" type="text" placeholder="请输入" />
					<!-- <select class="form-control" name="employeeNum">	
						<option value="">-请选择-</option>					
						<option value="1">50人以下</option>					
						<option value="2">50-100人</option>					
						<option value="3">100-500人</option>					
						<option value="4">500-1000人</option>			
						<option value="5">1000-3000人</option>		
						<option value="6">3000-5000人</option>		
						<option value="7">5000-10000人</option>			
						<option value="8">10000人以上</option>							
					</select> -->		
				</div>			
			</div>
			<div class="form-group col-sm-6">
				<label class="col-sm-4 control-label" for="registeredCapital">注册资金（元）</label>
				<div class="col-sm-8">
					<input class="form-control" name="registeredCapital" type="text" placeholder="请输入" />
				</div>				
			</div>
		</div>
		
		<div class="row">
			<div class="form-group col-sm-6">
				<label class="col-sm-4 control-label" for="">注册地址</label>
				<div class="col-sm-8">
					<div id="basic_add_form_registerAddress" class="bs-chinese-region flat dropdown" data-min-level="1" data-max-level="3" data-def-val="[name=registerAddress]">
								<input type="text" class="form-control" id="" placeholder="请选择" data-toggle="dropdown" readonly="">
								<input type="hidden" class="form-control" name="registerAddress" value="">
								<div class="dropdown-menu" role="menu" aria-labelledby="dLabel">
									<div>
										<ul class="nav nav-tabs" role="tablist">
											<li role="presentation" class="active"><a href="#province" data-next="city" role="tab" data-toggle="tab">省份</a></li>
											<li role="presentation"><a href="#city" data-next="district" role="tab" data-toggle="tab">城市</a></li>
											<li role="presentation"><a href="#district" data-next="street" role="tab" data-toggle="tab">县区</a></li>
										</ul>
										<div class="tab-content">
											<div role="tabpanel" class="tab-pane active" id="province">--</div>
											<div role="tabpanel" class="tab-pane" id="city">--</div>
											<div role="tabpanel" class="tab-pane" id="district">--</div>
										</div>
									</div>
								</div>
							</div>
				</div>
				<!-- <div class="col-sm-1">
					<span  id="showMap">测试地址</span>
				</div> -->
			</div>
			<div class="form-group col-sm-6">
				<label class="col-sm-4 control-label" for="name">通讯地址</label>
				<div class="col-sm-8">
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
		</div>
		
		
		<div class="row">
			<div class="form-group col-sm-6">
				<label class="col-sm-4 control-label" for="">详细地址[注册]</label>
				<div class="col-sm-8">
					<input class="form-control" name="registerAddressDetail" type="text"
						placeholder="请输入" />
				</div>
			</div>
			
			<div class="form-group col-sm-6">
				<label class="col-sm-4 control-label" for="">详细地址[通讯]</label>
				<div class="col-sm-8">
					<input class="form-control" name="addressDetail" type="text"
						placeholder="请输入" />
				</div>
			</div>
		</div>
		

		<div class="row">
			<div class="form-group col-sm-6">
				<label class="col-sm-4 control-label" for="isHigh">是否高新</label>
				<div class="radio col-sm-4">
					<label> <input type="radio" name="isHigh" id="" value="0"
						checked> 否
					</label>
				</div>
				<div class="radio  col-sm-4">
					<label> <input type="radio" name="isHigh" id="" value="1">是
					</label>
				</div>
			</div>
			
			
			<div class="form-group col-sm-6">
				<label class="col-sm-4 control-label" for="">其他资质</label>
				<div class="col-sm-8">
					<select class="form-control" name="aptitudes" id="aptitudes" multiple="multiple">
						<c:forEach var="dic" items="${DIC007}" varStatus="status">
							<option value="${dic.getCode()}">${dic.getName()}</option>
						</c:forEach>
					</select>
				</div>
			</div>
		</div>
		
		<div class="row" id="timeRow">
			
			<div class="form-group col-sm-6 hide" id="timeHigh">
				<label for="dtp_input2" class="col-sm-4 control-label">通过时间[高新]</label>
				<div class="col-sm-8">
					<div  class="input-group date form_date" data-date=""  data-date-format="" data-link-field="val_timeHigh" data-link-format="yyyy-mm-dd">
						<input id="show_timeHigh" class="form-control" id="show_timeHigh" size="10" type="text" value="" readonly>
						<span class="input-group-addon"><span
							class="glyphicon glyphicon-remove"></span></span> 
						<span
							class="input-group-addon"><span
							class="glyphicon glyphicon-calendar"></span></span>
					</div>
					<input type="hidden"  id="val_timeHigh" name="passHighDate" value="" />
				</div>
			</div>
			
			<c:forEach var="dic" items="${DIC007}" varStatus="status">
				<div class="form-group col-sm-6 hide timeAptitudes" id="timeAptitudes_${dic.getCode()}">
					<label for="dtp_input2" class="col-sm-4 control-label">通过时间[${dic.getName()}]</label>
					<div class="col-sm-8">
						<div  class="input-group date form_date" id="div_timeAptitudes_${dic.getCode()}" data-date=""  data-date-format="" data-link-field="val_timeAptitudes_${dic.getCode()}" data-link-format="yyyy-mm-dd">
							<input id="show_timeAptitudes_${dic.getCode()}"  class="form-control" size="10" type="text" value="" readonly  >
							<span class="input-group-addon"><span
								class="glyphicon glyphicon-remove"></span></span> 
							<span
								class="input-group-addon"><span
								class="glyphicon glyphicon-calendar"></span></span>
						</div>
						<input type="hidden" id="val_timeAptitudes_${dic.getCode()}" name="timeAptitudes_${dic.getCode()}" value="" />
					</div>
				</div>
			</c:forEach>
		</div>
		
		
		
		<div class="row">
			<div class="form-group col-sm-6">
				<label class="col-sm-4 control-label" for="isPublic">是否上市</label>
				<div class="radio col-sm-4">
					<label> <input type="radio" name="isPublic" id="" value="0"
						checked> 未上市
					</label>
				</div>
				<div class="radio  col-sm-4">
					<label> <input type="radio" name="isPublic" id="" value="1">已上市
					</label>
				</div>
			</div>
			
			<div class="form-group col-sm-6">
				<label class="col-sm-4 control-label" for="isMember">是否会员</label>
				<div class="radio col-sm-4">
					<label> <input type="radio" name="isMember" id="" value="0"
						checked> 非会员
					</label>
				</div>
				<div class="radio  col-sm-4">
					<label> <input type="radio" name="isMember" id="" value="1">会员
					</label>
				</div>
			</div>
		</div>
		
		
		<div class="row">
			
			<div class="form-group col-sm-6">
				<label class="col-sm-4 control-label" for="glNum">管理人员总数</label>
				<div class="col-sm-8">
					<input class="form-control" name="glNum" type="text"
						placeholder="请输入" />
				</div>
			</div>
			
			<div class="form-group col-sm-6">
				<label class="col-sm-4 control-label" for="postalCode">邮政编码</label>
				<div class="col-sm-8">
					<input class="form-control" name="postalCode" type="text"
						placeholder="请输入" />
				</div>
			</div>
			
		</div>
		
		
		<div class="row">
		
		
			<div class="form-group col-sm-6">
				<label class="col-sm-4 control-label" for="yfNum">研发人员总数</label>
				<div class="col-sm-8">
					<input class="form-control" name="yfNum" type="text"
						placeholder="请输入" />
				</div>
			</div>
			<div class="form-group col-sm-6">
				<label class="col-sm-4 control-label" for="zbNum">大专及本科人数</label>
				<div class="col-sm-8">
					<input class="form-control" name="zbNum" type="text"
						placeholder="请输入" />
				</div>
			</div>
			
		</div>
		
		
		<div class="row">
			<div class="form-group  col-sm-6">
				<label class="col-sm-4 control-label" for="ssNum">硕士学历人数</label>
				<div class="col-sm-8">
					<input class="form-control" name="ssNum" type="text"
						placeholder="请输入" />
				</div>
			</div>
			<div class="form-group  col-sm-6">
				<label class="col-sm-4 control-label" for="bsNum">博士学历人数</label>
				<div class="col-sm-8">
					<input class="form-control" name="bsNum" type="text"
						placeholder="请输入" />
				</div>
			</div>
		</div>
		
		<div class="row">
			<div class="form-group  col-sm-6">
				<label class="col-sm-4 control-label" for="avgNum">当年（月平均）职工总数</label>
				<div class="col-sm-8">
					<input class="form-control" name="avgNum" type="text"
						placeholder="请输入" />
				</div>
			</div>
		</div>
		
		<div class="row">
			<div class="form-group col-sm-12">
				<label class="col-sm-2 control-label" for="remark">公司简介</label>
				<div class="col-sm-10">
					<textarea class="form-control" rows="5" name="remark"></textarea>
				</div>
			</div>
		</div>
		
		
		<!-- -->
		
		
		
		
		
		
		

		

		<div class="form-group ">
			<div class="col-sm-12">
				<input id="basic_info_eId" type="hidden" name="id">
				<input id="isDetail" type="hidden" name="isDetail">
				<input  type="hidden" name="enable">
				<button type="button" class="btn btn-primary center-block"
					id="badicInfo-btn">保&nbsp;&nbsp;存</button>
				<!-- <button type="button" class="btn btn-primary">重置</button> -->
			</div>
		</div>


	</form>




	<div class="modal fade" id="modal-map" role="dialog" aria-hidden="true" >
		<div class="modal-dialog"  style="width: 750px;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">地址信息</h4>
				</div>
				<div class="modal-body" id="modal-map-content" style="">
						<iframe id="modal-map-iframe" src="" height="400px" width="100%"  scrolling="no"  frameborder="no"></iframe>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
	





	<script type="text/javascript">
		//alertPansky(window.parent.isDetail);
		//var eId = parent.eId;
		if(window.parent.isDetail=='1'){
			$('input,select,textarea',$('#basic_add_form')).attr('disabled','disabled'); 
		}
		
		setEId(getEId());

		$(document).ready(function() {
			
			
			
			$("#basic_add_form").find("input[name='isHigh']").click(function(){
				//alertPansky($(this).val());
				var isHigh = $(this).val();
				if(isHigh==1){
					//alertPansky("show");
					$("#timeHigh").removeClass("hide");
					
				}else{
					//alertPansky("hide");
					$("#timeHigh").addClass("hide");

					$("#show_timeHigh").val('');
					$("#val_timeHigh").val('');
				}
				
			});


			 $("#aptitudes").on("change",function(e){
				var vals = $(this).select2("data");
				var timeAptitudes = $("#basic_add_form").find("[class*='timeAptitudes']");
				$(timeAptitudes).addClass("hide");
				for(var i=0;i<vals.length;i++){
						//alertPansky(JSON.stringify(vals[i]));
						//alertPansky(vals[i].id);
					$("#timeAptitudes_"+vals[i].id).removeClass("hide");
				}
				
				for(var i=0;i<timeAptitudes.length;i++){
					//alertPansky(JSON.stringify(hideDates[i]));
					//alertPansky(timeAptitudes[i].id);
					if($("#"+timeAptitudes[i].id).hasClass('hide')){
						//alertPansky(timeAptitudes[i].id);
						 $("#show_"+timeAptitudes[i].id).val('');
						$("#val_"+timeAptitudes[i].id).val(''); 
					}
				}
			}) 
			
			
			initRegion();

			$("#aptitudes").select2({
				placeholder : "请选择",
				language : "zh-CN", //设置 提示语言
				tags : true,
				maximumSelectionLength : 3
			});

			$("#techFields").select2({
				placeholder : "请选择",
				language : "zh-CN", //设置 提示语言
				tags : true,
				maximumSelectionLength : 3
			//最多能够选择的个数
			});

			$("#techFields").select2({
				placeholder : "请选择",
				language : "zh-CN", //设置 提示语言
				tags : true,
				maximumSelectionLength : 3
			//最多能够选择的个数
			});

			$("#industrys").select2({
				placeholder : "请选择",
				language : "zh-CN", //设置 提示语言
				tags : true,
				maximumSelectionLength : 3
			//最多能够选择的个数
			});
			
			

			/* $(".form_datetime").datetimepicker({
			    format: "yyyy-mm-dd",
			    autoclose: true,
			    todayBtn: true,
			    pickerPosition: "bottom-left",
			    minView:2,
			    language: "zh-CN" //设置 提示语言
			}); */

			$('.form_date').datetimepicker({
				 format: "yyyy-mm-dd",
				 autoclose: true,
				 todayBtn: true,
				 pickerPosition: "bottom-left",
				 minView:2,
			     language: "zh-CN" //设置 提示语言
			});

			//$("#techFields").select2();

			$("#badicInfo-btn").click(function() {
				saveBasicInfo();
			});
			
			$("#showMap").click(function() {
				toMap();
			});

			/* alertPansky("父页面===》》》" + window.parent.getEId_enterpriseSingle());
			alertPansky("子页面===》》》" + getEId()); */
			//如果存在企业ID,那么则把企业信息取出显示在页面上
			if (getEId() != "") {
				setBasicForm();
			}
			
			
			/* $("#modal-map").on('show.bs.modal', function () {
			})*/
			
			//初始化表单校验
			basic_add_form_bind_valid();
		});

		//保存企业基本信息
		function saveBasicInfo() {
			/* alertPansky(JSON.stringify($("#basic_add_form").serialize()));
			return ; */
			
			if(form_isValid("basic_add_form")){
				$.ajax({
					url : "enterprise/add",
					type : 'POST',
					dataType : "JSON",
					data : $("#basic_add_form").serialize(),
					success : function(resp) {
						
						if(resp.flag==1){
							setEId(resp.data.id);
							window.parent.setEId_enterpriseSingle(resp.data.id);
							alertPansky("保存成功");
							
						}else{
							alertPansky("保存失败");
						}
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						alertPansky("保存失败！[系统异常]");
					}
				});
			}
			
			
		}

		function setBasicForm() {
			$.ajax({
				url : "enterprise/selectByPrimaryKey",
				type : 'POST',
				dataType : "JSON",
				data : {
					id : getEId()
				},
				success : function(resp) {
					
					//alertPansky(JSON.stringify(resp.data.otherParams).split(','));
					
					$("#basic_add_form").setForm(resp.data);
					$("#basic_add_form").setForm(resp.data.otherParams);
					
					if(resp.data.isHigh==1){
						$("#timeHigh").removeClass("hide");
					}
					
					
					$.each(resp.data.otherParams, function (name, ival) {
						  $("#show_"+name).val(ival); 
			         	});

					$("#basic_add_form_form_date_firstTime").val(resp.data.firstTime); 
					$("#show_timeHigh").val(resp.data.passHighDate); 
					//$('.bs-chinese-region').chineseRegion('render',id);
					$('#basic_add_form_registerAddress').chineseRegion('render',resp.data.registerAddress);
					$('#basic_add_form_address').chineseRegion('render',resp.data.address);
					
					
					
					
					//alertPansky('eeee');
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					/* alertPansky(XMLHttpRequest.status);
					alertPansky(XMLHttpRequest.readyState);
					alertPansky(errorThrown); */
					alertPansky("保存失败！[系统异常]");
				}
			});

		}

		//设置企业基本信息页面的企业ID
		function setEId(eId) {
			$("#basic_info_eId").val(eId);
		}

		function getEId() {
			return window.parent.getEId_enterpriseSingle();
		}
		
		function initRegion(){

			 $.getJSON('./dictionary/getRegion',function(resp){
				 var data = resp.data;
				 for (var i = 0; i < data.length; i++) {
						//var area = {id:data[i].id,name:data[i].cname,level:data[i].level,parentId:data[i].upid};
						var area = {id:data[i].id,name:data[i].name,level:data[i].level,parentId:data[i].pCode};
						data[i] = area;
					}

					$('#basic_add_form_registerAddress').chineseRegion('source',data).on('completed.bs.chinese-region',function(e,areas){
						//alertPansky(areas[areas.length-1].id);
						$(this).find('[name=registerAddress]').val(areas[areas.length-1].id);
					});
				 
			 });  
			
			 $.getJSON('./dictionary/getRegion',function(resp){
				 var data = resp.data;
				 for (var i = 0; i < data.length; i++) {
						//var area = {id:data[i].id,name:data[i].cname,level:data[i].level,parentId:data[i].upid};
						var area = {id:data[i].id,name:data[i].name,level:data[i].level,parentId:data[i].pCode};
						data[i] = area;
					}
					$('#basic_add_form_address').chineseRegion('source',data).on('completed.bs.chinese-region',function(e,areas){
						$(this).find('[name=address]').val(areas[areas.length-1].id);
					});
			 }); 
			
		}
		
		
		
		
		
		function toMap(){
			var code = $("#basic_add_form").find("input[name='registerAddress']").val();
			var detail = $("#basic_add_form").find("input[name='registerAddressDetail']").val();
			
			$.ajax({
				url : "dictionary/getNameByCode",
				type : 'POST',
				dataType : "JSON",
				data : {
					code : code
				},
				success : function(resp) {
					var address = resp.data + detail;
					alertPansky(address);
					$("#modal-map-iframe").attr("src","./map/map?address="+address);
					$("#modal-map").modal('show');
					//alertPansky(JSON.stringify(resp));
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					
				}
			});
		}
		
		//表单绑定校验
		function basic_add_form_bind_valid(){
			 $('#basic_add_form').bootstrapValidator({
		            message: '校验不通过',
		            feedbackIcons: {/*输入框不同状态，显示图片的样式*/
		                valid: 'glyphicon glyphicon-ok',
		                invalid: 'glyphicon glyphicon-remove',
		                validating: 'glyphicon glyphicon-refresh'
		            },
		            fields: {/*验证*/
		            	name: {/*键名username和input name值对应*/
		                    message: '企业名称校验不通过',
		                    validators: {
		                        notEmpty: {/*非空提示*/
		                            message: '企业名称不能为空'
		                        },
		                        stringLength: {/*长度提示*/
		                            min: 1,
		                            max: 50,
		                            message: '用户名长度必须在1到50之间'
		                        }
		                    }
		                },
		                url: {/*键名username和input name值对应*/
		                    message: '企业网址校验不通过',
		                    validators: {
		                        uri: {/*非空提示*/
		                            message: '网址格式有误'
		                        },
		                        stringLength: {/*长度提示*/
		                            min: 0,
		                            max: 100,
		                            message: '网址长度不可超过100个字符'
		                        }
		                    }
		                },
		                registeredCapital: {/*键名username和input name值对应*/
		                    message: '注册资金校验不通过',
		                    validators: {
		                    	numeric: {/*非空提示*/
		                            message: '格式有误'
		                        },
		                        stringLength: {/*长度提示*/
		                            min: 0,
		                            max: 13,
		                            message: '长度不可超过100个字符'
		                        }
		                    }
		                },
		                yfNum: {/*键名username和input name值对应*/
		                    message: '校验不通过',
		                    validators: {
		                    	regexp: {
		                            regexp:  /^([1-9]\d*|[0]{1,1})$/,
		                            message: '请输入正确的人数，如:23'
		                        },
		                        stringLength: {/*长度提示*/
		                            min: 0,
		                            max: 6,
		                            message: '最大不可超过999999'
		                        }
		                    }
		                },
		                glNum: {/*键名username和input name值对应*/
		                    message: '校验不通过',
		                    validators: {
		                    	regexp: {
		                            regexp:  /^([1-9]\d*|[0]{1,1})$/,
		                            message: '请输入正确的人数，如:23'
		                        },
		                        stringLength: {/*长度提示*/
		                            min: 0,
		                            max: 6,
		                            message: '最大不可超过999999'
		                        }
		                    }
		                },
		                zbNum: {/*键名username和input name值对应*/
		                    message: '校验不通过',
		                    validators: {
		                    	regexp: {
		                            regexp:  /^([1-9]\d*|[0]{1,1})$/,
		                            message: '请输入正确的人数，如:23'
		                        },
		                        stringLength: {/*长度提示*/
		                            min: 0,
		                            max: 6,
		                            message: '最大不可超过999999'
		                        }
		                    }
		                },
		                ssNum: {/*键名username和input name值对应*/
		                    message: '校验不通过',
		                    validators: {
		                    	regexp: {
		                            regexp:  /^([1-9]\d*|[0]{1,1})$/,
		                            message: '请输入正确的人数，如:23'
		                        },
		                        stringLength: {/*长度提示*/
		                            min: 0,
		                            max: 6,
		                            message: '最大不可超过999999'
		                        }
		                    }
		                },
		                bsNum: {/*键名username和input name值对应*/
		                    message: '校验不通过',
		                    validators: {
		                    	regexp: {
		                            regexp:  /^([1-9]\d*|[0]{1,1})$/,
		                            message: '请输入正确的人数，如:23'
		                        },
		                        stringLength: {/*长度提示*/
		                            min: 0,
		                            max: 6,
		                            message: '最大不可超过999999'
		                        }
		                    }
		                },
		                avgNum: {/*键名username和input name值对应*/
		                    message: '校验不通过',
		                    validators: {
		                    	regexp: {
		                            regexp:  /^([1-9]\d*|[0]{1,1})$/,
		                            message: '请输入正确的人数，如:23'
		                        },
		                        stringLength: {/*长度提示*/
		                            min: 0,
		                            max: 6,
		                            message: '最大不可超过999999'
		                        }
		                    }
		                },
		                postalCode: {/*键名username和input name值对应*/
		                    message: '校验不通过',
		                    validators: {
		                    	regexp: {
		                            regexp:  /^[1-9][0-9]{5}$/,
		                            message: '请输入格式正确的邮政编码'
		                        },
		                        stringLength: {/*长度提示*/
		                            min: 0,
		                            max: 6,
		                            message: '长度不可超过6个字符'
		                        }
		                    }
		                }
		                
		                
		                
		                
		                
		                
		            }
		        });
		}
		
		
		
		//
		
		
		
		
		
	</script>



</body>
</html>