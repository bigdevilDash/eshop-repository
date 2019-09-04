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
var cart = (function () {
  var confirmDiv

  function initList () {
    $.each($('input[class*=text_box]'), function (i, t) {
      subtotal($(t))
    })
    $('.checkAllProduct').click(function () {
      checkAllProduct(this)
    })

    $('#deleteSelectProduct').click(function () {
      deleteCheck()
    })

    $('.checkBox').click(function () {
      checkBox()
    })

    // 点击添加数量按钮触发事件
    $('.add').click(function () {
      var t = $(this).parent().find('input[class*=text_box]')
      t.val(parseInt(t.val()) + 1)
      if (isNaN(t.val())) {
        t.val(1)
      }
      var cartItemId = $(this).data('id')
      var quantity = parseInt(t.val())
      $.post(g_rootPath + '/front/cart/set/quantity/' + cartItemId + '/' + quantity)
      subtotal(t)
    })
    confirmDiv = $(
      '<div class="ms-alert">' + '<div class="ms-alert-body">' + '<div class="ms-alert-content">是否删除购物车中的购物项</div>' + '<div class="ms-alert-buttons">'
      + '<div class="ms-alert-button confirm">确定</div>' + '<div class="ms-alert-button cancel">取消</div>' + '</div>' + '</div>' + '</div>').alertconfirm()
    // 点击删除按钮触发事件
    $('.delete_cart_btn').click(function () {
      var ids = $(this).data('id')
      var url = g_rootPath + '/front/cart/delete/' + ids

      confirmDiv.on('confirm.ms.alert', function (e) {
        // 执行删除
        $.ajax({
          url: url,
          type: 'DELETE',
          success: function (result) {
            window.location.href = g_rootPath + '/front/cart/list'
          }
        })
        e.preventDefault()
      }).on('show.ms.alert shown.ms.alert hide.ms.alert hidden.ms.alert')
      confirmDiv.alertconfirm('show')
    })
    // 点击结算按钮触发事件
    $('.to_total').click(function () {
      var params = ''
      $('.list_pro ul li').find('input[name="checkItem"]:checked').each(function () {
        var $this = $(this)
        var id = $this.data('id')
        var $ul = $(this).parent().parent()
        var count = $ul.find('input[class*=text_box]').val()
        var param = id + '_' + count
        params += param + ','// 遍历选中的checkbox
      })
      window.location.href = g_rootPath + '/front/order/settlement?params=' + params
    })
    $('.min').click(function () {
      var t = $(this).parent().find('input[class*=text_box]')
      t.val(parseInt(t.val()) - 1)
      if (parseInt(t.val()) <= 0 || isNaN(t.val())) {
        t.val(1)
      }
      var cartItemId = $(this).data('id')
      var quantity = parseInt(t.val())
      $.post(g_rootPath + '/front/cart/set/quantity/' + cartItemId + '/' + quantity)
      subtotal(t)
    })
    // 数量输入框输入后离开的事件
    $('input[class*=text_box]').keyup(function () {
      var t = $(this)
      t.val(parseInt(t.val()))
      if (isNaN(t.val()) || parseInt(t.val()) <= 0) {
        t.val(1)
      }
      if (t.val(parseInt(t.val())) != t.val()) {
        t.val(parseInt(t.val()))
      }

      var productId = $(this).data('id')
      var quantity = parseInt(t.val())
      $.post(g_rootPath + '/front/cart/set/quantity/' + productId + '/' + quantity)
      subtotal(t)
    })

    setTotal()
    setTotalCount()
  }

  function initForm () {

  } // 计算選中价格
  function subtotal (sum) {
    var subtotal = 0
    subtotal = parseFloat(sum.siblings('.price').text()) * parseInt(sum.val())
    sum.siblings('.subtotal').html(subtotal.toFixed(2))
    setTotal()
    setTotalCount()
  }

  // 计算中价格
  function checkBox (sum) {
    setTotal()
    setTotalCount()
  }

  // 计算总价格
  function setTotal () {
    var s = 0
    $('.list_pro ul li').find('input[name="checkItem"]:checked').each(function () {
      var $ul = $(this).parent().parent()
      s += parseInt($ul.find('input[class*=text_box]').val()) * parseFloat($ul.find('li[class*=price]').text())
    })
    $('#total').html(s.toFixed(2))
  }

  // 计算总数量
  function setTotalCount () {
    var count = 0
    $('.list_pro ul li').find('input[name="checkItem"]:checked').each(function () {
      var $ul = $(this).parent().parent()
      count += parseInt($ul.find('input[class*=text_box]').val())
    })
    $('#totalCount').html(count)
    $('#cartCount').html(count)

  }

  // 全选
  function checkAllProduct (evt) {
    $('#cartItemList').find('input[type=\'checkbox\']').prop('checked', evt.checked)
    setTotal()
    setTotalCount()
    setTotalCount()
  }

  /**
   * 删除选中的数据
   *
   */
  function deleteCheck () {
    var ids = ''
    $('.list_pro ul li').find('input[name="checkItem"]:checked').each(function () {
      var $this = $(this)
      var id = $this.data('id')
      ids += id + ','// 遍历选中的checkbox
    })
    // 如果删除的数据在数据库中已经存在则向后台发送删除请求
    if (ids !== '') {
      var url = g_rootPath + '/front/cart/delete/' + ids

      confirmDiv.on('confirm.ms.alert', function (e) {
        // 执行删除
        $.ajax({
          url: url,
          type: 'DELETE',
          success: function (result) {
            window.location.href = g_rootPath + '/front/cart/list'
          }
        })
        e.preventDefault()
      }).on('show.ms.alert shown.ms.alert hide.ms.alert hidden.ms.alert')
      confirmDiv.alertconfirm('show')
    }
    setTotalCount()
  }

  return {
    initList: initList
  }
})()