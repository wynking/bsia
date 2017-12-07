<%@page import="cn.com.pansky.otp5.baseplatform.dao.po.Dictionary"%>
<%@page import="cn.com.pansky.otp5.common.DictionaryUtil"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- <%@ page import="cn.com.pansky.otp5.baseplatform.enums.ResourceTypeEnum"%>	 --%>
<%
	String eId = (String)(request.getAttribute("eId")!=null?request.getAttribute("eId"):"");
	String isDetail = (String)(request.getAttribute("isDetail")!=null?request.getAttribute("isDetail"):"");
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>企业信息统计</title>

</head>
<body>

	<input type="hidden" id="enterpriseSingle_eId" value="<%=eId%>">
	<ul id="myTab" class="nav nav-tabs">
		<li class="active"><a href="#" id="1"> 上市统计 </a></li>
		<li><a href="#" id="2">高新统计</a></li>
		<li><a href="#" id="3">会员发展趋势统计</a></li>
		<li><a href="#" id="5">知识产权汇总统计</a></li>
		<li><a href="#" id="4">自定义统计</a></li>

	</ul>
	<div id="myTabContent" class="tab-content">
		<div class="tab-pane fade in active" id="public-analysis"></div>
		<div class="tab-pane fade" id="high-analysis"></div>
		<div class="tab-pane fade" id="member-analysis"></div>
		<div class="tab-pane fade" id="knowledge-analysis"></div>
		<div class="tab-pane fade" id="custom-analysis"></div>
	</div>




	<script type="text/javascript">


$(document).ready(function() {
	
	showPublic();
	
	//标签页切换事件
	$('#myTab a').click(function (e) {
		//alertPansky('aaa');
	   e.preventDefault();
	   $curTab = $(e.target);
		  var tabId = $curTab.attr("id");


		  $('#myTabContent .tab-pane').removeClass("active").removeClass("fade").removeClass("in");
		  if(tabId=="1"){
			    $('#public-analysis').addClass("active").addClass("fade").addClass("in");
			    showPublic();
		  }else if(tabId=="2"){
				 $('#high-analysis').addClass("active").addClass("fade").addClass("in");
				 showHigh();
		  }else if(tabId=="3"){
				 $('#member-analysis').addClass("active").addClass("fade").addClass("in");
				 showMember();
		  }else if(tabId=="4"){
				 $('#custom-analysis').addClass("active").addClass("fade").addClass("in");
				 showCustom();
		  }else if(tabId=="5"){
				 $('#knowledge-analysis').addClass("active").addClass("fade").addClass("in");
				 showKnowledge();
		  }
		  $curTab.tab('show');
	  
	  
	 // alertPansky('bbb');
	})
		
	
	
});


function showPublic(){
	//alert("showPublic");
	//alertPansky(getEId_enterpriseSingle());
	$("#public-analysis").load(
			"./analysis/toPublic",
			{},
			function(response,
					status, xhr) {
			});
}

function showHigh(){
	//alert("showHigh");
	//$("#public-analysis").html("");
	$("#high-analysis").load(
			"./analysis/toHigh",
			{},
			function(response,
					status, xhr) {
			});
}

function showMember(){
	//alert("showHigh");
	//$("#public-analysis").html("");
	$("#member-analysis").load(
			"./analysis/toMember",
			{},
			function(response,
					status, xhr) {
			});
}

function showCustom(){
	//alert("showHigh");
	//$("#public-analysis").html("");
	$("#custom-analysis").load(
			"./analysis/toCustom",
			{},
			function(response,
					status, xhr) {
			});
}



function showKnowledge(){
	//alert("showHigh");
	//$("#public-analysis").html("");
	$("#knowledge-analysis").load(
			"./analysis/toKnowledge",
			{},
			function(response,
					status, xhr) {
			});
}





</script>



</body>
</html>