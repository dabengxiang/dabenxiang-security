package com.dabenxiang.web.controller;

import com.dabenxiang.exception.UserNotException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;

/**
 * project name : mysecurity
 * Date:2018/7/25
 * Author: yc.guo
 * DESC:
 */

@ControllerAdvice
public class ControllerExceptionHandler  {

    @ExceptionHandler(UserNotException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Object handleUserNotException(UserNotException ex){
        System.out.println("ControllerAdvice 在执行。。");
        HashMap<String, Object> map = new HashMap<>();
        map.put("id",ex.getId());
        map.put("msg",ex.getMessage());
        return map;
    }

}
