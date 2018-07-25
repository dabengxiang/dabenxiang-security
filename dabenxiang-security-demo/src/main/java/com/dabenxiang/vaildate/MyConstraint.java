package com.dabenxiang.vaildate;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

/**
 * Date:2018/7/25
 * Author: yc.guo the one whom in nengxun
 * Desc:
 */

@Target({ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MyConstraintValidator.class)
public @interface MyConstraint {

    String message() default "xixi";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };


}
