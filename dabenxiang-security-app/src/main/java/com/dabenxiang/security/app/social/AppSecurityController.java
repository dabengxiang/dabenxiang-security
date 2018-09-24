package com.dabenxiang.security.app.social;

import com.dabenxiang.security.core.social.SocialUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * Date:2018/9/23
 * Author:gyc
 * Desc:
 */

@RestController
@RequestMapping
public class AppSecurityController {


    @Autowired
    private AppSignUpUtils appSignUpUtils;

    @Autowired
    private ProviderSignInUtils providerSignInUtils;

    @GetMapping("/social/signUp")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public SocialUserInfo getSocialUserInfo(HttpServletRequest request){
        Connection<?> connectionFromSession = providerSignInUtils.getConnectionFromSession(new ServletWebRequest(request));
        SocialUserInfo socialUserInfo = new SocialUserInfo();
        socialUserInfo.setNickname(connectionFromSession.getDisplayName());
        socialUserInfo.setHeadimg(connectionFromSession.getImageUrl());
        socialUserInfo.setProviderId(connectionFromSession.getKey().getProviderId());
        socialUserInfo.setProviderUserId(connectionFromSession.getKey().getProviderUserId());
        appSignUpUtils.saveConnectionData(new ServletWebRequest(request),connectionFromSession.createData());
        return socialUserInfo;

    }


}
