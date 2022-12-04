package com.qyh.strategy_factory_template_demo.handler.concreteHandler;


import com.qyh.strategy_factory_template_demo.constant.LogisticsConstant;
import com.qyh.strategy_factory_template_demo.factory.Factory;
import com.qyh.strategy_factory_template_demo.handler.Handler;
import lombok.Data;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;


@Service
@Data
public class JDHandler extends Handler {

    @Override
    public String calculateA() {
        return "这是京东计价A方法";
    }

    @Override
    public Integer getCode() {
        return LogisticsConstant.JD_LOGISTICS;
    }
}
