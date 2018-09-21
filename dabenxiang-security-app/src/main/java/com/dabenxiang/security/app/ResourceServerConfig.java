package com.dabenxiang.security.app;

import com.dabenxiang.security.core.authentication.mobile.SmsCodeConfig;
import com.dabenxiang.security.core.properties.SecurityConstants;
import com.dabenxiang.security.core.properties.SecurityProperties;
import com.dabenxiang.security.core.social.qq.SocialConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfiguration;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.jws.Oneway;

/**
 * Date:2018/9/11
 * Author: yc.guo the one whom in nengxun
 * Desc:
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private AuthenticationSuccessHandler successHandler;

    @Autowired
    private AuthenticationFailureHandler failureHandler;

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private SmsCodeConfig smsCodeConfig;

    @Autowired
    private SpringSocialConfigurer springSocialConfigurer;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.formLogin().successHandler(successHandler)
                .failureHandler(failureHandler)
                .loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
                .loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)
                .and()
                .apply(smsCodeConfig)
                .and().apply(springSocialConfigurer)
                .and()
                .authorizeRequests()
                .antMatchers(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
                        SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM,
                        "/code/*",
                        "/user/regist",
                        securityProperties.getSocialProperties().getQq().getSigunUpUrl(),
                        securityProperties.getBrowser().getSession().getSESSION_INVALID_URL(),
                        securityProperties.getBrowser().getLogOutUrl(),
                        securityProperties.getBrowser().getLoginPage()).permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable();

    }
}
