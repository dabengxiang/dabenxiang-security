package com.dabenxiang.security.core.authentication.mobile;

import com.dabenxiang.security.core.properties.SecurityContants;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Date:2018/8/29
 * Author: yc.guo the one whom in nengxun
 * Desc:
 */
@Component
public class SmsAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    private boolean postOnly = true;

    private String mobileParameter = SecurityContants.DEFAULT_PARAMETER_NAME_MOBILE;

    public SmsAuthenticationFilter() {
        super(new AntPathRequestMatcher("/mobile", "POST"));
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
