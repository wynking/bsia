<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%-- <%@ page import="cn.com.pansky.otp5.baseplatform.enums.ResourceTypeEnum"%>	 --%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>数据导入</title>


</head>
<body>

<div class="row" style="margin-top: 20px;">


	<div id="searchArea" style="margin-top: 20px;">
	
			<!-- <form class="form-horizontal" id="form-dictionary" enctype="multipart/form-data" method="post">
				<input type="hidden" name="dataType" value="dictionary">
				<div class="form-group">
					<label class="col-sm-1 control-label" >字典信息</label>
					<div class="col-sm-3">
						<input type="file" name="file" id=""  />
					</div>
					<div class="col-sm-3">
						<button type="button" class="btn btn-info center-block" style="width:100px;" id="importDictionary_btn" >导入</button>
					</div>
				</div>
			</form> -->
			
			
			
			<form class="form-horizontal" id="form-format" enctype="multipart/form-data" method="post">
				<input type="hidden" name="dataType" value="all">
				<input type="hidden" name="format" value="1">
				<div class="form-group">
					<label class="col-sm-1 control-label" >初始化企业信息</label>
					<div class="col-sm-3">
						<input type="file" name="file" id=""  />
					</div>
					<div class="col-sm-3">
						<button type="button" class="btn btn-info center-block" style="width:100px;" id="importFormat_btn" >导入</button>
					</div>
				</div>
			</form>
			
			
			<!-- <form class="form-horizontal" id="form-all" enctype="multipart/form-data" method="post">
				<input type="hidden" name="dataType" value="all">
				<div class="form-group">
					<label class="col-sm-1 control-label" >回复备份数据</label>
					<div class="col-sm-3">
						<input type="file" name="file" id=""  />
					</div>
					<div class="col-sm-3">
						<button type="button" class="btn btn-info center-block" style="width:100px;" id="importAll_btn" >导入</button>
					</div>
				</div>
			</form>  -->
			<!-- 
			
			<form class="form-horizontal" id="form-basicInfo" enctype="multipart/form-data" method="post">
				<input type="hidden" name="dataType" value="enterpriseBasic">
				<div class="form-group">
					<label class="col-sm-1 control-label" >企业基础信息</label>
					<div class="col-sm-3">
						<input type="file" name="file" id=""  />
					</div>
					<div class="col-sm-3">
						<button type="button" class="btn btn-info center-block" style="width:100px;" id="importBasicInfo_btn" >导入</button>
					</div>
				</div>
			</form>
			
			<form class="form-horizontal" id="form-aptitude" enctype="multipart/form-data" method="post">
				<input type="hidden" name="dataType" value="aptitude">
				<div class="form-group">
					<label class="col-sm-1 control-label" >企业资质信息</label>
					<div class="col-sm-3">
						<input type="file" name="file" id=""  />
					</div>
					<div class="col-sm-3">
						<button type="button" class="btn btn-info center-block" style="width:100px;" id="importAptitude_btn" >导入</button>
					</div>
				</div>
			</form>
			
			<form class="form-horizontal" id="form-tech" enctype="multipart/form-data" method="post">
				<input type="hidden" name="dataType" value="tech">
				<div class="form-group">
					<label class="col-sm-1 control-label" >企业技术领域信息</label>
					<div class="col-sm-3">
						<input type="file" name="file" id=""  />
					</div>
					<div class="col-sm-3">
						<button type="button" class="btn btn-info center-block" style="width:100px;" id="importTech_btn" >导入</button>
					</div>
				</div>
			</form>
			
			
			
			<form class="form-horizontal" id="form-industry" enctype="multipart/form-data" method="post">
				<input type="hidden" name="dataType" value="industry">
				<div class="form-group">
					<label class="col-sm-1 control-label" >企业行业领域信息</label>
					<div class="col-sm-3">
						<input type="file" name="file" id=""  />
					</div>
					<div class="col-sm-3">
						<button type="button" class="btn btn-info center-block" style="" id="importIndustry_btn" >导入</button>
					</div>
				</div>
			</form>
			
			
			
			
			<form class="form-horizontal" id="form-contact" enctype="multipart/form-data" method="post">
				<input type="hidden" name="dataType" value="contact">
				<div class="form-group">
					<label class="col-sm-1 control-label" >联系人信息</label>
					<div class="col-sm-3">
						<input type="file" name="file" id=""  />
					</div>
					<div class="col-sm-3">
						<button type="button" class="btn btn-info center-block" style="width:100px;" id="importContact_btn" >导入</button>
					</div>
				</div>
			</form>
			
			<form class="form-horizontal" id="form-annualReport" enctype="multipart/form-data" method="post">
				<input type="hidden" name="dataType" value="annualReport">
				<div class="form-group">
					<label class="col-sm-1 control-label" >年报信息</label>
					<div class="col-sm-3">
						<input type="file" name="file" id=""  />
					</div>
					<div class="col-sm-3">
						<button type="button" class="btn btn-info center-block" style="width:100px;" id="annualReport_btn" >导入</button>
					</div>
				</div>
			</form>
			
			<form class="form-horizontal" id="form-shareholder" enctype="multipart/form-data" method="post">
				<input type="hidden" name="dataType" value="shareholder">
				<div class="form-group">
					<label class="col-sm-1 control-label" >股东信息</label>
					<div class="col-sm-3">
						<input type="file" name="file" id=""  />
					</div>
					<div class="col-sm-3">
						<button type="button" class="btn btn-info center-block" style="width:100px;" id="shareholder_btn" >导入</button>
					</div>
				</div>
			</form>
			
			
			
			<form class="form-horizontal" id="form-knowledgeRight" enctype="multipart/form-data" method="post">
				<input type="hidden" name="dataType" value="knowledgeRight">
				<div class="form-group">
					<label class="col-sm-1 control-label" >知识产权信息</label>
					<div class="col-sm-3">
						<input type="file" name="file" id=""  />
					</div>
					<div class="col-sm-3">
						<button type="button" class="btn btn-info center-block" style="width:100px;" id="knowledgeRight_btn" >导入</button>
					</div>
				</div>
			</form>
			
			
			
			<form class="form-horizontal" id="form-production" enctype="multipart/form-data" method="post">
				<input type="hidden" name="dataType" value="production">
				<div class="form-group">
					<label class="col-sm-1 control-label" >产品信息</label>
					<div class="col-sm-3">
						<input type="file" name="file" id=""  />
					</div>
					<div class="col-sm-3">
						<button type="button" class="btn btn-info center-block" style="width:100px;" id="production_btn" >导入</button>
					</div>
				</div>
			</form> -->
			
			<!-- <form class="form-horizontal" id="form-productionField" enctype="multipart/form-data" method="post">
				<input type="hidden" name="dataType" value="productionField">
				<div class="form-group">
					<label class="col-sm-1 control-label" for="file">产品领域信息</label>
					<div class="col-sm-3">
						<input type="file" name="file" id=""  />
					</div>
					<div class="col-sm-3">
						<button type="button" class="btn btn-info center-block" style="width:100px;" id="productionField_btn" >导入</button>
					</div>
				</div>
			</form> -->
			
	</div>
	
	

