package com.qyh.decorator_mode;

import com.qyh.decorator_mode.handlerIntercepter.impl.HandlerIntercepterImpl;
import com.qyh.decorator_mode.myDecorator.HanderIntercepterImplDeractor;
import com.qyh.decorator_mode.myDecorator.MyDecorator;

public class test {
    public static void main(String[] args) {
        MyDecorator myDecorator = new HanderIntercepterImplDeractor(new HandlerIntercepterImpl());
        myDecorator.preHandle("测试数据");
    }
}
