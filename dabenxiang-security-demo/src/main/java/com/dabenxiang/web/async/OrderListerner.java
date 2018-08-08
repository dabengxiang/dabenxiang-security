package com.dabenxiang.web.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * Date:2018/7/30
 * Author: yc.guo the one whom in nengxun
 * Desc:
 */

//@Component
public class OrderListerner {

    @Autowired
    private MockQueue mockQueue;

    @Autowired
    private DeferedResultSet deferedResultSet;

    private Logger logger = LoggerFactory.getLogger(OrderListerner.class);

    @Scheduled(fixedDelay = 1000)
    public void listenOrder() throws InterruptedException {
        logger.info("副线程外部！！");
        while (true){
            String compeleOrder = mockQueue.getCompeleOrder();
            if(compeleOrder!=null){
                logger.info("订单号:"+compeleOrder+"开始！！");
                DeferredResult result = deferedResultSet.getResults().get(compeleOrder);
                Thread.sleep(1000);
                mockQueue.setCompeleOrder(null);
                logger.info("订单号:"+compeleOrder+"结束！！");
                result.setResult("ok");
            }else{
                    Thread.sleep(1000);
            }
        }
    }

}
