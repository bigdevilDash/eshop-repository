package com.qingshixun.project.eshop.module.member.dao;

import com.qingshixun.project.eshop.dto.MemberLevelDTO;
import com.qingshixun.project.eshop.web.MyBatisRepository;
import org.apache.ibatis.annotations.Param;

@MyBatisRepository
public interface MemberLevelDaoMyBatis {

    /**
     * 获取对应名称的用户级别
     */
    MemberLevelDTO getMemberLevelByName(@Param("levelName") String levelName);

}
