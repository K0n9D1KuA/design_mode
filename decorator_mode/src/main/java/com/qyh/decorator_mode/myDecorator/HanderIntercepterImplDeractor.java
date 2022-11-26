package com.qyh.decorator_mode.myDecorator;

import com.qyh.decorator_mode.handlerIntercepter.HandlerIntercepter;

/**
 * @author K0n9D1KuA
 * @version 1.0
 * @description: 装饰器实现类
 * @date 2022/11/26 19:59
 */

public class HanderIntercepterImplDeractor extends MyDecorator {
    public HanderIntercepterImplDeractor(HandlerIntercepter handlerIntercepter) {
        super(handlerIntercepter);
    }

    @Override
    public boolean preHandle(String request) {
        System.out.println("前置增强");
        super.preHandle(request);
        System.out.println("后置增强");
        return true;
    }
}
