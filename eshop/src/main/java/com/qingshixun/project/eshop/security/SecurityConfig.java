package com.qingshixun.project.eshop.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SimpleLoginSuccessHandler simpleLoginSuccessHandler;

    @Bean
    public UserDetailsService customUserDetailsService() {
        return new CustomUserDetailsService();
    }

    @Bean
    public Md5PasswordEncoder passwordEncoder() {
        return new Md5PasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService()).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
          .authorizeRequests()
          // 所有用户均可访问的资源
          .antMatchers("/plugins/**", "/css/**", "/js/**", "/images/**", "/product/**", "/front/**", "/").permitAll()
          // 拥有ROLE_USER权限才能访问的资源
          .antMatchers("/front/order/main/*", "/front/order/list", "/front/order/settlement").hasRole("USER")
          // 任何尚未匹配的URL只需要验证用户即可访问
          .anyRequest().authenticated()
          .and()
          .formLogin()
          // 自定义登录成功处理器
          .successHandler(simpleLoginSuccessHandler)
          .loginPage("/front/login").failureUrl("/front/login?error=1");

        http.logout().logoutUrl("/front/logout").logoutSuccessUrl("/front/index");
    }
}