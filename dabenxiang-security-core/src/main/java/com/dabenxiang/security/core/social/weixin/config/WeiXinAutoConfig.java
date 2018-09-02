package com.dabenxiang.security.core.social.weixin.config;

import com.dabenxiang.security.core.properties.SecurityProperties;
import com.dabenxiang.security.core.properties.SocialProperties;
import com.dabenxiang.security.core.properties.WeiXinProperties;
import com.dabenxiang.security.core.social.weixin.connect.WeiXinConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.social.connect.ConnectionFactory;

/**
 * Date:2018/9/2
 * Author:gyc
 * Desc:
 */

@Configuration
@Order(2)
public class WeiXinAutoConfig extends SocialAutoConfigurerAdapter {


    @Autowired
    private SecurityProperties securityProperties;

    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        WeiXinProperties weixin = securityProperties.getSocialProperties().getWeixin();
        return new WeiXinConnectionFactory(weixin.getProviderId(),weixin.getAppId(),weixin.getAppSecret());
    }
}
