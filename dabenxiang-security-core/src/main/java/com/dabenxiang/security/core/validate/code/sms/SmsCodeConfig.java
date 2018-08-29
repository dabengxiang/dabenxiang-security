package com.dabenxiang.security.core.validate.code.sms;

import com.dabenxiang.security.core.authentication.mobile.SmsAuthenticationFilter;
import com.dabenxiang.security.core.authentication.mobile.SmsAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Date:2018/8/29
 * Author: yc.guo the one whom in nengxun
 * Desc:
 */

@Configuration
public class SmsCodeConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain,HttpSecurity> {


    @Autowired
    private SmsAuthenticationFilter smsAuthenticationFilter;

    @Autowired
    private AuthenticationSuccessHandler gycAuthenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler gycAuthenticationFailureHandler;

    @Autowired
    private SmsAuthenticationProvider provider;


    @Override
    public void configure(HttpSecurity http) throws Exception {
        AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
        smsAuthenticationFilter.setAuthenticationManager(authenticationManager);
        smsAuthenticationFilter.setAuthenticationSuccessHandler(gycAuthenticationSuccessHandler);
        smsAuthenticationFilter.setAuthenticationFailureHandler(gycAuthenticationFailureHandler);
        HttpSecurity httpSecurity = http.authenticationProvider(provider);
        http.authenticationProvider(provider).addFilterAfter(smsAuthenticationFilter,UsernamePasswordAuthenticationFilter.class);

    }
}
