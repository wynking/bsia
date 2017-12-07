<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 -->

<!DOCTYPE html>
<html>
<head>
<title>长天科技</title>
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

<style type="text/css">

body{
	background-color: #679BFE; 
}

.center-vertical {
   margin: auto;  
  position: absolute;  
  top: 0; left: 0; bottom: 0; right: 0;  
}


</style>
</head>



<body>



<div class="container">


<div class="row center-vertical"  style="height: 220px;">
	<div class="center-block " style="width: 50%;" >
	<div class="col-md-12" >
			<div class="panel panel-info">
				<div class="panel-heading">
					<span class="panel-title">登录</span>
				</div>
				<div class="panel-body">
					
					<form id="form-login" class="form-horizontal" role="form">
					  <div class="form-group ">
					    <div class="col-md-offset-1 col-sm-10 input-group input-group-md">
					    	<span class="input-group-addon" id="sizing-addon1"><i class="glyphicon glyphicon-user" aria-hidden="true"></i></span>
                        	<input type="text" class="form-control" id="loginName" name="loginName" value="wangyanan" placeholder="请输入登录名">
					    </div>
					  </div>
					  <div class="form-group">
					    <div class="col-md-offset-1 col-sm-10 input-group input-group-md">
					    	<span class="input-group-addon" id="sizing-addon1"><i class="glyphicon glyphicon-lock" aria-hidden="true"></i></span>
                        	<input type="password" class="form-control" id="password" name="password" value="123456" placeholder="请输入密码">
					    </div>
					  </div>
					  
					  <div class="form-group">
							<button type="button" class= "btn btn-success col-md-offset-1 col-sm-10" id="btn-login">登录</button>
					  </div>
					  
					</form>
				</div>
			</div>
		</div>
	</div>

</div>


<!-- 
<div class="row">
	<div class="center-block " style="width: 50%;" >
	<div class="col-md-12" >
			<div class="panel panel-info">
				<div class="panel-heading">
					<h2 class="panel-title">登录</h2>
				</div>
				<div class="panel-body">
					
					<form class="form-horizontal" role="form">
					  <div class="form-group ">
					    <div class="col-md-offset-1 col-sm-10 input-group input-group-md">
					    	<span class="input-group-addon" id="sizing-addon1"><i class="glyphicon glyphicon-user" aria-hidden="true"></i></span>
                        	<input type="text" class="form-control" id="loginName" placeholder="请输入登录名">
					    </div>
					  </div>
					  <div class="form-group">
					    <div class="col-md-offset-1 col-sm-10 input-group input-group-md">
					    	<span class="input-group-addon" id="sizing-addon1"><i class="glyphicon glyphicon-lock" aria-hidden="true"></i></span>
                        	<input type=""password"" class="form-control" id="password" placeholder="请输入密码">
					    </div>
					  </div>
					  
					  <div class="form-group">
							<button type="button" class="btn btn-success col-md-offset-1 col-sm-10">登录</button>
					  </div>
					  
					</form>
				</div>
			</div>
		</div>
	</div>
	</div>
	 -->
	
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
