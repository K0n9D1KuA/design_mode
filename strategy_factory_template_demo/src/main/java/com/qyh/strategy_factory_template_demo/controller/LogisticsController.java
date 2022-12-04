package com.qyh.strategy_factory_template_demo.controller;


import com.qyh.strategy_factory_template_demo.entity.LogisticsEntity;
import com.qyh.strategy_factory_template_demo.factory.Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogisticsController {
    @Autowired
    private Factory factory;

    @PostMapping("/calculate")
    public String calculate(@RequestBody LogisticsEntity logisticsEntity) {
        return factory
                .getLogisticsHandlerMap()
                .get(logisticsEntity.getLogisticsType())
                .calculateA();
    }
}
