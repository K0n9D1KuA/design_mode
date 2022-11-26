package com.qyh.demo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author K0n9D1KuA
 * @version 1.0
 * @description: 只有添加了该注解的方法才会进行白名单校验
 * key: 通过此 可以拿到方法里面的入参 例如指定userId
 * 那么就可以拿到方法参数里面的userId
 * resultJson 白名单校验未通过的返回值
 * @date 2022/11/27 1:07
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface MyDoor {

    String key() default "";

    String returnJson() default "";

}
