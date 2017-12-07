<%@page import="cn.com.pansky.otp5.baseplatform.dao.po.Dictionary"%>
<%@page import="cn.com.pansky.otp5.common.DictionaryUtil"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- <%@ page import="cn.com.pansky.otp5.baseplatform.enums.ResourceTypeEnum"%>	 --%>
<%
	String eId = (String)(request.getAttribute("eId")!=null?request.getAttribute("eId"):"");
	String isDetail = (String)(request.getAttribute("isDetail")!=null?request.getAttribute("isDetail"):"");
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<style type="text/css">


#allmap {
	width: 100%; 
	height: 500px;
	border: 5px solid #F0F2F5;
}


</style>
</head>
<body>

<div id="searchArea" style="margin-top: 20px;">
			<form class="form-horizontal" id="form-search">
				<div class="form-group">
				<input type="hidden" name="pageNumber" value="">
				<input type="hidden" name="pageSize" value="">
				<label class="col-sm-1 control-label" for="name">名称</label>
				<div class="col-sm-3">
					<input class="form-control" name="name" type="text"
						placeholder="请输入" />
				</div>
				<%-- <label class="col-sm-1 control-label" for="">行业领域</label>
				<div class="col-sm-3">
					<select class="form-control" name="" id="industry" 
						multiple="multiple">
						<option value="">-请选择-</option>
						<c:forEach var="dic" items="${DIC005}" varStatus="status">
							<option value="${dic.getCode()}">${dic.getName()}</option>
						</c:forEach>
					</select>
				</div> --%>
				<label class="col-sm-1 control-label" for="isPublic">是否上市</label>
				<div class="col-sm-3">
					<select class="form-control" name="isPublic"  >
						<option value="">-请选择-</option>
						<option value="0">未上市</option>
						<option value="1">已上市</option>
					</select>
				</div>
				
				<label for="dtp_input2" class="col-sm-1 control-label">入会时间</label>
				<div class="col-sm-3">
					<div  class="input-group date form_date" data-date=""  data-date-format="" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
						<input id="basic_add_form_form_date_firstTime" class="form-control" size="10" type="text" value="" readonly>
						<span class="input-group-addon"><span
							class="glyphicon glyphicon-remove"></span></span> <span
							class="input-group-addon"><span
							class="glyphicon glyphicon-calendar"></span></span>
					</div>
					<input type="hidden" id="dtp_input2" name="firstTime" value="" />
				</div>
			</div>
			<div class="form-group">
				<%-- <label class="col-sm-1 control-label" for="infoSources">信息来源</label>
				<div class="col-sm-3">
					<select class="form-control" name="infoSources" >
						<option value="">-请选择-</option>
						<c:forEach var="dic" items="${DIC003}" varStatus="status">
							<option value="${dic.getCode()}">${dic.getName()}</option>
						</c:forEach>
					</select>
				</div> --%>
				<label class="col-sm-1 control-label" for="">是否高新</label>
				<div class="col-sm-3">
					<select class="form-control" name="isHigh" id="isHigh" >
						<option value="">-请选择-</option>
						<option value="0">否</option>
						<option value="1">是</option>
					</select>
				</div>
				<label class="col-sm-1 control-label" for="">是否会员</label>
				<div class="col-sm-3">
					<select class="form-control" name="isMember" id="isMember" >
						<option value="">-请选择-</option>
						<option value="0">非会员</option>
						<option value="1">会员</option>
					</select>
				</div>
				<label class="col-sm-1 control-label" for="hyLevel">会员级别</label>
				<div class="col-sm-3">
					<select class="form-control" name="hyLevel">
						<option value="">-请选择-</option>
						<c:forEach var="dic" items="${DIC001}" varStatus="status">
							<option value="${ dic.getCode()}">${dic.getName()}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			
			
			<div class="form-group">
				<label class="col-sm-1 control-label" for="techs">技术领域</label>
				<div class="col-sm-3">
					<select class="form-control" name="techs" id="techFields"
						multiple="multiple">
						<c:forEach var="dic" items="${DIC002}" varStatus="status">
							<option value="${dic.getCode()}">${dic.getName()}</option>
						</c:forEach>
					</select>
				</div>
				<label class="col-sm-1 control-label" for="industrys">行业领域</label>
				<div class="col-sm-3">
					<select class="form-control" name="industrys" id="industrys" multiple="multiple">
						<c:forEach var="dic" items="${DIC005}" varStatus="status">
							<option value="${dic.getCode()}">${dic.getName()}</option>
						</c:forEach>
					</select>
				</div>
				<label class="col-sm-1 control-label" for="">通讯地址</label>
				<div class="col-sm-3">
					<div id="basic_add_form_address" class="bs-chinese-region flat dropdown" data-min-level="1" data-max-level="3" data-def-val="[name=address]">
								<input type="text" class="form-control" id="" placeholder="请选择" data-toggle="dropdown" readonly="">
								<input type="hidden" class="form-control" name="address" value="">
								<div class="dropdown-menu" role="menu" aria-labelledby="dLabel">
									<div>
										<ul class="nav nav-tabs" role="tablist">
											<li role="presentation" class="active"><a href="#province" data-next="city" role="tab" data-toggle="tab">省份</a></li>
											<li role="presentation"><a href="#city" data-next="district" role="tab" data-toggle="tab">城市</a></li>
											<li role="presentation"><a href="#district" data-next="street" role="tab" data-toggle="tab">县区</a></li>
										</ul>
										<div class="tab-content">
											<div role="tabpanel" class="tab-pane active" id="province">--</div>
											<div role="tabpanel" class="tab-pane" id="city">--</div>
											<div role="tabpanel" class="tab-pane" id="district">--</div>
										</div>
									</div>
								</div>	
								<style>
									.bs-chinese-region .clear{
										/* background-color:gray; */
										position: absolute;
										top:0px;
										right: 0px;
										cursor: pointer;
										height: 34px;
										width: 34px;
										border-left: 1px solid #CCCCCC;
									}
									.bs-chinese-region .clear span{
										/* background-color: red; */
										display: block;
										height: 13px;
										width: 15px;
										position: absolute;
										top:10px;
										left: 10px;
									}
								</style>
								<div class="clear"><span class="glyphicon glyphicon-remove "></span></div>
					</div>
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-1 control-label" for="">注册资金（元）</label>
				<div class="col-sm-3">
					<input class="form-control" name="registeredCapital_start" type="text"
						placeholder="请输入注册资金下限" /> --
					<input class="form-control" name="registeredCapital_end" type="text"
						placeholder="请输入注册资金上限" />
				</div>
				<label class="col-sm-1 control-label" >年收入总额（元）</label>
				<div class="col-sm-3">
					<input class="form-control" name="totalIncome_start" type="text"
						placeholder="请输入年收入总额下限" /> --
					<input class="form-control" name="totalIncome_end" type="text"
						placeholder="请输入年收入总额上限" />
				</div>
				<label class="col-sm-1 control-label" >研发费用总额（元）</label>
				<div class="col-sm-3">
					<input class="form-control" name="developExpenses_start" type="text"
						placeholder="请输入研发费用总额下限" /> --
					<input class="form-control" name="developExpenses_end" type="text"
						placeholder="请输入研发费用总额上限" />
				</div>
			</div>
			
			
			
			
			
			<div class="form-group">
				<label class="col-sm-1 control-label" >当年（月平均）职工总数</label>
				<div class="col-sm-3">
					<input class="form-control" name="avgNum_start" type="text"
						placeholder="请输入当年（月平均）职工总数下限" /> --
					<input class="form-control" name="avgNum_end" type="text"
						placeholder="请输入当年（月平均）职工总数上限" />
				</div>
				<div class="col-sm-3 col-sm-offset-1">
					<button type="button" class="btn btn-primary" id="search_btn" style="width: 100px;">查询</button>
				</div>
			</div>
	</div>
	
	<style>
		#map_div{
			position: relative;
		}
		#mapInfoBar{
			position: absolute;
			top: 0px;
			left: 0px;
			width: auto;
			height: auto;
			background-color: #FFFFFF;
			opacity:0.7;
			font-weight: 900;
		}
		.areaItem{
			margin-left:2px;
			padding: 2px;
		}
		
		
	</style>

