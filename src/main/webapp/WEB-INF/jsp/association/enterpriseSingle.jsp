<%@page import="cn.com.pansky.otp5.baseplatform.dao.po.Dictionary"%>
<%@page import="cn.com.pansky.otp5.common.DictionaryUtil"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%-- <%@ page import="cn.com.pansky.otp5.baseplatform.enums.ResourceTypeEnum"%>	

 --%>
<%
	String eId = (String)(request.getAttribute("eId")!=null?request.getAttribute("eId"):"");
	String isDetail = (String)(request.getAttribute("isDetail")!=null?request.getAttribute("isDetail"):"");
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>企业信息管理</title>
<script type="text/javascript">
	var eId = "<%=eId%>";
	//alertPansky("aaaa===>>>" + eId);
</script>	
</head>
<body>
		
<input type="hidden" id="enterpriseSingle_eId" value="<%=eId%>">		
<ul id="myTab" class="nav nav-tabs">
	<li class="active"> 
		<a href="#"  id="1">
			 企业基本信息
		</a>
	</li>
	<li><a href="#" id="2">联系人</a></li>
	<li><a href="#" id="3">年报</a></li>
	<li><a href="#" id="4">股东</a></li>
	<li><a href="#" id="5">知识产权</a></li>
	<li><a href="#" id="6">产品</a></li>
	
</ul>
<div id="myTabContent" class="tab-content">
	<div class="tab-pane fade in active" id="basic-info">
	</div>
	<div class="tab-pane fade" id="contact-info">
	</div>
	<div class="tab-pane fade" id="annualReport">
	</div>
	<div class="tab-pane fade" id="shareholder">
	</div>
	<div class="tab-pane fade" id="knowledgeRight">
	</div>
	<div class="tab-pane fade" id="production">
	</div>
</div>


		
		
<script type="text/javascript">

var isDetail = '<%=isDetail%>';
$("#isDetail").val(isDetail);

$(document).ready(function() {
	
	showBasicInfo();
	
	//标签页切换事件
	$('#myTab a').click(function (e) {
		//alertPansky('aaa');
	   e.preventDefault();
	   $curTab = $(e.target);
		  var tabId = $curTab.attr("id");
		 if(getEId_enterpriseSingle() ==""){
			 if(tabId!="1"){
				 alertPansky("请先保存企业基本信息！");
			 }
		 }else{
			  $('#myTabContent .tab-pane').removeClass("active").removeClass("fade").removeClass("in");
			  if(tabId=="1"){
				    $('#basic-info').addClass("active").addClass("fade").addClass("in");
					showBasicInfo();
			  }else if(tabId=="2"){
					 $('#contact-info').addClass("active").addClass("fade").addClass("in");
					 showContact();
			  }else if(tabId=="3"){
					 $('#annualReport').addClass("active").addClass("fade").addClass("in");
					  	showAnnualReport();
			  }else if(tabId=="4"){
					 $('#shareholder').addClass("active").addClass("fade").addClass("in");
					 showShareholder();
		  		}else if(tabId=="5"){
					 $('#knowledgeRight').addClass("active").addClass("fade").addClass("in");
					 showKonwledgeRight();
		  		}else if(tabId=="6"){
					 $('#production').addClass("active").addClass("fade").addClass("in");
					 showProduction();
		  		}
			  $curTab.tab('show');
		 }
	  
	  
	 // alertPansky('bbb');
	})
		
	
	$('a[data-toggle="tab"]').on('show.bs.tab', function (e) {
		 // e.target // 激活的标签页
		 // e.relatedTarget // 前一个激活的标签页
		 
		/*  $curTab = $(e.target);
		 //alertPansky($curTab.attr("id"));
		 var tabId = $curTab.attr("id");
		 if(tabId=="1"){
			showBasicInfo();
		 }else if(tabId=="2"){
			 if(getEId_enterpriseSingle() ==""){
				 $('#1').tab('show')
				 alertPansky( $('#1'));
				 alertPansky("请先保存企业基本信息！");
			 }else{
				 showContact();
			 }
		 } */
		 
		 
		})
	
	
});

//显示基本信息页面
function showBasicInfo(){
	//alertPansky(getEId_enterpriseSingle());
	$("#basic-info").load(
			"./enterprise/toBasic",
			{
				eId:getEId_enterpriseSingle()
			},
			function(response,
					status, xhr) {
				// $(this).unmask();
			});
}

//显示联系人信息页面
function showContact(){
	
	$("#contact-info").load(
			"./contact/toContact",
			{
				eId:getEId_enterpriseSingle()
			},
			function(response,
					status, xhr) {
				// $(this).unmask();
			});
}


//显示年报信息页面
function showAnnualReport(){
	
	$("#annualReport").load(
			"./annualReport/toAnnualReport",
			{
				eId:getEId_enterpriseSingle()
			},
			function(response,
					status, xhr) {
				// $(this).unmask();
			});
}

//显示股东信息页面
function showShareholder(){
	
	$("#shareholder").load(
			"./shareholder/toShareholder",
			{
				eId:getEId_enterpriseSingle()
			},
			function(response,
					status, xhr) {
				// $(this).unmask();
			});
}

//显示知识产权信息页面
function showKonwledgeRight(){
	
	$("#knowledgeRight").load(
			"./knowledgeRight/toKnowledgeRight",
			{
				eId:getEId_enterpriseSingle()
			},
			function(response,
					status, xhr) {
				// $(this).unmask();
			});
}

//显示产品信息页面
function showProduction(){
	
	$("#production").load(
			"./production/toProduction",
			{
				eId:getEId_enterpriseSingle()
			},
			function(response,
					status, xhr) {
				// $(this).unmask();
			});
}


//设置本页面的企业ID
function setEId_enterpriseSingle(eId){
	$("#enterpriseSingle_eId").val(eId);
}

//获取本页面的企业ID
function getEId_enterpriseSingle(){
	return $("#enterpriseSingle_eId").val();
}


</script>


</body>
</html>