package com.dabenxiang.security;

import com.dabenxiang.security.core.properties.BrowserProperties;
import com.dabenxiang.security.core.properties.SecurityProperties;
import com.dabenxiang.security.support.SimpleResponse;
import org.apache.commons.lang.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Date:2018/8/8
 * Author: yc.guo the one whom in nengxun
 * Desc:
 */
@RestController
public class BrowserSecurityController {


    private RequestCache requestCache = new HttpSessionRequestCache();

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    @Autowired
    private SecurityProperties securityProperties;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/authentication/require")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public SimpleResponse requireAuthentication(HttpServletResponse response, HttpServletRequest request) throws IOException {
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if(savedRequest!=null){
            String redirectUrl = savedRequest.getRedirectUrl();
            if(StringUtils.endsWithIgnoreCase(redirectUrl,".html")){
                logger.info("引发过来的路径是："+redirectUrl);
                String loginPage = securityProperties.getBrowser().getLoginPage();
                redirectStrategy.sendRedirect(request,response,loginPage);

            }

        }
        return new SimpleResponse("访问的服务需要身份认证，请引导用户到登录页");

    }

}
