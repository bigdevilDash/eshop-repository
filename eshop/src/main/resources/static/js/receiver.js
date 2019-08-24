/*****************************************************************************
 * Copyright (c) 2015, www.qingshixun.com
 *
 * All rights reserved
 *
 *****************************************************************************/
/**
 * 自定义收货人的js文件用于收货人的js
 * 
 * @author QingShiXun
 * 
 * @version 1.0
 */
var receiver = (function() {

	function initForm() {
		
		//点击选择地点触发事件
        $("#city").click(function(e) {
            SelCity(this, e);
        });
    	
    	//点击选择城市的弹出的关闭窗口 触发事件
        $(".js-modal-close, .modal-overlay").click(function() {
            $(".modal-box, .modal-overlay").fadeOut(500, function() {
                $(".modal-overlay").remove();
            });
        });

        // 提交收货人地址
        $('.save-receiver-btn').click(function(e) {
            submitReceiverForm();
        });
        
        // 返回按钮事件
        $('.return-receiver-btn').click(function(e) {
            $(".close").trigger('click');
        });
        
	}
	
    // 提交收货人表单信息
    function submitReceiverForm() {
    	var $receiverForm  =$('#receiverForm');
    	var isValid = $receiverForm.validationEngine('validate', {
			promptPosition : 'topLeft',
			autoHidePrompt : true,
			scroll : false
		});

		// 输入有效性验证未通过
		if (!isValid) {
			return false;
		}
        //表单提交
        $('#receiverForm').ajaxSubmit({
            success : function(response) {
                if (response.status == '0') {
                    toastr.success('收货人编辑成功！');
                    $('.close').trigger('click');
                    window.location.reload();
                } else {
                    toastr.warning('收货人编辑失败！' + response.data);
                }
            },
            error : function(context, xhr) {
                $.alert({
                    title : '出现错误',
                    content : xhr,
                    confirmButton : '确定'
                });
            }
        });
    }

	return {
		initForm : initForm
	};
})();