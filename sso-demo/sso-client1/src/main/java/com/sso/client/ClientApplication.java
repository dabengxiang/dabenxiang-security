package com.sso.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Date:2018/9/25
 * Author: yc.guo the one whom in nengxun
 * Desc:
 */


@SpringBootApplication
@RestController
@EnableOAuth2Sso
public class ClientApplication {

    public static void main(String[] args){
        SpringApplication.run(ClientApplication.class);
    }

    @GetMapping("/user")
    public Object getUser(Authentication authentication){
        return authentication;
    }

}
