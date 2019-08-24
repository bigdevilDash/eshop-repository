/*****************************************************************************
 * Copyright (c) 2017, www.qingshixun.com
 *
 * All rights reserved
 *
 *****************************************************************************/
package com.qingshixun.project.eshop.dto;

/**
 * 品牌实体类
 * 
 * @author QingShiXun
 * 
 * @version 1.0
 */

public class ConfigDTO extends BaseDTO {

    private static final long serialVersionUID = -6109590619136943215L;

    // 编码
    private String code;

    // 名称
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
