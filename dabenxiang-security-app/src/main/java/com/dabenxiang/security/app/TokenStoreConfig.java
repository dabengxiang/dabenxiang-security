package com.dabenxiang.security.app;

import com.dabenxiang.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * Date:2018/9/23
 * Author:gyc
 * Desc:
 */
@Configuration
public class TokenStoreConfig {

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;


    @Bean
    @ConditionalOnProperty(prefix = "dabenxiang.security.oauth2",name = "jwtKey" , havingValue = "redis")
    public TokenStore getTokenStore(){
        return new RedisTokenStore(redisConnectionFactory);
    }




    @Configuration
    @ConditionalOnProperty(prefix = "dabenxiang.security.oauth2",name = "jwtKey" ,matchIfMissing = true , havingValue = "jwt")
    public static class JwtTokenConfig{

        @Autowired
        private SecurityProperties securityProperties;

        @Autowired
        private JwtAccessTokenConverter jwtAccessTokenConverter;

        @Bean
        public TokenStore getTokenStore(){
            return new JwtTokenStore(jwtAccessTokenConverter);
        }

        /**
         * 注入转换器，并且设置密钥
         * @return
         */
        @Bean
        public JwtAccessTokenConverter jwtAccessTokenConverter(){
            String jwtKey = securityProperties.getOauth2().getJwtKey();
            JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
            converter.setSigningKey(jwtKey);
            return converter;
        }

        @Bean
        @ConditionalOnBean(TokenEnhancer.class)
        public TokenEnhancer tokenEnhancer(){
            return new MyTokenEnhancer();
        }

    }


}
