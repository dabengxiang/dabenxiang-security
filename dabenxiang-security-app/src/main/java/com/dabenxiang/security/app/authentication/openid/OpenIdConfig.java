package com.dabenxiang.security.app.authentication.openid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.security.SocialUserDetailsService;

/**
 * Date:2018/9/22
 * Author:gyc
 * Desc:
 */

@Configuration
public class OpenIdConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain,HttpSecurity> {


    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private SocialUserDetailsService socialUserDetailsService;

    @Autowired
    private UsersConnectionRepository usersConnectionRepository;


    @Override
    public void configure(HttpSecurity http) throws Exception {

        OpenIdAuthenticationFilter openIdAuthenticationFilter = new OpenIdAuthenticationFilter();

        AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);

        openIdAuthenticationFilter.setAuthenticationManager(authenticationManager);

        openIdAuthenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        openIdAuthenticationFilter.setAuthenticationFailureHandler(authenticationFailureHandler);


        OpenIdAuthenticationProvider openIdProvider = new OpenIdAuthenticationProvider();

        openIdProvider.setSocialUserDetailsService(socialUserDetailsService);

        openIdProvider.setUsersConnectionRepository(usersConnectionRepository);

        http.authenticationProvider(openIdProvider);
        http.addFilterAfter(openIdAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }



}
