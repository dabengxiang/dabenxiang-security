package com.dabenxiang.security.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Date:2018/8/8
 * Author: yc.guo the one whom in nengxun
 * Desc:
 */
@ConfigurationProperties(prefix = "dabenxiang.security")
public class SecurityProperties {
    private BrowserProperties browser = new BrowserProperties();

    public BrowserProperties getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserProperties browser) {
        this.browser = browser;
    }
}
