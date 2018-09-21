package com.dabenxiang.security.session;

import com.dabenxiang.security.core.validate.code.ValidateCode;
import com.dabenxiang.security.core.validate.code.ValidateCodeRepository;
import com.dabenxiang.security.core.validate.code.ValidateCodeType;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Date:2018/9/21
 * Author: yc.guo the one whom in nengxun
 * Desc:
 */

@Repository
public class SessionValidateRepository implements ValidateCodeRepository {

    private final String PREFIX_SESSION_KEY = "SESSION_KEY_FOR_CODE_";

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Override
    public void save(ServletWebRequest request, ValidateCodeType type, ValidateCode validateCode) {
        sessionStrategy.setAttribute(request,getSessionKey(type),validateCode);

    }

    @Override
    public ValidateCode get(ServletWebRequest request, ValidateCodeType type) {
        return (ValidateCode) sessionStrategy.getAttribute(request,getSessionKey(type));

    }

    @Override
    public void remove(ServletWebRequest request, ValidateCodeType type) {
        sessionStrategy.removeAttribute(request,getSessionKey(type));
    }

    public String getSessionKey(ValidateCodeType type){
        return PREFIX_SESSION_KEY+type.toString().toUpperCase();
    }
}
