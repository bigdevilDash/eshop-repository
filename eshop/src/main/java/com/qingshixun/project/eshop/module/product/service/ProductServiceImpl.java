package com.qingshixun.project.eshop.module.product.service;

import com.qingshixun.project.eshop.dto.ProductDTO;
import com.qingshixun.project.eshop.module.product.dao.ProductDaoMyBatis;
import com.qingshixun.project.eshop.web.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl extends BaseService {

    @Autowired
    private ProductDaoMyBatis productDao;

    @Autowired
    private ProductTypeValueServiceImpl productTypeValueService;

    /**
     * 获取热门商品列表
     */
    public List<ProductDTO> getHotProducts() {
        return productDao.getHotProducts();
    }

    /**
     * 获取新商品列表
     */
    public List<ProductDTO> getNewProducts() {
        return productDao.getNewProducts();
    }

    /**
     * 获取精品商品列表
     */
    public List<ProductDTO> getBestProducts() {
        return productDao.getBestProducts();
    }

    /**
     * 获取指定类型商品列表
     */
    public List<ProductDTO> getProductsByCategory(Long categoryId) {
        return productDao.getProductsByCategory(categoryId);
    }

    /**
     * 获取同价位商品列表
     */
    public List<ProductDTO> getProductsByPrice(Double price) {
        return productDao.getProductsByPrice(price);
    }

    /**
     * 获取产品详情
     */
    public ProductDTO getProduct(Long productId) {
        ProductDTO product = productDao.getProduct(productId);

        // 获取商品属性列表
        product.setValues(productTypeValueService.getProductTypeValuesByProduct(productId));

        return product;
    }

}
