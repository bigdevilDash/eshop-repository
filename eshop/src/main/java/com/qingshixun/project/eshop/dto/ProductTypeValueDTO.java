/********************************************
 * Copyright (c) 2017, www.qingshixun.com
 *
 * All rights reserved
 *
 *********************************************/
package com.qingshixun.project.eshop.dto;

public class ProductTypeValueDTO extends BaseDTO {

    // 商品id
    private Long productId;

    // 商品类型id
    private Long typeAttributeId;

    // 商品属性值
    private String value;

    // 商品属性
    private ProductTypeAttributeDTO typeAttribute;

    // 商品属性值以数组的形式显示
    private String[] values;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getTypeAttributeId() {
        return typeAttributeId;
    }

    public void setTypeAttributeId(Long typeAttributeId) {
        this.typeAttributeId = typeAttributeId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public ProductTypeAttributeDTO getTypeAttribute() {
        return typeAttribute;
    }

    public void setTypeAttribute(ProductTypeAttributeDTO typeAttribute) {
        this.typeAttribute = typeAttribute;
    }

    public String[] getValues() {
        if (value != null) {
            return value.split(",");
        }

        return null;
    }

    public void setValues(String[] values) {
        this.values = values;
    }

}
