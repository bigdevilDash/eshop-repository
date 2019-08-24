package com.qingshixun.project.eshop.module.product.service;

import com.qingshixun.project.eshop.dto.ProductTypeValueDTO;
import com.qingshixun.project.eshop.module.product.dao.ProductTypeValueDaoMyBatis;
import com.qingshixun.project.eshop.web.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductTypeValueServiceImpl extends BaseService {

    @Autowired
    private ProductTypeValueDaoMyBatis productTypeValueDao;

    /**
     * 获取指定商品的属性列表
     */
    public List<ProductTypeValueDTO> getProductTypeValuesByProduct(Long productId) {
        return productTypeValueDao.getProductTypeValuesByProduct(productId);
    }

}
