package com.qingshixun.project.eshop.module.cart.dao;

import com.qingshixun.project.eshop.dto.CartItemDTO;
import com.qingshixun.project.eshop.web.MyBatisRepository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisRepository
public interface CartItemDaoMyBatis {

    /**
     * 通过会员Id获取购物项列表数据
     */
    List<CartItemDTO> getCartItemsByMember(@Param("memberId") Long memberId);

    /**
     * 通过会员Id商品id获取购物项数据
     */
    CartItemDTO getCartItem(@Param("memberId") Long memberId, @Param("productId") Long productId);

    /**
     * 保存购物车
     */
    void saveCartItem(CartItemDTO cartItem);

    /**
     * 更新购物车商品数量
     */
    void updateCartItemQuantity(CartItemDTO cartItem);

    /**
     * 删除购物车
     */
    void deleteCartItem(@Param("cartItemId") Long cartItemId);

}
