package com.qingshixun.project.eshop.module.product.dao;

import com.qingshixun.project.eshop.dto.ProductCategoryDTO;
import com.qingshixun.project.eshop.web.MyBatisRepository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisRepository
public interface ProductCategoryDaoMyBatis {

    /**
     * 根据父亲节点ID获取商品分类列表
     *
     * @param parentId 试题ID集合
     *
     * @return 商品分类列表
     */
    List<ProductCategoryDTO> getProductCategoriesByParent(@Param("parentId") Long parentId);

}
