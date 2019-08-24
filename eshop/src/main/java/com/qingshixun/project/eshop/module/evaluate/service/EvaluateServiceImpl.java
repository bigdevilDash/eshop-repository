package com.qingshixun.project.eshop.module.evaluate.service;

import com.qingshixun.project.eshop.dto.EvaluateDTO;
import com.qingshixun.project.eshop.dto.MemberDTO;
import com.qingshixun.project.eshop.module.evaluate.dao.EvaluateDaoMyBatis;
import com.qingshixun.project.eshop.web.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.util.List;

@Service
@Transactional
public class EvaluateServiceImpl extends BaseService {

    @Autowired
    private EvaluateDaoMyBatis evaluateDao;

    /**
     * 更新或保存评价
     */
    public void saveOrUpdateEvaluate(EvaluateDTO evaluate, MemberDTO member) {
        evaluate.setMember(member);
        evaluateDao.saveOrUpdateEvaluate(evaluate);
    }

    /**
     * 获取指定状态的产品评论列表
     */
    public List<EvaluateDTO> getEvaluatesByStatusAndProduct(String status, Long productId) {
        return evaluateDao.getEvaluatesByStatusAndProduct(status, productId);
    }

    /**
     * 获取指定用户在指定订单下对某个产品的评价
     */
    public EvaluateDTO getEvaluateByMemberAndProductAndOrder(Long memberId, Long productId, Long orderId) {
        EvaluateDTO evaluate = evaluateDao.getEvaluateByMemberAndProductAndOrder(memberId, productId, orderId);

        if (evaluate == null) {
            return new EvaluateDTO();
        }

        return evaluate;
    }

    /**
     * 获取平均评论分数
     */
    public String getAverageEvaluateScoreByProduct(Long productId) {
        // 获取项目评论总分
        Integer totalScore = evaluateDao.getTotalScoreByProduct(productId);
        // 获取项目评论总数
        Integer totalCount = getEvaluateCountByProduct(productId);

        Double averageEvaluateScore = 0d;

        if (totalCount != 0) {
            averageEvaluateScore = totalScore / (double) totalCount;
        }

        DecimalFormat df = new DecimalFormat("######0.00");
        return df.format(averageEvaluateScore);
    }

    /**
     * 获取项目评论总数
     */
    public Integer getEvaluateCountByProduct(Long productId) {
        return evaluateDao.getEvaluateCountByProduct(productId);
    }

    /**
     * 获取指定状态的产品评论总数
     */
    public Integer getEvaluateCountByStatusAndProduct(String status, Long productId) {
        return evaluateDao.getEvaluateCountByStatusAndProduct(status, productId);
    }

}
