<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
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


<!-- HTML5 Shim 和 Respond.js 用于让 IE8 支持 HTML5元素和媒体查询 -->
<!-- 注意： 如果通过 file://  引入 Respond.js 文件，则该文件无法起效果 -->
<!--[if lt IE 9]>
         <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
         <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
      <![endif]-->

<style type="text/css">
.header {
	height: 60px;
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
	background-color: #3D3F43;
	/* color: white; */
	min-height: 500px;
	height: auto;
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
	background-color: white;
	min-height: 500px;
	height: auto;
	/* width: 80%; */
	position: relative;
	padding: 20px;
}

.footer {
	height: 40px;
	background-color: #E3E8EE;
}
</style>
</head>



<body class="container">



	<header class="header row"> 我是头部 </header>

	<nav class="nav row">
		<!-- 我是导航 -->
		<ul>
			<li class="oneLevelNav" trgt="accordion">Apples</li>
			<li class="oneLevelNav" trgt="accordion2">Bananas</li>
			<li class="oneLevelNav" trgt="" url="./resource/list">Lemons</li>
			<li class="oneLevelNav">Oranges</li>
		</ul>
	</nav>

	<div class="clearfix row" >

		<aside class="left-menu pull-left col-sm-3">


			<div class="panel-group oneLevelNav-pannel" id="accordion">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" data-parent="#accordion"
								href="#collapseOne"> 系统管理<span class="caret"></span>
							</a>
						</h4>
					</div>
					<div id="collapseOne" class="panel-collapse collapse in">
						<div class="panel-body" style="border: 0px;">
							<div class="list-group" style="margin-bottom: 0px">
								<a href="javascript:void(0)" class="list-group-item threeLevel">
									<p class="list-group-item-heading">
										三级导航 <span class="caret"></span>
									</p>
								</a>
								<div class="fourLevelNav hide">
									<a href="javascript:void(0)" class="list-group-item "
										url="./user/list">
										<p class="list-group-item-text ">四级导航</p>
									</a> <a href="javascript:void(0)" class="list-group-item "
										url="./role/list">
										<p class="list-group-item-text ">四级导航</p>
									</a>
								</div>
							</div>

							<div class="list-group" style="margin-bottom: 0px">
								<a href="javascript:void(0)" class="list-group-item threeLevel">
									<p class="list-group-item-heading ">
										三级导航 <span class="caret"></span>
									</p>
								</a>
								<div class="fourLevelNav hide">
									<a href="javascript:void(0)" class="list-group-item "
										url="./user/list">
										<p class="list-group-item-text ">四级导航</p>
									</a> <a href="javascript:void(0)" class="list-group-item "
										url="./role/list">
										<p class="list-group-item-text ">四级导航</p>
									</a>
								</div>
							</div>
							<div class="list-group" style="margin-bottom: 0px">
								<a href="javascript:void(0)" class="list-group-item threeLevel"
									url="./resource/list">
									<p class="list-group-item-heading ">资源管理</p>
								</a>
							</div>

						</div>

					</div>
				</div>

				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a class="twoLevelNav" data-toggle="collapse"
								data-parent="#accordion" href="#collapseTwo"> 二级导航<span
								class="caret"></span>
							</a>
						</h4>
					</div>
					<div id="collapseTwo" class="panel-collapse collapse ">
						<div class="panel-body">
							<div class="list-group">
								<a href="#" class="list-group-item threeLevel">
									<p class="list-group-item-heading">
										三级导航 <span class="caret"></span>
									</p>
								</a>
								<div class="fourLevelNav hide">
									<a href="#" class="list-group-item">
										<p class="list-group-item-text ">四级导航</p>
									</a> <a href="#" class="list-group-item">
										<p class="list-group-item-text ">四级导航</p>
									</a>
								</div>

							</div>
						</div>
					</div>
				</div>


				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a class="twoLevelNav" url="./user/list" isLeaf='1'
								href="javascript:void(0);"> 二级导航 </a>
						</h4>
					</div>
				</div>



			</div>





			<div class="panel-group oneLevelNav-pannel hide" id="accordion2">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" data-parent="#accordion2"
								href="#collapse3"> 系统管理2 </a>
						</h4>
					</div>
					<div id="collapse3" class="panel-collapse collapse in">
						<div class="panel-body" style="border: 0px;">
							<div class="list-group" style="margin-bottom: 0px">
								<a href="javascript:void(0)" class="list-group-item threeLevel">
									<p class="list-group-item-heading">
										三级导航 <span class="caret"></span>
									</p>
								</a>
								<div class="fourLevelNav hide">
									<a href="javascript:void(0)" class="list-group-item "
										url="./user/list">
										<p class="list-group-item-text ">四级导航</p>
									</a> <a href="javascript:void(0)" class="list-group-item "
										url="./role/list">
										<p class="list-group-item-text ">四级导航</p>
									</a>
								</div>
							</div>

							<div class="list-group" style="margin-bottom: 0px">
								<a href="javascript:void(0)" class="list-group-item threeLevel">
									<p class="list-group-item-heading ">
										三级导航 <span class="caret"></span>
									</p>
								</a>
								<div class="fourLevelNav hide">
									<a href="javascript:void(0)" class="list-group-item "
										url="./user/list">
										<p class="list-group-item-text ">四级导航</p>
									</a> <a href="javascript:void(0)" class="list-group-item "
										url="./role/list">
										<p class="list-group-item-text ">四级导航</p>
									</a>
								</div>
							</div>
							<div class="list-group" style="margin-bottom: 0px">
								<a href="javascript:void(0)" class="list-group-item threeLevel"
									url="./role/list">
									<p class="list-group-item-heading ">三级导航</p>
								</a>
							</div>

						</div>

					</div>
				</div>

				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" data-parent="#accordion2"
								href="#collapse4"> 二级导航2 </a>
						</h4>
					</div>
					<div id="collapse4" class="panel-collapse collapse ">
						<div class="panel-body">
							<div class="list-group">
								<a href="#" class="list-group-item threeLevel">
									<p class="list-group-item-heading">
										三级导航 <span class="caret"></span>
									</p>
								</a>
								<div class="fourLevelNav hide">
									<a href="#" class="list-group-item">
										<p class="list-group-item-text ">四级导航</p>
									</a> <a href="#" class="list-group-item">
										<p class="list-group-item-text ">四级导航</p>
									</a>
								</div>

							</div>
						</div>
					</div>
				</div>

			</div>







		</aside>

		<section class="main-content pull-right col-sm-9">
			<div class="left-menu-switch">
				<span class="glyphicon glyphicon-list" style="font-size: 30px;">
			</div>
			我是内容
		</section>

	</div>

	<footer class="footer row"> 我是页脚 </footer>










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
	<script src="./resources/js/jquery.ztree.all.min.js"></script>
	<script type="text/javascript" src="./resources/js/common.js"></script>

	<!-- Latest compiled and minified Locales -->
	<script type="text/javascript"
		src="./resources/js/bootstrap-table-master/dist/locale/bootstrap-table-zh-CN.min.js"></script>
	<script type="text/javascript"
		src="./resources/js/bootstrapvalidator-master/dist/js/language/zh_CN.js"></script>



	<script type="text/javascript">
		$(document).ready(
				function() {

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
