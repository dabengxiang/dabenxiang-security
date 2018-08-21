package com.dabenxiang.security.core.validate.code.impl;

import com.dabenxiang.security.core.validate.code.ValidateCode;
import com.dabenxiang.security.core.validate.code.ValidateCodeProcessor;
import com.dabenxiang.security.core.validate.code.ValidateCodeGenerate;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.context.request.ServletWebRequest;

import java.io.IOException;
import java.util.Map;

/**
 * Date:2018/8/20
 * Author: yc.guo the one whom in nengxun
 * Desc: 获取验证码的整个过程，先生成验证码，存进session里，然后处理
 */
public abstract class AbstractValidateCodeProcessor<C extends ValidateCode> implements ValidateCodeProcessor {

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Autowired
    private Map<String,ValidateCodeGenerate> validateCodeGenerates;

    /**
     * 主流程
     * @param servletWebRequest
     * @return
     * @throws IOException
     */
    @Override
    public ValidateCode create(ServletWebRequest servletWebRequest) throws IOException {
        C validateCode = generate(servletWebRequest);
        save(validateCode,servletWebRequest);
        send(servletWebRequest,validateCode);
        return validateCode;
    }


    /**
     * 生成验证码
     * @param servletWebRequest
     * @return
     */
    private  C generate(ServletWebRequest servletWebRequest){
        String processorType = getProcessorType(servletWebRequest);
        ValidateCodeGenerate validateCodeGenerate = validateCodeGenerates.get(getProcessorType(servletWebRequest) + "CodeGenerate");
        return (C) validateCodeGenerate.generate();
    }


    /**
     * 保存验证码
     * @param validateCode
     * @param servletWebRequest
     */
    protected  void save(C validateCode,ServletWebRequest servletWebRequest){
        sessionStrategy.setAttribute(servletWebRequest,SESSION_VALICATE_CODE_PREFIX+getProcessorType(servletWebRequest).toUpperCase(),validateCode);
    }


    /**
     * 后续处理
     * @param servletWebRequest
     * @param validateCode
     * @throws IOException
     */
    protected abstract void send(ServletWebRequest servletWebRequest,C validateCode) throws IOException;


    /**
     * 获取uri:/code/{type} , type这个字符串
     * @param servletWebRequest
     * @return
     */
    String getProcessorType(ServletWebRequest servletWebRequest){
        return StringUtils.substringAfter(servletWebRequest.getRequest().getRequestURI(),"/code/");
    }

}
