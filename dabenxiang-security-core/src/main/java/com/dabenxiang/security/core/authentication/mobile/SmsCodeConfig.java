package com.dabenxiang.security.core.authentication.mobile;

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
import org.springframework.stereotype.Component;

/**
 * Date:2018/8/29
 * Author: yc.guo the one whom in nengxun
 * Desc:
 */

@Component
public class SmsCodeConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain,HttpSecurity> {

    @Autowired
    private AuthenticationSuccessHandler gycAuthenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler gycAuthenticationFailureHandler;

    @Autowired
    private SmsAuthenticationProvider provider;


    @Override
    public void configure(HttpSecurity http) throws Exception {
        AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
        SmsAuthenticationFilter smsAuthenticationFilter=new SmsAuthenticationFilter();
        smsAuthenticationFilter.setAuthenticationManager(authenticationManager);
        smsAuthenticationFilter.setAuthenticationSuccessHandler(gycAuthenticationSuccessHandler);
        smsAuthenticationFilter.setAuthenticationFailureHandler(gycAuthenticationFailureHandler);

        http.authenticationProvider(provider)
                .addFilterAfter(smsAuthenticationFilter,UsernamePasswordAuthenticationFilter.class);

    }
}
