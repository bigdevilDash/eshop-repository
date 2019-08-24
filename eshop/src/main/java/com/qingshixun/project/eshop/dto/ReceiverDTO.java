/*****************************************************************************
 * Copyright (c) 2017, www.qingshixun.com
 *
 * All rights reserved
 *
 *****************************************************************************/
package com.qingshixun.project.eshop.dto;



/**
 * 订单实体类
 * 
 * @author QingShiXun
 * 
 * @version 1.0
 */

public class ReceiverDTO extends BaseDTO {

    // 收货人姓名
    private String name;

    // 收货地区路径
    private String areaPath;

    // 地址
    private String address;

    // 电话
    private String phone;

    // 所属会员
    private MemberDTO member;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAreaPath() {
        return areaPath;
    }

    public void setAreaPath(String areaPath) {
        this.areaPath = areaPath;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public MemberDTO getMember() {
        return member;
    }

    public void setMember(MemberDTO member) {
        this.member = member;
    }
}
