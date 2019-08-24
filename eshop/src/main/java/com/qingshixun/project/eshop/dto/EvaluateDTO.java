/*****************************************************************************
 * Copyright (c) 2015, www.qingshixun.com
 *
 * All rights reserved
 *
 *****************************************************************************/
package com.qingshixun.project.eshop.dto;

import com.qingshixun.project.eshop.util.DateUtils;

import java.util.Date;

/**
 * 评论实体类
 *
 * @author QingShiXun
 * @version 1.0
 */
public class EvaluateDTO extends BaseDTO {

    // 评分
    private Integer score = 0;

    // 满意度
    private String evaluateStatus;

    // 描述
    private String description;

    // 订单id
    private Long orderId;

    // 会员
    private MemberDTO member;

    // 商品
    private ProductDTO product;

    // 创建时间
    private String createTime = DateUtils.timeToString(new Date());

    // 修改时间
    private String updateTime = DateUtils.timeToString(new Date());

    public EvaluateDTO() {
        setEvaluateStatus();
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MemberDTO getMember() {
        return member;
    }

    public void setMember(MemberDTO member) {
        this.member = member;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getEvaluateStatus() {
        return evaluateStatus;
    }

    public void setEvaluateStatus(String evaluateStatus) {
        this.evaluateStatus = evaluateStatus;
    }

    public void setEvaluateStatus() {
        setEvaluateStatus(score);
    }

    public void setEvaluateStatus(int score) {
        // 分数小于等于1表示不满意，大于1小于等于3表示一般，大于3表示满意
        if (score <= 1) {
            this.setEvaluateStatus("不满意");
        } else if (score <= 3 && score > 1) {
            this.setEvaluateStatus("一般");
        } else {
            this.setEvaluateStatus("满意");
        }
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

}
