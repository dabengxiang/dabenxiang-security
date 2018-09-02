package com.dabenxiang.security.core.properties;

import lombok.Data;
import org.springframework.boot.autoconfigure.social.SocialProperties;

/**
 * Date:2018/9/2
 * Author:gyc
 * Desc:
 */
@Data
public class WeiXinProperties extends SocialProperties {
    private String providerId = "weixin";

}
