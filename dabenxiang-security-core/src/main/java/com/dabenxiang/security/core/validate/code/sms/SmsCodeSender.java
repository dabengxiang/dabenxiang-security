package com.dabenxiang.security.core.validate.code.sms;

/**
 * Date:2018/8/20
 * Author: yc.guo the one whom in nengxun
 * Desc:
 */
public interface SmsCodeSender {

    void send(String mobile,String code);
}
