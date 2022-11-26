package com.qyh.chain_of_responsibility_demo.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 责任链抽象类  -- 顶层抽象父类
 */
public abstract class AbstractHandler {
    //责任链中的下一个对象
    private AbstractHandler nextHandler;

    /**
     * set责任链中的下一个对象
     */
    public void setNextHandler(AbstractHandler nextHandler) {
        this.nextHandler = nextHandler;
    }
    //get方法
    public AbstractHandler getNextHandler() {
        return nextHandler;
    }

    /**
     * 具体参数拦截逻辑交给子类去实现
     */
    public void filter(HttpServletRequest request, HttpServletResponse response) {
        doFilter(request, response);
        if (getNextHandler() != null) {
            getNextHandler().filter(request, response);
        }
    }

    //具体的参数拦截逻辑 交给子类去实现
    public abstract void doFilter(HttpServletRequest request, HttpServletResponse response);
}
