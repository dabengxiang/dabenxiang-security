package com.dabenxiang.security.core.validate.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Date:2018/8/28
 * Author: yc.guo the one whom in nengxun
 * Desc:
 */
@Component
public class ValidateCodeProcessorHolder {

    @Autowired
    private Map<String, ValidateCodeProcessor> processorMap;


    public ValidateCodeProcessor findValidateCodeProcessor(ValidateCodeType type) {
       return findValidateCodeProcessor(type.toString().toLowerCase());
    }


    public ValidateCodeProcessor findValidateCodeProcessor(String  type) {
        String name = type.toLowerCase() + ValidateCodeProcessor.class.getSimpleName();
        ValidateCodeProcessor validateCodeProcessor = processorMap.get(name);
        return  validateCodeProcessor;
    }

}