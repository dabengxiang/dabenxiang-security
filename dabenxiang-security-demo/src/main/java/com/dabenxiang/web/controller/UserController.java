package com.dabenxiang.web.controller;

import com.dabenxiang.dto.User;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
    
    @GetMapping
    public User query(User user){return user;}
    
    @PostMapping 
    public List<User> page(User user, @PageableDefault(size = 17,page = 2,sort = "username,asc")
            Pageable pageable){
        System.out.println(ReflectionToStringBuilder.toString(user, ToStringStyle.MULTI_LINE_STYLE));

        System.out.println(pageable.getPageSize());
        System.out.println(pageable.getPageNumber());
        System.out.println(pageable.getSort());

        List<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());
        users.add(new User());
        return users;
    }
    
    

}
