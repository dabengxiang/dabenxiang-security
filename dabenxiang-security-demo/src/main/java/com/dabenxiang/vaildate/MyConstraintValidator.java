package com.dabenxiang.vaildate;

import com.dabenxiang.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Date:2018/7/25
 * Author: yc.guo the one whom in nengxun
 * Desc:
 */
public class MyConstraintValidator implements ConstraintValidator<MyConstraint, Object> {

    @Autowired
    private HelloService helloService;


    @Override
    public void initialize(MyConstraint constraintAnnotation) {

        System.out.println("init");
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        helloService.hello("你好");
        System.out.println(value);
        return false;
    }
}
