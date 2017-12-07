<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户信息</title>

</head>
<body>


	<form class="form-horizontal" id="user_update_form"
		style="margin-top: 20px;">
		
		<div class="form-group col-sm-6">
			<label class="col-sm-4 control-label" for="loginName">登录名称</label>
			<div class="col-sm-8">
				<input class="form-control" name="loginName" type="text" 
				placeholder="请输入" />
			</div>
		</div>
		<div class="form-group col-sm-6">
			<label class="col-sm-4 control-label" for="trueName">姓名</label>
			<div class="col-sm-8">
				<input class="form-control" name="trueName" type="text" 
									placeholder="请输入" />
			</div>
		</div>
		
		
		<div class="form-group col-sm-6">
			<label class="col-sm-4 control-label">密码</label>
			<div class="col-sm-8">
				<input class="form-control" name="password" type="text" 
				placeholder="请输入" />
			</div>
		</div>
		<div class="form-group col-sm-6">
			<label class="col-sm-4 control-label" >确认密码</label>
			<div class="col-sm-8">
				<input class="form-control" name="confirmPassword" type="text" 
				placeholder="请输入" />
			</div>
		</div>
		
		
		<div class="form-group col-sm-6">
			<label class="col-sm-4 control-label" >手机</label>
			<div class="col-sm-8">
				<input class="form-control" name="phone" type="text" 
				placeholder="请输入" />
			</div>
		</div>
		
		
		<div class="form-group col-sm-6" >
			<label class="col-sm-4 control-label" >所属角色</label>
			<div class="col-sm-8">
					<select class="form-control" name="isSuper" >			
							<option value="0">管理员</option>					
							<option value="1">超级管理员</option>										
					</select>
			</div>
		</div>


		<div class="form-group ">
			<div class="col-sm-12">
				<input  type="hidden" name="id" value="${user.id}">
				<!-- <input id="isDetail" type="hidden" name="isDetail"> -->
				<input  type="hidden" name="enable">
				<button type="button" class="btn btn-primary center-block"
					id="user_update_form_btn">保&nbsp;&nbsp;存</button>
				<!-- <button type="button" class="btn btn-primary">重置</button> -->
			</div>
		</div>


	</form>



	<script type="text/javascript">
	



	
	
		$(document).ready(function() {
			
			user_add_form_bind_valid();
			setUserForm();
		
			$("#user_update_form_btn").click(function(){
				updateUserInfo();
			});
		});
		
		
		function setUserForm(){
			var userId = $("#user_update_form").find("input[name='id']").val();
			//alertPansky(userId);
			$.ajax({
				url : "user/selectByPrimaryKey",
				type : 'POST',
				dataType : "JSON",
				data : {id:userId},
				success : function(resp) {
					if(resp.flag==1){
						$("#user_update_form").setForm(resp.data);
						$("#user_update_form").setFormReadOnly();
						$("#user_update_form").find("input[name='password']").removeAttr("readOnly");
						$("#user_update_form").find("input[name='trueName']").removeAttr("readOnly");
						$("#user_update_form").find("input[name='phone']").removeAttr("readOnly");
						$("#user_update_form").find("input[name='confirmPassword']").removeAttr("readOnly");
						
						
					}else{
						alertPansky("用户信息获取失败！");
					}
					//alertPansky(JSON.stringify(resp.rows));
					//$('#resource-table').bootstrapTable('load', resp);
					// initTable();
					// alertPansky("提交成功");
					// $("#user_add").modal('hide');
				},
				error:function(){
					alertPansky("用户信息获取失败！[系统异常]");
				}
			});
		}
		
		function updateUserInfo(){
			if(form_isValid("user_update_form")){
				//$.blockUI();
				$.ajax({
				  url: "user/updateByPrimaryKey",
				  type: 'POST',
				  dataType: "JSON",
				  data: $("#user_update_form").serialize(),
				  success: function(resp){
					 // $.unblockUI();
					  if(resp.flag==1){
						  $("#userName",window.parent.document).html($("#user_update_form").find("input[name='trueName']").val());
						  	alertPansky("用户信息保存成功！");
						}else{
							alertPansky("用户信息保存失败！");
						}
					  
				  },
				  error:function(){
					 // $.unblockUI();
						alertPansky("用户信息保存失败！[系统异常]");
					}
				});
			}
			
		}
		
		//用户修改表单绑定校验
		function user_add_form_bind_valid(){
			 $('#user_update_form').bootstrapValidator({
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
		                trueName: {
		                	message:'姓名校验不通过',
		                    validators: {
		                        notEmpty: {
		                            message: '姓名不能为空'
		                        }
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
		                confirmPassword: {
		                	message:'密码校验不通过',
		                    validators: {
		                        identical: {//
		                            field: 'password',
		                            message: '两次密码不一致'
		                        },
		                        notEmpty: {
		                            message: '密码不能为空'
		                        }
		                    }
		                },
		                  phone: {
		                    message: '手机号码校验不通过',
		                    validators: {
		                        notEmpty: {
		                            message: '手机号码不能为空'
		                        },
		                        regexp: {
		                            regexp: /^[1][3,4,5,7,8][0-9]{9}$/,
		                            message: '请输入正确的手机号码'
		                        }
		                    }
		                } 
		            }
		        });
		}
	</script>
</body>
</html>