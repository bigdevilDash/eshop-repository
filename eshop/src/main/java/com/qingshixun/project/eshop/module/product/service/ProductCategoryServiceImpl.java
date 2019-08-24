package com.qingshixun.project.eshop.module.product.service;

import com.qingshixun.project.eshop.dto.ProductCategoryDTO;
import com.qingshixun.project.eshop.module.product.dao.ProductCategoryDaoMyBatis;
import com.qingshixun.project.eshop.web.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCategoryServiceImpl extends BaseService {

    @Autowired
    private ProductCategoryDaoMyBatis productCategoryDao;

    /**
     * 获取所有商品分类
     */
    public List<ProductCategoryDTO> getProductCategories() {
        // 先获取父级
        List<ProductCategoryDTO> productCategories = getProductCategoriesByParent(null);

        // 再轮询获取子级
        for (ProductCategoryDTO productCategory : productCategories) {
            List<ProductCategoryDTO> children = getProductCategoriesByParent(productCategory.getId());

            // 再轮询获取子级的子级
            for (ProductCategoryDTO child : children) {
                child.setChildren(getProductCategoriesByParent(child.getId()));
            }

            productCategory.setChildren(children);
        }

        return productCategories;
    }

    /**
     * 根据父亲节点ID获取商品分类列表
     *
     * @param parentId 试题ID集合
     *
     * @return 商品分类列表
     */
    public List<ProductCategoryDTO> getProductCategoriesByParent(Long parentId) {
        return productCategoryDao.getProductCategoriesByParent(parentId);
    }

}
