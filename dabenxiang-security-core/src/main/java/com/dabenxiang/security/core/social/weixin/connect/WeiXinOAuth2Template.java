package com.dabenxiang.security.core.social.weixin.connect;

import com.alibaba.fastjson.JSONObject;
import com.dabenxiang.security.core.social.weixin.api.WeiXinAccessGrant;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

/**
 * Date:2018/9/2
 * Author:gyc
 * Desc:
 */
public class WeiXinOAuth2Template extends OAuth2Template {

    private String clientId;

    private String clientSecret;

    private String accessTokenUrl;

    private String openId;


    private String REFRESH_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";


    public WeiXinOAuth2Template(String clientId, String clientSecret, String authorizeUrl, String accessTokenUrl) {
        super(clientId, clientSecret, authorizeUrl, accessTokenUrl);
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.accessTokenUrl = accessTokenUrl;
    }


    @Override
    public AccessGrant exchangeForAccess(String authorizationCode, String redirectUri, MultiValueMap<String, String> additionalParameters) {

        StringBuilder  tokenUrl = new StringBuilder(accessTokenUrl);

        tokenUrl.append("?appid="+this.clientId);
        tokenUrl.append("&secret="+this.clientSecret);
        tokenUrl.append("&code="+authorizationCode);
        tokenUrl.append("&grant_type=authorization_code");
        return getAccessToken(tokenUrl.toString());
}


    public AccessGrant getAccessToken(String url){
        String forObject = getRestTemplate().getForObject(url, String.class);

        Map<String,Object> map = JSONObject.parseObject(forObject, Map.class);

        if(map.containsKey("errcode")){
            Integer errcode = (Integer) map.get("errcode");
            String errmsg = (String) map.get("errmsg");
            throw  new RuntimeException("获取token 失败" + "errcode :"+errcode+" errmsg : " + errmsg);
        }

        String accessToken  = (String) map.get("access_token");
        String scope = (String) map.get("scope");
        String refreshToken = (String) map.get("refresh_token");
        Integer expiresIn = (Integer) map.get("expires_in");
        openId = (String)map.get("openid");

       return new WeiXinAccessGrant( accessToken,  scope,  refreshToken,  new Long(expiresIn),openId) ;

    }



    @Override
    public AccessGrant refreshAccess(String refreshToken, MultiValueMap<String, String> additionalParameters) {
        StringBuilder  refreshUrl = new StringBuilder(REFRESH_TOKEN_URL);
        refreshUrl.append("?appid="+this.clientId);
        refreshUrl.append("&refresh_token="+refreshToken);
        refreshUrl.append("&grant_type=refresh_token");
        return getAccessToken(this.accessTokenUrl);

    }


    @Override
    public String buildAuthorizeUrl(OAuth2Parameters parameters) {
        return buildAuthenticateUrl(parameters);
    }

    @Override
    public String buildAuthenticateUrl(OAuth2Parameters parameters) {
        String url = super.buildAuthenticateUrl(parameters);
        url = url+"&scope=snsapi_login";
        url = url + "&appid=" + clientId;
        return url;
    }

    @Override
    protected RestTemplate createRestTemplate() {
        RestTemplate restTemplate = super.createRestTemplate();
        List<HttpMessageConverter<?>> messageConverters = restTemplate.getMessageConverters();

        messageConverters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));

        return restTemplate;


    }
}