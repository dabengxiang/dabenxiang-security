package com.dabenxiang.web.async;

import org.springframework.stereotype.Component;

/**
 * Date:2018/7/30
 * Author: yc.guo the one whom in nengxun
 * Desc:
 */

@Component
public class MockQueue {

    private String placeOrder;
    private String compeleOrder;

    public String getPlaceOrder() {
        return placeOrder;
    }

    public void setPlaceOrder(String placeOrder) {
        this.placeOrder = placeOrder;
    }

    public String getCompeleOrder() {
        return compeleOrder;
    }

    public void setCompeleOrder(String compeleOrder) {
        this.compeleOrder = compeleOrder;
    }
}
