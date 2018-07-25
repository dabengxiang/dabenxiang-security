package com.dabenxiang.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.dabenxiang.dto.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;

/**
 * project name : mysecurity
 * Date:2018/7/23
 * Author: yc.guo
 * DESC:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserControllerTest {

    @Autowired
    private WebApplicationContext wac;
    
    private MockMvc mockMvc;
    
    @Before
    public void setup(){
        mockMvc= MockMvcBuilders.webAppContextSetup(wac).build();
    }
    
    @Test
    public void whenGetInfoSuccess() throws Exception {
        String user = mockMvc.perform(get("/user/1").contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("dabenxiang"))
                .andReturn().getResponse().getContentAsString();
        
        System.out.println(user);
    }


    @Test
    public void whenGetInfoFail() throws  Exception{
        mockMvc.perform(get("/user/a").contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is4xxClientError());
    }



    @Test
    public void query() throws  Exception{
        String contentAsString = mockMvc.perform(post("/user")
                .param("id","1")
                .param("username","dabenxiang")
                .param("password","123")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(contentAsString );

    }

    @Test
    public void whenCreateSuccess() throws  Exception {
        String contentAsString = mockMvc.perform(get("/user")
                .param("username", "张三")
                .param("password", "554"))
                .andExpect(status().isOk()).andExpect(jsonPath("$.length()").value(2))
                .andReturn().getResponse().getContentAsString();
        System.out.println(contentAsString);
    }


    /**
     * 测试不同view的返回
     * @throws Exception
     */
    @Test
    public void getSimpleView() throws  Exception{
        String contentAsString = mockMvc.perform(get("/user")
                .param("userName","李四")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(contentAsString);
    }


    /**
     * 传送json的
     */
    @Test
    public void createUserJson() throws  Exception{


        String content = "{\"username\":\"tom\",\"password\":\"tom\",\"birthday\":"+new Date().getTime()+"}";


        String contentAsString = mockMvc.perform(post("/user/json").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(contentAsString);
    }






}
