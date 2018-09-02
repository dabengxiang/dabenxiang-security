package com.dabenxiang.security;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Component;

/**
 * Date:2018/9/1
 * Author:gyc
 * Desc:
 */
@Component
public class DemoSecurity implements ConnectionSignUp {

    /**
     * 当social验证成功时，自动登录这里就会返回的值就是数据库里的id标识userId
     * @param connection
     * @return
     */
    @Override
    public String execute(Connection<?> connection) {
        return connection.getDisplayName();
    }
}
