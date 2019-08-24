/*****************************************************************************
 * Copyright (c) 2017, www.qingshixun.com
 *
 * All rights reserved
 *
 *****************************************************************************/

package com.qingshixun.project.eshop.dto;

/**
 * 会员等级实体类
 * 
 * @author QingShiXun
 * 
 * @version 1.0
 */
public class MemberLevelDTO extends BaseDTO {

    // 对象序列化ID
    private static final long serialVersionUID = 2189941376177920282L;

    // 等级名称
    private String levelName;

    // 折扣
    private Float discount;

    // 等级积分
    private Integer levelScore;

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    public Integer getLevelScore() {
        return levelScore;
    }

    public void setLevelScore(Integer levelScore) {
        this.levelScore = levelScore;
    }

}
