package com.qingshixun.project.eshop.module.brand.dao;

import com.qingshixun.project.eshop.dto.BrandDTO;
import com.qingshixun.project.eshop.web.MyBatisRepository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisRepository
public interface BrandDaoMyBatis {

    /**
     * 获取指定类型的品牌列表
     */
    List<BrandDTO> getBrandsByCategory(@Param("categoryId") Long categoryId);

}
