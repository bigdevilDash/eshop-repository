/*****************************************************************************
 * Copyright (c) 2017, www.qingshixun.com
 *
 * All rights reserved
 *
 *****************************************************************************/
package com.qingshixun.project.eshop.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 商品分类实体类
 * 
 * @author QingShiXun
 * 
 * @version 1.0
 */

public class ProductCategoryDTO extends BaseDTO {

    private static final long serialVersionUID = -5132652107151648662L;
    // 树路径分隔符
    public static final String PATH_SEPARATOR = ",";

    // 分类名称
    private String name;

    // 排序
    private Integer orderList;

    // 分类拥有的品牌
    private String brands;

    // 上级分类
    private Long parentId;

    // 下级分类
    private List<ProductCategoryDTO> children = new ArrayList<ProductCategoryDTO>();

    // 商品
    private Set<ProductDTO> productSet;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOrderList() {
        return orderList;
    }

    public void setOrderList(Integer orderList) {
        this.orderList = orderList;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public List<ProductCategoryDTO> getChildren() {
        return children;
    }

    public void setChildren(List<ProductCategoryDTO> children) {
        this.children = children;
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

    public String getBrands() {
        return brands;
    }

    public void setBrands(String brands) {
        this.brands = brands;
    }

}
