/*****************************************************************************
 * Copyright (c) 2017, www.qingshixun.com
 *
 * All rights reserved
 *
 *****************************************************************************/
package com.qingshixun.project.eshop.dto;

/**
 * 状态对象
 *
 * @author QingShiXun
 *
 * @version 1.0
 */
public class State {
    // 选中状态
    private boolean selected;

    public State(boolean selected) {
        this.selected = selected;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

}