<div class="row" id="map_div">
	<div id="allmap" class="col-sm-12"></div>
	<div id="mapInfoBar" >
	</div>
</div>


<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=2.0&ak=yod4H5mIePm1psfTpvSZZgWOyalCYy3j"></script>
<script type="text/javascript">

var areas = ['北京市丰台区','北京市门头沟区','北京市石景山区','北京市房山区','北京市通州区','北京市顺义区','北京市昌平区','北京市大兴区','北京市怀柔区','北京市平谷区','北京市延庆区','北京市密云区','北京市海淀区','北京市朝阳区'];
var areaColors = ['#FF0A00','#FF0A00','#FF0A00','#FF0A00','#FF0A00','#FF0A00','#FF0A00','#FF0A00','#FF0A00','#FF0A00','#FF0A00','#FF0A00','#FF0A00','#FF0A00'];

var map = new BMap.Map("allmap");
map.centerAndZoom(new BMap.Point(116.331398,39.897445),11);
map.enableScrollWheelZoom(true);


$(function(){
	initRegion();

	$("#techFields").select2({
		placeholder : "请选择",
		language : "zh-CN", //设置 提示语言
		tags : true,
		maximumSelectionLength : 3
	//最多能够选择的个数
	});
	
	$("#industrys").select2({
		placeholder : "请选择",
		language : "zh-CN", //设置 提示语言
		tags : true,
		maximumSelectionLength : 3
	//最多能够选择的个数
	});
	
	$('.form_date').datetimepicker({
		 format: "yyyy-mm-dd",
		 autoclose: true,
		 todayBtn: true,
		 pickerPosition: "bottom-left",
		 minView:2,
	     language: "zh-CN" //设置 提示语言
	});
	
	$("#search_btn").on('click',function(){
		renderMap();
		renderMapBar();
	});
	
});


