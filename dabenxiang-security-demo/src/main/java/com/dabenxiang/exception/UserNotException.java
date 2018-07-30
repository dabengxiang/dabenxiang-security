package com.dabenxiang.exception;

/**
 * project name : mysecurity
 * Date:2018/7/25
 * Author: yc.guo
 * DESC:
 */
public class UserNotException extends  RuntimeException {
    private static final long serialVersionUID = -6112780192479692859L;

    private String id;

    public UserNotException(String id) {
        super("user not exist");
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
