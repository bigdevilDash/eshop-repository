package com.qingshixun.project.eshop.module.product.dao;

import com.qingshixun.project.eshop.dto.ProductTypeAttributeDTO;
import com.qingshixun.project.eshop.web.MyBatisRepository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisRepository
public interface ProductTypeAttributeDaoMyBatis {

    /**
     * 获取指定商品类型的属性列表
     */
    List<ProductTypeAttributeDTO> getProductTypeAttributesByProductType(@Param("productTypeId") Long productTypeId);

}
