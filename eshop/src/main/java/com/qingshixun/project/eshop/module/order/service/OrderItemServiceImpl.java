package com.qingshixun.project.eshop.module.order.service;

import com.google.common.collect.Lists;
import com.qingshixun.project.eshop.dto.CartItemDTO;
import com.qingshixun.project.eshop.dto.OrderItemDTO;
import com.qingshixun.project.eshop.module.order.dao.OrderItemDaoMyBatis;
import com.qingshixun.project.eshop.module.product.service.ProductServiceImpl;
import com.qingshixun.project.eshop.web.BaseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderItemServiceImpl extends BaseService {

    @Autowired
    private OrderItemDaoMyBatis orderItemDao;

    @Autowired
    private ProductServiceImpl productService;

    /**
     * 获取选中的购物车
     *
     * @return 返回生成的订单ID
     */
    public List<OrderItemDTO> getSelectCart(String params, HttpSession session) {
        List<Long> productIds = Lists.newArrayList();
        // params 拼接参数拼接方式是（商品id_商品数量，商品id_商品数量）
        Map<Long, Integer> productCountMap = new HashMap<Long, Integer>();
        if (!StringUtils.isEmpty(params)) {
            // 切割字符串，获取商品id，跟该商品的数量的字符串如：商品id_商品数量
            for (String productId : params.split(",")) {
                productIds.add(Long.valueOf(productId.split("_")[0]));
                productCountMap.put(Long.valueOf(productId.split("_")[0]), Integer.valueOf(productId.split("_")[1]));
            }
        }
        List<CartItemDTO> cartList = (List<CartItemDTO>) session.getAttribute("cart");
        // 非空判断
        if (cartList == null) {
            cartList = Lists.newArrayList();
        }
        // 遍历购物列表
        for (CartItemDTO cartItem : cartList) {
            cartItem.setQuantity(productCountMap.get(cartItem.getProduct().getId()));
        }
        session.setAttribute("cart", cartList);
        List<OrderItemDTO> result = new ArrayList<OrderItemDTO>();
        // 遍历商品id的集合讲商品详添加到订单项数据库中
        for (Long productId : productIds) {
            OrderItemDTO orderItem = new OrderItemDTO();
            orderItem.setProduct(productService.getProduct(productId));
            orderItem.setProductQuantity(productCountMap.get(productId));
            orderItem.setTotalPrice(orderItem.getProduct().getPrice() * orderItem.getProductQuantity());
            result.add(orderItem);
        }

        return result;
    }

    /**
     * 获取订单所有项
     */
    public List<OrderItemDTO> getOrderItemsByOrder(Long orderId) {
        return orderItemDao.getOrderItemsByOrder(orderId);
    }

    /**
     * 保存订单项
     */
    public void saveOrderItem(OrderItemDTO orderItem) {
        orderItemDao.saveOrderItem(orderItem);
    }

}
