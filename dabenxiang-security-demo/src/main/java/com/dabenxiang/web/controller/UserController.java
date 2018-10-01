package com.dabenxiang.web.controller;

import com.dabenxiang.dto.User;
import com.dabenxiang.exception.UserNotException;
//import com.dabenxiang.security.app.social.AppSignUpUtils;
import com.dabenxiang.security.core.properties.SecurityProperties;
import com.fasterxml.jackson.annotation.JsonView;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * project name : mysecurity
 * Date:2018/7/23
 * Author: yc.guo
 * DESC:
 */


@RestController
@EnableScheduling
@RequestMapping("/user")
@Api("用户处理")
@Slf4j
public class UserController {


    @Autowired
    private SecurityProperties securityProperties;


    @Autowired
    private ProviderSignInUtils providerSignInUtils;

//    @Autowired
//    private AppSignUpUtils appSignUpUtils;


//    @GetMapping("/me")
//    public Object getCurrentUser(@AuthenticationPrincipal UserDetails user) {
//        return user;
//    }


    @GetMapping("/me")
    public Object getCurrentUser(Authentication  user,HttpServletRequest request) throws UnsupportedEncodingException {
        String token = StringUtils.substringAfter(request.getHeader("Authorization"), "bearer ");
        byte[] bytes = securityProperties.getOauth2().getJwtKey().getBytes("utf-8");

        Claims body = Jwts.parser().setSigningKey(bytes)
                .parseClaimsJws(token).getBody();


        Object company = body.get("company");
        log.info(company.toString());
        return body;
    }

    @PostMapping("/regist")
    public void regist(User user, HttpServletRequest request){
        String username = user.getUsername();
//        appSignUpUtils.signUp(new ServletWebRequest(request),username);
        providerSignInUtils.doPostSignUp(username,new ServletWebRequest(request));
    }


    @GetMapping("/userDetails")
    public UserDetails getUser(@AuthenticationPrincipal UserDetails userDetails){
        return userDetails;
    }

    @GetMapping("/{id:\\d+}")
    @ApiOperation("查询用户")
    public User query(@PathVariable @ApiParam("用户id") String id){
        User user = new User();
        user.setUsername("dabenxiang");
        return user;
    }

//    @GetMapping
//    public User query(User user){return user;}

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


    @GetMapping
   // @JsonView(User.UserSimpleView.class)  //简单的传输
    @JsonView(User.UserDetailView.class)
    public User getSimpleView(@Valid User inputUser,BindingResult errors){
        if(errors.hasErrors()){
            errors.getAllErrors().stream().forEach(error-> System.out.println(error));
        }

        User user = new User();

        user.setBirthday(new Date());
        user.setId("1234");
        user.setUsername("胡亚");
        user.setPassword("2222");
        return user;
    }

    @JsonView(User.UserDetailView.class)
    @GetMapping("/error")
    public User errorExample(@Valid User inputUser){
        User user = new User();

        user.setBirthday(new Date());
        user.setId("1234");
        user.setUsername("胡亚");
        user.setPassword("2222");
        return user;
    }

    @PostMapping("/json")
    public User getJsonUser(@Valid @RequestBody User user){
        return user;
    }

    @GetMapping("/handlerException/{id:\\d+}")
    public User handlerException(@PathVariable String id){
        throw new UserNotException(id);
    }

}