function renderMap(){
	$.ajax({
		url : "analysis/analysisMap",
		type : 'POST',
		dataType : "JSON",
		data : $("#form-search").serialize(),
		success : function(resp) {
			//alert(JSON.stringify(resp.data));
			if(resp.flag==1){
				var myKeys = new Array();
				var a = resp.data;
				for(var i=0;i<a.length;i++){
					myKeys[i] = a[i];
				}
				search(myKeys);
			}else{
				alertPansky("失败");
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alertPansky("失败！[系统异常]");
		}
	});
}


function renderMapBar(){
	$.ajax({
		url : "analysis/analysisCustom",
		type : 'POST',
		dataType : "JSON",
		data : $("#form-search").serialize(),
		success : function(resp) {
			//alert(JSON.stringify(resp.data));
			if(resp.flag==1){
  		    	//renderBar(resp);
				var len = resp.data.data1.length;
				var data1 = resp.data.data1;
				var data2 = resp.data.data2;

				/* <div id="mapInfoBar" >
					<div class="areaItem">昌平区（0）</div> */
				var mapInfoBar = $("#mapInfoBar");
				mapInfoBar.html("");
				for(var i=0;i<len;i++){
					mapInfoBar.append("<div class='areaItem'>"+ data1[i] +"["+ data2[i] +"]</div>");
				}
  		    	
			}else{
				alertPansky("统计加载失败");
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alertPansky("统计图加载失败！[系统异常]");
		}
	});
}


function search(myKeys){
	
	var map = new BMap.Map("allmap");

	map.centerAndZoom(new BMap.Point(116.331398,39.897445),11);
	map.enableScrollWheelZoom(true); 
	
	
	var bdary = new BMap.Boundary();
	var areaNum = areas.length;
	for(var i=0;i<areaNum;i++){
		bdary.get(areas[i], function(rs){       //获取行政区域
			//map.clearOverlays();        //清除地图覆盖物       
			var count = rs.boundaries.length; //行政区域的点有多少个
			if (count === 0) {
				alert('未能获取 '+areas[i]+' 行政区域');
				return ;
			}
	      	var pointArray = [];
			for (var i = 0; i < count; i++) {
				var ply = new BMap.Polygon(rs.boundaries[i], {fillColor:'#FFFFFF',fillOpacity:0.5,strokeOpacity:0, strokeWeight: 2, strokeColor: areaColors[i]}); //建立多边形覆盖物
				//ply.setStyle({backgroundColor:'blue'});
				map.addOverlay(ply);  //添加覆盖物
				pointArray = pointArray.concat(ply.getPath());
			}    
			map.setViewport(pointArray);    //调整视野  
			//addlabel();               
		}); 
	}
	
	setTimeout(function () {

		bdary.get('北京市东城区', function(rs){       //获取行政区域
			//map.clearOverlays();        //清除地图覆盖物       
			var count = rs.boundaries.length; //行政区域的点有多少个
			if (count === 0) {
				alert('未能获取 北京市东城区 行政区域');
				return ;
			}
	      	var pointArray = [];
			for (var i = 0; i < count; i++) {
				var ply = new BMap.Polygon(rs.boundaries[i], {fillColor:'#FFFFFF',fillOpacity:0.5,strokeOpacity:0, strokeWeight: 2, strokeColor: areaColors[i]}); //建立多边形覆盖物
				map.addOverlay(ply);  //添加覆盖物
				pointArray = pointArray.concat(ply.getPath());
			}    
			map.setViewport(pointArray);    //调整视野  
			//addlabel();               
		});
		
		bdary.get('北京市西城区', function(rs){       //获取行政区域
			//map.clearOverlays();        //清除地图覆盖物       
			var count = rs.boundaries.length; //行政区域的点有多少个
			if (count === 0) {
				alert('未能获取 北京市西城区 行政区域');
				return ;
			}
	      	var pointArray = [];
			for (var i = 0; i < count; i++) {
				var ply = new BMap.Polygon(rs.boundaries[i], {fillColor:'#FFFFFF',fillOpacity:0.5,strokeOpacity:0, strokeWeight: 2, strokeColor: areaColors[i]}); //建立多边形覆盖物
				map.addOverlay(ply);  //添加覆盖物
				pointArray = pointArray.concat(ply.getPath());
			}    
			map.setViewport(pointArray);    //调整视野  
			//addlabel();               
		});
		
	  }, 2000);
	
	


	
	
	var len = myKeys.length;
	//alert(len);
	for(var i=0;i<len;i++){
		var item = myKeys[i];
		//alert(item.name);
		//var posi = item.split(","); 
		//alert(posi[0] + '==' + posi[1]);
		//alert(item.longitude);
		 if(item.longitude != "" && item.longitude!=null && item.longitude != "null"
			 && item.longitude!="" && item.longitude!=null && item.longitude != "null"	 ){
			 var new_point = new BMap.Point(item.longitude, item.dimension);
				var marker = new BMap.Marker(new_point);  // 创建标注
				map.addOverlay(marker);              // 将标注添加到地图中
				map.panTo(new_point); 
				
				addClickHandler(item,marker);
		} 
		
		
	}
	
	
	function addClickHandler(item,marker){
		
		marker.addEventListener("click",function(e){
			openInfo(item,e)}
		);
	}
	
	function openInfo(item,e){
		var p = e.target;
		var point = new BMap.Point(p.getPosition().lng, p.getPosition().lat);
		
		var opts = {
				width : 250,     // 信息窗口宽度
				height: 80,     // 信息窗口高度
				title : item.name , // 信息窗口标题
				enableMessage:true//设置允许信息窗发送短息
			   };

		var addressName = "";
		var addressDetail = "";
		if(item.addressName!=null && item.addressName!="" && item.addressName!="null"){
			addressName = item.addressName;
		}
		if(item.addressDetail!=null && item.addressDetail!="" && item.addressDetail!="null"){
			addressDetail = item.addressDetail;
		}

		var remark = "";
		if(item.remark!=null ){
			remark = item.remark;
		}
		var content = "地址：" + addressName + addressDetail +"<br>" + "简介："+remark;
		
		var infoWindow = new BMap.InfoWindow(content,opts);  // 创建信息窗口对象 
		map.openInfoWindow(infoWindow,point); //开启信息窗口
	}
	
	//百度地图API功能
	/* var map = new BMap.Map("allmap");            // 创建Map实例
	map.centerAndZoom(new BMap.Point(116.404, 39.915), 11);
	var local = new BMap.LocalSearch(map, {
		renderOptions:{map: map, panel:"r-result"},
		pageCapacity:5
	});
	map.setCurrentCity("北京");    
	map.enableScrollWheelZoom(true);
	//alert(myKeys.length);
	local.searchInBounds(myKeys, map.getBounds()); */

    //map.addEventListener("click", showInfo);  
    
	

	
	
}


function initRegion(){
	
	 $.getJSON('./dictionary/getRegion',function(resp){
		 var data = resp.data;
		 for (var i = 0; i < data.length; i++) {
				//var area = {id:data[i].id,name:data[i].cname,level:data[i].level,parentId:data[i].upid};
				var area = {id:data[i].id,name:data[i].name,level:data[i].level,parentId:data[i].pCode};
				data[i] = area;
			}
		 //$('#basic_add_form_address').chineseRegion('level',3,3)
			/* $('#basic_add_form_address').chineseRegion('source',data).on('completed.bs.chinese-region',function(e,areas){
				//alert(JSON.stringify(areas));
				$(this).find('[name=address]').val(areas[areas.length-1].id);
			}); */
		 
		 $('#basic_add_form_address').chineseRegion('source',data).on('changed.bs.chinese-region',function(e,areas){
			 $(this).find('[name=address]').val(areas[areas.length-1].id);
			});
		 
	    }); 
	 
	 
	 $('#basic_add_form_address .clear span').on("click",function(event){
		 //alert($('#basic_add_form_address').find('[name=address]').val());
		 $('#basic_add_form_address').find('input').val("");
		 event.preventDefault();
	 });
}


</script>



</body>
</html>