/*****************************************************************************
 * Copyright (c) 2015, www.qingshixun.com
 *
 * All rights reserved
 *
 *****************************************************************************/
package com.qingshixun.project.eshop.module.config.listener;

import com.google.common.collect.Maps;
import com.qingshixun.project.eshop.dto.ConfigDTO;
import com.qingshixun.project.eshop.module.config.service.ConfigServiceImpl;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;
import java.util.List;
import java.util.Map;

/**
 * 启动tomcat时加载配置文件静态资源
 *
 * @author QingShiXun
 * @version 1.0
 */
@Configuration
public class LoadConfigListener implements InitializingBean, ServletContextAware {

    public static Map<String, String> configMap = Maps.newHashMap();

    @Autowired
    private ConfigServiceImpl configService;

    /**
     * 获取对应code的value值
     */
    public static String getConfig(String code) {
        return configMap.get(code);
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        List<ConfigDTO> configs = configService.getConfigs();

        for (ConfigDTO config : configs) {
            configMap.put(config.getCode(), config.getValue());
        }
    }
}
