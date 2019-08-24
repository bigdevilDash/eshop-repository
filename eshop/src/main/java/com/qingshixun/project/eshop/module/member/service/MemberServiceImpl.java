/*****************************************************************************
 * Copyright (c) 2015, www.qingshixun.com
 *
 * All rights reserved
 *
 *****************************************************************************/

package com.qingshixun.project.eshop.module.member.service;

import com.qingshixun.project.eshop.core.Constants;
import com.qingshixun.project.eshop.dto.MemberDTO;
import com.qingshixun.project.eshop.exception.RepetitionException;
import com.qingshixun.project.eshop.module.member.dao.MemberDaoMyBatis;
import com.qingshixun.project.eshop.util.CryptoUtils;
import com.qingshixun.project.eshop.util.EmailService;
import com.qingshixun.project.eshop.util.GenerateLinkUtils;
import com.qingshixun.project.eshop.util.Md5Util;
import com.qingshixun.project.eshop.web.BaseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 会员处理 Service 类
 *
 * @author QingShiXun
 * @version 1.0
 */
@Service
@Transactional
public class MemberServiceImpl extends BaseService {

    // 会员处理Dao
    @Autowired
    private MemberDaoMyBatis memberDao;

    // 发送邮件
    @Autowired
    private EmailService emailService;

    @Autowired
    private MemberLevelServiceImpl memberLevelService;

    /**
     * 根据id获取会员信息
     *
     * @param memberId 试题Id
     *
     * @return 会员实体
     */
    public MemberDTO getMember(Long memberId) {
        return memberDao.getMemberById(memberId);
    }

    /**
     * 保存会员信息并根据 path 判断是否发送邮件
     *
     * @param member 会员
     * @param path   项目根路径
     *
     * @throws Exception
     */
    public void saveMember(MemberDTO member, String path) throws Exception {
        MemberDTO dbMember = this.getMember(member.getUserName());
        MemberDTO emailMember = this.getMemberByEmail(member.getEmail());

        // 如果我为空表示的是新增，则要进行密码加密
        if (member.getId() == 0L) {
            member.setMemberLevel(memberLevelService.getMemberLevelByName("普通会员"));
            member.setPassword(Md5Util.encode(member.getPassword()));
            // 验证唯一性
            if (dbMember != null) {
                throw new RepetitionException("用户名已经存在");
            }
            if (emailMember != null) {
                throw new RepetitionException("邮箱被注册已经存在");
            }
        }
        memberDao.saveMember(member);

        // 判断 path 是否为空，不为空则发送注册邮件
        if (StringUtils.isNotEmpty(path)) {
            sendMail(member, path);
        }
    }

    /**
     * 更新用户默认收货地址
     */
    public void updateMemberDefaultReceiverId(Long memberId, Long receiverId) {
        memberDao.updateMemberDefaultReceiverId(memberId, receiverId);
    }

    /**
     * 激活账户
     *
     * @param memberId     会员 id
     * @param validateCode 验证码
     */
    public void activeMember(String memberId, String validateCode) throws Exception {
        Long id = Long.valueOf(CryptoUtils.aesDecryptUrl(memberId, Constants.KEY));
        MemberDTO dbMember = getMember(id);
        if (GenerateLinkUtils.verifyCheckcode(dbMember, validateCode)) {
            memberDao.updateMemberStatus(id, "0");
        }
    }

    /**
     * 发送用户激活邮件
     *
     * @param member 会员实体
     * @param path   url路径
     *
     * @throws Exception
     */
    public void sendMail(MemberDTO member, String path) throws Exception {
        String subject = "电子商务 用户激活";
        String html = "尊敬的用户 ： <br/>&nbsp;&nbsp;&nbsp;您好！:<br/>&nbsp;&nbsp;&nbsp;您也可以点击如下链接激活账号:<br/>&nbsp;&nbsp;&nbsp;<a href='" + GenerateLinkUtils.generateActivateLink(member, path) + "'>点击激活帐户</a>";

        emailService.sendMail(member.getEmail(), subject, html);
    }

    /**
     * 删除会员信息
     *
     * @param memberId 试题Id
     */
    public void deleteMember(Long memberId) {
        memberDao.deleteMember(memberId);

    }

    /**
     * 根据会员名称获取会员信息
     *
     * @param userName 会员名称
     *
     * @return 会员实体
     */
    public MemberDTO getMember(String userName) {
        return memberDao.getMember(userName);
    }

    /**
     * 根据会员邮箱获取会员信息
     *
     * @param email 会员邮箱
     *
     * @return 会员实体
     */
    public MemberDTO getMemberByEmail(String email) {
        return memberDao.getMemberByEmail(email);
    }

}
