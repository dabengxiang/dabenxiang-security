package com.dabenxiang.security.core.validate.code;

import java.awt.image.BufferedImage;
import java.time.LocalTime;
import java.util.logging.Logger;

/**
 * Date:2018/8/20
 * Author: yc.guo the one whom in nengxun
 * Desc:
 */
public class ValidateCode {
    private String code;
    private LocalTime localTime;

    public  boolean isExpire(){
        return LocalTime.now().isAfter(localTime);
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalTime getLocalTime() {
        return localTime;
    }

    public void setLocalTime(LocalTime localTime) {
        this.localTime = localTime;
    }

    public ValidateCode(String code, LocalTime localTime) {
        this.code = code;
        this.localTime = localTime;
    }

    public ValidateCode(String code, int timeLenth){
        this.code = code;
        LocalTime now = LocalTime.now();
        this.localTime = now.plusSeconds(timeLenth);
    }


}
