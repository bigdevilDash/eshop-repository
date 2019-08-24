/*****************************************************************************
 * Copyright (c) 2015, www.qingshixun.com
 *
 * All rights reserved
 *
 *****************************************************************************/

package com.qingshixun.project.eshop.web;

import com.qingshixun.project.eshop.dto.MemberDTO;
import com.qingshixun.project.eshop.module.member.service.MemberServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * Controller 基类
 * 
 * @author QingShiXun
 * 
 * @version 1.0
 */
public abstract class BaseController {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    protected HttpServletRequest request;

    // 注入会员service
    @Autowired
    private MemberServiceImpl memberService;

    /**
     * 获取当前登录用户信息
     *
     * @return
     */
    protected MemberDTO getCurrentUser() {
        MemberDTO dbMember = null;
        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            dbMember = memberService.getMember(userDetails.getUsername());
        } catch (Exception e) {
            return null;
        }

        return dbMember;
    }

    /**
     * 获取 session 中的数据
     * @param key 数据 的 key
     * @return 指定的变量
     */
    protected Object getSessionAttribute(String key) {
        return getSession().getAttribute(key);
    }

    /**
     * 向 session 中放入数据
     * @param key 数据的 key
     * @param obj 数据
     */
    protected void setSessionAttribute(String key, Object obj) {
        getSession().setAttribute(key, obj);
    }

    /**
     * 得到当前 session
     * @return 当前会话
     */
    protected HttpSession getSession() {
        return this.request.getSession();
    }

    /**
     * 获取webapp路径的地址
     * 
     * @return
     * @throws SocketException
     */
    protected String getRealPath() throws SocketException {
        String contextPath = request.getContextPath();
        String basePath = request.getScheme() + "://" + getRealIp() + ":" + request.getServerPort() + contextPath;
        return basePath;
    }

    /**
     * @return 本机IP
     * @throws SocketException
     */
    public String getRealIp() throws SocketException {
        String localip = null;// 本地IP，如果没有配置外网IP则返回它
        String netip = null;// 外网IP

        Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
        InetAddress ip = null;
        boolean finded = false;// 是否找到外网IP
        while (netInterfaces.hasMoreElements() && !finded) {
            NetworkInterface ni = netInterfaces.nextElement();
            Enumeration<InetAddress> address = ni.getInetAddresses();
            while (address.hasMoreElements()) {
                ip = address.nextElement();
                if (!ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1) {// 外网IP
                    netip = ip.getHostAddress();
                    finded = true;
                    break;
                } else if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1) {// 内网IP
                    localip = ip.getHostAddress();
                }
            }
        }

        if (netip != null && !"".equals(netip)) {
            return netip;
        } else {
            return localip;
        }
    }

}
