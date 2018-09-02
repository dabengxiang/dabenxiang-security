package com.dabenxiang.security.core.properties;

import lombok.Data;
import org.springframework.boot.autoconfigure.social.SocialProperties;

/**
 * Date:2018/8/31
 * Author: yc.guo the one whom in nengxun
 * Desc:
 */

@Data
public class QQProperties extends SocialProperties {
    private String providerId = "qq";
    private String filterProcessesUrl = "/qqLogin";
    private String sigunUpUrl = "/demo-signUp.html";

}
