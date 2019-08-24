/*****************************************************************************
 * Copyright (c) 2017, www.qingshixun.com
 *
 * All rights reserved
 *
 *****************************************************************************/
package com.qingshixun.project.eshop.dto;

/**
 * 商品属性实体类
 * 
 * @author QingShiXun
 * 
 * @version 1.0
 */

public class ProductAttributeDTO extends BaseDTO {

    private static final long serialVersionUID = 2989102568413246570L;
    // 属性名称
    private String name;

    // 属性名称
    private String value;

    // 商品类型
    private ProductDTO product;

    // 类型
    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
