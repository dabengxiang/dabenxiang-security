package com.dabenxiang.security.core.validate.code.image;

import com.dabenxiang.security.core.validate.code.ValidateCode;
import com.dabenxiang.security.core.validate.code.impl.AbstractValidateCodeProcessor;
import com.sun.imageio.plugins.common.ImageUtil;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * Date:2018/8/20
 * Author: yc.guo the one whom in nengxun
 * Desc:
 */
@Component("imageValidateCodeProcessor")
public class ImageValidateCodeProcessor extends AbstractValidateCodeProcessor<ImageCode> {


    @Override
    protected void send(ServletWebRequest servletWebRequest, ImageCode imageCode) throws IOException {
        ImageIO.write(imageCode.getBufferedImage(), "JPEG", servletWebRequest.getResponse().getOutputStream());
    }


}
