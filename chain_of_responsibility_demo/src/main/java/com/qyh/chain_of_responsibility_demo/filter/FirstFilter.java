package com.qyh.chain_of_responsibility_demo.filter;

import com.qyh.chain_of_responsibility_demo.handler.AbstractHandler;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;


/**
 * 第一个拦截器
 */
@Order(1)
@Component
public class FirstFilter extends AbstractHandler {
    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("第一个拦截器获得request中的header ---- > " + request.getHeader("token"));
        System.out.println("第一个拦截器");
    }
}
