package com.qingshixun.project.eshop.module.product.dao;

import com.qingshixun.project.eshop.dto.ProductTypeValueDTO;
import com.qingshixun.project.eshop.web.MyBatisRepository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisRepository
public interface ProductTypeValueDaoMyBatis {

    /**
     * 获取指定商品的属性列表
     */
    List<ProductTypeValueDTO> getProductTypeValuesByProduct(@Param("productId") Long productId);

}
