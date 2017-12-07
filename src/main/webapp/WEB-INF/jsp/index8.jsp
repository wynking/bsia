<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="cn.com.pansky.otp5.common.ConstantUtil"%>
<%@page import="cn.com.pansky.otp5.baseplatform.controller.vo.UserVO"%>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 -->

<!DOCTYPE html>
<html>
<head>
<title>长天科技</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!--   收藏用logo图标 -->
<link rel="bookmark" type="image/x-icon"
	href="./resources/img/favicon.ico" />
<!-- 网站显示页logo图标 -->
<link rel="shortcut icon" href="./resources/img/favicon.ico">
<!-- 引入 Bootstrap -->
<!--  <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet"> -->
<link rel="stylesheet" href="./resources/css/bootstrap.min.css">
<!-- <link rel="stylesheet" href="./resources/css/bootstrap-treeview.min.css"> -->
<link rel="stylesheet" href="./resources/css/zTreeStyle.css">
<link rel="stylesheet" href="./resources/css/bootstrapValidator.css" />
<link rel="stylesheet"
	href="./resources/js/bootstrap-table-master/dist/bootstrap-table.min.css">
<link rel="stylesheet"
	href="./resources/js/select2-master/dist/css/select2.min.css">
<link rel="stylesheet"
	href="./resources/js/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.css">
<link rel="stylesheet" 
	href="./resources/js/bootstrap-chinese-region-master/example/lib/bootstrap-chinese-region/bootstrap-chinese-region.css" />
	
<!-- <link rel="stylesheet" 
	href="./resources/js/bootstrap-multiselect-master/dist/css/bootstrap-multiselect.css" type="text/css"/>  -->


<!-- HTML5 Shim 和 Respond.js 用于让 IE8 支持 HTML5元素和媒体查询 -->
<!-- 注意： 如果通过 file://  引入 Respond.js 文件，则该文件无法起效果 -->
<!--[if lt IE 9]>
         <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
         <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
      <![endif]-->

<style type="text/css">
.header {
	height: 50px;
	background-color: #E3E8EE;
}

nav {
	overflow: hidden;
	height: 30px;
	background-color: #006AB0;
}

nav li {
	line-height: 30px;
	float: left;
	padding: 0 6px;
	list-style-type: none;
	cursor: pointer;
	color: white
}

nav li:hover {
	color: red;
}

.left-menu {
	background-color: #1D2B36;
	/* color: white; */
	/* min-height: 100%; */
	height: 100%;
	position: absolute;
	bottom: 0;
	/* max-width: 200px; */
	/* width: 20%  *//* padding-top:10px; 
		padding-left:20px;  */
}

.left-menu .list-group-item-text {
	margin-left: 16px;
}

.left-menu-switch {
	width: 30px;
	height: 30px;
	background-color: white;
	position: absolute;
	top: 200px;
	left: -30px;
	opacity: 0.8;
	filter: Alpha(opacity = 80); /* IE8 以及更早的浏览器 */
	cursor: pointer;
}

.main-content {
/* 	background-color: #F4F8F9; */
	min-height: 100%;
	height: 516px;
	/* width: 80%; */
	/* position: relative; */
	padding: 20px;
	/* background-image: url(./resources/img/1.jpg ); */
	background-repeat: no-repeat;
}

.footer {
	height: 40px;
	background-color: #f2f2f2;
}
#curUserInfo{
	
}

.center-vertical {
    position:relative;
    top:50%;
    transform:translateY(-50%);
}
</style>
</head>

<!-- <body class="container"> -->
 <body class="">

	<header class="header row" style="margin-right: 0px;">
		<div class="col-sm-2" style="background-color: #224B8C;height: 50px;">
			<img src="./resources/img/logo.png" class="center-block center-vertical">
		</div>
		<div class="col-xs-10 " style="padding-right: 0px;padding-left: 15px;background-image : url(./resources/img/cen2.png);height: 50px;">
		  	
		  	<div class="pull-left"><span style="color: white;font-size: 20px;line-height: 50px;">北京软件和信息服务业协会 - 企业信息管理系统</span></div>
		  	<div class="pull-right" style="height: 50px;width: 50px;background-color: #224B8C" >
				<img src="./resources/img/logout.png" class="center-block center-vertical">
		  	</div>
		  	
		</div>
		
	</header>

