package com.qyh.decorator_mode.handlerIntercepter;

/**
 * @author K0n9D1Kua
 * @version 1.0
 * @description: 待增强的对象的接口
 * @date 2022/11/26 19:46
 */

public interface HandlerIntercepter {

    boolean preHandle(String request);
}
