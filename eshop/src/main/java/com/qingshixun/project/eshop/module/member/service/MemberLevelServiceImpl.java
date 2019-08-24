package com.qingshixun.project.eshop.module.member.service;

import com.qingshixun.project.eshop.dto.MemberLevelDTO;
import com.qingshixun.project.eshop.module.member.dao.MemberLevelDaoMyBatis;
import com.qingshixun.project.eshop.web.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberLevelServiceImpl extends BaseService {

    @Autowired
    private MemberLevelDaoMyBatis memberLevelDao;

    /**
     * 获取对应名称的用户级别
     */
    public MemberLevelDTO getMemberLevelByName(String levelName) {
        return memberLevelDao.getMemberLevelByName(levelName);
    }

}
