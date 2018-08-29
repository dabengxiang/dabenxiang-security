package com.dabenxiang.security.core.validate.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Date:2018/8/9
 * Author: yc.guo the one whom in nengxun
 * Desc:
 */

@RestController
public class ValidateController {


    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Autowired
    private Map<String,ValidateCodeProcessor> validateCodeProcessors;

    @GetMapping("/code/{type}")
    public void createCode(HttpServletRequest request, HttpServletResponse response, @PathVariable("type")String type) throws IOException {
        validateCodeProcessors.get(type+"ValidateCodeProcessor").create(new ServletWebRequest(request,response));

    }



}
