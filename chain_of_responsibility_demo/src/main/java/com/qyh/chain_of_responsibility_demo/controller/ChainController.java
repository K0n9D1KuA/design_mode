package com.qyh.chain_of_responsibility_demo.controller;


import com.qyh.chain_of_responsibility_demo.filter.union.HandlerChain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class ChainController {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;
    @Autowired
    HandlerChain handlerChain;

    @GetMapping("/testChain")
    public void testChain() {
        handlerChain.execute(request, response);
    }
}
