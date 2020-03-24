package com.yx.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author ZhouBing
 * @version 1.0
 * @date 2020/3/23 11:06
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {


        http.formLogin() // 表单登录
                .and()
                .authorizeRequests() // 授权配置
                .antMatchers("/admin/hello").permitAll()
                .antMatchers("/doc.html","/swagger-resources/**","/webjars/**","/v2/**").permitAll()
                .antMatchers("/admin/login", "/admin/register")// 对登录注册要允许匿名访问
                .permitAll()
                .anyRequest()  // 所有请求
                .authenticated() // 都需要认证
                .and().csrf().disable();

    }
}
