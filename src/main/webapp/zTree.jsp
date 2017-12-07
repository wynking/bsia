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
<link rel="stylesheet" href="./resources/css/bootstrapValidator.css"/>
<link rel="stylesheet" href="./resources/css/zTreeStyle.css">
    


<!-- HTML5 Shim 和 Respond.js 用于让 IE8 支持 HTML5元素和媒体查询 -->
<!-- 注意： 如果通过 file://  引入 Respond.js 文件，则该文件无法起效果 -->
<!--[if lt IE 9]>
         <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
         <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
      <![endif]-->

</head>



<body>

<div id="DropdownMenuBackground" style="position:absolute; height:260px;width: 260px;min-width:205px; background-color:white;border:1px solid;overflow-y:auto;overflow-x:auto;margin-top: 74px;margin-left: 47%;">
    <!-- ztree要求放在ul元素下， class指定为ztree样式文件的ztree -->
    <ul id="dropdownMenu" class="ztree"></ul>
</div>


	<!-- jQuery (Bootstrap 的 JavaScript 插件需要引入 jQuery) -->
	<!-- <script src="https://code.jquery.com/jquery.js"></script> -->
	<script src="./resources/js/jquery-2.1.1.js"></script>
	<!-- 包括所有已编译的插件 -->
	<!--   <script src="js/bootstrap.min.js"></script> -->
	<script src="./resources/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="./resources/js/bootstrapValidator.js"></script>
	<script src="./resources/js/jquery.ztree.all.min.js"></script>

	<script type="text/javascript">
	//ztree配置
	var setting = {
	    async: {
	        //设置 zTree 是否开启异步加载模式默认值：false
	        enable: true,
	        //请求URL
	        url:"resource/showResourceTree",
	        //请求时自动提交的参数， 该参数服务端可直接用$_POST(php)/（java）request.getParameter()获取
	        autoParam:["id", "name"],
	        //返回的数据过滤器
	        dataFilter: filter,
	        //对返回返回的数据字体设置
	        fontCss: setFont,
	    },
	    //设置回调
	    callback: {
	        //节点点击之前触发方法
	       // beforeClick: zTreeOnBeforeClick,
	        //节点点击时
	        onClick: zTreeOnClick,
	        //异步请求错误回调方法
	        onAsyncError: zTreeOnAsyncError
	    }
	};

	//异步请求错误回调方法, 未处理！！！
	function zTreeOnAsyncError(event, treeId, treeNode, XMLHttpRequest, textStatus, errorThrown) {
	   alert(XMLHttpRequest);
	};

	/**
	* 对请求返回的数据进行字体设置
	* @param treeId 树节点id
	* @param treeNode 树节点对象
	*/
	function setFont(treeId, treeNode) {
	    if (treeNode && treeNode.isParent) {
	        //父节点设置字体为blue
	        return {color: "blue"};
	    } else {
	        return null;
	    }
	}

	/*
	*节点点击前触发方法，用于捕获单击节点之前的事件回调函数，并且根据返回值确定是否允许单击操作，默认值：null
	*若返回folse, 则中断后续操作， 不会像服务器发送请求
	*/
	function zTreeOnBeforeClick(treeId, treeNode) {
	    //该处判断点击节点是否属于父节点
	    var check = (treeNode && !treeNode.isParent);
	    return check;
	}

	//请求成功后的数据过滤函数
	function filter(treeId, parentNode, childNodes) {
	    if (!childNodes) return null;
	    for (var i=0, l=childNodes.length; i<l; i++) {
	        childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
	    }
	    return childNodes;
	}

	//节点点击时触发的回调
	function zTreeOnClick(even, treeId, treeNode) {
	    //获取点击节点的id和name, 要求返回的json数据有该属性
	    var sub_id       = treeNode.id;
	    var sub_name     = treeNode.name;
	    var parentId     = treeNode.getParentNode().id;
	    var parentName   = treeNode.getParentNode().name;
	    
	    alert(sub_id + "---" + sub_name + "---" + parentId + "---" + parentName);

	}
	

	//页面在家是初始化ztree， 需要绑定tree显示的dom元素ID
	$(document).ready(function(){
	    $.fn.zTree.init($("#dropdownMenu"), setting);
	});
	</script>

</body>
</html>
