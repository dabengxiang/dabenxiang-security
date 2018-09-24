package com.dabenxiang.security.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.ArrayList;
import java.util.List;

/**
 * Date:2018/9/11
 * Author: yc.guo the one whom in nengxun
 * Desc:
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter{

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;


    @Autowired
    private TokenStore tokenStore;


    @Autowired(required =  false)
    private TokenEnhancer tokenEnhancer;



    @Autowired(required = false)
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore);
        endpoints.authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService);

        if(jwtAccessTokenConverter!=null && tokenEnhancer!=null ){

            TokenEnhancerChain chain = new TokenEnhancerChain();
            List<TokenEnhancer> list = new ArrayList<>();
            list.add(tokenEnhancer);
            list.add(jwtAccessTokenConverter);
            chain.setTokenEnhancers(list);
            endpoints.tokenEnhancer(chain);
       }

    }


    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("dabenxiang")
                .secret("dabenxiang")
                .accessTokenValiditySeconds(7200)
                .scopes("all","write","read");
    }


}
