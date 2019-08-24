/*****************************************************************************
 * Copyright (c) 2017, www.qingshixun.com
 *
 * All rights reserved
 *
 *****************************************************************************/

package com.qingshixun.project.eshop.dto;

import java.io.Serializable;

/**
 * 实体类基类
 * 
 * @author QingShiXun
 * 
 * @version 1.0
 */
public abstract class BaseDTO implements Serializable {

    /**
     * 序列号
     */
    private static final long serialVersionUID = 1L;

    // id主键
    protected Long id = 0L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
