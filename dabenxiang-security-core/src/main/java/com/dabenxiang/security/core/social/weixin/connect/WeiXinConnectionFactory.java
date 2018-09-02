package com.dabenxiang.security.core.social.weixin.connect;

import com.dabenxiang.security.core.social.weixin.api.WeiXinAccessGrant;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2ServiceProvider;

/**
 * Date:2018/9/2
 * Author:gyc
 * Desc:
 */
public class WeiXinConnectionFactory extends OAuth2ConnectionFactory {



    public WeiXinConnectionFactory(String providerId, String appId,String clientSecret ) {
        super(providerId,  new WeiXinServiceProvider(appId, clientSecret), new WeiXinApiAdapter());
    }

    @Override
    public Connection createConnection(AccessGrant accessGrant) {

        WeiXinApiAdapter weiXinApiAdapter = null;
        if(accessGrant instanceof  WeiXinAccessGrant){
            WeiXinAccessGrant weiXinAccessGrant = (WeiXinAccessGrant) accessGrant;
            weiXinApiAdapter = (WeiXinApiAdapter) this.getApiAdapter();
            weiXinApiAdapter.setOpenId(weiXinAccessGrant.getOpenId());

        }
        return super.createConnection(accessGrant);
    }



}
