package com.dabenxiang.security.core.validate.code;

import com.dabenxiang.security.core.properties.SecurityConstants;
import com.dabenxiang.security.core.properties.SecurityProperties;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Date:2018/8/9
 * Author: yc.guo the one whom in nengxun
 * Desc:
 */

@Component
public class ValidateCodeFilter extends OncePerRequestFilter {


    private Logger logger = LoggerFactory.getLogger(ValidateCodeFilter.class);

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();


    private Map<String,ValidateCodeType> urlmap = new HashMap<>();


    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private SecurityProperties securityProperties;
    @Autowired
    private ValidateCodeProcessorHolder processorHolder;

    private AntPathMatcher antPathMatcher = new AntPathMatcher();


    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        urlmap.put(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM, ValidateCodeType.IMAGE);
        urlmap.put(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE, ValidateCodeType.SMS);
        addUrlMap(securityProperties.getCode().getImageCodeProperties().getUrl(),ValidateCodeType.IMAGE);
        addUrlMap(securityProperties.getCode().getSmsCodeProperties().getUrl(),ValidateCodeType.SMS);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        ValidateCodeType type = getValidateCodeType(request);
        if(type!=null){
            try {
                //先找到是哪个的验证器，找到则验证
                logger.info("开始验证。。。");
                ValidateCodeProcessor validateCodeProcessor = processorHolder.findValidateCodeProcessor(type);
                validateCodeProcessor.validate(new ServletWebRequest(request,response));
                logger.info("验证完成。。。");
            } catch (AuthenticationException exception) {
                logger.error(exception.getMessage());
                authenticationFailureHandler.onAuthenticationFailure(request, response, exception);
                return ;
            }
        }
        filterChain.doFilter(request,response);
    }


    private void addUrlMap(String validateCodeurl, ValidateCodeType type){
        if(StringUtils.isNotBlank(validateCodeurl)){
            String[] urls = validateCodeurl.split(",");
            for (String url : urls) {
                urlmap.put(url,type);
            }
        }
    }


    private ValidateCodeType getValidateCodeType(HttpServletRequest request){
        ValidateCodeType result = null;

        String uri = request.getRequestURI();
        if(!StringUtils.equalsIgnoreCase( "get",request.getMethod())){
            for (String url : urlmap.keySet()) {
                if(antPathMatcher.match(url,request.getRequestURI())){
                    result = urlmap.get(url);
                    break;
                }
            }
        }

        return result;

    }

}
