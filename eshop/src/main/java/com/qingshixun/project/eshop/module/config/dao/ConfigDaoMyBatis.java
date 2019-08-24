/*****************************************************************************
 * Copyright (c) 2015, www.qingshixun.com
 *
 * All rights reserved
 *
 *****************************************************************************/

package com.qingshixun.project.eshop.module.config.dao;

import com.qingshixun.project.eshop.dto.ConfigDTO;
import com.qingshixun.project.eshop.web.MyBatisRepository;

import java.util.List;

@MyBatisRepository
public interface ConfigDaoMyBatis {

    /**
     * 获取所有配置
     */
    List<ConfigDTO> getConfigs();

}