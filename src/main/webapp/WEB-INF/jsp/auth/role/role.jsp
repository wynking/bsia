<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">  
<!-- HTTP 1.0 -->  
<meta http-equiv="cache-control" content="no-cache">  
<!-- Prevent caching at the proxy server -->  
<meta http-equiv="expires" content="0">  
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />  
<title>角色管理</title>

</head>
<body>

	<div id="searchArea">
		<form class="form-horizontal" id="form-search">
			<div class="form-group">
			<label class="col-sm-1 control-label" for="name">名称</label>
				<div class="col-sm-3">
					<input class="form-control" name="name" type="text"
						placeholder="请输入" />
				</div>
				<div class="col-sm-3">

					<button type="button" class="btn btn-primary" id="btn-search">查询</button>
					<button type="button" class="btn btn-info" id="btn_show_role_add_modal"
						>新增</button>

				</div>
			</div>
		</form>
	</div>


	<div class="modal fade" id="role_add_modal" role="dialog" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">新增角色</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" id="role_add_form">
					<input type="hidden" name="id" >
					<input type="hidden" name="resourceIds" >
						<div class="form-group">
							<label class="col-sm-2 control-label" for="name">角色名称</label>
							<div class="col-sm-8">
								<input class="form-control" name="name" type="text"
									placeholder="请输入" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="resources">选择资源</label>
							<div class="col-sm-8">
								<div id="resource_tree" class="ztree"></div>
								
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="remark">备注</label>
							<div class="col-sm-8">
								<!-- <input class="form-control" name="remark" type="text"
									placeholder="请输入" /> -->
									<textarea  rows="5" cols="53" name="remark"></textarea>
							</div>
						</div>
						
					</form>


				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" id="role_add_submit">提交</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>



	<table id="role-table" class="col-xs-12" data-toolbar="#toolbar"></table>



	<script type="text/javascript">

		var zTreeObj;
		var checkedNodeIDs = "";
		
		$(document).ready(function() {
			//初始化表格
			initTable();
			//绑定查询事件
			$("#btn-search").click(function() {
				refreshTable();
			});

			$("#btn_show_role_add_modal").click(function() {
				initTree();
				 $("#role_add_modal").modal('show');
			});
			
			
			//用户新增表单绑定校验
			role_add_form_bind_valid();
			
			$("#role_add_submit").click(function(){
				if(form_isValid("role_add_form")){
					//alertPansky(JSON.stringify($("#contact_add_form").serialize()));
					var id = $("#role_add_form").find("input[name='id']").val();
					//alertPansky(id);
					//return ;
					if(id!=""){
						update();
					}else{
						add();
					}
				}
			});
			
			

			//清除弹窗原数据
			$("#role_add_modal").on("hide.bs.modal", function() {
				$("#role_add_form")[0].reset();
				$("#role_add_form").find("input[name='id']").val("");
				resetValid();
			});
			

			//初始化资源树
			//$("#role_add_modal").on("show.bs.modal", function() {
				//initTree();
			//});
			
			//清除弹窗原数据
			/* $("#role_add").on("hidden.bs.modal", function() {
				//alert('aa');
				$("#role_add_form")[0].reset();
			}); */
		});
		
		
		

		function add(){
			$.ajax({
				  url: "role/add",
				  type: 'POST',
				  dataType: "JSON",
				  data: $("#role_add_form").serialize(),
				  success: function(resp){
					  if(resp.flag==1){
						  $("#role_add_modal").modal('hide');
						  alertPansky("保存成功！");
						  refreshTable();
					  }else{
						  alertPansky("保存失败！");
					  }
				  },
				  error:function(){
					  alertPansky("保存失败！[系统异常]");
				  }
				});
		}
		

		//显示修改联系人model
		function showUpdate(id){
			$.ajax({
				  url: "role/selectByPrimaryKey",
				  type: 'POST',
				  dataType: "JSON",
				  data: {
					  id : id
				  },
				  success: function(resp){
					 //alertPansky(JSON.stringify(resp.data.rrs));
					  if(resp.flag==1){
						  initTree(id);
						  $("#role_add_form").setForm(resp.data);
						  $("#role_add_modal").modal('show');
					  }else{
						  alertPansky("获取用户信息失败！");
					  }
					  
				  },
				  error: function(XMLHttpRequest, textStatus, errorThrown) {
					  alertPansky("获取用户信息失败！[系统异常]");
				  }
			}); 
			
			
			
		}
		
		function update(){
			//alert(JSON.stringify($("#role_add_form").serialize()));
			$.ajax({         
				  url: "role/updateByPrimaryKey",
				  type: 'POST',
				  dataType: "JSON",
				  data: $("#role_add_form").serialize(),
				  success: function(resp){
					  if(resp.flag==1){
						  $("#role_add_modal").modal('hide');
						  alertPansky("修改成功");
						 // $("#role_add").modal('hide');
						  refreshTable();
					  }else{
						  alertPansky("修改失败！");
					  }
				  },
				  error:function(){
					  alertPansky("修改失败！[系统异常]");
				  }
				});
		}
		


		function delFun(id){
			$.ajax({
				  url: "role/updateEnabledByPrimaryKey",
				  type: 'POST',
				  dataType: "JSON",
				  data: {id:id,enabled:ENABLED.sc},
				  success: function(resp){
					  if(resp.flag==1){
						  alertPansky("删除成功");
						  refreshTable();
					  }else{
						  alertPansky("删除失败！");
					  }
					 
				  },
				  error:function(){
					  alertPansky("删除失败！[系统异常]");
				  }
				});
		}

		function deleteRole(id){
			deletePansky(delFun,id);
		}
		
		
		
		//用户新增表单绑定校验
		function role_add_form_bind_valid(){
			 $('#role_add_form').bootstrapValidator({
		            message: 'This value is not valid',
		            feedbackIcons: {/*输入框不同状态，显示图片的样式*/
		                valid: 'glyphicon glyphicon-ok',
		                invalid: 'glyphicon glyphicon-remove',
		                validating: 'glyphicon glyphicon-refresh'
		            },
		            fields: {/*验证*/
		                loginName: {/*键名username和input name值对应*/
		                    message: '名称校验不通过',
		                    validators: {
		                        notEmpty: {/*非空提示*/
		                            message: '名称不能为空'
		                        },
		                        stringLength: {/*长度提示*/
		                            min: 6,
		                            max: 30,
		                            message: '名称长度必须在2到30之间'
		                        }/*最后一个没有逗号*/
		                    }
		                }
		                
		            }
		        });
		}

		function initTable() {

			$('#role-table').bootstrapTable({
				method : 'post',
				contentType : "application/x-www-form-urlencoded",
				//toolbar : '#toolbar', //工具按钮用哪个容器
				striped : true, //是否显示行间隔色
				//cache : false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
				pagination : true, //是否显示分页（*）
				sortable : false, //是否启用排序
				sortOrder : "asc", //排序方式
				pageNumber : 1, //初始化加载第一页，默认第一页
				pageSize : CONSTANT.pageSize, //每页的记录行数（*）
				pageList : CONSTANT.pageList, //可供选择的每页的行数（*）
				url : "role/findByPage",//这个接口需要处理bootstrap table传递的固定参数
				queryParamsType : '', //默认值为 'limit' ,在默认情况下 传给服务端的参数为：offset,limit,sort
				// 设置为 ''  在这种情况下传给服务器的参数为：pageSize,pageNumber

				queryParams: queryParams,//前端调用服务时，会默认传递上边提到的参数，如果需要添加自定义参数，可以自定义一个函数返回请求参数
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
					align : 'center',
					visible: false
				}, {
					field : 'name',
					title : '名称',
					align : 'center'
				}, {
					field : 'remark',
					title : '备注',
					align : 'center'
				}, {
					field : '',
					title : '操作',
					align : 'center',
					formatter : function(value, row, index) {

						//通过formatter可以自定义列显示的内容
						//value：当前field的值，即id
						//row：当前行的数据
						var edit = '<button title="修改" class="update-enterprise" data-id="'+row.id+'" onClick="showUpdate(\''+row.id+'\')"><span  class="glyphicon glyphicon-pencil"></button>';
						var del  = '<button title="删除" class="update-enterprise" data-id="'+row.id+'" onClick="deleteRole(\''+row.id+'\')"><span  class="glyphicon glyphicon-trash"></button>';
						return edit + del;
					}
				} ]
			});
		}
		
		//设置传入参数
		function queryParams(params) {
			var obj1 = {
		    		pageSize: params.pageSize,
		            pageNumber: params.pageNumber
	            }; 
			var obj2= $("#form-search").serializeObject();
			//obj2合并到obj1
			$.extend( obj1, obj2 );
		    return obj1;       
		}
		
		
		//刷新表格
		function refreshTable(parms){
			$("#role-table").bootstrapTable('destroy');
			initTable();
			//alertPansky(parms);
			
			/* var obj =  {url: 'role/findByPage',pageNumber:1};
			if(parms!=undefined){
				obj = parms;
			}
			$('#role-table').bootstrapTable('refresh',obj); */
			//alert('表格刷新成功');
		}

		function resetValid(){
			$("#role_add_form").data('bootstrapValidator').destroy();
		    $('#role_add_form').data('bootstrapValidator', null);
		    role_add_form_bind_valid();
		}
		
		

		// zTree 的参数配置，深入使用请参考 API 文档（setting 配置详解）  
		var setting = {

			check : {
				enable : true,
			//nocheckInherit: false  
			chkStyle: 'checkbox',  
			chkboxType: { "Y": "p", "N": "s" }  
			},
			view : {
				selectedMulti : false,
				showIcon : true, //设置是否显示节点图标  
				showLine : true //设置是否显示节点与节点之间的连线  
			// ,fontCss: setFontCss  
			},
			async : {
				enable : false, // 设置 zTree是否开启异步加载模式  加载全部信息  
				url : "", // Ajax 获取数据的 URL 地址    
				autoParam : [ "id" ], // 异步加载时自动提交父节点属性的参数,假设父节点 node = {id:1, name:"test"}，异步加载时，提交参数 zId=1     
			//dataFilter: filter  
			},
			callback : {
				//onDblClick : onExpandFun,
				//onExpand : onExpandFun,
				onCheck : onCheck
				//onCollapse : zTreeOnCollapse
				//beforeExpand: beforeExpandFun
			//点击事件  
			},
			data : { // 必须使用data    
				simpleData : {
					enable : true,
					idKey : "id", // id编号命名 默认    
					pIdKey : "pId", // 父id编号命名 默认    
					//root : 0
					rootPId : 'ROOT'
				// 用于修正根节点父节点数据，即 Key 指定的属性值    
				},
				keep: {
					parent: true
				}
			}
		};


		function zTreeOnCollapse(event, treeId, treeNode){
			var treeObj = $.fn.zTree.getZTreeObj(treeId);
			treeObj.removeChildNodes(treeNode);
		}
		
		//选中或取消选中节点事件
		function onCheck(e,treeId,treeNode){
            var treeObj=$.fn.zTree.getZTreeObj("resource_tree")
            nodes=treeObj.getCheckedNodes(true);
			setCheckNodeIds(nodes);
      	}
		
		function initTree(roleId) {
			//显示区域树  
			$.ajax({
				type : "POST",
				url : "resource/showAllResourceTree",
				data : {
					 id : 'ROOT' 
				},
				dataType : "json",
				success : function(data) {
					//alert(setting);
					zTreeObj = $.fn.zTree.init($("#resource_tree"), setting,
							data);
					if(roleId != undefined){
						$.ajax({
							type : "POST",
							url : "role/selectCheckedNodes",
							data : {
								 roleId : roleId 
							},
							dataType : "json",
							success : function(resp) {
								var rrs = resp.data;
								var nodes = new Array();
								for(var i=0;i<rrs.length;i++){
									  var node = zTreeObj.getNodeByParam('id',rrs[i].resourceId);
									  zTreeObj.selectNode(node);
									  zTreeObj.checkNode(node, true, true);
									  nodes[i]=node;
								} 
								setCheckNodeIds(nodes);
							}
						});
					}
					
				}
			});
		}
		
		
		function setCheckNodeIds(nodes){
			var checkedNodeIDs = "";
	           // var names = '';
	            for(var i=0;i<nodes.length;i++){
	            	checkedNodeIDs+=nodes[i].id + ",";
	            	//names+=nodes[i].name + ",";
		           // alert(nodes[i].id); //获取选中节点的值
	            }
	            //alert(names);
	            $("#role_add_form").find("input[name='resourceIds']").val(checkedNodeIDs);
			
		}
		
		

	</script>
</body>
</html>