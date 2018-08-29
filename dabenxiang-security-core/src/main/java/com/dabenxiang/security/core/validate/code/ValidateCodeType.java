package com.dabenxiang.security.core.validate.code;

import com.dabenxiang.security.core.properties.SecurityContants;

/**
 * Date:2018/8/28
 * Author: yc.guo the one whom in nengxun
 * Desc:
 */
public enum ValidateCodeType {

    /**
     * 短信验证码
     */
    SMS{
        @Override
        public String getParamNameOnValidate() {
            return SecurityContants.DEFAULT_PARAMETER_NAME_CODE_SMS;
        }
    },
    IMAGE{
        @Override
        public String getParamNameOnValidate() {
            return SecurityContants.DEFAULT_PARAMETER_NAME_CODE_IMAGE;
        }
    };
    public  abstract String getParamNameOnValidate();
}
