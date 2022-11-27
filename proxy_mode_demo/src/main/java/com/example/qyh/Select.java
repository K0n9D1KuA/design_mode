package com.example.qyh;

import java.lang.annotation.*;

//模拟mybatis的注解
//@Document 是 java 在生成文档，是否显示注解的开关。
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Select {
    //sql语句
    String value() default "";
}
