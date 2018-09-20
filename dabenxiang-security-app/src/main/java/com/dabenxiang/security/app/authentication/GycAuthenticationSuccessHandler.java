package com.dabenxiang.security.app.authentication;

import com.dabenxiang.security.core.properties.LoginType;
import com.dabenxiang.security.core.properties.SecurityProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Date:2018/8/9
 * Author: yc.guo the one whom in nengxun
 * Desc:
 */
@Component
public class GycAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private ObjectMapper objectMapper;

    private String credentialsCharset = "UTF-8";

    @Autowired
    private ClientDetailsService clientDetailsService;

    @Autowired
    private AuthorizationServerTokenServices tokenServices;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        logger.info("登陆成功");
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Basic ")) {
            String[] tokens = this.extractAndDecodeHeader(header, request);
            assert tokens.length == 2;
            String clientId = tokens[0];
            String clientSecret = tokens[1];
            ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);
            if(clientDetails == null){
                throw new UnapprovedClientAuthenticationException("clientId对应的配置信息不存在:" + clientId);
            }else if (!clientDetails.getClientSecret().equals(clientSecret)){
                throw new UnapprovedClientAuthenticationException("clientSecret密码错误" + clientId);
            }

            TokenRequest tokenRequest = new TokenRequest(MapUtils.EMPTY_MAP,
                    clientId,clientDetails.getScope(),"custom");
            OAuth2Request oAuth2Request = tokenRequest.createOAuth2Request(clientDetails);
            OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(oAuth2Request, authentication);
            OAuth2AccessToken accessToken = tokenServices.createAccessToken(oAuth2Authentication);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(accessToken));

        }


    }

    private String[] extractAndDecodeHeader(String header, HttpServletRequest request) throws IOException {
        byte[] base64Token = header.substring(6).getBytes("UTF-8");

        byte[] decoded;
        try {
            decoded = Base64.decode(base64Token);
        } catch (IllegalArgumentException var7) {
            throw new BadCredentialsException("Failed to decode basic authentication token");
        }

        String token = new String(decoded, this.getCredentialsCharset(request));
        int delim = token.indexOf(":");
        if (delim == -1) {
            throw new BadCredentialsException("Invalid basic authentication token");
        } else {
            return new String[]{token.substring(0, delim), token.substring(delim + 1)};
        }
    }


    protected String getCredentialsCharset(HttpServletRequest httpRequest) {
        return this.credentialsCharset;
    }
}
