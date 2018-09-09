package com.dabenxiang.security.session;

import org.springframework.security.web.session.InvalidSessionStrategy;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Date:2018/9/8
 * Author:gyc
 * Desc:
 */
public class MyInvalidSessionStrategy extends  AbstractSessionStrategy implements InvalidSessionStrategy {

    public MyInvalidSessionStrategy(String targetUrl) {
        super(targetUrl);
    }

    @Override
    public void onInvalidSessionDetected(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException, ServletException {
        onSessionInvalid(httpServletRequest,httpServletResponse);
    }
}
