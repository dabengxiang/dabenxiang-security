package com.dabenxiang.security.core.validate.code.image;

import com.dabenxiang.security.core.validate.code.ValidateCode;

import java.awt.image.BufferedImage;
import java.time.LocalTime;

/**
 * Date:2018/8/9
 * Author: yc.guo the one whom in nengxun
 * Desc:
 */
public class ImageCode extends ValidateCode {
    private BufferedImage bufferedImage;

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

    public ImageCode(BufferedImage bufferedImage, String code, LocalTime localTime) {
        super(code,localTime);
        this.bufferedImage = bufferedImage;
    }

    public ImageCode(BufferedImage bufferedImage,String code, int timeLenth){
        super(code,timeLenth);
        this.bufferedImage = bufferedImage;
    }
}
