package com.dabenxiang.security.core.validate.code;

import java.awt.image.BufferedImage;
import java.time.LocalTime;

/**
 * Date:2018/8/9
 * Author: yc.guo the one whom in nengxun
 * Desc:
 */
public class ImageCode {

    private BufferedImage bufferedImage;

    private String code;

    private LocalTime localTime;


    public  boolean isExpire(){
        return LocalTime.now().isAfter(localTime);
    }


    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    public void setBufferedImage(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
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

    public ImageCode(BufferedImage bufferedImage, String code, LocalTime localTime) {
        this.bufferedImage = bufferedImage;
        this.code = code;
        this.localTime = localTime;
    }

    public ImageCode(BufferedImage bufferedImage,String code, int timeLenth){

        this.bufferedImage = bufferedImage;
        this.code = code;
        LocalTime now = LocalTime.now();
        this.localTime = now;
    }
}
