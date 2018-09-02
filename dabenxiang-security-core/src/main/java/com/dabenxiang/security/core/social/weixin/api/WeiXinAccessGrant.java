package com.dabenxiang.security.core.social.weixin.api;

import lombok.Data;
import org.springframework.social.oauth2.AccessGrant;

/**
 * Date:2018/9/2
 * Author:gyc
 * Desc:
 */

@Data
public class WeiXinAccessGrant extends AccessGrant {

    private String openId;

    public WeiXinAccessGrant(String accessToken, String scope, String refreshToken, Long expiresIn,String openId) {
        super(accessToken, scope, refreshToken, expiresIn);
        this.openId = openId;
    }




}