</div>

	
    
    <script type="text/javascript">
    
    $(function(){
    	//render();
    	
        $("#importDictionary_btn").click(function() {  
            $("#form-dictionary").ajaxSubmit({  
                url : "import/importData",  
                type : "post",  
                dataType : 'json',  
                success : function(resp) {  
                	if(resp.flag==1){
						alertPansky("导入完成");
					}else{
						alertPansky("失败");
					}  
                },  
                error : function(data) {  
					alertPansky("失败");
                }  
            });  
        });  


        $("#importFormat_btn").click(function() {  
            $("#form-format").ajaxSubmit({  
                url : "import/importAll",  
                type : "post",  
                dataType : 'json',  
                success : function(resp) {  
                	//alert(JSON.stringify(resp));
                	if(resp.flag==1){
						alertPansky("导入完成");
					}else{
						alertPansky("失败");
					}  
                },  
                error : function(data) {  
					alertPansky("失败");
                }  
            });  
        }); 
    	
        
        
        $("#importAll_btn").click(function() {  
            $("#form-all").ajaxSubmit({  
                url : "import/importAll",  
                type : "post",  
                dataType : 'json',  
                success : function(resp) {  
                	//alert(JSON.stringify(resp));
                	if(resp.flag==1){
						alertPansky("导入完成");
					}else{
						alertPansky("失败");
					}  
                },  
                error : function(data) {  
					alertPansky("失败");
                }  
            });  
        }); 
    	

        
        $("#importBasicInfo_btn").click(function() {  
            $("#form-basicInfo").ajaxSubmit({  
                url : "import/importData",  
                type : "post",  
                dataType : 'json',  
                success : function(resp) {  
                	//alert(JSON.stringify(resp));
                	if(resp.flag==1){
						alertPansky("导入完成");
					}else{
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
                url : "import/importData",  
                type : "post",  
                dataType : 'json',  
                success : function(resp) {  
                	//alert(JSON.stringify(resp));
                	if(resp.flag==1){
						alertPansky("导入完成");
					}else{
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
                url : "import/importData",  
                type : "post",  
                dataType : 'json',  
                success : function(resp) {  
                	//alert(JSON.stringify(resp));
                	if(resp.flag==1){
						alertPansky("导入完成");
					}else{
						alertPansky("失败");
					}  
                },  
                error : function(data) {  
					alertPansky("失败");
                }  
            });  
        }); 
        


        
        $("#importIndustry_btn").click(function() {  
            $("#form-industry").ajaxSubmit({  
                url : "import/importData",  
                type : "post",  
                dataType : 'json',  
                success : function(resp) {  
                	//alert(JSON.stringify(resp));
                	if(resp.flag==1){
						alertPansky("导入完成");
					}else{
						alertPansky("失败");
					}  
                },  
                error : function(data) {  
					alertPansky("失败");
                }  
            });  
        }); 
        
        


        
        $("#importContact_btn").click(function() {  
            $("#form-contact").ajaxSubmit({  
                url : "import/importData",  
                type : "post",  
                dataType : 'json',  
                success : function(resp) {  
                	//alert(JSON.stringify(resp));
                	if(resp.flag==1){
						alertPansky("导入完成");
					}else{
						alertPansky("失败");
					}  
                },  
                error : function(data) {  
					alertPansky("失败");
                }  
            });  
        }); 


        
        $("#annualReport_btn").click(function() {  
            $("#form-annualReport").ajaxSubmit({  
                url : "import/importData",  
                type : "post",  
                dataType : 'json',  
                success : function(resp) {  
                	//alert(JSON.stringify(resp));
                	if(resp.flag==1){
						alertPansky("导入完成");
					}else{
						alertPansky("失败");
					}  
                },  
                error : function(data) {  
					alertPansky("失败");
                }  
            });  
        }); 


        
        $("#shareholder_btn").click(function() {  
            $("#form-shareholder").ajaxSubmit({  
                url : "import/importData",  
                type : "post",  
                dataType : 'json',  
                success : function(resp) {  
                	//alert(JSON.stringify(resp));
                	if(resp.flag==1){
						alertPansky("导入完成");
					}else{
						alertPansky("失败");
					}  
                },  
                error : function(data) {  
					alertPansky("失败");
                }  
            });  
        }); 



        
        $("#knowledgeRight_btn").click(function() {  
            $("#form-knowledgeRight").ajaxSubmit({  
                url : "import/importData",  
                type : "post",  
                dataType : 'json',  
                success : function(resp) {  
                	//alert(JSON.stringify(resp));
                	if(resp.flag==1){
						alertPansky("导入完成");
					}else{
						alertPansky("失败");
					}  
                },  
                error : function(data) {  
					alertPansky("失败");
                }  
            });  
        }); 



        
        $("#production_btn").click(function() {  
            $("#form-production").ajaxSubmit({  
                url : "import/importData",  
                type : "post",  
                dataType : 'json',  
                success : function(resp) {  
                	//alert(JSON.stringify(resp));
                	if(resp.flag==1){
						alertPansky("导入完成");
					}else{
						alertPansky("失败");
					}  
                },  
                error : function(data) {  
					alertPansky("失败");
                }  
            });  
        }); 


        
        $("#productionField_btn").click(function() {  
            $("#form-productionField").ajaxSubmit({  
                url : "import/importData",  
                type : "post",  
                dataType : 'json',  
                success : function(resp) {  
                	//alert(JSON.stringify(resp));
                	if(resp.flag==1){
						alertPansky("导入完成");
					}else{
						alertPansky("失败");
					}  
                },  
                error : function(data) {  
					alertPansky("失败");
                }  
            });  
        }); 
    });

    
    
    
    </script>

</body>
</html>