package com.dabenxiang.security.core.properties;

import java.time.LocalTime;

/**
 * Date:2018/8/20
 * Author: yc.guo the one whom in nengxun
 * Desc:
 */
public class ValidateCodeProperties {

    private SmsCodeProperties smsCodeProperties = new SmsCodeProperties();

    private ImageCodeProperties imageCodeProperties = new ImageCodeProperties();

    public SmsCodeProperties getSmsCodeProperties() {
        return smsCodeProperties;
    }

    public void setSmsCodeProperties(SmsCodeProperties smsCodeProperties) {
        this.smsCodeProperties = smsCodeProperties;
    }

    public ImageCodeProperties getImageCodeProperties() {
        return imageCodeProperties;
    }

    public void setImageCodeProperties(ImageCodeProperties imageCodeProperties) {
        this.imageCodeProperties = imageCodeProperties;
    }
}
