<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户管理</title>
<!-- <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet"> -->
<!-- <link href="../resources/css/bootstrap.min.css" rel="stylesheet"> -->

<!-- Latest compiled and minified CSS -->
<!-- <link rel="stylesheet"
	href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.1/bootstrap-table.min.css"> -->
<link rel="stylesheet"
	href="./resources/js/bootstrap-table-master/dist/bootstrap-table.min.css">

<!-- jQuery (Bootstrap 的 JavaScript 插件需要引入 jQuery) -->
<!--   <script src="https://code.jquery.com/jquery.js"></script> -->
<!-- <script src="../resources/js/jquery-2.1.1.js"></script> -->
<!-- 包括所有已编译的插件 -->
<!--   <script src="js/bootstrap.min.js"></script> -->
<!-- <script src="../resources/js/bootstrap.min.js"></script> -->

<!-- Latest compiled and minified JavaScript -->
<!-- <script
		src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.1/bootstrap-table.min.js"></script> -->
<script
	src="./resources/js/bootstrap-table-master/dist/bootstrap-table.min.js"></script>

<!-- Latest compiled and minified Locales -->
<script
	src="./resources/js/bootstrap-table-master/dist/locale/bootstrap-table-zh-CN.min.js"></script>


</head>
<body>


	<div id="searchArea">
		<form class="form-horizontal">
			<div class="form-group">
				<label class="col-sm-1 control-label" for="trueName">姓名</label>
				<div class="col-sm-3">
					<input class="form-control" id="trueName" type="text"
						placeholder="请输入" />
				</div>
				<div class="col-sm-3">


					<button type="button" class="btn btn-primary">查询</button>
					<button type="button" class="btn btn-info" data-toggle="modal"
						data-target="#user_add">新增</button>

				</div>
			</div>
		</form>
	</div>


	<div class="modal fade" id="user_add" role="dialog" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">新增用戶</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" id="user_add_form">
						<div class="form-group">
							<label class="col-sm-2 control-label" for="loginName">登录名称</label>
							<div class="col-sm-8">
								<input class="form-control" name="loginName" type="text"
									placeholder="请输入" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="password">密码</label>
							<div class="col-sm-8">
								<input class="form-control" name="password" type="password"
									placeholder="请输入" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="confirm_password">确认密码</label>
							<div class="col-sm-8">
								<input class="form-control" name="confirm_password" type="password"
									placeholder="请输入" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="trueName">姓名</label>
							<div class="col-sm-8">
								<input class="form-control" name="trueName" type="text"
									placeholder="请输入" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="phone">手机</label>
							<div class="col-sm-8">
								<input class="form-control" name="phone" type="text"
									placeholder="请输入" />
							</div>
						</div>
					</form>


				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" id="user_add_submit">提交</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>



	<table id="test-table" class="col-xs-12" data-toolbar="#toolbar"></table>



	<script type="text/javascript">
		$(document).ready(function() {
			initTable();
			
			$("#user_add_submit").click(function(){
				
				
				$.ajax({
					  url: "user/add",
					  type: 'POST',
					  dataType: "JSON",
					  data: $("form").serialize(),
					  success: function(resp){
						  alert("提交成功")
						 // $("#user_add").modal('hide');
					  }
					});
				
			});

			//清除弹窗原数据
			$("#user_add").on("show.bs.modal", function() {
				//alert('aa');
				$("#user_add_form")[0].reset();
			});
			//清除弹窗原数据
			/* $("#user_add").on("hidden.bs.modal", function() {
				//alert('aa');
				$("#user_add_form")[0].reset();
			}); */
			
			
			
			
			
			// 在键盘按下并释放及提交后验证提交表单
			var validate = $("#user_add_form").validate({
                debug: true, //调试模式取消submit的默认提交功能   
                //errorClass: "label.error", //默认为错误的样式类为：error   
                focusInvalid: false, //当为false时，验证无效时，没有焦点响应  
                onkeyup: false,   
                submitHandler: function(form){   //表单提交句柄,为一回调函数，带一个参数：form   
                    alert("提交表单");   
                    form.submit();   //提交表单   
                },   
                
                rules:{
                    loginName:{
                        required:true
                    },
                    password:{
                        required:true,
                        rangelength:[3,10]
                    },
                    confirm_password:{
                        equalTo:"#password"
                    }                    
                },
                messages:{
                	loginName:{
                        required:"必填"
                    },
                    password:{
                        required: "不能为空",
                        rangelength: $.format("密码最小长度:{0}, 最大长度:{1}。")
                    },
                    confirm_password:{
                        equalTo:"两次密码输入不一致"
                    }                                    
                }
                          
            });    
			
		});

		function initTable() {
			$('#test-table').bootstrapTable({
				method : 'get',
				//toolbar : '#toolbar', //工具按钮用哪个容器
				striped : true, //是否显示行间隔色
				//cache : false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
				pagination : true, //是否显示分页（*）
				sortable : false, //是否启用排序
				sortOrder : "asc", //排序方式
				pageNumber : 1, //初始化加载第一页，默认第一页
				pageSize : CONSTANT.pageSize, //每页的记录行数（*）
				pageList : CONSTANT.pageList, //可供选择的每页的行数（*）
				url : "user/findByPage",//这个接口需要处理bootstrap table传递的固定参数
				queryParamsType : '', //默认值为 'limit' ,在默认情况下 传给服务端的参数为：offset,limit,sort
				// 设置为 ''  在这种情况下传给服务器的参数为：pageSize,pageNumber

				//queryParams: queryParams,//前端调用服务时，会默认传递上边提到的参数，如果需要添加自定义参数，可以自定义一个函数返回请求参数
				sidePagination : "server", //分页方式：client客户端分页，server服务端分页（*）
				//search: true,      //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
				//strictSearch : true,
				//showColumns: true,     //是否显示所有的列
				//showRefresh: true,     //是否显示刷新按钮
				//minimumCountColumns : 2, //最少允许的列数
				clickToSelect : true, //是否启用点击选中行
				searchOnEnterKey : true,
				pagination : true,
				columns : [ {
					field : 'id',
					title : 'id',
					align : 'center'
				}, {
					field : 'loginName',
					title : '登录名称',
					align : 'center'
				}, {
					field : 'password',
					title : '密码',
					align : 'center'
				}, {
					field : '',
					title : '操作',
					align : 'center',
					formatter : function(value, row, index) {

						//通过formatter可以自定义列显示的内容
						//value：当前field的值，即id
						//row：当前行的数据
						var a = '<a href="" >测试</a>';
						return a;
					}
				} ]
			});
		}
	</script>
</body>
</html>