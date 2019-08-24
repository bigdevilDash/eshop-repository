/*****************************************************************************
 * Copyright (c) 2017, www.qingshixun.com
 *
 * All rights reserved
 *
 *****************************************************************************/
package com.qingshixun.project.eshop.dto;

import java.util.Set;

/**
 * 会员实体类
 *
 * @author QingShiXun
 * @version 1.0
 */

public class MemberDTO extends BaseDTO {

    // 用户名
    private String userName;

    // 密码
    private String password;

    // E-mail
    private String email;

    // 积分
    private Integer point;

    // 会员等级
    private MemberLevelDTO memberLevel;

    // 用户状态, 0表示已激活，1表示未激活
    private Integer memberStatus = 1;

    // 默认收货地址
    private Long defaultReceiverId;

    // 购物车项
    private Set<CartItemDTO> cartItemSet;

    public Long getDefaultReceiverId() {
        return defaultReceiverId;
    }

    public void setDefaultReceiverId(Long defaultReceiverId) {
        this.defaultReceiverId = defaultReceiverId;
    }

    private String validateCode;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public MemberLevelDTO getMemberLevel() {
        return memberLevel;
    }

    public void setMemberLevel(MemberLevelDTO memberLevel) {
        this.memberLevel = memberLevel;
    }

    public Set<CartItemDTO> getCartItemSet() {
        return cartItemSet;
    }

    public void setCartItemSet(Set<CartItemDTO> cartItemSet) {
        this.cartItemSet = cartItemSet;
    }

    public String getValidateCode() {
        return validateCode;
    }

    public Integer getMemberStatus() {
        return memberStatus;
    }

    public void setMemberStatus(Integer memberStatus) {
        this.memberStatus = memberStatus;
    }
}
