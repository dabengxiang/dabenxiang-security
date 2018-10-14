package com.sso.server;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import javax.annotation.Resource;
import javax.sql.DataSource;


/**
 * Date:2018/9/25
 * Author: yc.guo the one whom in nengxun
 * Desc:
 */
@Configuration
@EnableAuthorizationServer
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class SsoAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {



    @Resource
    private DataSource dataSource;








    @Bean
    public ClientDetailsService clientDetailsService(){
        return new JdbcClientDetailsService(dataSource);
    }


    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {


        clients.withClientDetails(clientDetailsService());


//        clients.inMemory().withClient("myClient1")
//                .secret("myClient1").scopes("all")
//                .authorizedGrantTypes("authorization_code", "refresh_token")
//                .and().withClient("myClient2")
//                .secret("myClient2").scopes("all")
//                .authorizedGrantTypes("authorization_code", "refresh_token");

    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("isAuthenticated()");

    }


    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(jwtTokenStore()) .accessTokenConverter(jwtAccessTokenConverter());
    }

    @Bean
    public JwtTokenStore jwtTokenStore(){
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter(){
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setSigningKey("dabenxiang");
        return jwtAccessTokenConverter;
    }


}
