/*****************************************************************************
 * Copyright (c) 2015, www.qingshixun.com
 *
 * All rights reserved
 *
 *****************************************************************************/
package com.qingshixun.project.eshop.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间处理util 类
 *
 * @author QingShiXun
 *
 * @version 1.0
 */
public class DateUtils {
    // 时间精确到秒的格式
    public final static String DEFAULT_DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 将时间以字符串的形式显示
     */
    public static final String timeToString(Date date) {
        return dateToString(date, DEFAULT_DATETIME_PATTERN);
    }

    /**
     * 以"yyyy-MM-dd HH:mm:ss"形式展现时间
     */
    public static final String dateToString(Date date, String pattern) {
        return new SimpleDateFormat(pattern).format(date);
    }

}
