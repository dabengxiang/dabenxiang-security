package com.dabenxiang.security.core.social.qq.api;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

/**
 * Date:2018/8/30
 * Author: yc.guo the one whom in nengxun
 * Desc:
 */
@Slf4j
public class QQImpl extends AbstractOAuth2ApiBinding implements QQ {

    private static final String URL_GET_OPENID = "https://graph.qq.com/oauth2.0/me?access_token=%s";

    private static final String URL_GET_USERINFO = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";

    private ObjectMapper objectMapper = new ObjectMapper();

    private String appid;

    private String openid;

    public QQImpl(String accessToken,String appid ) {
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
        this.appid = appid;
        String url = String.format(URL_GET_OPENID, accessToken);
        String returnData = getRestTemplate().getForObject(url,String.class);
        openid = StringUtils.substringBetween(returnData,"\"openid\":\"","\"}");
        this.openid = openid;


    }

    @Override
    public QQUserInfo getUserInfo() {
        String url = String.format(URL_GET_USERINFO, appid,openid);
        String result = getRestTemplate().getForObject(url,String.class);
        
        try {
            QQUserInfo qqUserInfo = JSONObject.parseObject(result, QQUserInfo.class);
            qqUserInfo.setOpenId(openid);
            return qqUserInfo;

        }catch (Exception e){
            log.error(e.getMessage());
            throw new RuntimeException("获取用户信息失败。。。");
            
        }

    }


}
