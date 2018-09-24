package com.dabenxiang.security.app.authentication.openid;

import com.dabenxiang.security.core.properties.SecurityConstants;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Date:2018/9/22
 * Author:gyc
 * Desc:
 */

public class OpenIdAuthenticationFilter extends AbstractAuthenticationProcessingFilter{


    private String usernameParameter = "openId";
    private String passwordParameter = "providerId";
    private boolean postOnly = true;


    protected OpenIdAuthenticationFilter() {
        super(new AntPathRequestMatcher(SecurityConstants.DEFAULT_OPENID_URL, "POST"));
    }



    protected String obtainOpenId(HttpServletRequest request) {
        return request.getParameter(this.passwordParameter);
    }

    protected String obtainProviderId(HttpServletRequest request) {
        return request.getParameter(this.usernameParameter);
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (this.postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        } else {
            String providerId = this.obtainProviderId(request);
            String openId = this.obtainOpenId(request);
            if (providerId == null) {
                providerId = "";
            }

            if (openId == null) {
                openId = "";
            }

            providerId = providerId.trim();
            OpenIdAuthenticationToken authRequest = new OpenIdAuthenticationToken(providerId, openId);
            this.setDetails(request, authRequest);
            return this.getAuthenticationManager().authenticate(authRequest);
        }
    }

    protected void setDetails(HttpServletRequest request, OpenIdAuthenticationToken authRequest) {
        authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
    }
}
