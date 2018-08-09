package com.dabenxiang.security.core.properties;

import org.springframework.stereotype.Component;

/**
 * Date:2018/8/8
 * Author: yc.guo the one whom in nengxun
 * Desc:
 */
public class BrowserProperties {
    private String loginPage = "/imooc-signIn.html";

    private LoginType loginType = LoginType.JSON;

    public BrowserProperties() {
    }

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }
}
