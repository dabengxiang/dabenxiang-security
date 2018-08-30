package com.dabenxiang.security.core.authentication.mobile;

import com.dabenxiang.security.core.properties.SecurityConstants;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Date:2018/8/29
 * Author: yc.guo the one whom in nengxun
 * Desc:
 */
public class SmsAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    private boolean postOnly = true;

    private String mobileParameter = SecurityConstants.DEFAULT_PARAMETER_NAME_MOBILE;

    public SmsAuthenticationFilter() {
        super(new AntPathRequestMatcher(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE, "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        if (this.postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        } else {
            String mobile = this.obtainMobile(request);
            if (mobile == null) {
                mobile = "";
            }
            mobile = mobile.trim();
            SmsAuthenticationToken authRequest = new SmsAuthenticationToken(mobile);
            this.setDetails(request, authRequest);
            return this.getAuthenticationManager().authenticate(authRequest);
        }
    }


    protected void setDetails(HttpServletRequest request, SmsAuthenticationToken authRequest) {
        authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
    }


    protected String obtainMobile(HttpServletRequest request) {
        return request.getParameter(this.mobileParameter);
    }


    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }

}
