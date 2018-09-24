package com.dabenxiang.security.app;

import com.dabenxiang.security.core.social.DabenxiangSpringSocialConfigurer;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Configuration;

import javax.swing.*;

/**
 * Date:2018/9/23
 * Author:gyc
 * Desc:
 */

@Configuration
public class SpringSocialConfigurerPostProcesser implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String s) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String name) throws BeansException {
        if("dabenxiangSpringSocialConfigurer".equalsIgnoreCase(name)){
            DabenxiangSpringSocialConfigurer config = (DabenxiangSpringSocialConfigurer) bean;
            config.signupUrl("/social/signUp");
            return config;
        }


        return bean;
    }
}
