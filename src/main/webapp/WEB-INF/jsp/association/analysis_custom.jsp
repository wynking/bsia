<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%-- <%@ page import="cn.com.pansky.otp5.baseplatform.enums.ResourceTypeEnum"%>	 --%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>北京高新企业分布统计</title>


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
				<div class="col-sm-1 col-sm-offset-1">
					<button type="button" class="btn btn-primary center-block" style="width:100px;" id="search_btn" >查&nbsp;&nbsp;询</button>
				</div>
			</div>
					
		</form>
	</div>



<div class="row" style="margin-top: 20px;">
	
	<div class="panel-group col-sm-6">
		<div class="panel panel-info">
		    <div class="panel-heading">
		        <h3 class="panel-title">北京高新企业分布统计</h3>
		    </div>
		    <div class="panel-body">
		        <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
		    <div id="chart-high-cus" style="height:400px;"></div>
		    </div>
		</div>
		
	</div>
	
	<div class="panel-group col-sm-6">
		
		<div class="panel panel-info">
		    <div class="panel-heading">
		        <h3 class="panel-title">北京高新企业分布统计</h3>
		    </div>
		    <div class="panel-body">
		        <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
		    <div id="chartBar-high-cus" style="height:400px;"></div>
		    </div>
		</div>
	</div>
	
</div>

	
    
    <script type="text/javascript">

    $(function(){
    	//init();
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
    		$.ajax({
    					url : "analysis/analysisCustom",
    					type : 'POST',
    					dataType : "JSON",
    					data : $("#form-search").serialize(),
    					success : function(resp) {
    						//alert(JSON.stringify(resp.data));
    						if(resp.flag==1){
    							/* var myKeys = new Array();
    							var a = resp.data;
    							for(var i=0;i<a.length;i++){
    								myKeys[i]=a[i].name;
    							} */
    							//search(myKeys);
    							renderCylinder(resp);
    			  		    	renderBar(resp);
    						}else{
    							alertPansky("失败");
    						}
    					},
    					error : function(XMLHttpRequest, textStatus, errorThrown) {
    						alertPansky("失败！[系统异常]");
    					}
    				});
    	});
    	
    });



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
    

    
    
	function renderCylinder(resp){
		var chartHigh = echarts.init(document.getElementById('chart-high-cus'));
		var option = {
					  title: {
			                //text: '北京高新企业分布统计'
			            },
					    color: ['red'],
					    tooltip : {
					        trigger: 'axis',
					        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
					            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
					        }
					    },
					    grid: {
					        left: '3%',
					        right: '4%',
					        bottom: '3%',
					        containLabel: true
					    },
					    xAxis : [
					        {
					            type : 'category',
					            data : resp.data.data1,
					            axisTick: {
					                alignWithLabel: true
					            }
					        }
					    ],
					    yAxis : [
					        {
					            type : 'value'
					        }
					    ],
					    series : [
					        {
					            name:'高新企业',
					            type:'bar',
					            barWidth: '60%',
					            data:resp.data.data2
					        }
					    ]
					};


		        // 使用刚指定的配置项和数据显示图表。
		       chartHigh.setOption(option);
		
		
    }
    
	function BarData(name,value) {
	    this.name = name;
	    this.value = value;
	}
	

	function renderBar(resp){
		var chartBarHigh = echarts.init(document.getElementById('chartBar-high-cus'));
		var len = resp.data.data1.length;
		var data1 = resp.data.data1;
		var data2 = resp.data.data2;
		var series_data = new Array();
		
		for(var i=0;i<len;i++){
			var p = new BarData(data1[i],data2[i]);
			series_data[i]=p;
		}
		
		var option = {
			    title : {
			        //text: '某站点用户访问来源',
			        //subtext: '纯属虚构',
			       // x:'center'
			    },
			    tooltip : {
			        trigger: 'item',
			        formatter: "{a} <br/>{b} : {c} ({d}%)"
			    },
			    legend: {
			        orient: 'vertical',
			        left: 'left',
			        data: resp.data.data1,
			    },
			    series : [
			        {
			            name: '高新企业',
			            type: 'pie',
			            radius : '55%',
			            center: ['50%', '60%'],
			            data:series_data,
			            itemStyle: {
			                emphasis: {
			                    shadowBlur: 10,
			                    shadowOffsetX: 0,
			                    shadowColor: 'rgba(0, 0, 0, 0.5)'
			                }
			            }
			        }
			    ]
			};

		

		        // 使用刚指定的配置项和数据显示图表。
		       chartBarHigh.setOption(option);
		
		
    }
    
    </script>

</body>
</html>