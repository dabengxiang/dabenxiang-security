package com.dabenxiang.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

import java.io.IOException;

/**
 * Date:2018/8/20
 * Author: yc.guo the one whom in nengxun
 * Desc:
 */
public interface ValidateCodeProcessor  {

    String PREFIX_SESSION_KEY = "SESSION_KEY_FOR_CODE_";

    public ValidateCode create(ServletWebRequest servletWebRequest) throws IOException;

    public void validate(ServletWebRequest servletWebRequest) throws IOException;

}
