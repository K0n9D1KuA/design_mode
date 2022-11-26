package com.qyh.chain_of_responsibility_demo.filter.union;

import com.qyh.chain_of_responsibility_demo.handler.AbstractHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 责任链对象
 */
@Component
public class HandlerChain {
    //自动注入各个责任链对象
    @Autowired
    private List<AbstractHandler> abstractHandlerList;

    private AbstractHandler abstractHandler;

    //初始化之前执行该方法 将责任链中的对象连接起来
    @PostConstruct
    public void initializeChainFilter() {
        //责任链对象数量
        int handlerSize = abstractHandlerList.size();
        for (int i = 0; i < handlerSize; i++) {
            if (i == 0) {
                //说明是第一个责任链
                abstractHandler = abstractHandlerList.get(0);
            } else {
                //1 2 3
                AbstractHandler aheadHandler = abstractHandlerList.get(i - 1);
                AbstractHandler nextHandler = abstractHandlerList.get(i);
                aheadHandler.setNextHandler(nextHandler);
            }
        }
    }

    //直接调用这个方法进行责任链的调用
    public HttpServletResponse execute(HttpServletRequest request, HttpServletResponse response) {
        abstractHandler.filter(request, response);
        return response;
    }


}
