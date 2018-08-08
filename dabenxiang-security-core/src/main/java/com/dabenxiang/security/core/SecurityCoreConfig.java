package com.dabenxiang.security.core;

import com.dabenxiang.security.core.properties.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Date:2018/8/8
 * Author: yc.guo the one whom in nengxun
 * Desc:
 */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityCoreConfig {
}
