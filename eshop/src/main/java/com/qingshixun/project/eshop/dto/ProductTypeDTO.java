/*****************************************************************************
 * Copyright (c) 2017, www.qingshixun.com
 *
 * All rights reserved
 *
 *****************************************************************************/
package com.qingshixun.project.eshop.dto;

import java.util.List;
import java.util.Set;

/**
 * 商品类型实体类
 * 
 * @author QingShiXun
 * 
 * @version 1.0
 */

public class ProductTypeDTO extends BaseDTO {

    private static final long serialVersionUID = -6173231303962800416L;
    // 类型名称
    private String name;

    // 商品属性
    private List<ProductAttributeDTO> productAttributeList;

    // 商品
    private Set<ProductDTO> productSet;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProductAttributeDTO> getProductAttributeList() {
        return productAttributeList;
    }

    public void setProductAttributeList(List<ProductAttributeDTO> productAttributeList) {
        this.productAttributeList = productAttributeList;
    }

    public Set<ProductDTO> getProductSet() {
        return productSet;
    }

    public void setProductSet(Set<ProductDTO> productSet) {
        this.productSet = productSet;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
