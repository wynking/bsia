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
<link href="./resources/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="./resources/css/bootstrapValidator.css" />
<link rel="stylesheet" href="./resources/css/zTreeStyle.css">



<!-- HTML5 Shim 和 Respond.js 用于让 IE8 支持 HTML5元素和媒体查询 -->
<!-- 注意： 如果通过 file://  引入 Respond.js 文件，则该文件无法起效果 -->
<!--[if lt IE 9]>
         <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
         <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
      <![endif]-->

</head>



<body>

	<div class="ibox-content" style="height: 700px;">
		<ul id="tree1" class="ztree"></ul>
		<!-- id="tree1"是ZTree的标识 ，class="ztree"是引入ztree的类 -->
	</div>


	<!-- jQuery (Bootstrap 的 JavaScript 插件需要引入 jQuery) -->
	<!-- <script src="https://code.jquery.com/jquery.js"></script> -->
	<script src="./resources/js/jquery-2.1.1.js"></script>
	<!-- 包括所有已编译的插件 -->
	<!--   <script src="js/bootstrap.min.js"></script> -->
	<script src="./resources/js/bootstrap.min.js"></script>
	<script type="text/javascript"
		src="./resources/js/bootstrapValidator.js"></script>
	<script src="./resources/js/jquery.ztree.all.min.js"></script>

	<script type="text/javascript">
		$(function() {

			var setting = {
				data : {
					simpleData : {
						enable : true,
						idKey : "id",
						idPKey : "pId",
					}
				},
				async : {
					enable : true, //开启异步加载
					url : "resource/showResourceTree", //设置获取异步动态加载的数据
					dataType : "text",
					autoParam : [ "id" ],
					otherParam : {},
					dataFilter : filter
				},

				callback : {
					onClick : onClick
				//回调函数为单击操作
				}
			};
			//滤波
			function filter(treeId, parentNode, childNodes) {
				var array = childNodes;
				return array;
			}

			//单击操作---------------------------------------------------------------------------------------------
			function onClick(event, treeId, treeNode, clickFlag) {
				
				if (treeNode.isParent) {
					var zTree = $.fn.zTree.getZTreeObj(treeId);
					//扩展所有子节点
					zTree.expandNode(treeNode);
				}
				
				//单击后在标题处 重新拼接html,显示为***部门的任务信息概览
			/* 	document.getElementById('dg_h').innerHTML = '<h5>['
						+ treeNode.name + ']任务信息概览</h5>'; */

				//单击后的操作
				onClickAjax(treeNode.id,treeNode.name);
			}

			//初始化操作---严格按照头文件加载，否则会出现init函数没有定义这种情况-------------------------------------------------------------------------------------
			function init() {
				
				$.post("resource/showResourceTree", {
					id : 0
				}, function(data) { //id=3是初始输入，确立根节点的id=3
					//alert(data);
					//var result = $.parseJSON(data);
				//alert('aaa');
					$.fn.zTree.init($("#tree1"), setting, data); //将得到的数据解析并填充到ZTree上
					//alert('bbb');

				});
			}
			
			
			init();
		});

		/**
		 * =====点击节点的操作来获取相应的信息的后台请求操作=======================
		 * =============================
		 * */
		function onClickAjax(id,treeNodeName) {
			$.ajax({
				url : "resource/showResourceTree",
				data : {
					ORG_NAME : treeNodeName
				}, //向后台传递的数据是名字 也可以是id 可以根据实际情况来定
				type : 'post',
				async : false,
				dataType : "json", //返回的数据类型是json
				success : function(data) {
					//alert('got it!');
					$('#tree1').datagrid('loadData', data);//回调成功后重新加载表格的数据 ，前提是表格的字段已经确定
				},
			});
		}
	</script>

</body>
</html>
