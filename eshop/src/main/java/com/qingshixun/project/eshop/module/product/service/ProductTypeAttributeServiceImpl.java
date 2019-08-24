package com.qingshixun.project.eshop.module.product.service;

import com.qingshixun.project.eshop.dto.ProductTypeAttributeDTO;
import com.qingshixun.project.eshop.module.product.dao.ProductTypeAttributeDaoMyBatis;
import com.qingshixun.project.eshop.web.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductTypeAttributeServiceImpl extends BaseService {

    @Autowired
    private ProductTypeAttributeDaoMyBatis productTypeAttributeDao;

    /**
     * 获取指定商品类型的属性列表
     */
    public List<ProductTypeAttributeDTO> getProductTypeAttributesByProductType(Long productTypeId) {
        return productTypeAttributeDao.getProductTypeAttributesByProductType(productTypeId);
    }

}
