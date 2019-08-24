package com.qingshixun.project.eshop.security;

import com.qingshixun.project.eshop.dto.MemberDTO;
import com.qingshixun.project.eshop.module.member.service.MemberServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * 一个自定义的service用来和数据库进行操作. 即以后我们要通过数据库保存权限.则需要我们继承UserDetailsService
 *
 * @author liukai
 */
public class CustomUserDetailsService implements UserDetailsService {

    // 注入会员service
    @Autowired
    private MemberServiceImpl memberServiceImpl;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
        UserDetails user = null;
        MemberDTO member = null;
        try {
            // 搜索数据库以匹配用户登录名.
            // 我们可以通过dao使用JDBC来访问数据库
            member = memberServiceImpl.getMember(username);
            user = new User(member.getUserName(), member.getPassword().toLowerCase(), getAuthorities());
        } catch (Exception e) {
            e.printStackTrace();
            throw new UsernameNotFoundException("Error in retrieving user");
        }
        if (member.getMemberStatus() == 1) {
            throw new DisabledException("用户未激活,请激活用户");
        }
        return user;
    }

    /**
     * 获得访问角色权限
     *
     * @return
     */
    public Collection<GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(2);

        // 所有的用户默认拥有ROLE_USER权限
        authList.add(new SimpleGrantedAuthority("ROLE_USER"));

        return authList;
    }
}
