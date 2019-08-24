/*****************************************************************************
 * Copyright (c) 2017, www.qingshixun.com
 *
 * All rights reserved
 *
 *****************************************************************************/

package com.qingshixun.project.eshop.dto;

import com.google.common.collect.Lists;

import java.io.Serializable;
import java.util.List;

/**
 * 用户实体类（管理端）
 * 
 * @author QingShiXun
 * 
 * @version 1.0
 */
public class UserDTO extends BaseDTO implements Serializable {

    /**
     * 序列号
     */
    private static final long serialVersionUID = 1L;

    // 登录名称
    private String loginName;

    // 真实姓名
    private String name;

    // 密码
    private String password;

    // 密码位数
    private String salt;

    // 角色列表
    private List<String> roles = Lists.newArrayList();

    // 选择的角色
    private String[] selectRoles;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String[] getSelectRoles() {
        return selectRoles;
    }

    public void setSelectRoles(String[] selectRoles) {
        this.selectRoles = selectRoles;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Override
    public String toString() {
        return loginName;
    }
}