<!-- 我是导航 -->
<!-- <nav class="nav row">
		<ul>
			<li class="oneLevelNav" trgt="accordion">Apples</li>
			<li class="oneLevelNav" trgt="accordion2">Bananas</li>
			<li class="oneLevelNav" trgt="" url="./resource/list">Lemons</li>
			<li class="oneLevelNav">Oranges</li>
		</ul>
	</nav> -->

	<div class="clearfix row" style="margin-right: 0px;position: relative;" id="content">

		<aside class="left-menu pull-left col-sm-2" style="padding-left: 20px;padding-right: 5px;">
		<div id="curUserInfo" class="" style="margin: 10px 0;">
			<img src="./resources/img/tx.png" class="img-circle center-block" style="width:80px;height:80px;">
			<span class="center-block" style="color: #D0DAE6;text-align: center;">${user.trueName}</span>
			<div style="border-bottom: 2px solid #0F1D28;"></div>
		</div>

		
		<c:set var="isSuper" value="${user.isSuper}"/>  
		
		<c:if test="${isSuper == 0}">
		   
			<div class="panel-group oneLevelNav-pannel" id="accordion" style="margin-top: 5px; margin-bottom: 5px; ">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a class="twoLevelNav" url="./enterprise/list" isLeaf='1'
								href="javascript:void(0);">企业信息管理 </a>
						</h4>
					</div>
				</div>
			</div>


			<div class="panel-group oneLevelNav-pannel" id="dictionary-menu" style="margin-top: 5px; margin-bottom: 5px; ">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a class="twoLevelNav" url="./dictionary/list" isLeaf='1'
								href="javascript:void(0);">字典信息管理 </a>
						</h4>
					</div>
				</div>
			</div>

		</c:if>
		

			
			
		<c:if test="${isSuper == 1}">

			<div class="panel-group oneLevelNav-pannel" id="dictionary-menu" style="margin-top: 5px; margin-bottom: 5px; ">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a class="twoLevelNav" url="./user/list" isLeaf='1'
								href="javascript:void(0);">用户信息管理 </a>
						</h4>
					</div>
				</div>
			</div>
	</c:if>


		</aside>

		<section class="main-content pull-right col-sm-10" style="background-color: #F4F8F9;">
			<div class="left-menu-switch">
				<span class="glyphicon glyphicon-list" style="font-size: 30px;">
			</div>
			我是内容
		</section>

	</div>

	<footer class="footer row" style="margin-right: 0px;">
		<div class="col-xs-12">
			<span class="center-block" style="line-height: 40px;text-align: center;">京ICP备06005339号 版权所有：北京软件和信息服务业协会&nbsp;&nbsp;&nbsp;&nbsp;技术支持：<a href="http://www.pansky.com.cn" target="_blank">长天科技有限公司</a></span>
		</div>
	 </footer>










	<!-- jQuery (Bootstrap 的 JavaScript 插件需要引入 jQuery) -->
	<!-- <script src="https://code.jquery.com/jquery.js"></script> -->
	<script src="./resources/js/jquery-2.1.1.js"></script>
	<!-- 包括所有已编译的插件 -->
	<!--   <script src="js/bootstrap.min.js"></script> -->
	<script type="text/javascript" src="./resources/js/bootstrap.min.js"></script>
	<script type="text/javascript"
		src="./resources/js/bootstrapValidator.js"></script>
	<script type="text/javascript"
		src="./resources/js/bootstrap-table-master/dist/bootstrap-table.min.js"></script>
	<!-- <script src="./resources/js/bootstrap-treeview.min.js"></script> -->
	<script  type="text/javascript" 
		src="./resources/js/jquery.ztree.all.min.js"></script>
	<script  type="text/javascript" 
		src="./resources/js/select2-master/dist/js/select2.full.min.js"></script>
	<script  type="text/javascript" 
		src="./resources/js/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.js"></script>
	<script  type="text/javascript"  
		src="./resources/js/bootstrap-datetimepicker-master/js/locales/bootstrap-datetimepicker.zh-CN.js"/></script>
	<script  type="text/javascript" 
		src="./resources/js/select2-master/dist/js/i18n/zh-CN.js"></script>
		<script type="text/javascript"
		src="./resources/js/bootstrap-chinese-region-master/example/lib/bootstrap-chinese-region/bootstrap-chinese-region.js"></script>
		
	
	
	<!-- <script type="text/javascript" 
		src="./resources/js/bootstrap-multiselect-master/dist/js/bootstrap-multiselect.js"></script>  --> 
	<script type="text/javascript" src="./resources/js/common.js"></script>

	<!-- Latest compiled and minified Locales -->
	<script type="text/javascript"
		src="./resources/js/bootstrap-table-master/dist/locale/bootstrap-table-zh-CN.min.js"></script>
	<script type="text/javascript"
		src="./resources/js/bootstrapvalidator-master/dist/js/language/zh_CN.js"></script>



	<script type="text/javascript">
		$(document).ready(
				function() {
					
					var height = $(document).height() - 60 - 40;;
					//alert(height - 60 -40);
					
					$("#content").css("min-height",height);
					$(".left-menu").css("min-height",height);
					

					$(".left-menu .list-group-item-heading").click(
							function(event) {
								event.preventDefault();
								$(this).parent().next().toggleClass("hide");
							});

					$(".left-menu .list-group-item")
							.click(
									function(event) {
										event.preventDefault();
										if ($(this).hasClass("threeLevel")) {
											if ($(this).next().hasClass(
													"fourLevelNav")) {
												//alert("存在四级");
											} else {
												var url = $(this).attr("url");

												$(".main-content").load(
														url,
														function(response,
																status, xhr) {
															// $(this).unmask();
														});
											}
										} else {
											var url = $(this).attr("url");
											//alert(url);
											//url = "www.baidu.com";
											$(".main-content").load(
													url,
													function(response, status,
															xhr) {
														//$(this).unmask();
													});
										}
									});

					$(".oneLevelNav").click(
							function(event) {
								event.preventDefault();
								// alert();
								var a = $(this).attr("trgt");
								//  alert(a);
								//如果一级导航没有下级导航
								if (a == "") {
									$(".left-menu").hide();
									$(".main-content").css("width", "100%");
									var url = $(this).attr("url");
									$(".main-content").load(url,
											function(response, status, xhr) {

											});
								} else {
									$(".main-content").css("width", "80%");
									$(".left-menu").show();
									$(".oneLevelNav-pannel").hide();
									$("#" + a).removeClass("hide").show();
								}

							});

					$(".twoLevelNav").click(
							function(event) {
								event.preventDefault();
								// alert();
								var a = $(this).attr("isLeaf");
								// alert(a);
								if (a) {
									var url = $(this).attr("url");
									$(".main-content").load(url,
											function(response, status, xhr) {

											});
								}

							});

					$(".left-menu-switch").click(function(event) {

						//alert($(".left-menu").is(":hidden"));
						if ($(".left-menu").is(":hidden")) {
							$(".left-menu").show("slow", function() {
								$(".main-content").css("width", "80%");
								$(".left-menu-switch").css("left", "-30px");
							});
						} else {
							$(".left-menu").hide("slow", function() {
							});
							$(".main-content").css("width", "100%");
							$(".left-menu-switch").css("left", "0");
						}

						// $(".left-menu-switch .glyphicon").toggleClass("glyphicon-forward");

					});

				});
	</script>

</body>
</html>
