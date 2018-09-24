package com.dabenxiang.security.core.social;

import org.springframework.social.security.SocialAuthenticationFilter;

/**
 * Date:2018/9/22
 * Author:gyc
 * Desc:
 */
public interface SocialAuthenticationFilterPostProcessor  {

    void process(SocialAuthenticationFilter socialAuthenticationFilter);
}
