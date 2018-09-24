package com.dabenxiang.security.core.social;

import com.dabenxiang.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * Date:2018/8/31
 * Author: yc.guo the one whom in nengxun
 * Desc:
 */
public class DabenxiangSpringSocialConfigurer extends SpringSocialConfigurer {

    private String filterProcessesUrl;


    @Autowired(required = false)
    private SocialAuthenticationFilterPostProcessor socialAuthenticationFilterPostProcessor;

    public DabenxiangSpringSocialConfigurer(String filterProcessesUrl) {
        this.filterProcessesUrl = filterProcessesUrl;
    }




    @Override
    protected <T> T postProcess(T object) {
        T result = super.postProcess(object);
        SocialAuthenticationFilter socialAuthenticationFilter = (SocialAuthenticationFilter) result;
        socialAuthenticationFilter.setFilterProcessesUrl(filterProcessesUrl);


        if(socialAuthenticationFilterPostProcessor!=null){
            socialAuthenticationFilterPostProcessor.process(socialAuthenticationFilter);
        }

        return result;
    }



}
