package com.dabenxiang.security.core.validate.code.sms;

import com.dabenxiang.security.core.properties.SecurityProperties;
import com.dabenxiang.security.core.properties.SmsCodeProperties;
import com.dabenxiang.security.core.validate.code.ValidateCode;
import com.dabenxiang.security.core.validate.code.ValidateCodeGenerate;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Date:2018/8/9
 * Author: yc.guo the one whom in nengxun
 * Desc: 生成短信验证码
 */

@Component("smsCodeGenerate")
public class SmsValidateCodeGenerate implements ValidateCodeGenerate {

    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 生成手机验证
     * @return
     */
    public ValidateCode generate() {
        SmsCodeProperties smsCodeProperties = securityProperties.getCode().getSmsCodeProperties();
        return new ValidateCode(RandomStringUtils.randomNumeric(smsCodeProperties.getLength()),smsCodeProperties.getExpireIn());
    }

}
