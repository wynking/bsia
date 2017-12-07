    


//扩展一个jquery的setform方法
    (function ($) {
        $.fn.setForm = function (jsonValue) {
        	//alert("jsonValue===>>>" + JSON.stringify(jsonValue));
            var obj = this;
            $.each(jsonValue, function (name, ival) {
            	/*alert("name===>>>" + name);
            	alert(obj.find("input:[name='" + name + "']"));*/
                var $oinput = obj.find("input[name=" + name + "]");
                //alert($oinput);
                if ($oinput.attr("type")== "radio" || $oinput.attr("type")== "checkbox") {
                   $oinput.each(function(){
                     if($(this).val()==ival)
                        $(this).attr("checked", "checked");
                   });
                }
                else
                {
                var $other = obj.find("[name="+name+"]");
                //$(element)[0].tagName 
               // alert($other);
	                if($other.length>0){
	                	if($other[0].tagName == 'SELECT'){
		                   	// alert(name + '---'+ $other.attr("class"));
	                		 if($other.hasClass("select2-hidden-accessible")){
	                			 $other.select2().val(ival).trigger('change');
	                		 }else{
	                			 $other.val(ival);
	                		 }
		               	}else{
		               		$other.val(ival); 
		               	}
	                }
                 }
            });
        }

        $.fn.setFormDisabled = function () {
        	 var obj = this;
        	 $(this).find("input").attr("disabled",true); 
        	 $(this).find("textarea").attr("disabled",true); 
        	 $(this).find("select").attr("disabled",true); 
        	 $(this).find("radio").attr("disabled",true); 
        	 $(this).find("checkbox").attr("disabled",true); 
        }
        
        $.fn.setFormReadOnly = function () {
       	 var obj = this;
       	 $(this).find("input").attr("readonly","readonly"); 
       	 $(this).find("textarea").attr("readonly","readonly"); 
       	 $(this).find("select").attr("readonly","readonly"); 
       	 $(this).find("radio").attr("readonly","readonly"); 
       	 $(this).find("checkbox").attr("readonly","readonly"); 
       }
        
        //清空form表单
        $.fn.resetForm = function () {
        	 //var obj = this;
        	 $(this)[0].reset();
        	 var sels =  $(this).find("select");
        	 if(sels.length > 0){
        		 sels.each(function(){
        			 $(this).select2().val("").trigger('change');
        		 });
        	 }
     		//$("#fwlys").select2().val("").trigger('change');
        }
        
        //表单对象转JSON对象
        $.fn.serializeObject = function()
        {
            var o = {};
            var a = this.serializeArray();
            $.each(a, function() {
                if (o[this.name] !== undefined) {
                    if (!o[this.name].push) {
                        o[this.name] = [o[this.name]];
                    }
                    o[this.name].push(this.value || '');
                } else {
                    o[this.name] = this.value || '';
                }
            });
            return o;
        };
        
      
    })(jQuery)

    
    

/**
 * 启动form校验
 * @param formId 表单ID
 */
function form_validate(formId){
	$('#'+formId).data('bootstrapValidator').validate();  
}

/**
 * 判断表单是否校验通过
 * @param formId 表单ID
 */
function form_isValid(formId){
	form_validate(formId);
	return $('#'+formId).data('bootstrapValidator').isValid()
}
/**
 * 重置表单所有验证规则
 * @param formId 表单ID
 */
function form_resetValid(formId){
	$('#'+formId).data("bootstrapValidator").resetForm();
}


/**
 * 重置表单所有验证规则
 * @param formId 表单ID
 */
function destroy(formId,formValidFun){
	$('#'+formId).data('bootstrapValidator').destroy();
	$('#'+formId).data('bootstrapValidator', null);
	formValidFun();
}

function deletePansky(fun,id){
	$.confirm({
		 title: '提示：',
		content: '是否确认删除该信息？',
	    buttons: {
	    	confirm: {
	        	 text: '确认',
	        	action: function () {
	        		//alert(id);
	        		fun(id);
		        }
	        },
	        cancel: {
	            text: '取消',
	            action: function () {
	            }
	        }
	    }
	});
}

function alertPansky(msg){
	$.alert({
	    title: '提示：',
	    content: "<center>"+msg+"</center>",
	    buttons:{
	    	 cancel: {
	    		 text: '关闭',
	             action: function () {
	                // $.alert('Critical action <strong>was performed</strong>.');
	             }
	         }
	    }
	});
}


function getTimestamp(){
	var timestamp = Date.parse(new Date());
	return timestamp;
}


REGEX={
		"email":"^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$",
		"phone":"^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8}$",
		"tel":"^(0\\d{2}-\\d{8}(-\\d{1,4})?)|(0\\d{3}-\\d{7,8}(-\\d{1,4})?)$",
		"int_1":"^[0-9]*[1-9][0-9]*$",//正整数
		"num2":"^([1-9][0-9]*)+(.[0-9]{1,2})?$",//最多两位小数
		"num_3":"^([1-9][0-9]*)+(.[0-9]{1,4})?$"//最多四位小数
}

CONSTANT={
		"pageSize":2,
		"pageList":[ 2,10, 25 ]
}
ENABLED={
		"ty":0,//停用
		"qy":1,//启用
		"sc":2//删除
}


