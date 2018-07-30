package com.dabenxiang.web.async;

import com.dabenxiang.service.HelloService;
import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Map;
import java.util.concurrent.Callable;

/**
 * Date:2018/7/30
 * Author: yc.guo the one whom in nengxun
 * Desc:
 */

@RestController
@RequestMapping("/order")
public class AsyncController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private HelloService helloService;

    @Autowired
    private MockQueue mockQueue;

    @Autowired
    private DeferedResultSet deferedResultSet;


    @GetMapping("/callable")
    public Callable callableMethod(){
        logger.info("主线程开始。。");

        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                logger.info("副线程开始。。");
                helloService.hello("abc");
                Thread.sleep(1000);
                logger.info("副线程结束。。");
                return "hello";
            }
        };
        logger.info("主线程结束。。");
        return callable;
    }

    /**
     * 模拟下订单
     * @return
     */
    @GetMapping("/1")
    public DeferredResult<String> order1(){
        logger.info("主线程开始！！");
        String orderNo = RandomStringUtils.randomNumeric(8);
        mockQueue.setCompeleOrder(orderNo);
        DeferredResult result = new DeferredResult();
        deferedResultSet.getResults().put(mockQueue.getCompeleOrder(),result);
        logger.info("主线程结束");
        return result;
    }

}
