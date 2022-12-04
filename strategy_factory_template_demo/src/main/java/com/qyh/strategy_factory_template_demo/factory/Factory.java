package com.qyh.strategy_factory_template_demo.factory;


import com.qyh.strategy_factory_template_demo.handler.Handler;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//存放不同handler的工厂
@Component
@Data
public class Factory {
    @Autowired
    private List<Handler> handlerList;
    //不同策略存放不同handler
    private Map<Integer, Handler> logisticsHandlerMap = null;


    //初始化 logisticsHandlerMap
    @PostConstruct
    public void initLogisticsHandlerMap() {
        this.logisticsHandlerMap = handlerList
                .stream()
                .collect(Collectors.toMap(Handler::getCode, handler -> handler));
    }

}
