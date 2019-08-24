/*****************************************************************************
 * Copyright (c) 2017, www.qingshixun.com
 *
 * All rights reserved
 *
 *****************************************************************************/

package com.qingshixun.project.eshop.dto;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * 菜单实体类（管理端）
 * 
 * @author QingShiXun
 * 
 * @version 1.0
 */
public class MenuDTO extends BaseDTO {

    private static final long serialVersionUID = 1L;

    // 编码
    private String code;

    // 资源名称
    private String name;

    // 图标
    private String icon;

    // 访问URL
    private String url;

    // 父级菜单
    private MenuDTO parent;

    // 父级菜单id
    private Long parentId;

    // 排序
    private Integer indexNo;

    // 资源列表
    private List<ResourceDTO> resources;

    // 子菜单
    private List<MenuDTO> children = Lists.newArrayList();

    public MenuDTO(Long id) {
        this.id = id;
    }

    public MenuDTO() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public MenuDTO getParent() {
        return parent;
    }

    public void setParent(MenuDTO parent) {
        this.parent = parent;
    }

    public List<ResourceDTO> getResources() {
        return resources;
    }

    public void setResources(List<ResourceDTO> resources) {
        this.resources = resources;
    }

    public Integer getIndexNo() {
        return indexNo;
    }

    public void setIndexNo(Integer indexNo) {
        this.indexNo = indexNo;
    }

    public List<MenuDTO> getChildren() {
        return children;
    }

    public void setChildren(List<MenuDTO> children) {
        this.children = children;
    }

    public void addChildren(MenuDTO child) {
        this.children.add(child);
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

}
