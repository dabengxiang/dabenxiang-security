package com.dabenxiang.security.core.validate.code.sms;

import com.dabenxiang.security.core.validate.code.ValidateCode;
import com.dabenxiang.security.core.validate.code.image.ImageCode;
import com.dabenxiang.security.core.validate.code.impl.AbstractValidateCodeProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * Date:2018/8/20
 * Author: yc.guo the one whom in nengxun
 * Desc:
 */
@Component("smsValidateCodeProcessor")
public class SmsValidateCodeProcessor extends AbstractValidateCodeProcessor<ValidateCode> {

    @Autowired
    private SmsCodeSender smsCodeSender;

    @Override
    protected void send(ServletWebRequest servletWebRequest, ValidateCode smsCode) throws IOException {
        String mobile = servletWebRequest.getParameter("mobile");
        smsCodeSender.send(mobile,smsCode.getCode());
    }




}
