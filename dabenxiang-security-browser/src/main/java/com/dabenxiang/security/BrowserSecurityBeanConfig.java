package com.dabenxiang.security;

import com.dabenxiang.security.authentication.MyLogoutSuccessHandler;
import com.dabenxiang.security.core.properties.SecurityProperties;
import com.dabenxiang.security.session.MyExpiredSessionStrategy;
import com.dabenxiang.security.session.MyInvalidSessionStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

/**
 * Date:2018/9/8
 * Author:gyc
 * Desc:
 */
@Configuration
public class BrowserSecurityBeanConfig {

    @Autowired
    private SecurityProperties securityProperties;

    @Bean
    @ConditionalOnMissingBean(InvalidSessionStrategy.class)
    public MyInvalidSessionStrategy myInvalidSessionStrategyInstance(){
        return new MyInvalidSessionStrategy(securityProperties.getBrowser().getSession().getSESSION_INVALID_URL());
    }


    @Bean
    @ConditionalOnMissingBean(SessionInformationExpiredStrategy.class)
    public MyExpiredSessionStrategy myExpiredSessionStrategyInstance(){
        return new MyExpiredSessionStrategy(securityProperties.getBrowser().getSession().getSESSION_INVALID_URL());
    }


    @Bean
    @ConditionalOnMissingBean(LogoutSuccessHandler.class)
    public LogoutSuccessHandler LogoutSuccessHandlerInstance(){
        return new MyLogoutSuccessHandler(securityProperties.getBrowser().getLogOutUrl());
    }
}
