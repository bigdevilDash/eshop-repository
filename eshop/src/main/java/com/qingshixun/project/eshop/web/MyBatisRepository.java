/*****************************************************************************
 * Copyright (c) 2017, www.qingshixun.com
 *
 * All rights reserved
 *
 *****************************************************************************/
package com.qingshixun.project.eshop.web;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * 自定义的注解，方便org.mybatis.spring.mapper.MapperScannerConfigurer的扫描。
 * MyBatis是一个支持普通SQL查询,存储过程和高级映射的优秀持久层框架。 repository一般作为持久层的Dao的命名
 * 
 * @author QingShiXun
 * 
 * @version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Component
@Mapper
public @interface MyBatisRepository {

}
