package com.dabenxiang.security.core.authentication.mobile;

import com.dabenxiang.security.core.authentication.mobile.SmsAuthenticationFilter;
import com.dabenxiang.security.core.authentication.mobile.SmsAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.method.P;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
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
    private UserDetailsService userDetailsService;

    //这里不能直接这样注入，因为这样注入的话，默认的provider就会用他了，之前的账号密码provider就没用了
//    @Autowired
//    private SmsAuthenticationProvider provider;


    @Override
    public void configure(HttpSecurity http) throws Exception {
        AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
        SmsAuthenticationFilter smsAuthenticationFilter=new SmsAuthenticationFilter();
        smsAuthenticationFilter.setAuthenticationManager(authenticationManager);
        smsAuthenticationFilter.setAuthenticationSuccessHandler(gycAuthenticationSuccessHandler);
        smsAuthenticationFilter.setAuthenticationFailureHandler(gycAuthenticationFailureHandler);

        SmsAuthenticationProvider provider = new SmsAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);

        http.authenticationProvider(provider)
                .addFilterAfter(smsAuthenticationFilter,UsernamePasswordAuthenticationFilter.class);

    }
}
