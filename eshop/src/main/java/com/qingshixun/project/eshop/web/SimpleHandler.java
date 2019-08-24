/*****************************************************************************
 * Copyright (c) 2017, www.qingshixun.com
 *
 * All rights reserved
 *
 *****************************************************************************/
package com.qingshixun.project.eshop.web;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * 自定义抽象类用于简单操作的公共方法
 * 
 * @author QingShiXun
 * 
 * @version 1.0
 */
public abstract class SimpleHandler {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    // 操作
    private String action;

    // 构造函数
    public SimpleHandler(HttpServletRequest request) {
        this.action = request.getServletPath();
    }

    // 简单操作抽离封装
    public ResponseData handle() {
        ResponseData responseData = new ResponseData();
        try {
            doHandle(responseData);
        } catch (Exception e) {
            logger.error(this.action, ExceptionUtils.getStackTrace(e));
            responseData.setError(e.getMessage());
        }
        return responseData;
    }

    // 简单操作抽离封装
    protected abstract void doHandle(ResponseData responseData) throws Exception;
}
