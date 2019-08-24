/*****************************************************************************
 * Copyright (c) 2017, www.qingshixun.com
 *
 * All rights reserved
 *
 *****************************************************************************/
package com.qingshixun.project.eshop.dto;

import java.util.List;

/**
 * 树状结构json对象
 * 
 * @author QingShiXun
 * 
 * @version 1.0
 */
public class PowerTree {
    // 节点id
    private String id;

    // 节点内容
    private String text;

    // 子节点
    private List<PowerTree> children;

    // 节点状态
    private State state;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<PowerTree> getChildren() {
        return children;
    }

    public void setChildren(List<PowerTree> children) {
        this.children = children;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

}
