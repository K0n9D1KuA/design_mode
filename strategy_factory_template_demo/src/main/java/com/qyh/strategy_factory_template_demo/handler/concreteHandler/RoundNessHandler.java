package com.qyh.strategy_factory_template_demo.handler.concreteHandler;


import com.qyh.strategy_factory_template_demo.constant.LogisticsConstant;
import com.qyh.strategy_factory_template_demo.factory.Factory;
import com.qyh.strategy_factory_template_demo.handler.Handler;
import lombok.Data;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;


@Service
@Data
public class RoundNessHandler extends Handler {
    //圆通物流
    //计价方式

    @Override
    public String calculateB() {
        return "这是圆通物流计价B方法";
    }

    /**
     * 注册
     */
    @PostConstruct
    public void addLogisticsServiceImplToFactory() {
        Factory.registerLogisticsHandler(LogisticsConstant.ROUNDNESS_LOGISTICS, this);
    }
}
