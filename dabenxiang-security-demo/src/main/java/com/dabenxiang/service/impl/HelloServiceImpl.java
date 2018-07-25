package com.dabenxiang.service.impl;

import com.dabenxiang.service.HelloService;
import org.springframework.stereotype.Service;

/**
 * Date:2018/7/25
 * Author: yc.guo the one whom in nengxun
 * Desc:
 */

@Service
public class HelloServiceImpl implements HelloService {
    @Override
    public void hello(String msg) {
        System.out.println(msg);
    }
}
