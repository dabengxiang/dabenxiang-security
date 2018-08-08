package com.dabenxiang.security;

import com.dabenxiang.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Date:2018/8/7
 * Author: yc.guo the one whom in nengxun
 * Desc:
 */
@Configuration
public class BrowerSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Bean
    public BCryptPasswordEncoder getBCryptPasswordEncoder(){
        return  new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.httpBasic().and().authorizeRequests().anyRequest().authenticated();
        http.formLogin().loginPage("/authentication/require")
                .loginProcessingUrl("/authentication/form")
                .and()
                .authorizeRequests()
                .antMatchers("/authentication/require",
                        securityProperties.getBrowser().getLoginPage()).permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable();

//        super.configure(http);
    }


}
