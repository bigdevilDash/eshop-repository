/*****************************************************************************
 * Copyright (c) 2017, www.qingshixun.com
 *
 * All rights reserved
 *
 *****************************************************************************/
package com.qingshixun.project.eshop.dto;

import java.util.Set;

/**
 * 品牌实体类
 * 
 * @author QingShiXun
 * 
 * @version 1.0
 */

public class BrandDTO extends BaseDTO {

    private static final long serialVersionUID = -6109590619136943215L;
    // 名称
    private String name;

    // 商品
    private Set<ProductDTO> productSet;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ProductDTO> getProductSet() {
        return productSet;
    }

    public void setProductSet(Set<ProductDTO> productSet) {
        this.productSet = productSet;
    }


}
