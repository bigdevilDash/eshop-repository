/*****************************************************************************
 * Copyright (c) 2015, www.qingshixun.com
 *
 * All rights reserved
 *
 *****************************************************************************/
/**
 * 自定义评论的js文件用于封装评论的js
 * 
 * @author QingShiXun
 * 
 * @version 1.0
 */
var evaluate = (function() {
	var confirmDiv;
	function initList() {
		// 点击分页确定按钮触发事件
		$(".commit_btn").click(
				function() {
					var pageNo = $(this).prev().val();
					var url = g_rootPath
							+ "/front/product/evaluate/list/?productId="
							+ productId + "&pageNo=" + pageNo + "&status="
							+ status;
					$("#evaluateListPanel").load(url);
				});
	}

	function initMain(orderId) {
		// 保存评论
		$('.save-evaluate-btn').click(function(e) {
			submitEvaluationForm(orderId);
		});
		$('#subComm').click(function(e) {
			var sebei = document.getElementById('sebei').value;
		});
		starComm('star1');
	}

	// 提交评论表单信息
	function submitEvaluationForm(orderId) {
		var score = $('#score').val();
		if (score <= 0) {
			toastr.warning('商品评分不能为空！');
			return false;
		}
		var message=$('#message').val();
		if (message == '') {
			toastr.warning('商品评价不能为空！');
			return false;
		}
		$('#evaluateForm').ajaxSubmit({
			success : function(response) {
				console.info(response);
				if (response.status == '0') {
					toastr.success('评论成功！');
					window.location.href = g_rootPath + '/front/order/detail/' + orderId
				} else {
					toastr.warning('评论失败！' + response.data);
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
	// 设置点评星星样式
	function starComm(starId) {
		var oStar = document.getElementById(starId);
		var aLi = oStar.getElementsByTagName("li");
		var oUl = oStar.getElementsByTagName("ul")[0];
		var oP = oStar.getElementsByTagName("input")[0];
		var i = iScore = iStar = 0;
		for (i = 1; i <= aLi.length; i++) {
			aLi[i - 1].index = i;
			// 点击后进行评分处理
			aLi[i - 1].onclick = function() {
				iStar = this.index;
				fnPoint(iStar);
				oP.value = iStar;
			}
		}
		// 评分处理
		var fnPoint = function(iArg) {
			// 分数赋值
			iScore = iArg || iStar;
			for (i = 0; i < aLi.length; i++)
				aLi[i].className = i < iScore ? "on" : "";
		}

	}

	return {
		initList : initList,
		initMain : initMain
	};
})();