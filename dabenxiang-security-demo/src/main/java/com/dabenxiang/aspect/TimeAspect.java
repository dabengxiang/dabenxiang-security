package com.dabenxiang.aspect;

import org.apache.tomcat.jni.Proc;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Date:2018/7/27
 * Author: yc.guo the one whom in nengxun
 * Desc:
 */

//@Aspect
//@Component
public class TimeAspect {

    @Around("execution(* com.dabenxiang.web.controller.UserController.*(..)) ")
    public Object handlerControllerMehtod(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("timeAsp进来了！！");
        Object[] args = pjp.getArgs();
        for (Object arg : args) {
            System.out.println("arg is "+arg);
        }

        Object proceed = pjp.proceed(args);

        System.out.println("返回的结果:"+proceed);

        System.out.println("timeAsp离开了！！");

        return proceed;
    }
}
