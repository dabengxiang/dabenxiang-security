package com.dabenxiang.security.support;

/**
 * Date:2018/8/8
 * Author: yc.guo the one whom in nengxun
 * Desc:
 */
public class SimpleResponse {
    private Object content;

    public SimpleResponse(String content) {
        this.content = content;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
