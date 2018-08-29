package com.dabenxiang.security.core.authentication;

import com.dabenxiang.security.core.properties.SecurityContants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServlet;

/**
 * Date:2018/8/28
 * Author: yc.guo the one whom in nengxun
 * Desc:
 */
public  class AbstractChannelSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationSuccessHandler gycAuthenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler gycAuthenticationFailureHandler;


    /**
     * 设置基本的设置
     * @param http
     * @throws Exception
     */
    protected void defaultApply(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage(SecurityContants.DEFAULT_UNAUTHENTICATION_URL)
                .loginProcessingUrl(SecurityContants.DEFAULT_LOGIN_PROCESSING_URL_FORM)
                .successHandler(gycAuthenticationSuccessHandler)
                .failureHandler(gycAuthenticationFailureHandler);
    }




}
