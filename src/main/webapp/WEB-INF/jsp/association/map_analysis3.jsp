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
				<%-- <label class="col-sm-1 control-label" for="techs">技术领域</label>
				<div class="col-sm-3">
					<select class="form-control" name="techs" id="techFields"
						multiple="multiple">
						<c:forEach var="dic" items="${DIC002}" varStatus="status">
							<option value="${dic.getCode()}">${dic.getName()}</option>
						</c:forEach>
					</select>
				</div> --%>
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
					<div class="col-sm-3">
						<button type="button" class="btn btn-info" id="search_btn" >查询</button>
					</div>
				</div>
					
			</form>
	</div>

<div class="row">
	<div id="allmap" class="col-sm-12"></div>
</div>


<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=2.0&ak=yod4H5mIePm1psfTpvSZZgWOyalCYy3j"></script>
<script type="text/javascript">

//百度地图API功能
var map = new BMap.Map("allmap");
map.centerAndZoom(new BMap.Point(116.403765, 39.914850), 5);
map.enableScrollWheelZoom();

function getBoundary(){       
	var bdary = new BMap.Boundary();

	bdary.get("北京市海淀区", function(rs){       //获取行政区域
		map.clearOverlays();        //清除地图覆盖物       
		var count = rs.boundaries.length; //行政区域的点有多少个
		if (count === 0) {
			alert('未能获取当前输入行政区域');
			return ;
		}
      	var pointArray = [];
		for (var i = 0; i < count; i++) {
			var ply = new BMap.Polygon(rs.boundaries[i], {strokeWeight: 2, strokeColor: "#ff0000"}); //建立多边形覆盖物
			map.addOverlay(ply);  //添加覆盖物
			pointArray = pointArray.concat(ply.getPath());
		}    
		map.setViewport(pointArray);    //调整视野  
		addlabel();               
	}); 

	bdary.get("北京市昌平区", function(rs){       //获取行政区域
		//map.clearOverlays();        //清除地图覆盖物       
		var count = rs.boundaries.length; //行政区域的点有多少个
		if (count === 0) {
			alert('未能获取当前输入行政区域');
			return ;
		}
      	var pointArray = [];
		for (var i = 0; i < count; i++) {
			var ply = new BMap.Polygon(rs.boundaries[i], {strokeWeight: 2, strokeColor: "blue"}); //建立多边形覆盖物
			map.addOverlay(ply);  //添加覆盖物
			pointArray = pointArray.concat(ply.getPath());
		}    
		map.setViewport(pointArray);    //调整视野  
		addlabel();               
	}); 
	
	
	
	
}

setTimeout(function(){
	getBoundary();
}, 2000);

function addlabel() {
    var pointArray = [
      new BMap.Point(121.716076,23.703799),
      new BMap.Point(112.121885,14.570616),
      new BMap.Point(123.776573,25.695422)];
    var optsArray = [{},{},{}];
    var labelArray = [];
    var contentArray = [
      "台湾是中国的！",
      "南海是中国的！",
      "钓鱼岛是中国的！"];
    for(var i = 0;i < pointArray.length; i++) {
      optsArray[i].position = pointArray[i];
      labelArray[i] = new BMap.Label(contentArray[i],optsArray[i]);
      labelArray[i].setStyle({
		color : "red",
		fontSize : "12px",
			 height : "20px",
			 lineHeight : "20px",
			 fontFamily:"微软雅黑"
		 });
      map.addOverlay(labelArray[i]);
    }	  
}


</script>



</body>
</html>