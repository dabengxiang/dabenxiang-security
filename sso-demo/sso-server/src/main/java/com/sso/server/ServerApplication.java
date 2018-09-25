package com.sso.server;

import org.omg.CORBA.ObjectHelper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Date:2018/9/25
 * Author: yc.guo the one whom in nengxun
 * Desc:
 */

@SpringBootApplication
public class ServerApplication {

    public static void main(String[] args){
        SpringApplication.run(ServerApplication.class);
    }




}
