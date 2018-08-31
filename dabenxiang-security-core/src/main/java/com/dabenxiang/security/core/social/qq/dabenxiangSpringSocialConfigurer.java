package com.dabenxiang.security.core.social.qq;

import com.dabenxiang.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * Date:2018/8/31
 * Author: yc.guo the one whom in nengxun
 * Desc:
 */
public class dabenxiangSpringSocialConfigurer extends SpringSocialConfigurer {

    private String securityProperties;


    public dabenxiangSpringSocialConfigurer(String securityProperties) {
        this.securityProperties = securityProperties;
    }


    @Override
    protected <T> T postProcess(T object) {
        T result = super.postProcess(object);
        SocialAuthenticationFilter socialAuthenticationFilter = (SocialAuthenticationFilter) result;
        socialAuthenticationFilter.setFilterProcessesUrl(securityProperties);
        return result;
    }
}
