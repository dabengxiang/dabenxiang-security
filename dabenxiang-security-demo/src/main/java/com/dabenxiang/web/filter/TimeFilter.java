package com.dabenxiang.web.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

/**
 * project name : mysecurity
 * Date:2018/7/25
 * Author: yc.guo
 * DESC:
 */


public class TimeFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("timeFilter 初始化了");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {


        System.out.println("timeFilter 进来了");
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("timeFilter 结束了");


    }

    @Override
    public void destroy() {

        System.out.println("timeFilter destory了");

    }
}
