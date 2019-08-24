/*****************************************************************************
 * Copyright (c) 2017, www.qingshixun.com
 *
 * All rights reserved
 *
 *****************************************************************************/

package com.qingshixun.project.eshop.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 角色实体类（管理端）
 * 
 * @author QingShiXun
 * 
 * @version 1.0
 */
public class RoleDTO extends BaseDTO {

    /**
     * 序列号
     */
    private static final long serialVersionUID = 1L;
    // 角色名称
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
