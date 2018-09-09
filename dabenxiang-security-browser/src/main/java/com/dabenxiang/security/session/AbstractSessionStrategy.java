package com.dabenxiang.security.session;

import com.alibaba.fastjson.JSONObject;
import com.dabenxiang.security.support.SimpleResponse;
import lombok.Data;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Date:2018/9/8
 * Author:gyc
 * Desc:
 */
@Data
public class AbstractSessionStrategy {

    private boolean createNewSession = true;

    private String targetUrl;

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    private boolean isConcurrency;


    public AbstractSessionStrategy(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public void onSessionInvalid(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(createNewSession)
            request.getSession();

       String sourceURI = request.getRequestURI();
        String targeUrl = this.targetUrl;

        if(StringUtils.endsWithIgnoreCase(sourceURI,".html")){
            targeUrl = this.targetUrl+"html";
            redirectStrategy.sendRedirect(request,response,targetUrl);
        }else {
            Object result = buildResponseContent(request);
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(JSONObject.toJSONString(result));
        }

    }


    /**
     * @param request
     * @return
     */
    protected Object buildResponseContent(HttpServletRequest request) {
        String message = "session已失效";
        if (isConcurrency()) {
            message = message + "，有可能是并发登录导致的";
        }
        return new SimpleResponse(message);
    }

    protected   String processSession(String targetUrl){return targetUrl;}

    public boolean isConcurrency() {
        return isConcurrency;
    }

    public void setConcurrency(boolean concurrency) {
        isConcurrency = concurrency;
    }
}
