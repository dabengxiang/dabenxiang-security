package com.dabenxiang.web.controller;

import com.dabenxiang.dto.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * project name : mysecurity
 * Date:2018/7/23
 * Author: yc.guo
 * DESC:
 */


@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/{id:\\d+}")
    public User query(@PathVariable String id){
        User user = new User();
        user.setUsername("dabenxiang");
        return user;
    }
    

}
