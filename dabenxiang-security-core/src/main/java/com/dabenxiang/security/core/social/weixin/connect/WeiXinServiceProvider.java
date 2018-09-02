package com.dabenxiang.security.core.social.weixin.connect;

import com.dabenxiang.security.core.social.weixin.api.WeiXin;
import com.dabenxiang.security.core.social.weixin.api.WeiXinImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Template;

/**
 * Date:2018/9/2
 * Author:gyc
 * Desc:
 */
public class WeiXinServiceProvider extends AbstractOAuth2ServiceProvider<WeiXin> {


    private final  static String authorizeUrl = "https://open.weixin.qq.com/connect/qrconnect";

    private final  static  String accessTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token";


    public WeiXinServiceProvider( String clientId, String clientSecret) {
        super(new WeiXinOAuth2Template(clientId,clientSecret,authorizeUrl,accessTokenUrl));
    }

    @Override
    public WeiXin getApi(String accessToken) {
        return new WeiXinImpl(accessToken);
    }
}
