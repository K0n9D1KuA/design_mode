package com.qyh.strategy_factory_template_demo.factory;


import com.qyh.strategy_factory_template_demo.handler.Handler;

import java.util.HashMap;
import java.util.Map;

/**
 * 工厂设计模式
 */
public class Factory {
    //不同策略存放不同handler
    private static Map<Integer, Handler> logisticsHandler = new HashMap<>();

    //根据logisticsType 找到  LogisticsService
    public static Handler getInvokeStrategy(Integer logisticsType) {
        return logisticsHandler.get(logisticsType);
    }

    //注册 LogisticsHandler
    public static void registerLogisticsHandler(Integer logisticsType, Handler logisticsService) {
        //健壮性判断
        if (logisticsType == null || logisticsService == null) {
            return;
        } else {
            logisticsHandler.put(logisticsType, logisticsService);
        }
    }
}
