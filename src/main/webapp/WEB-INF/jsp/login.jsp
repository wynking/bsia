<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 -->

<!DOCTYPE html>
<html>
<head>
<title>企业信息管理系统</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!--   收藏用logo图标 -->
<link rel="bookmark" type="image/x-icon"
	href="../resources/img/favicon.ico" />
<!-- 网站显示页logo图标 -->
<link rel="shortcut icon" href="../resources/img/favicon.ico">
<!-- 引入 Bootstrap -->
<!--  <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet"> -->
<link rel="stylesheet" href="../resources/css/bootstrap.min.css">
<!-- <link rel="stylesheet" href="./resources/css/bootstrap-treeview.min.css"> -->

<link rel="stylesheet" href="../resources/css/bootstrapValidator.css" />



<!-- HTML5 Shim 和 Respond.js 用于让 IE8 支持 HTML5元素和媒体查询 -->
<!-- 注意： 如果通过 file://  引入 Respond.js 文件，则该文件无法起效果 -->
<!--[if lt IE 9]>
         <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
         <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
      <![endif]-->

<style>

html, body {
    height:100%;
    margin:0;
    padding:0
}
#dHead {
     height: auto; 
   /*  height: 400px; */
    /* line-height:100px; */
  /*   background:#690; */
	width:100%;
   /*  position:absolute; */
   /*  z-index:5;
    top:0; */
    text-align:center;
}
#dBody {
    /* background:#FC0; */
    width:100%;
   /*  overflow:auto; */
  /*   top:430px; */
   /*  position: relative; */
}
.mycontent {
    /* padding:20px; */
     width:300px;
     padding-top: 50px;
}





</style>
</head>
<body class="">

<div id="dHead">
	<img style="display: block;" src="../resources/img/login_top2.png" class="center-block center-vertical img-responsive">
</div>

<div id="dBody" style="">
  <div class="mycontent center-block" >
	   <form id="form-login" class="form-horizontal " role="form">
					  <div class="form-group ">
					    <div class="input-group input-group-md">
					    	<span class="input-group-addon" id="sizing-addon1"><i class="glyphicon glyphicon-user" aria-hidden="true"></i></span>
                        	<input type="text" class="form-control" id="loginName" name="loginName" value="wangyanan" placeholder="请输入登录名">
					    </div>
					  </div>
					  <div class="form-group">
					    <div class="input-group input-group-md">
					    	<span class="input-group-addon"  id="sizing-addon1"><i class="glyphicon glyphicon-lock" aria-hidden="true"></i></span>
                        	<input type="password" class="form-control" id="password" name="password" value="123456" placeholder="请输入密码">
					    </div>
					  </div>
					  
					  <div class="form-group" style="">
							<button type="button" class= "btn  form-control" style="background-color: #5171C1;color: white;font-size: 18px;line-height: 18px;" id="btn-login">登录</button>
					  </div>
					  
					</form>
  </div>
</div>
<!-- jQuery (Bootstrap 的 JavaScript 插件需要引入 jQuery) -->
	<!-- <script src="https://code.jquery.com/jquery.js"></script> -->
	<script src="../resources/js/jquery-2.1.1.js"></script>
	<!-- 包括所有已编译的插件 -->
	<!--   <script src="js/bootstrap.min.js"></script> -->
	<script type="text/javascript" src="../resources/js/bootstrap.min.js"></script>
	<script type="text/javascript"
		src="../resources/js/bootstrapValidator.js"></script>

	<script type="text/javascript" src="../resources/js/common.js"></script>

	<!-- Latest compiled and minified Locales -->
	<script type="text/javascript"
		src="../resources/js/bootstrapvalidator-master/dist/js/language/zh_CN.js"></script>



	<script type="text/javascript">
		$(document).ready(function() {

				$("#btn-login").click(function(){
					login();
				});
			
		});

		
		function login(){
			
			//alert(JSON.stringify($("#form-login").serialize()));
			$.ajax({
				url : "login",
				type : 'POST',
				dataType : "JSON",
				data : $("#form-login").serialize(),
				success : function(resp) {
					//alert(JSON.stringify(resp));
					if(resp.flag==1){
						//alert("登录成功");
						window.location.href = "../index";
					}else{
						if(resp.msg!=""){
							alert(resp.msg);
						}else{
							alert("登录失败");
						}
					}
					//$('#resource-table').bootstrapTable('load', resp);
					// initTable();
					 //alert("提交成功");
					// $("#user_add").modal('hide');
				},
				error : function(){
					alert("系统异常，登录失败！");
				}
			});
			
		}
		
	</script>
</body>
</html>
