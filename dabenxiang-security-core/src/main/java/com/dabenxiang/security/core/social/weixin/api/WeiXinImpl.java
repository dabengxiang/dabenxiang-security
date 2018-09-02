package com.dabenxiang.security.core.social.weixin.api;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import java.nio.charset.Charset;
import java.util.List;

/**
 * Date:2018/9/2
 * Author:gyc
 * Desc:
 */
public class WeiXinImpl extends AbstractOAuth2ApiBinding implements WeiXin {

    private String GET_WEIXIN_TOKEN_URL = "https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s";

    private String accessToken;

    public WeiXinImpl(String accessToken){
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
        this.accessToken = accessToken;

    }


    @Override
    public WeiXinInfo getUserIfo(String openId) {
        String getUserInfoUrl = String.format(GET_WEIXIN_TOKEN_URL, accessToken, openId);
        String forObject = getRestTemplate().getForObject(getUserInfoUrl, String.class);

        WeiXinInfo weiXinInfo = JSONObject.parseObject(forObject, WeiXinInfo.class);
        return weiXinInfo;
    }


    /**
     * 因为默认的StringHttpMessageConverter是iso8859-1的编码，把他改为utf-8的
     * @return
     */
    @Override
    protected List<HttpMessageConverter<?>> getMessageConverters() {
        List<HttpMessageConverter<?>> messageConverters = super.getMessageConverters();
        messageConverters.remove(0);
        messageConverters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        return messageConverters;

    }
}
