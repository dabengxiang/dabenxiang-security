package com.dabenxiang.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

import java.io.IOException;

/**
 * Date:2018/8/20
 * Author: yc.guo the one whom in nengxun
 * Desc:
 */
public interface ValidateCodeProcessor  {

    String SESSION_VALICATE_CODE_PREFIX = "SESSION_VALICATE_CODE_";

    public ValidateCode create(ServletWebRequest servletWebRequest) throws IOException;

}
