package com.dabenxiang.security.core.validate.code;

import com.dabenxiang.security.core.validate.code.image.ImageCode;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Date:2018/8/9
 * Author: yc.guo the one whom in nengxun
 * Desc:
 */
public class ValidateCodeFilter extends OncePerRequestFilter {


    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    private final String PIC_CODE_SESSION = "PIC_VALIVATE_CODE";

    private AuthenticationFailureHandler authenticationFailureHandler;


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws IOException, ServletException {
        if(StringUtils.equals("/authentication/form",httpServletRequest.getRequestURI())
                &&"post".equals(httpServletRequest.getMethod())){
            try {
                validateCode(httpServletRequest,httpServletResponse);
            }
            catch (org.springframework.security.core.AuthenticationException exception){

            }
        }

        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }

    private void validateCode(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
        String imageCode = httpServletRequest.getParameter("imageCode");
        ImageCode sessionImageCode = (ImageCode) sessionStrategy.getAttribute(new ServletWebRequest(httpServletRequest), PIC_CODE_SESSION);

        if(!StringUtils.isBlank(imageCode)){
            throw  new ValidateCodeException("验证码的值不能为空");
        }

        if(sessionImageCode==null){
            throw new ValidateCodeException("验证码不存在");
        }

        if(sessionImageCode.isExpire()){
            throw  new ValidateCodeException("验证码过时！！");
        }

        if(!imageCode.equals(sessionImageCode.getCode()))
            throw  new ValidateCodeException("验证码不正确！");
        sessionStrategy.removeAttribute(new ServletWebRequest(httpServletRequest),PIC_CODE_SESSION);
    }


}
