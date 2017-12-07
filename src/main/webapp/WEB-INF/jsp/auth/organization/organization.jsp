<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>组织管理</title>

<style type="text/css">

.breadcrumb > li + li:before {
    color: #CCCCCC;
    content: "/ ";
    padding: 0 5px;
}



</style>
</head>
<body>



	<div class="col-sm-2 pull-left" style="">
		<div id="organization_tree" class="ztree"></div>
	</div>

	<div class="col-sm-10 pull-left">
	
		<div>
			<ol id="curNode-breadcrumb" class="breadcrumb">
			    <li>组织机构</li>
			</ol>
		</div>
	

		<div id="searchArea">
			<form class="form-horizontal" id="form-search">
			<input  type="text" name="pId" value="0"/>
				<div class="form-group">
					<label class="col-sm-1 control-label" for="name">名称</label>
					<div class="col-sm-5">
						<input class="form-control" id="name" type="text" name="name"
							placeholder="请输入" />
					</div>
					<div class="col-sm-4">
						<button type="button" class="btn btn-primary" id="btn-search">查询</button>
						<button type="button" class="btn btn-info" data-toggle="modal"
							data-target="#organization-add-modal">新增</button>
						<!-- <button type="button" class="btn btn-info" id="btn-organization-syn">同步信息</button> -->
					</div>
				</div>
			</form>
		</div>

		<div class="modal fade" id="organization-add-modal" role="dialog"
			aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">新增组织</h4>
					</div>
					<div class="modal-body">
						<form class="form-horizontal" id="organization_add_form">
						<input type="text" name="id" >
						<input  type="text" name="pId" value="0"/>
						<!-- <input  type="hidden" name="pCode" value=""/>
						<input  type="hidden" name="type" value=""/> -->
							<div class="form-group">
								<label class="col-sm-2 control-label" for="name">名称</label>
								<div class="col-sm-8">
									<input class="form-control" name="name" type="text"
										placeholder="请输入" />
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-2 control-label" for="code">组织代码</label>
								<div class="col-sm-8">
									<input class="form-control" name="code" type="text"
										placeholder="请输入" />
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-2 control-label" for="remark">remark</label>
								<div class="col-sm-8">
									<textarea  rows="5" cols="53" name="remark"></textarea>
								</div>
							</div>
						</form>


					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						<button type="button" class="btn btn-primary" id="organization-add-btn">提交</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal -->
		</div>


		<table id="organization-table" class="col-xs-12" data-toolbar="#toolbar"></table>

	</div>







	<script type="text/javascript">
	
	var nodes = new Array();
	var nodes_index = 0;
	
	
		$(document).ready(function() {
			
			initTree();
			//初始化表格
			initTable();

			$("#btn-search").click(function() {
				refreshTable();
			});

			/* $("#btn-organization-syn").click(function() {
				//refreshTable();
				synDicToCache();
			}); */
			
			
			//清除弹窗原数据
			$("#organization-add-modal").on("hide.bs.modal", function() {
				$("#organization_add_form")[0].reset();
				//setEId(getEId());
				$("#organization_add_form").find("input[name='id']").val("");
				//resetValid();
			});
			
			

		});

		var zTreeObj;
		// zTree 的参数配置，深入使用请参考 API 文档（setting 配置详解）  
		var setting = {
			check : {
				enable : false,
			//nocheckInherit: false  
			//chkStyle: 'checkbox',  
			//chkboxType: { "Y": "ps", "N": "ps" }  
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
				onDblClick : onExpandFun,
				onExpand : onExpandFun
				//beforeExpand: beforeExpandFun
			//点击事件  
			},
			data : { // 必须使用data    
				simpleData : {
					enable : true,
					idKey : "id", // id编号命名 默认    
					Key : "", // 父id编号命名 默认    
					root : 0
				// 用于修正根节点父节点数据，即 Key 指定的属性值    
				}
			}
		};

		function onExpandFun(event, treeId, treeNode) {
			var treeObj = $.fn.zTree.getZTreeObj(treeId);
			var node = treeObj.getNodeByTId(treeNode.tId);
			
			setCurNodeId(treeNode)
			
			nodes = new Array();
			nodes_index = 0; 

			checkAllParents(treeNode);
			//设置资源导航
			setCurNodeBreadcrumb(nodes);

			//if (node.children == null || node.children == "undefined" || node.children=="") {
				//alertPansky('chaxun');
				$.ajax({
					type : "POST",
					async : false,
					url : "organization/showOrganizationTree",
					data : {
						id : treeNode.id
					},
					dataType : "json",
					success : function(data) {
						//alertPansky('aaa');
						treeObj.removeChildNodes(treeNode);
						if (data != null && data != "") {
							//添加新节点  
							newNode = treeObj.addNodes(node, data);
						}
						
						refreshTable();
						
						
					},
					error : function(event, XMLHttpRequest, ajaxOptions,
							thrownError) {
						//result = true;
						alertPansky("请求失败!");
					}
				});
			//}
		}
		
		
		 function checkAllParents(treeNode){
			nodes[nodes_index++]=treeNode;
			//alertPansky('ffff===>>>' + treeNode.id);
			if (treeNode==null || treeNode.id=='0') {
				//alertPansky(nodes.length);
				return ;
			}
			else
			{
				checkAllParents(treeNode.getParentNode());
			}
		} 

		function initTree() {
			//显示区域树  
			$.ajax({
				type : "POST",
				url : "organization/showOrganizationTree",
				data : {
					 id : 'ROOT' 
				},
				dataType : "json",
				success : function(data) {
					zTreeObj = $.fn.zTree.init($("#organization_tree"), setting,
							data);
				}
			});
		}

		//alertPansky(CONSTANT.pageList);
		
		function initTable() {

			var timestamp = Date.parse(new Date());
			$('#organization-table').bootstrapTable({
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
				url : "organization/findByPage?timestamp="+timestamp,//这个接口需要处理bootstrap table传递的固定参数
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
					field : 'SerialNumber',
					title : '序号',
					align : 'center',
					formatter : function(value, row, index) {
						return index + 1;
					}
				}, {
					field : 'id',
					title : 'ID',
					align : 'center',
					visible: false
				}, {
					field : 'name',
					title : '名称',
					align : 'center'
				}, {
					field : 'code',
					title : '组织代码',
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
						var del  = '<button title="删除" class="update-enterprise" data-id="'+row.id+'" onClick="deleteByPrimaryKey(\''+row.id+'\')"><span  class="glyphicon glyphicon-trash"></button>';
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
		
		
		function refreshTable(){
			$("#organization-table").bootstrapTable('destroy');
			initTable();
		}
		
		function setCurNodeId(node){
			$("#form-search").find("input[name='pId']").val(node.id);
			$("#organization_add_form").find("input[name='pId']").val(node.id);
			/* $("#organization_add_form").find("input[name='pCode']").val(node.params.code);
			$("#organization_add_form").find("input[name='type']").val(node.params.type); */
		}
		
		
		

		//保存信息
		$("#organization-add-btn").click(function(){
			$("#organization-add-modal").block();
			var id = $("#organization_add_form").find("input[name='id']").val();
			if(id!=""){
				update();
			}else{
				add();
			}
		});
		
		function add(){
			$.ajax({
				  url: "organization/add",
				  type: 'POST',
				  dataType: "JSON",
				  data: $("#organization_add_form").serialize(),
				  success: function(resp){
					  if(resp.flag==1){
						  $("#organization-add-modal").modal('hide');
						  $("#organization-add-modal").unblock();
						  alertPansky("保存成功");
						  refrashChildrenNode();
						  refreshTable();
					  }else{
						  alertPansky("保存失败！");
						  $("#organization-add-modal").unblock();
					  }
				  },
				  error:function(){
					  $("#organization-add-modal").unblock();
					  alertPansky("保存失败！[系统异常]");
				  }
				});
		}
		
		//显示修改联系人model
		function showUpdate(id){
			//alert(id);
			$.ajax({
				  url: "organization/selectByPrimaryKey",
				  type: 'POST',
				  dataType: "JSON",
				  data: {
					  id : id
				  },
				  success: function(resp){
					  //alertPansky(resp.data);
					  if(resp.flag==1){
						  $("#organization_add_form").setForm(resp.data);
						  $("#organization-add-modal").modal('show');
					  }else{
						  alertPansky("获取信息失败！");
					  }
					  
				  },
				  error: function(XMLHttpRequest, textStatus, errorThrown) {
					  
					  alertPansky("获取信息失败！[系统异常]");
				  }
			}); 
		}
		
		
		

		function update(){
			$.ajax({
				  url: "organization/updateByPrimaryKey",
				  type: 'POST',
				  dataType: "JSON",
				  data: $("#organization_add_form").serialize(),
				  success: function(resp){
					  if(resp.flag==1){
						  $("#organization-add-modal").modal('hide');
						  $("#organization-add-modal").unblock();
						  alertPansky("修改成功");
						  refrashChildrenNode();
						  refreshTable();
					  }else{
						  $("#organization-add-modal").unblock();
						  alertPansky("修改失败！");
					  }
				  },
				  error:function(){
					  $("#organization-add-modal").unblock();
					  alertPansky("修改信息失败！[系统异常]");
				  }
				});
		}
		


		

		function delFun(id){
			$.ajax({
				  url: "organization/updateEnabledByPrimaryKey",
				  type: 'POST',
				  dataType: "JSON",
				  data: {id:id,enabled:'2'},
				  success: function(resp){
					  if(resp.flag==1){
						  alertPansky("删除成功");
						  refreshTable();
						  //refreshTable();
						  refrashChildrenNode();
					  }else{
						  alertPansky("删除失败！");
					  }
				  },
				  error:function(){
					  alertPansky("删除失败！[系统异常]");
				  }
				});
		}

		function deleteByPrimaryKey(id){
			deletePansky(delFun,id);
		}
		
		
		
		/**
	     * 清空选中子节点
	     */
	    function removeChildNodesTree(treeObj)
	    {
	      var treeObj = $.fn.zTree.getZTreeObj("baseTree");
	      //获取全部节点数据
	      var nodes = treeObj.getNodes();
	      for (var i=0, l=nodes.length; i < l; i++)
	      {
	        //清空选中的第一个节点的子节点
	        treeObj.removeChildNodes(nodes[i]);
	      }
	    }
		
	    /**
	     * 设置面包屑导航
	     */
		function setCurNodeBreadcrumb(nodes){
			var a = $("#curNode-breadcrumb").html("");
			for(var i=nodes.length-1; i>=0; i--){
				//alertPansky(nodes[i].name);
				a.append("<li>"+nodes[i].name+"</li>");
			}
		}
	    
	    function refrashChildrenNode(){
	    	var zTree =  $.fn.zTree.getZTreeObj("organization_tree");
			var curNodeId = $("#form-search").find("input[name='pId']").val();
			var node = zTree.getNodeByParam("id",curNodeId);
			zTree.expandNode(node,false,true,true,false);  
            zTree.expandNode(node,true,false,true,true);  
	    }

		
		
	</script>

</body>
</html>