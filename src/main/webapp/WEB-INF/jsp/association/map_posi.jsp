<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String name = (String)request.getAttribute("name");
String id = (String)request.getAttribute("id");
%>
<!DOCTYPE html>
<html>

<link rel="stylesheet" href="../resources/css/bootstrap.min.css">
	<link rel="stylesheet" 
		href="../resources/js/jquery-confirm-master/css/jquery-confirm.css">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<style type="text/css">
body, html {
	width: 100%;
	height: 100%;
	margin: 0;
	font-family: "微软雅黑";
}

#allmap {
	 width: 100%; 
	height: 400px;
}

p {
	margin-left: 5px;
	font-size: 14px;
}
</style>

<title>根据关键字本地搜索</title>
</head>

<body>


<div id="searchArea" class="clearfix" style="margin-top: 20px; margin-bottom: 20px; ">
		
			<form class="form" role="form" style="">
			  <div class="form-group">
			    <label class="col-sm-2 control-label" style="width:80px;float: left; ">关键字</label>
			    <div class="col-sm-3 " style="width:400px;float: left;">
					<input class="form-control" id='keys' name="keys" type="text" value="<%=name %>"
												placeholder="请输入" />
			    </div>
			     <div class="col-sm-2" style="width:100px;float: left;">
					<button type="button" class="btn btn-info center-block" style="width:100px;" id="search_btn" >查&nbsp;&nbsp;询</button>
				</div>
			    
			  </div>

			</form>
			
	</div>

	

	<div id="allmap" style=""></div>
	
	
</body>
</html>


 <script type="text/javascript" src="../resources/js/jquery-2.1.1.js"></script>
	<script type="text/javascript" src="../resources/js/bootstrap.min.js"></script>
<script type="text/javascript"
		src="../resources/js/jquery-confirm-master/js/jquery-confirm.js"></script>
<script type="text/javascript" src="../resources/js/common.js"></script>
<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=2.0&ak=yod4H5mIePm1psfTpvSZZgWOyalCYy3j"></script>
<script type="text/javascript">


var name = '<%=name%>';
var id = '<%=id%>';

$(function(){

	renderMap(name);
	
});


	//alert(name);
	
	
	
	function renderMap(key){
		 // 百度地图API功能
		var map = new BMap.Map("allmap");
		map.centerAndZoom(new BMap.Point(116.404, 39.915), 11);
		map.enableScrollWheelZoom(true);
	    map.addEventListener("click", showInfo);  
		
		// 创建地址解析器实例
		var myGeo = new BMap.Geocoder();
		// 将地址解析结果显示在地图上,并调整地图视野
		//var sContent = "北京市海淀区";
		myGeo.getPoint(name, function(point){
			//alert(point);
			if (point) {
				var local = new BMap.LocalSearch(map, {
					renderOptions : {
						map : map
					}
				});
				local.search(key); 
			}else{
				alert("您选择地址没有解析到结果!");
			}
		}, "北京市"); 
	}
	
	
	//点击地图时间处理  
	function showInfo(e) {  
	  $.confirm({
		 title: '提示：',
		content: '是否定位为公司地址？',
	    buttons: {
	    	confirm: {
	        	 text: '确认',
	        	action: function () {
	        		$.ajax({
	        			url : "../enterprise/updatePosition",
	        			type : 'POST',
	        			dataType : "JSON",
	        			data : {
	        				id: id,
	        				longitude: e.point.lng,
	        				dimension: e.point.lat
	        			},
	        			success : function(resp) {
	        				//alert(JSON.stringify(resp.data));
	        				if(resp.flag==1){
	        					alertPansky("定位成功");
	        				}else{
	        					alertPansky("定位失败");
	        				}
	        			},
	        			error : function(XMLHttpRequest, textStatus, errorThrown) {
	        				alertPansky("定位失败！[系统异常]");
	        			}
	        		});
		        }
	        },
	        cancel: {
	            text: '取消',
	            action: function () {
	            }
	        }
	    }
	});
		
		
	} 

	$("#search_btn").on('click',function(){
		var keys = $("#keys").val();
		renderMap(keys);
	});
	
	
</script>