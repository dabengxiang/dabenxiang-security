package com.dabenxiang.security.core.validate.code.sms;

/**
 * Date:2018/8/20
 * Author: yc.guo the one whom in nengxun
 * Desc:
 */
public class DefaultSmsCodeSender implements  SmsCodeSender {
    @Override
    public void send(String mobile, String code) {
        System.out.println("向"+mobile+"发送信息："+code);
    }
}
