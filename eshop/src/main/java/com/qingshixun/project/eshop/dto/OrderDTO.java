/*****************************************************************************
 * Copyright (c) 2017, www.qingshixun.com
 *
 * All rights reserved
 *
 *****************************************************************************/
package com.qingshixun.project.eshop.dto;


import com.google.common.collect.Lists;
import com.qingshixun.project.eshop.util.DateUtils;

import java.util.Date;
import java.util.List;

/**
 * 订单实体类
 * 
 * @author QingShiXun
 * 
 * @version 1.0
 */

public class OrderDTO extends BaseDTO {

    // 订单状态
    private OrderStatus status;

    // 商品总价格
    private Double productTotalPrice = 0d;

    // 订单总额
    private Double totalAmount = 0d;

    private String orderNum = "";

    // 订单渠道(默认采用的是网页端)
    private OrderChannel orderChannel;

    private MemberDTO member;

    private ReceiverDTO receiver;

    // 创建时间
    private String createTime = DateUtils.timeToString(new Date());

    // 修改时间
    private String updateTime = DateUtils.timeToString(new Date());

    // 商品总数
    private Integer productTotalQuantity = 0;

    private List<OrderItemDTO> orderItems = Lists.newArrayList();

    public List<OrderItemDTO> getOrderItems() {

        return orderItems;
    }

    public void setOrderItems(List<OrderItemDTO> orderItems) {
        this.orderItems = orderItems;
    }

    public Double getProductTotalPrice() {
        return productTotalPrice;
    }

    public void setProductTotalPrice(Double productTotalPrice) {
        this.productTotalPrice = productTotalPrice;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getProductTotalQuantity() {
        return productTotalQuantity;
    }

    public void setProductTotalQuantity(Integer productTotalQuantity) {
        this.productTotalQuantity = productTotalQuantity;
    }

    public OrderChannel getOrderChannel() {
        return orderChannel;
    }

    public void setOrderChannel(OrderChannel orderChannel) {
        this.orderChannel = orderChannel;
    }

    public MemberDTO getMember() {
        return member;
    }

    public void setMember(MemberDTO member) {
        this.member = member;
    }

    public ReceiverDTO getReceiver() {
        return receiver;
    }

    public void setReceiver(ReceiverDTO receiver) {
        this.receiver = receiver;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

}
