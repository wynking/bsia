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
		<ul id="treeDemo" class="ztree"></ul>
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
		var zTreeObj;
		// zTree 的参数配置，深入使用请参考 API 文档（setting 配置详解）  
		var setting = {
			check : {
				enable : false,

			//nocheckInherit: false  
			//chkStyle: 'checkbox',  

			//chkboxType: { "Y": "ps", "N": "ps" }  
			},
			view : {
				selectedMulti : false,
				showIcon : true, //设置是否显示节点图标  
				showLine : true, //设置是否显示节点与节点之间的连线  
			// fontCss: setFontCss  
			},
			async : {
				enable : false, // 设置 zTree是否开启异步加载模式  加载全部信息  
				url : "", // Ajax 获取数据的 URL 地址    
				autoParam : [ "id" ], // 异步加载时自动提交父节点属性的参数,假设父节点 node = {id:1, name:"test"}，异步加载时，提交参数 zId=1     
			//dataFilter: filter  
			},
			callback : {
				onClick : zTreeOnOnClick ,
				onExpand : zTreeOnOnClick
			//点击事件  
			},
			data : { // 必须使用data    
				simpleData : {
					enable : true,
					idKey : "id", // id编号命名 默认    
					pIdKey : "pId", // 父id编号命名 默认    
					rootPId : 0
				// 用于修正根节点父节点数据，即 pIdKey 指定的属性值    
				}
			}
		};
		
	    function zTreeOnOnClick(event, treeId, treeNode){  
	           var treeObj = $.fn.zTree.getZTreeObj(treeId);  
	           var node = treeObj.getNodeByTId(treeNode.tId);  
	          // alert('aaa');
	         //没有子节点才去查询  
	            if(node.children == null || node.children == "undefined"){  
	                $.ajax({  
	                    type: "POST",  
	                    async:false,  
	                    url: "resource/showResourceTree",  
	                    data:{  
	                        id : treeNode.id  
	                    },  
	                    dataType:"json",
	                    success: function(data){  
	                        if(data !=null && data !=""){  
	                            //添加新节点  
	                            newNode = treeObj.addNodes(node, data);  
	                        }  
	                    },  
	                    error:function(event, XMLHttpRequest, ajaxOptions, thrownError){  
	                        result = true;  
	                        alert("请求失败!");  
	                    }  
	                });  
	            }  
	    }
		$(document).ready(
				function() {
					//显示区域树  
					$.ajax({
						type : "POST",
						url : "resource/showResourceTree",
						data : {id:0},
						dataType : "json",
						success : function(data) {

							zTreeObj = $.fn.zTree.init($("#treeDemo"), setting,
									data);
						},
					});

				});
	</script>

</body>
</html>
