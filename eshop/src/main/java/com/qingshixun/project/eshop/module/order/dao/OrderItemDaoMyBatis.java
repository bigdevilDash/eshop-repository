package com.qingshixun.project.eshop.module.order.dao;

import com.qingshixun.project.eshop.dto.OrderItemDTO;
import com.qingshixun.project.eshop.web.MyBatisRepository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisRepository
public interface OrderItemDaoMyBatis {

    /**
     * 获取订单所有项
     */
    List<OrderItemDTO> getOrderItemsByOrder(@Param("orderId") Long orderId);

    /**
     * 保存订单项
     */
    void saveOrderItem(OrderItemDTO orderItem);

}
