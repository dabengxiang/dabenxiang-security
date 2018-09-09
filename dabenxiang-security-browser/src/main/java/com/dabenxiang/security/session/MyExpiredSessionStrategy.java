package com.dabenxiang.security.session;

import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Date:2018/9/8
 * Author:gyc
 * Desc:
 */
public class MyExpiredSessionStrategy extends AbstractSessionStrategy implements SessionInformationExpiredStrategy{
    public MyExpiredSessionStrategy(String targetUrl) {
        super(targetUrl);
    }

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent sessionInformationExpiredEvent) throws IOException, ServletException {
        this.setConcurrency(true);
        onSessionInvalid(sessionInformationExpiredEvent.getRequest(),sessionInformationExpiredEvent.getResponse());
    }


    @Override
    protected String processSession(String targetUrl) {
        return targetUrl+"?concurrency=true";
    }

}
