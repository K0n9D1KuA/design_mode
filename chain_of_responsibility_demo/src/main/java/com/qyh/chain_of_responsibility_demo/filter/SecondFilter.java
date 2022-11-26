package com.qyh.chain_of_responsibility_demo.filter;


import com.qyh.chain_of_responsibility_demo.handler.AbstractHandler;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 第二个拦截器
 */
@Order(2)
@Component
public class SecondFilter extends AbstractHandler {
    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("第二个拦截器获得request中的header ---- > " + request.getHeader("token"));
        System.out.println("第二个拦截器");
    }
}
