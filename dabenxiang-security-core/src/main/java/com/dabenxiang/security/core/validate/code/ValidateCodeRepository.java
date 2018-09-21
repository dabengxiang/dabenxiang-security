package com.dabenxiang.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * Date:2018/9/21
 * Author: yc.guo the one whom in nengxun
 * Desc:
 */
public interface ValidateCodeRepository {

    /**
     * 保存验证码
     * @param request
     * @param type
     * @param validateCode
     */
    public void save(ServletWebRequest request, ValidateCodeType type, ValidateCode validateCode);

    /**
     * 获取验证码
     * @param request
     * @param type
     * @return
     */
    ValidateCode get(ServletWebRequest request, ValidateCodeType type);


    /**
     * 移除验证码
     * @param request
     * @param type
     */
    void remove(ServletWebRequest request,ValidateCodeType type);


}
