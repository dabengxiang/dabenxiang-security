package com.dabenxiang.security.core.validate.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Date:2018/8/9
 * Author: yc.guo the one whom in nengxun
 * Desc:
 */

@RestController
public class ValidateController {


    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Autowired
    private ImageGenerate imageGenerate;

    private final String PIC_CODE_SESSION = "PIC_VALIVATE_CODE";


    @GetMapping("/code/image")
    public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ImageCode generate = imageGenerate.generate();
        sessionStrategy.setAttribute(new ServletWebRequest(request),PIC_CODE_SESSION,generate);
        ImageIO.write(generate.getBufferedImage(),"JPEG", response.getOutputStream());
    }



}
