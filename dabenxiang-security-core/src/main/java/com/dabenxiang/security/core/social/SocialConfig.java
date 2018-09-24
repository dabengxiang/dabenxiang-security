package com.dabenxiang.security.core.social;

import com.dabenxiang.security.core.properties.SecurityProperties;
import com.dabenxiang.security.core.properties.SocialProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

/**
 * Date:2018/8/31
 * Author: yc.guo the one whom in nengxun
 * Desc:
 */

@Configuration
@EnableSocial
@Order(1)
public class SocialConfig extends SocialConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private SecurityProperties securtyProperties;

    @Autowired(required = false)
    private ConnectionSignUp connectionSignUp;

    @Bean(name = "dabenxiangSpringSocialConfigurer")
    public SpringSocialConfigurer getSocialConfigurer(){
        SocialProperties socialProperties = securtyProperties.getSocialProperties();

        DabenxiangSpringSocialConfigurer springSocialConfigurer = new DabenxiangSpringSocialConfigurer(
                socialProperties.getQq().getFilterProcessesUrl());
        springSocialConfigurer.signupUrl(socialProperties.getQq().getSigunUpUrl());

        return springSocialConfigurer;

    }



    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        JdbcUsersConnectionRepository connectionRepository = new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText());
//        if(connectionSignUp!=null)
//            connectionRepository.setConnectionSignUp(connectionSignUp);
        return connectionRepository;
    }

    @Bean
    public ProviderSignInUtils getSignInUtils(ConnectionFactoryLocator connectionFactoryLocator){
        ProviderSignInUtils providerSignInUtils = new ProviderSignInUtils(connectionFactoryLocator,getUsersConnectionRepository(connectionFactoryLocator));
        return providerSignInUtils;

    }
}
