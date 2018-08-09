package com.dabenxiang.security.core.validate.code;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Date:2018/8/9
 * Author: yc.guo the one whom in nengxun
 * Desc:
 */
@Configuration
public class ValicateCodeBeanConfig {

    @Bean
    public ImageGenerate getImageGenerate(){
        return new DefaultImageGenerate();
    }

}
