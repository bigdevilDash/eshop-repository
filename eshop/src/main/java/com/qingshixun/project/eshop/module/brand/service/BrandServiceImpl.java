package com.qingshixun.project.eshop.module.brand.service;

import com.qingshixun.project.eshop.dto.BrandDTO;
import com.qingshixun.project.eshop.module.brand.dao.BrandDaoMyBatis;
import com.qingshixun.project.eshop.web.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl extends BaseService {

    @Autowired
    private BrandDaoMyBatis brandDao;

    /**
     * 获取指定类型的品牌列表
     */
    public List<BrandDTO> getBrandsByCategory(Long categoryId) {
        return brandDao.getBrandsByCategory(categoryId);
    }

}
