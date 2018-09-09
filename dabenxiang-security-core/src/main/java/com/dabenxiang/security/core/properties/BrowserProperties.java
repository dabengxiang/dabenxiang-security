package com.dabenxiang.security.core.properties;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * Date:2018/8/8
 * Author: yc.guo the one whom in nengxun
 * Desc:
 */

@Data
public class BrowserProperties {
    private String loginPage = "/imooc-signIn.html";


    private String logOutUrl = "/demo-logout.html";

    private LoginType loginType = LoginType.JSON;

    private SessionProperties session = new SessionProperties();


    public BrowserProperties() {
    }

}
