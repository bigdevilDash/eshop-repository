/*****************************************************************************
 * Copyright (c) 2015, www.qingshixun.com
 *
 * All rights reserved
 *
 *****************************************************************************/

package com.qingshixun.project.eshop.module.config.service;

import com.qingshixun.project.eshop.dto.ConfigDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 配置项 Service 类
 *
 * @author QingShiXun
 * @version 1.0
 */
@Service
public class ConfigServiceImpl {

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    /**
     * 获取所有配置项列表数据
     *
     * @return
     */
    public List<ConfigDTO> getConfigs() {
        return jdbcTemplate.query("select code, value from qsx_config", (RowMapper) (rs, rowNum) -> {
            ConfigDTO config = new ConfigDTO();
            config.setCode(rs.getString("code"));
            config.setValue(rs.getString("value"));
            return config;
        });
    }

}
