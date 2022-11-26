package com.qyh.decorator_mode.handlerIntercepter.impl;

import com.qyh.decorator_mode.handlerIntercepter.HandlerIntercepter;

/**
 * @author K0n9D1Kua
 * @version 1.0
 * @description: 原有的对象 现考虑给该对象功能升级，但不破坏该对象的结构
 * @date 2022/11/26 19:32
 */

public class HandlerIntercepterImpl implements HandlerIntercepter {
    @Override
    public boolean preHandle(String request) {
        System.out.println("原有的功能 打印" + request);
        return true;
    }
}
