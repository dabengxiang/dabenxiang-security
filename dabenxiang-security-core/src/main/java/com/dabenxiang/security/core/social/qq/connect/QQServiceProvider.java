package com.dabenxiang.security.core.social.qq.connect;

import com.dabenxiang.security.core.social.qq.api.QQ;
import com.dabenxiang.security.core.social.qq.api.QQImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Template;

/**
 * Date:2018/8/31
 * Author: yc.guo the one whom in nengxun
 * Desc:
 */
public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ> {


    private String appid;

    private static final String  authorizeUrl = "https://graph.qq.com/oauth2.0/authorize";

    private static final String  accessTokenUrl = "https://graph.qq.com/oauth2.0/token";

    public QQServiceProvider(String appid,String clientSecret ) {
        super(new QQOAuth2Template(appid,clientSecret,authorizeUrl,accessTokenUrl));
        this.appid = appid;

    }

    @Override
    public QQ getApi(String token) {
        return new QQImpl(token,appid);
    }
}
