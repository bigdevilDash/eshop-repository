package com.qingshixun.project.eshop.module.evaluate.dao;

import com.qingshixun.project.eshop.dto.EvaluateDTO;
import com.qingshixun.project.eshop.web.MyBatisRepository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisRepository
public interface EvaluateDaoMyBatis {

    /**
     * 获取指定状态的产品评论列表
     */
    List<EvaluateDTO> getEvaluatesByStatusAndProduct(@Param("status") String status, @Param("productId") Long productId);

    /**
     * 获取指定用户在指定订单下对某个产品的评价
     */
    EvaluateDTO getEvaluateByMemberAndProductAndOrder(@Param("memberId") Long memberId, @Param("productId") Long productId, @Param("orderId") Long orderId);

    /**
     * 获取项目总分
     */
    Integer getTotalScoreByProduct(@Param("productId") Long productId);

    /**
     * 获取项目评论总数
     */
    Integer getEvaluateCountByProduct(@Param("productId") Long productId);

    /**
     * 获取指定状态的产品评论总数
     */
    Integer getEvaluateCountByStatusAndProduct(@Param("status") String status, @Param("productId") Long productId);

    /**
     * 保存评价
     */
    void saveOrUpdateEvaluate(EvaluateDTO evaluate);

}
