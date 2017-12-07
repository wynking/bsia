<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
   <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
   
   <!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.1/bootstrap-table.min.css">
	
  <!-- jQuery (Bootstrap 的 JavaScript 插件需要引入 jQuery) -->
      <script src="https://code.jquery.com/jquery.js"></script>
      <!-- 包括所有已编译的插件 -->
    <!--   <script src="js/bootstrap.min.js"></script> -->
      <script src="./resources/js/bootstrap.min.js"></script>
      
      	<!-- Latest compiled and minified JavaScript -->
	<script
		src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.1/bootstrap-table.min.js"></script>

	<!-- Latest compiled and minified Locales -->
	<script
		src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.1/locale/bootstrap-table-zh-CN.min.js"></script>
      
</head>
<body>

<table id="table"></table>




	<script type="text/javascript">
	
	
	$(document).ready(function(){
		
		$('#table').bootstrapTable({
			columns : [ {
				field : 'id',
				title : 'Item ID'
			}, {
				field : 'name',
				title : 'Item Name'
			}, {
				field : 'price',
				title : 'Item Price'
			} ],
			data : [ {
				id : 1,
				name : 'Item 1',
				price : '$1'
			}, {
				id : 2,
				name : 'Item 2',
				price : '$2'
			} ]
		});
		
	})
	</script>
</body>
</html>