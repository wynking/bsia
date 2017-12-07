<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%-- <%@ page import="cn.com.pansky.otp5.baseplatform.enums.ResourceTypeEnum"%>	 --%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>数据导出</title>


</head>
<body>

<div class="row" style="margin-top: 20px;">


	<div id="searchArea" style="margin-top: 20px;">
	
		<!-- 	<form class="form-horizontal" id="form-dictionary" enctype="multipart/form-data" method="post">
				<input type="hidden" name="dataType" value="dictionary">
				<div class="form-group">
					<div class="col-sm-3">
						<button type="button" class="btn btn-info center-block" style="width:100px;" id="importDictionary_btn" >导出字典信息</button>
					</div>
				</div>
			</form> -->
			
			
			
			
			<!-- <form class="form-horizontal" id="form-print" enctype="multipart/form-data" method="post">
				<div class="form-group">
					<div class="col-sm-3">
					<a href="export/printData"> 打印</a>
					</div>
				</div>
			</form> -->
			
			<!-- <form class="form-horizontal" id="form-format" enctype="multipart/form-data" method="post">
				<div class="form-group">
					<div class="col-sm-3">
					<a href="export/exportData?dataType=all&format=1"> 导出格式</a>
					</div>
				</div>
			</form> -->
			
			<form class="form-horizontal" id="form-all" enctype="multipart/form-data" method="post">
				<div class="form-group">
					<div class="col-sm-3">
					<a href="export/exportData?dataType=all"> 备份企业全部信息</a>
					</div>
				</div>
			</form>
			
			<!-- 
			<form class="form-horizontal" id="form-basicInfo" enctype="multipart/form-data" method="post">
				<input type="hidden" name="dataType" value="enterpriseBasic">
				<div class="form-group">
					<div class="col-sm-3">
					<a href="export/exportData?dataType=enterpriseBasic"> 导出企业基础信息</a>
					</div>
				</div>
			</form>
			
			<form class="form-horizontal" id="form-aptitude" enctype="multipart/form-data" method="post">
				<input type="hidden" name="dataType" value="aptitude">
				<div class="form-group">
					<div class="col-sm-3">
						<a href="export/exportData?dataType=aptitude"> 导出企业资质信息</a>
						<button type="button" class="btn btn-info center-block" style="" id="importAptitude_btn" >导出企业资质信息</button>
					</div>
				</div>
			</form>
			
			
			<form class="form-horizontal" id="form-tech" enctype="multipart/form-data" method="post">
				<input type="hidden" name="dataType" value="tech">
				<div class="form-group">
					<div class="col-sm-3">
						<a href="export/exportData?dataType=tech"> 导出企业技术领域</a>
						<button type="button" class="btn btn-info center-block" id="importTech_btn" >导出企业技术领域</button>
					</div>
				</div>
			</form>
			
			<form class="form-horizontal" id="form-industry" enctype="multipart/form-data" method="post">
				<input type="hidden" name="dataType" value="industry">
				<div class="form-group">
					<div class="col-sm-3">
						<a href="export/exportData?dataType=industry"> 导出企业行业领域</a>
						<button type="button" class="btn btn-info center-block" id="importIndustry_btn" >导出企业行业领域</button>
					</div>
				</div>
			</form>
			
			
			
			<form class="form-horizontal" id="form-contact" enctype="multipart/form-data" method="post">
				<div class="form-group">
					<div class="col-sm-3">
						<a href="export/exportData?dataType=contact"> 导出企业联系人</a>
						<button type="button" class="btn btn-info center-block" id="importIndustry_btn" >导出企业行业领域</button>
					</div>
				</div>
			</form>
			
			<form class="form-horizontal" id="form-annual" enctype="multipart/form-data" method="post">
				<div class="form-group">
					<div class="col-sm-3">
						<a href="export/exportData?dataType=annual"> 导出企业年报</a>
					</div>
				</div>
			</form>
			
			<form class="form-horizontal" id="form-shareholder" enctype="multipart/form-data" method="post">
				<div class="form-group">
					<div class="col-sm-3">
						<a href="export/exportData?dataType=shareholder"> 导出股东信息</a>
					</div>
				</div>
			</form>
			
			
			<form class="form-horizontal" id="form-knowledgeRight" enctype="multipart/form-data" method="post">
				<div class="form-group">
					<div class="col-sm-3">
						<a href="export/exportData?dataType=knowledgeRight"> 导出知识产权信息</a>
					</div>
				</div>
			</form>
			
			
			
			<form class="form-horizontal" id="form-production" enctype="multipart/form-data" method="post">
				<div class="form-group">
					<div class="col-sm-3">
						<a href="export/exportData?dataType=production"> 导出企业产品信息</a>
						<button type="button" class="btn btn-info center-block" id="importIndustry_btn" >导出企业行业领域</button>
					</div>
				</div>
			</form>
			
			
			<form class="form-horizontal" id="form-productionField" enctype="multipart/form-data" method="post">
				<div class="form-group">
					<div class="col-sm-3">
						<a href="export/exportData?dataType=productionField"> 导出企业产品领域信息</a>
					</div>
				</div>
			</form>
			 -->
			
			
			
	</div>
	
	

