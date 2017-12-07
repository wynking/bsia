<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户管理</title>
<!-- <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet"> -->
<link href="../resources/css/bootstrap.min.css" rel="stylesheet">

<!-- Latest compiled and minified CSS -->
<!-- <link rel="stylesheet"
	href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.1/bootstrap-table.min.css"> -->
<link rel="stylesheet"
	href="../resources/js/bootstrap-table-master/dist/bootstrap-table.min.css">

<!-- jQuery (Bootstrap 的 JavaScript 插件需要引入 jQuery) -->
<!--   <script src="https://code.jquery.com/jquery.js"></script> -->
<script src="../resources/js/jquery-2.1.1.js"></script>
<!-- 包括所有已编译的插件 -->
<!--   <script src="js/bootstrap.min.js"></script> -->
<script src="../resources/js/bootstrap.min.js"></script>

<!-- Latest compiled and minified JavaScript -->
<!-- <script
		src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.1/bootstrap-table.min.js"></script> -->
<script
	src="../resources/js/bootstrap-table-master/dist/bootstrap-table.min.js"></script>

<!-- Latest compiled and minified Locales -->
<script
	src="../resources/js/bootstrap-table-master/dist/locale/bootstrap-table-zh-CN.min.js"></script>


</head>
<body>



	<table class="table table-hover" id="table">

	</table>


<script type="text/javascript">
	var $table = $('#table');
    $table.bootstrapTable({
    url: "findByPage", 
    //method: "get",  //使用get请求到服务器获取数据 
    method: "post", 
    contentType:"application/x-www-form-urlencoded; charset=UTF-8",
    dataType: "json",
    pagination: true, //分页
    singleSelect: false,
  //  data-locale:"zh-US",  //表格汉化
    search: true, //显示搜索框
    sidePagination: "server", //服务端处理分页
          columns: [
                  {
                    title: '活动名称',
                      field: 'id',
                      align: 'center',
                      valign: 'middle'
                  }, 
                  {
                      title: '状态',
                      field: 'loginName',
                      align: 'center',
                      valign: 'middle',
                  }, 
                  {
                      title: '参与人数',
                      field: 'password',
                      align: 'center'
                  },
                  {
                      title: '操作',
                      field: 'id',
                      align: 'center',
                      formatter:function(value,row,index){  
                   var e = '<a href="#" mce_href="#" onclick="edit(\''+ row.id + '\')">编辑</a> ';  
                   var d = '<a href="#" mce_href="#" onclick="del(\''+ row.id +'\')">删除</a> ';  
                        return e+d;  
                    } 
                  }
              ]
      });
	</script>
</body>
</html>