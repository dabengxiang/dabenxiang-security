package com.dabenxiang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * project name : mysecurity
 * Date:2018/7/23
 * Author: yc.guo
 * DESC:
 */

@SpringBootApplication
@RestController
@EnableScheduling
@EnableSwagger2
public class DemoApplication {
    
    public  static void main(String[] args){
        SpringApplication.run(DemoApplication.class,args);
    }
    
    @GetMapping("/hello")
    public String hello(){
        return "hello spring security";
    }
}
