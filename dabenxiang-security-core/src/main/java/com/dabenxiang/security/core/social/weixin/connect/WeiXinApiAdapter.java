package com.dabenxiang.security.core.social.weixin.connect;

import com.dabenxiang.security.core.social.weixin.api.WeiXin;
import com.dabenxiang.security.core.social.weixin.api.WeiXinInfo;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoRestTemplateCustomizer;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

/**
 * Date:2018/9/2
 * Author:gyc
 * Desc:
 */
public class WeiXinApiAdapter implements ApiAdapter<WeiXin> {

    private String openId;


    public WeiXinApiAdapter() {
    }

    public WeiXinApiAdapter(String openId) {
        this.openId = openId;
    }

    @Override
    public boolean test(WeiXin weiXin) {
        return true;
    }

    @Override
    public void setConnectionValues(WeiXin weiXin, ConnectionValues connectionValues) {
        WeiXinInfo userIfo = weiXin.getUserIfo(openId);
        connectionValues.setDisplayName(userIfo.getNickname());
        connectionValues.setImageUrl(userIfo.getHeadimgurl());
        connectionValues.setProviderUserId(userIfo.getOpenid());
        connectionValues.setProfileUrl(null);
    }

    @Override
    public UserProfile fetchUserProfile(WeiXin weiXin) {
        return null;
    }

    @Override
    public void updateStatus(WeiXin weiXin, String s) {

    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
}
