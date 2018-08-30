package com.dabenxiang.security.core.social.qq.api;

import org.apache.commons.lang.StringUtils;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.web.client.RestTemplate;

/**
 * Date:2018/8/30
 * Author: yc.guo the one whom in nengxun
 * Desc:
 */
public class QQImpl extends AbstractOAuth2ApiBinding implements QQ {

    private static final String URL_GET_OPENID = "https://graph.qq.com/oauth2.0/me?access_token=%s";

    private static final String URL_GET_USERINFO = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";


    private String appid;

    private String openid;

    public QQImpl(String accessToken,String appid ) {
        super(accessToken);
        this.appid = appid;
        String url = String.format(URL_GET_OPENID, accessToken);
        String returnData = getRestTemplate().getForObject(url,String.class);
        openid = StringUtils.substringBetween(returnData,"\"openid\":\"","\"");
        this.appid = appid;


    }

    @Override
    public QQUserInfo getUserInfo() {
        String url = String.format(URL_GET_OPENID, appid,openid);
        QQUserInfo userInfo = getRestTemplate().getForObject(url,QQUserInfo.class);
        userInfo.setOpenId(openid);
        return userInfo;
    }


}