</div>

	
    
    <script type="text/javascript">
    
    $(function(){
    	//render();
    	
    	   
					$("#print_btn").click(function() {
							$("#form-print").ajaxSubmit({
								url : "export/printData",
								type : "post",
								dataType : 'json',
								success : function(resp) {
									//alert(JSON.stringify(resp));
									if (resp.flag == 1) {
										alertPansky("导出完成");
									} else {
										alertPansky("失败");
									}
								},
								error : function(data) {
									alertPansky("失败");
								}
							});
						});

						$("#importDictionary_btn").click(function() {
							$("#form-dictionary").ajaxSubmit({
								url : "import/importData",
								type : "post",
								dataType : 'json',
								success : function(resp) {
									if (resp.flag == 1) {
										alertPansky("导入完成");
									} else {
										alertPansky("失败");
									}
								},
								error : function(data) {
									alertPansky("失败");
								}
							});
						});

						$("#importBasicInfo_btn").click(function() {
							$("#form-aptitude").ajaxSubmit({
								url : "export/exportBasicInfo",
								type : "post",
								dataType : 'json',
								success : function(resp) {
									//alert(JSON.stringify(resp));
									if (resp.flag == 1) {
										alertPansky("导出完成");
									} else {
										alertPansky("失败");
									}
								},
								error : function(data) {
									alertPansky("失败");
								}
							});
						});

						$("#importAptitude_btn").click(function() {
							$("#form-aptitude").ajaxSubmit({
								url : "export/exportData",
								type : "post",
								dataType : 'json',
								success : function(resp) {
									//alert(JSON.stringify(resp));
									if (resp.flag == 1) {
										alertPansky("导出完成");
									} else {
										alertPansky("失败");
									}
								},
								error : function(data) {
									alertPansky("失败");
								}
							});
						});

						$("#importTech_btn").click(function() {
							$("#form-tech").ajaxSubmit({
								url : "export/exportData",
								type : "post",
								dataType : 'json',
								success : function(resp) {
									//alert(JSON.stringify(resp));
									if (resp.flag == 1) {
										alertPansky("导出完成");
									} else {
										alertPansky("失败");
									}
								},
								error : function(data) {
									alertPansky("失败");
								}
							});
						});

						$("#importIndustry_btn").click(function() {

							/* $("#form-industry").ajaxSubmit({  
							    url : "export/exportData",  
							    type : "post",  
							    dataType : 'json',  
							    success : function(resp) {  
							    	//alert(JSON.stringify(resp));
							    	if(resp.flag==1){
										alertPansky("导出完成");
									}else{
										alertPansky("失败");
									}  
							    },  
							    error : function(data) {  
									alertPansky("失败");
							    }  
							});  
							 */

						});

					});
				</script>

</body>
</html>