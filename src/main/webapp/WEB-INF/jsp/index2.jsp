<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 -->
<!DOCTYPE html>
<html>
<head>
<title>首页</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 
width 	设置layout viewport  的宽度，为一个正整数，或字符串"width-device"
initial-scale 	设置页面的初始缩放值，为一个数字，可以带小数
minimum-scale 	允许用户的最小缩放值，为一个数字，可以带小数
maximum-scale 	允许用户的最大缩放值，为一个数字，可以带小数
height 	设置layout viewport  的高度，这个属性对我们并不重要，很少使用
user-scalable 	是否允许用户进行缩放，值为"no"或"yes", no 代表不允许，yes代表允许 
-->
<meta name="viewport" content="width=device-width, initial-scale=1.0">

 <!-- 引入 Bootstrap -->
<!-- <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
 -->
 
 <link href="./resources/css/bootstrap.css" rel="stylesheet">
 

<style type="text/css">
	.header{
		height: 60px;
		background-color: #E3E8EE;
	}
	.container{
		min-height: 500px;
		height:auto;
		background-color: gray;
		width: 100%;
	}
	.left-menu{
		background-color: #2C2E2F;
		/* color: white; */
		min-height: 500px;
		height:auto;
		/* padding-top:10px; 
		padding-left:20px;  */
	}
	.main-content{
		background-color: #EEEEEE;
		min-height: 500px;
		height:auto;
	}
	.footer{
		height: 40px;
		background-color: #E3E8EE;
	}
</style>

</head>
<body>

	<div class="header">
		我是头部
	</div>
	
	<div class="container">
		
		<div class="row">
			<div class="left-menu col-sm-2 col-md-2 col-lg-2">
	
					<div class="panel-group" id="accordion">
					    <div class="panel panel-default">
					        <div class="panel-heading">
					            <h4 class="panel-title">
					                <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
					                	系统管理
					                </a>
					            </h4>
					        </div>
					        <div id="collapseOne" class="panel-collapse collapse in">
					            <div class="panel-body">
									<ul id="systemSetting" class="nav nav-list ">
										<li url="./user/list"><a href="#"><i class="glyphicon glyphicon-user"></i>用户管理</a></li>
										<li><a href="#"><i class="glyphicon glyphicon-th-list"></i>菜单管理</a></li>
										<li  url="./role/list"><a href="#"><i
												class="glyphicon glyphicon-asterisk"></i>角色管理</a></li>
										<li><a href="#"><i class="glyphicon glyphicon-edit"></i>修改密码</a></li>
										<li><a href="#"><i
												class="glyphicon glyphicon-eye-open"></i>日志查看</a></li>
									</ul>
								</div>
					        </div>
					    </div>
					    <div class="panel panel-default">
					        <div class="panel-heading">
					            <h4 class="panel-title">
					                <a data-toggle="collapse" data-parent="#accordion" 
					                href="#collapseTwo">
					                	客户管理
					            </a>
					            </h4>
					        </div>
					        <div id="collapseTwo" class="panel-collapse collapse in">
					        <div class="panel-body">
					            <ul id="kehuManage" class="nav nav-list">
										<li><a href="#"><i class="glyphicon glyphicon-user clickMenu"></i>用户管理</a></li>
										<li><a href="#"><i class="glyphicon glyphicon-th-list"></i>菜单管理</a></li>
										<li><a href="#"><i
												class="glyphicon glyphicon-asterisk"></i>角色管理</a></li>
										<li><a href="#"><i class="glyphicon glyphicon-edit"></i>修改密码</a></li>
										<li><a href="#"><i
												class="glyphicon glyphicon-eye-open"></i>日志查看</a></li>
									</ul>
					        </div>
					        </div>
					    </div>
					</div>
					
				</div>
				
				
				<div class="main-content col-sm-10 col-md-10 col-lg-10 ">
					我是工作区
					
					
				</div>
			
		</div>
			
	</div>
	
	
	
	
	<div class="footer">
		我是脚部
	</div>


     <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<!-- <script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script> -->
<script src="./resources/js/jquery-2.1.1.js"></script>
 
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<!-- <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> -->
<script src="./resources/js/bootstrap.min.js"></script>

<script type="text/javascript">
	

$(document).ready(function(){
	
	  $(".nav-list li").click(function(event){
		  event.preventDefault();
		  //alert('aa');
		  var url = $(this).attr("url") ;
		 //$("#section").mask();
		  $(".main-content").load(url,function(response,status,xhr){
			 // $(this).unmask();
		  });
	  });
	  
	  
});

</script>
</body>
</html>