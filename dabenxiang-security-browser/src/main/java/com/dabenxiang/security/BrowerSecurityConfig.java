package com.dabenxiang.security;

import com.dabenxiang.security.core.authentication.AbstractChannelSecurityConfig;
import com.dabenxiang.security.core.properties.SecurityProperties;
import com.dabenxiang.security.core.validate.code.ValidateCodeSecurityConfig;
import com.dabenxiang.security.core.authentication.mobile.SmsCodeConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

/**
 * Date:2018/8/7
 * Author: yc.guo the one whom in nengxun
 * Desc:
 */
@Configuration
public class BrowerSecurityConfig extends AbstractChannelSecurityConfig {

    @Autowired
    private SecurityProperties securityProperties;


    @Autowired
    private DataSource dataSource;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    @Autowired
    private SmsCodeConfig smsCodeConfig;

    @Autowired
    private SpringSocialConfigurer springSocialConfigurer;

    @Bean
    public BCryptPasswordEncoder getBCryptPasswordEncoder(){
        return  new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        this.defaultApply(http);
        http.apply(validateCodeSecurityConfig)
            .and().apply(springSocialConfigurer)
            .and().apply(smsCodeConfig)
            .and().rememberMe()
            .tokenRepository(getTokenRepository())
            .tokenValiditySeconds(6000)
            .userDetailsService(myUserDetailsService)
        .and()
        .authorizeRequests()
        .antMatchers("/authentication/require",
                                "/code/*",
                                "/user/regist",
                                securityProperties.getSocialProperties().getQq().getSigunUpUrl(),
                                securityProperties.getBrowser().getLoginPage()).permitAll()
        .anyRequest().authenticated()
        .and().csrf().disable();
    }


    @Bean
    public PersistentTokenRepository getTokenRepository(){
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
//        jdbcTokenRepository.setCreateTableOnStartup(true);
        return jdbcTokenRepository;
    }


}
