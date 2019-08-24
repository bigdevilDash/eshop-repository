/*****************************************************************************
 * Copyright (c) 2015, www.qingshixun.com
 *
 * All rights reserved
 *
 *****************************************************************************/
/**
 * 自定义订单的js文件用于封装订单的js
 * 
 * @author QingShiXun
 * 
 * @version 1.0
 */
var order = (function() {
	function initMain(selectOrderId) {
		var orderId = selectOrderId;
		// 点击编辑按钮触发弹出层
		$('.eidt_receiver').click(function(e) {
			e.preventDefault();
			var receiverId = $(this).data("id");
			$("#popup1").load(g_rootPath + "/front/order/receiver/form/" + receiverId);
			$(".modal-overlay").fadeTo(500, 0.7);
			var modalBox = $(this).attr('data-modal-id');
			$('#' + modalBox).fadeIn($(this).data());
		});
		// 点击新增收货人按钮触发弹出层
		$('.add_receiver').click(function(e) {
			e.preventDefault();
			$("#popup1").load(g_rootPath + "/front/order/receiver/form/0/");
			$(".modal-overlay").fadeTo(500, 0.7);
			var modalBox = $(this).attr('data-modal-id');
			$('#' + modalBox).fadeIn($(this).data());
		});
		// 点击设置为默认联系人触发事件
		$(".set_default_receiver").click(function() {
			var receiverId = $(this).data("id");
			$.post(g_rootPath + "/front/order/set/default/receiver/" + receiverId, function(response) {
				$("#searchPanel").hide();
				window.location.reload();
			});
		});
		// 点击去结算按钮触发事件
		$("#commitOrder").click(
				function() {
					var receiverId = $("#defaultReceiver").data("id");
					var pargrams = '';
					var payDiv;
					var totalAmount = $('#totalAmount').html();
					$("#productList").find('.productCount').each(function() {
						var $this = $(this);
						var id = $this.data("id");
						var count = $this.data("count");
						var pargram = id + "_" + count;
						pargrams += pargram + ",";// 遍历选中的checkbox
					});
					if (receiverId === null) {
						toastr.warning('请先设置默认联系人！');
					} else {
						$.post(g_rootPath + "/front/order/commit/" + pargrams + "/" + receiverId, function(response) {
							if (response.status === '0') {
								var orderId = response.data;
								payDiv = $(
										'<div class="ms-alert">' + '<div class="ms-alert-body">' + '<div class="ms-alert-content">当前付款金额为' + totalAmount + ' <br> <br> 是否确认付款 </div>'
												+ '<div class="ms-alert-buttons">' + '<div class="ms-alert-button confirm">确定</div>' + '<div class="ms-alert-button cancel">取消</div>' + '</div>'
												+ '</div>' + '</div>').alertconfirm();
								// 点击确认付款
								payDiv.on('confirm.ms.alert', function(e) {
									$.post(g_rootPath + "/front/order/pay/" + orderId, function(response) {
										if (response.status === '0') {
											toastr.success('付款成功！');
											payDiv.alertconfirm('hide');
											window.location.href = g_rootPath + "/front/order/list";
										}

									});
									e.preventDefault();
								}).on('show.ms.alert shown.ms.alert hide.ms.alert hidden.ms.alert');
								payDiv.alertconfirm('show');
							}

						});
					}

				});
		// 点击删除按钮触发事件
		$(".delete-receiver-btn").click(function() {
			var id = $(this).data("id");
			var url = g_rootPath + "/front/order/receiver/delete/"+id;
			var confirmDiv = $(
					'<div class="ms-alert">' + '<div class="ms-alert-body">' + '<div class="ms-alert-content">是否删除联系人</div>' + '<div class="ms-alert-buttons">'
							+ '<div class="ms-alert-button confirm">确定</div>' + '<div class="ms-alert-button cancel">取消</div>' + '</div>' + '</div>' + '</div>').alertconfirm();
			//
			confirmDiv.on('confirm.ms.alert', function(e) {
				// 执行删除
				$.ajax({
					url : url,
					type : 'DELETE',
					success : function(result) {
						if(result.status === '0'){
							window.location.reload();
						}else{
							toastr.warning(result.data);
						}
						//window.location.href = g_rootPath + "/front/order/main";
					}
				});
				e.preventDefault();
			}).on('show.ms.alert shown.ms.alert hide.ms.alert hidden.ms.alert');
			confirmDiv.alertconfirm('show');
		});

		// 点击去结算按钮车发事件
		$("#payOrder").click(
				function() {
					var totalAmount = $('#totalAmount').html();
					payDiv = $(
							'<div class="ms-alert">' + '<div class="ms-alert-body">' + '<div class="ms-alert-content">当前付款金额为' + totalAmount + ' <br> <br> 是否确认付款 </div>'
									+ '<div class="ms-alert-buttons">' + '<div class="ms-alert-button confirm">确定</div>' + '<div class="ms-alert-button cancel">取消</div>' + '</div>' + '</div>'
									+ '</div>').alertconfirm();
					// 点击确认付款
					payDiv.on('confirm.ms.alert', function(e) {
						$.post(g_rootPath + "/front/order/pay/" + orderId, function(response) {
							if (response.status === '0') {
								toastr.success('付款成功！');
								payDiv.alertconfirm('hide');
								window.location.href = g_rootPath + "/front/order/list";
								
							}

						});
						e.preventDefault();
					}).on('show.ms.alert shown.ms.alert hide.ms.alert hidden.ms.alert');
					payDiv.alertconfirm('show');
				});

	}

	return {
		initMain : initMain
	};
})();