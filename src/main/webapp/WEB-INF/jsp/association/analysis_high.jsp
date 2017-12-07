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

<div class="row" style="margin-top: 20px;">
	
	<div class="panel-group col-sm-6">
		<div class="panel panel-info">
		    <div class="panel-heading">
		        <h3 class="panel-title">北京高新企业分布统计</h3>
		    </div>
		    <div class="panel-body">
		        <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
		    <div id="chart-high" style="height:400px;"></div>
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
		    <div id="chartBar-high" style="height:400px;"></div>
		    </div>
		</div>
	</div>
	
</div>

	
    
    <script type="text/javascript">
    
    $(function(){
    	render();
    });

    
    function render(){
        
    	 $.ajax({
  		  url: "analysis/analysisHigh",
  		  type: 'POST',
  		  dataType: "JSON",
  		  data: {},
  		  success: function(resp){

  			  if(resp.flag==1){

  		    	renderCylinder(resp);
  		    	renderBar(resp);
  		    	
  			  }else{
  				  alertPansky("统计信息失败！");
  			  }
  			 // $("#user_add").modal('hide');
  		  },
  		  error:function(){
  			  alertPansky("统计信息失败！[系统异常]");
  		  }
  		});
		
        
    }
    
    
	function renderCylinder(resp){
		var chartHigh = echarts.init(document.getElementById('chart-high'));
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
		var chartBarHigh = echarts.init(document.getElementById('chartBar-high'));
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