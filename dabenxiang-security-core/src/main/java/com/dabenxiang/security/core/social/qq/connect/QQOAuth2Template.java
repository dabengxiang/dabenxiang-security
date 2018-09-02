package com.dabenxiang.security.core.social.qq.connect;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.List;

/**
 * Date:2018/8/31
 * Author: yc.guo the one whom in nengxun
 * Desc:
 */
public class QQOAuth2Template extends OAuth2Template {

    public QQOAuth2Template(String clientId, String clientSecret, String authorizeUrl, String accessTokenUrl) {
        super(clientId, clientSecret, authorizeUrl, accessTokenUrl);
        setUseParametersForClientAuthentication(true);
    }

    @Override
    protected RestTemplate createRestTemplate() {
        RestTemplate restTemplate = super.createRestTemplate();
        List<HttpMessageConverter<?>> messageConverters = restTemplate.getMessageConverters();
        messageConverters.add(new StringHttpMessageConverter(Charset.forName("utf-8")));
        return restTemplate;
    }

    @Override
    protected AccessGrant postForAccessGrant(String accessTokenUrl, MultiValueMap<String, String> parameters) {

        AccessGrant accessGrant = null;
        String result = createRestTemplate().postForObject(accessTokenUrl, parameters, String.class);

        String[] split = StringUtils.split(result,"&");

        if(split!=null && split.length==3){
               String access_token =  StringUtils.substringAfter(split[0],"access_token=");
               String expires_in =  StringUtils.substringAfter(split[1],"expires_in=");
               String refresh_token =  StringUtils.substringAfter(split[2],"refresh_token=");
             accessGrant = new AccessGrant(access_token, null,  refresh_token,Long.parseLong(expires_in) );
        }
        return accessGrant;

    }
}
