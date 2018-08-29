package com.dabenxiang.security.core.validate.code;

import com.dabenxiang.security.core.properties.SecurityProperties;
import com.dabenxiang.security.core.validate.code.image.ImageValidateCodeGenerate;
import com.dabenxiang.security.core.validate.code.sms.DefaultSmsCodeSender;
import com.dabenxiang.security.core.validate.code.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Date:2018/8/9
 * Author: yc.guo the one whom in nengxun
 * Desc:
 */
@Configuration
public class ValidateCodeBeanConfig {

    @Autowired
    private SecurityProperties securityProperties;

    @Bean("imageValidateCodeGenerate")
    @ConditionalOnMissingBean(name = "imageValidateCodeGenerate")
    public ValidateCodeGenerate getImageGenerate(){
        ImageValidateCodeGenerate imageValidateCodeGenerate = new ImageValidateCodeGenerate();
        imageValidateCodeGenerate.setSecurityProperties(securityProperties);
        return imageValidateCodeGenerate;
    }

    @Bean
    @ConditionalOnMissingBean(SmsCodeSender.class)
    public SmsCodeSender getSmsCodeSender(){
        DefaultSmsCodeSender defaultSmsCodeSender = new DefaultSmsCodeSender();
        return defaultSmsCodeSender;
    }

}
