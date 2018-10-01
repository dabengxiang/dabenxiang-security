package com.dabenxiang.security.core.authorize;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Date:2018/9/30
 * Author: yc.guo the one whom in nengxun
 * Desc:
 */

@Component
public class GycAuthorizeConfigManager implements AuthorizeConfigManager {


    @Autowired(required = false)
    private List<AuthorizeConfigProvider> providersList;


    @Override
    public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {

        if(providersList==null)
            return ;

        for (AuthorizeConfigProvider authorizeConfigProvider : providersList) {
            authorizeConfigProvider.config(config);
        }
    }
}
