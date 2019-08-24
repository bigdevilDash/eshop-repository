package com.qingshixun.project.eshop.module.product.dao;

import com.qingshixun.project.eshop.dto.ProductDTO;
import com.qingshixun.project.eshop.web.MyBatisRepository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisRepository
public interface ProductDaoMyBatis {

    /**
     * 获取热门商品列表
     */
    List<ProductDTO> getHotProducts();

    /**
     * 获取新商品列表
     */
    List<ProductDTO> getNewProducts();

    /**
     * 获取精品商品列表
     */
    List<ProductDTO> getBestProducts();

    /**
     * 获取指定类型商品列表
     */
    List<ProductDTO> getProductsByCategory(@Param("categoryId") Long categoryId);

    /**
     * 获取同价位商品列表
     */
    List<ProductDTO> getProductsByPrice(@Param("price") Double price);

    /**
     * 获取产品详情
     */
    ProductDTO getProduct(@Param("productId") Long productId);

}
