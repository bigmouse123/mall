package com.jiankun.mall.annotation;

import java.lang.annotation.*;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/3/18 20:27
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyLog {
    String module() default "";

}
