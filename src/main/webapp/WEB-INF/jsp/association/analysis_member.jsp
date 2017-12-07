<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%-- <%@ page import="cn.com.pansky.otp5.baseplatform.enums.ResourceTypeEnum"%>	 --%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>会员年度统计趋势统计</title>


</head>
<body>

<div class="row" style="margin-top: 20px;">
	<div class="panel-group col-sm-12">
		<div class="panel panel-info">
		    <div class="panel-heading">
		        <h3 class="panel-title">会员年度统计趋势图</h3>
		    </div>
		    <div class="panel-body">
		        <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
		    <div id="chart-member" style="height:400px;"></div>
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
  		  url: "analysis/analysisMember",
  		  type: 'POST',
  		  dataType: "JSON",
  		  data: {},
  		  success: function(resp){
			  //alert(JSON.stringify(resp));
  			  if(resp.flag==1){
  				renderPoly(resp);
  			  }else{
  				  alertPansky("统计信息失败！");
  			  }
  		  },
  		  error:function(){
  			  alertPansky("统计信息失败！[系统异常]");
  		  }
  		});
		
        
    }
    
    
	function renderPoly(resp){
		//alert(JSON.stringify(resp));
		var chartMember = echarts.init(document.getElementById('chart-member'));
		var option = {
			    title: {
			      //  text: '折线图堆叠'
			    },
			    tooltip: {
			        trigger: 'axis'
			    },
			    legend: {
			        data:['会员年度统计趋势']
			    },
			    grid: {
			        left: '3%',
			        right: '4%',
			        bottom: '3%',
			        containLabel: true
			    },
			    toolbox: {
			        feature: {
			           // saveAsImage: {}
			        }
			    },
			    xAxis: {
			        type: 'category',
			        boundaryGap: false,
			        data: resp.data.data1
			    },
			    yAxis: {
			        type: 'value'
			    },
			    series: [
			        {
			            name:'会员总量',
			            type:'line',
			            stack: '总量',
			            data:resp.data.data2
			        }
			    ]
			};
		    // 使用刚指定的配置项和数据显示图表。
		   	chartMember.setOption(option);
    }
	
	
    
    </script>

</body>
</html>