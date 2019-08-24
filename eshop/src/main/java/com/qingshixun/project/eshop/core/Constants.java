/*****************************************************************************
 * Copyright (c) 2015, www.qingshixun.com
 *
 * All rights reserved
 *
 *****************************************************************************/
package com.qingshixun.project.eshop.core;


import com.qingshixun.project.eshop.module.config.listener.LoadConfigListener;

/**
 * 静态变量类
 * 
 * @author QingShiXun
 * 
 * @version 1.0
 */
public class Constants {

    // 获取商品图片路径
    public static final String PRODUCT_IMAGE_PATH = LoadConfigListener.getConfig("image.get.path");;
    // 分页数
    public static Integer PAGE_SIZE = 8;
    // 加密key
    public static final String KEY = "3ab570d768290fa3b08e6f5e4c8d38fc";

}
