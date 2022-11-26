package com.qyh.decorator_mode.myDecorator;

import com.qyh.decorator_mode.handlerIntercepter.HandlerIntercepter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author K0n9D1Kua
 * @version 1.0
 * @description: 抽象类 将原来功能待扩展的对象作为成员变量
 * 然后调用原来对象的方法 即可做到功能扩展 但不改变原来对象的
 * 结构
 * @date 2022/11/26 19:36
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public abstract class MyDecorator implements HandlerIntercepter {

    private HandlerIntercepter handlerIntercepter;

    @Override
    public boolean preHandle(String request) {
        return handlerIntercepter.preHandle(request);
    }
}
