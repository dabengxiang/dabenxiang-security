package com.dabenxiang.security.authentication;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Date:2018/9/9
 * Author:gyc
 * Desc:
 */


@Slf4j
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {

    private String logOutUrl ;



    public MyLogoutSuccessHandler(String logOutUrl) {
        this.logOutUrl = logOutUrl;
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

        log.info(this.getClass().toString(),"退出成功");
        if(logOutUrl!=null){
            httpServletResponse.sendRedirect(logOutUrl);
        }else{
            httpServletResponse.setContentType("application/json;charset=utf-8");
            httpServletResponse.getWriter().write("退出成功");
        }
    }
}
