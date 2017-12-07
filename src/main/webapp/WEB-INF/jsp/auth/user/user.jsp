<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户管理</title>

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
			//初始化表格
			initTable();
			//用户新增表单绑定校验
			user_add_form_bind_valid();
			
			$("#user_add_submit").click(function(){
				if(form_isValid("user_add_form")){
					$.ajax({
						  url: "user/add",
						  type: 'POST',
						  dataType: "JSON",
						  data: $("form").serialize(),
						  success: function(resp){
							  alertPansky("提交成功");
							 // $("#user_add").modal('hide');
						  }
						});
				}
			});

			//清除弹窗原数据
			$("#user_add").on("show.bs.modal", function() {
				//alertPansky('aa');
				destroy("user_add_form",user_add_form_bind_valid);
				$("#user_add_form")[0].reset();
			});
			//清除弹窗原数据
			/* $("#user_add").on("hidden.bs.modal", function() {
				//alertPansky('aa');
				$("#user_add_form")[0].reset();
			}); */
			
			
		});
		
		//用户新增表单绑定校验
		function user_add_form_bind_valid(){
			 $('#user_add_form').bootstrapValidator({
		            message: 'This value is not valid',
		            feedbackIcons: {/*输入框不同状态，显示图片的样式*/
		                valid: 'glyphicon glyphicon-ok',
		                invalid: 'glyphicon glyphicon-remove',
		                validating: 'glyphicon glyphicon-refresh'
		            },
		            fields: {/*验证*/
		                loginName: {/*键名username和input name值对应*/
		                    message: '用户名校验不通过',
		                    validators: {
		                        notEmpty: {/*非空提示*/
		                            message: '用户名不能为空'
		                        },
		                        stringLength: {/*长度提示*/
		                            min: 6,
		                            max: 30,
		                            message: '用户名长度必须在6到30之间'
		                        }/*最后一个没有逗号*/
		                    }
		                },
		                password: {
		                    message:'密码校验不通过',
		                    validators: {
		                        notEmpty: {
		                            message: '密码不能为空'
		                        },
		                        stringLength: {
		                            min: 6,
		                            max: 30,
		                            message: '用户名长度必须在6到30之间'
		                        }
		                    }
		                },
		                confirm_password: {
		                	message:'密码校验不通过',
		                    validators: {
		                        identical: {//不能和用户名相同
		                            field: 'password',
		                            message: '两次密码不一致'
		                        },
		                        notEmpty: {
		                            message: '密码不能为空'
		                        }
		                    }
		                },
		                trueName: {
		                	message:'姓名校验不通过',
		                    validators: {
		                        notEmpty: {
		                            message: '姓名不能为空'
		                        }
		                    }
		                },
		                phone: {
		                    message: '手机号码校验不通过',
		                    validators: {
		                        notEmpty: {
		                            message: '手机号码不能为空'
		                        },
		                       /*  stringLength: {
		                            min: 11,
		                            max: 11,
		                            message: '请输入11位手机号码'
		                        }, */
		                        regexp: {
		                            regexp: /^[1][3,4,5,7,8][0-9]{9}$/,
		                            message: '请输入正确的手机号码'
		                        }
		                    }
		                }
		                
		            }
		        });
		}

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
				    field: 'SerialNumber',
				    title: '序号',
					align : 'center',
				    formatter: function (value, row, index) {
				        return index+1;
				    }
				},{
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