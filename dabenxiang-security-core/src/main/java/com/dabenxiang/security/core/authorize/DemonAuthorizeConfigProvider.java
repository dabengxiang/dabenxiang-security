package com.dabenxiang.security.core.authorize;

import com.dabenxiang.security.core.properties.SecurityConstants;
import com.dabenxiang.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

/**
 * Date:2018/9/30
 * Author: yc.guo the one whom in nengxun
 * Desc:
 */

@Component
@Order(Integer.MAX_VALUE)
public class DemonAuthorizeConfigProvider implements AuthorizeConfigProvider {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        config.antMatchers(
                SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
                SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM,
                SecurityConstants.DEFAULT_OPENID_URL,
                SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
                SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
                SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
                "/code/*",
                "/user/regist",
                securityProperties.getSocialProperties().getQq().getSigunUpUrl(),
                securityProperties.getBrowser().getSession().getSESSION_INVALID_URL(),
                securityProperties.getBrowser().getLogOutUrl(),
                securityProperties.getBrowser().getLoginPage()
                ).permitAll()
                .anyRequest().authenticated();

    }
}
