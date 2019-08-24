package com.qingshixun.project.eshop.module.order.service;

import com.google.common.collect.Lists;
import com.qingshixun.project.eshop.dto.*;
import com.qingshixun.project.eshop.module.order.dao.OrderDaoMyBatis;
import com.qingshixun.project.eshop.module.product.service.ProductServiceImpl;
import com.qingshixun.project.eshop.module.receiver.service.ReceiverServiceImpl;
import com.qingshixun.project.eshop.web.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class OrderServiceImpl extends BaseService {

    @Autowired
    private OrderDaoMyBatis orderDao;

    @Autowired
    private OrderItemServiceImpl orderItemService;

    @Autowired
    private ReceiverServiceImpl receiverService;

    @Autowired
    private ProductServiceImpl productService;

    /**
     * 获取订单详情
     */
    public OrderDTO getOrder(Long orderId) {
        OrderDTO order = orderDao.getOrder(orderId);

        order.setOrderItems(orderItemService.getOrderItemsByOrder(orderId));

        return order;
    }

    /**
     * 获取用户订单列表
     */
    public List<OrderDTO> getOrdersByMember(Long memberId) {
        List<OrderDTO> orders = orderDao.getOrdersByMember(memberId);

        for (OrderDTO order : orders) {
            List<OrderItemDTO> orderItems = orderItemService.getOrderItemsByOrder(order.getId());

            order.setOrderItems(orderItems);
        }

        return orders;
    }

    public Long commitOrder(String params, MemberDTO member, Long receiverId, HttpSession session) {
        List<Long> productIds = Lists.newArrayList();
        Map<Long, Integer> productCountMap = new HashMap<Long, Integer>();
        // 切割字符串，获取商品id，跟该商品的数量的字符串如：商品id_商品数量
        for (String productId : params.split(",")) {
            productIds.add(Long.valueOf(productId.split("_")[0]));
            productCountMap.put(Long.valueOf(productId.split("_")[0]), Integer.valueOf(productId.split("_")[1]));
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
        OrderDTO order = new OrderDTO();
        order.setStatus(new OrderStatus("ORDS_UnPay"));
        order.setOrderChannel(new OrderChannel("ORDC_Web"));
        order.setReceiver(receiverService.getReceiver(receiverId));
        order.setOrderNum(getOrderNum(member.getId()));
        order.setMember(member);
        orderDao.saveOrUpdateOrder(order);
        // 遍历商品id的集合讲商品详添加到订单项数据库中
        for (Long productId : productIds) {
            OrderItemDTO orderItem = new OrderItemDTO();
            orderItem.setProduct(productService.getProduct(productId));
            orderItem.setProductQuantity(productCountMap.get(productId));
            orderItem.setTotalPrice(orderItem.getProduct().getPrice() * orderItem.getProductQuantity());
            orderItem.setOrder(orderDao.getOrder(order.getId()));
            orderItem.setStatus(new OrderItemStatus("ORIS_UnGrant"));
            orderItemService.saveOrderItem(orderItem);
        }

        saveOrder(order.getId(), member);
        return order.getId();
    }

    /**
     * 保存订单
     *
     * @param orderId 订单id
     * @param member  登录用户
     *
     * @return
     */
    public OrderDTO saveOrder(Long orderId, MemberDTO member) {
        OrderDTO order = orderDao.getOrder(orderId);
        order.setMember(member);
        Double productTotalPrice = 0.0;
        Integer productTotalQuantity = 0;
        List<OrderItemDTO> orderItems = orderItemService.getOrderItemsByOrder(orderId);
        for (OrderItemDTO orderItem : orderItems) {
            productTotalPrice += orderItem.getTotalPrice();
            productTotalQuantity += orderItem.getProductQuantity();
        }
        Double totalAmount = productTotalPrice;
        if (member.getMemberLevel() != null) {
            totalAmount = productTotalPrice * member.getMemberLevel().getDiscount();
        }
        BigDecimal bg = new BigDecimal(totalAmount);
        totalAmount = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        order.setProductTotalPrice(productTotalPrice);
        order.setProductTotalQuantity(productTotalQuantity);
        order.setTotalAmount(totalAmount);
        orderDao.saveOrUpdateOrder(order);
        return order;

    }

    /**
     * 更新支付状态
     */
    public void updateOrderStatus(Long orderId, String statusCode) {
        orderDao.updateOrderStatus(orderId, statusCode);
    }

    /**
     * 获取订单编号
     */
    public String getOrderNum(Long memberId) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhmmssSSS");
        String time = format.format(calendar.getTime());
        return time + "1" + String.valueOf(memberId);
    }

}
