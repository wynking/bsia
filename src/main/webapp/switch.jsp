<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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

 <link href="./resources/js/bootstrap-switch-master/docs/css/highlight.css" rel="stylesheet">
    <link href="./resources/js/bootstrap-switch-master/dist/css/bootstrap3/bootstrap-switch.css" rel="stylesheet">
	

<!-- HTML5 Shim 和 Respond.js 用于让 IE8 支持 HTML5元素和媒体查询 -->
<!-- 注意： 如果通过 file://  引入 Respond.js 文件，则该文件无法起效果 -->
<!--[if lt IE 9]>
         <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
         <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
      <![endif]-->

<style type="text/css">

</style>
</head>



<body>



	<!-- jQuery (Bootstrap 的 JavaScript 插件需要引入 jQuery) -->
	<!-- <script src="https://code.jquery.com/jquery.js"></script> -->
	<script src="./resources/js/jquery-2.1.1.js"></script>
	<!-- 包括所有已编译的插件 -->
	<!--   <script src="js/bootstrap.min.js"></script> -->
	<script type="text/javascript" src="./resources/js/bootstrap.min.js"></script>
	<script src="./resources/js/bootstrap-switch-master/docs/js/highlight.js"></script>
	<script type="text/javascript"
		src="./resources/js/bootstrap-switch-master/dist/js/bootstrap-switch.min.js"></script>	

	

	<script type="text/javascript">
		$(document).ready(function() {
			 $.getJSON('./resources/js/bootstrap-chinese-region-master/example/lib/bootstrap-chinese-region/sql_areas.json',function(data){

				 for (var i = 0; i < data.length; i++) {
						var area = {id:data[i].id,name:data[i].cname,level:data[i].level,parentId:data[i].upid};
						data[i] = area;
					}

					$('.bs-chinese-region').chineseRegion('source',data).on('completed.bs.chinese-region',function(e,areas){
						$(this).find('[name=address]').val(areas[areas.length-1].id);
					});
				 
			    });
			
		});
		
		
	</script>

</body>
</html>
