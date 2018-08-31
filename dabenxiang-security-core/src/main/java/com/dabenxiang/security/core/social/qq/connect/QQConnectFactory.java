package com.dabenxiang.security.core.social.qq.connect;

import com.dabenxiang.security.core.social.qq.api.QQ;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.oauth2.OAuth2ServiceProvider;

/**
 * Date:2018/8/31
 * Author: yc.guo the one whom in nengxun
 * Desc:
 */
public class QQConnectFactory  extends OAuth2ConnectionFactory<QQ> {

    public QQConnectFactory(String providerId, String appid,String clientSecret ) {
        super(providerId, new QQServiceProvider(appid,clientSecret), new QQAdapter());
    }
}
