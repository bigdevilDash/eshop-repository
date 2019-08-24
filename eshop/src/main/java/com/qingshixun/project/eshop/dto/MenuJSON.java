/*****************************************************************************
 * Copyright (c) 2017, www.qingshixun.com
 *
 * All rights reserved
 *
 *****************************************************************************/
package com.qingshixun.project.eshop.dto;

import java.util.List;

/**
 * 菜單json對象
 * 
 * @author QingShiXun
 * 
 * @version 1.0
 */
public class MenuJSON {
    // 节点id
    private Integer id;

    // 子节点对象
    private List<MenuJSON> children;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<MenuJSON> getChildren() {
        return children;
    }

    public void setChildren(List<MenuJSON> children) {
        this.children = children;
    }

}
