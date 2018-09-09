package com.dabenxiang.security.core.properties;

import lombok.Data;

/**
 * Date:2018/9/8
 * Author:gyc
 * Desc:
 */

@Data
public class SessionProperties  {
    public Integer MAX_SESISON = 1;

    public  String SESSION_INVALID_URL = SecurityConstants.DEFAULT_SESSION_INVALID_URL;

    public boolean maxSessionsPreventsLogin;


}
