package com.dabenxiang.security.core.validate.code.impl;

import com.dabenxiang.security.core.validate.code.*;
import com.dabenxiang.security.core.validate.code.image.ImageCode;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
     * 主流程 生成验证码--> 保存验证码 --> 后续处理
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
        String type = getValidateCodeType().toString().toLowerCase();
        ValidateCodeGenerate validateCodeGenerate = validateCodeGenerates.get(type + "ValidateCodeGenerate");
        return (C) validateCodeGenerate.generate();
    }


    /**
     * 保存验证码
     * @param validateCode
     * @param servletWebRequest
     */
    protected  void save(C validateCode,ServletWebRequest servletWebRequest){
        sessionStrategy.setAttribute(servletWebRequest,getSessionKey(),validateCode);
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
     * @return
     */
//    String getProcessorType(ServletWebRequest servletWebRequest){
//        return StringUtils.substringAfter(servletWebRequest.getRequest().getRequestURI(),"/code/");
//    }





    public String getSessionKey(){
        ValidateCodeType validateCodeType = getValidateCodeType();
        String name = validateCodeType.getParamNameOnValidate();
        return PREFIX_SESSION_KEY+name.toUpperCase();
    }
    

    private ValidateCodeType getValidateCodeType(){
        String name = StringUtils.substringBefore(this.getClass().getSimpleName(),
                "ValidateCodeProcessor");
        return ValidateCodeType.valueOf(name.toUpperCase());
    }




    /**
     * 验证的方法
     * @param request
     */
    @Override
    public  void validate(ServletWebRequest request){

        //获取存在session里的数据
        String sessionKey = getSessionKey();
        C sessionValue = (C) sessionStrategy.getAttribute(request, sessionKey);

        String paramName = getValidateCodeType().getParamNameOnValidate();
        String parameterValue = request.getParameter(paramName);


        if(StringUtils.isBlank(parameterValue)){
            throw  new ValidateCodeException("验证码的值不能为空");
        }

        if(sessionValue==null){
            throw new ValidateCodeException("验证码不存在");
        }

        if(sessionValue.isExpire()){
            throw  new ValidateCodeException("验证码过时！！");
        }

        if(!parameterValue.equals(sessionValue.getCode()))
            throw  new ValidateCodeException("验证码不正确！");
        sessionStrategy.removeAttribute(request,sessionKey);
    }




}
