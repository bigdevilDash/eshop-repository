/*****************************************************************************
 * Copyright (c) 2017, www.qingshixun.com
 *
 * All rights reserved
 *
 *****************************************************************************/

package com.qingshixun.project.eshop.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 资源实体类（管理端）
 * 
 * @author QingShiXun
 * 
 * @version 1.0
 */
public class ResourceDTO extends BaseDTO {

    /**
     * 序列号
     */
    private static final long serialVersionUID = 1L;

    // 名称
    private String name;

    // 资源的URL
    private String url;

    // 菜单id
    private Long menuId;

    public ResourceDTO(Long id) {
        this.id = id;
    }

    public ResourceDTO() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
