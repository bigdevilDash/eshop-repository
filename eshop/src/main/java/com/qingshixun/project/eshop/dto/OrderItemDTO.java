/*****************************************************************************
 * Copyright (c) 2017, www.qingshixun.com
 *
 * All rights reserved
 *
 *****************************************************************************/
package com.qingshixun.project.eshop.dto;

/**
 * 订单实体类
 * 
 * @author QingShiXun
 * 
 * @version 1.0
 */

public class OrderItemDTO extends BaseDTO {

    private static final long serialVersionUID = -6109590619136943215L;

    // 商品数量
    private Integer productQuantity;

    // 订单
    private OrderDTO order;

    // 商品
    private ProductDTO product;

    // 总价格
    private Double totalPrice;

    // 订单状态
    private OrderItemStatus status;

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

    public OrderDTO getOrder() {
        return order;
    }

    public void setOrder(OrderDTO order) {
        this.order = order;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public OrderItemStatus getStatus() {
        return status;
    }

    public void setStatus(OrderItemStatus status) {
        this.status = status;
    }

}
