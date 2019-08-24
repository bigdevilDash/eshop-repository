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
public class RoleResourceDTO {

    // 角色ID
    private Long roleId;

    // 角色对应的菜单
    private MenuDTO menu;

    // 角色对应的资源
    private ResourceDTO resource;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public MenuDTO getMenu() {
        return menu;
    }

    public void setMenu(MenuDTO menu) {
        this.menu = menu;
    }

    public ResourceDTO getResource() {
        return resource;
    }

    public void setResource(ResourceDTO resource) {
        this.resource = resource;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
