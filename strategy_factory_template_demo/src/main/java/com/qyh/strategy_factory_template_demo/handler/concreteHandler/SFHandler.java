package com.qyh.strategy_factory_template_demo.handler.concreteHandler;


import com.qyh.strategy_factory_template_demo.constant.LogisticsConstant;
import com.qyh.strategy_factory_template_demo.factory.Factory;
import com.qyh.strategy_factory_template_demo.handler.Handler;
import lombok.Data;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;


@Service
@Data
public class SFHandler extends Handler {

    @Override
    public String calculateB() {
        return "这是顺丰物流计价B方法";
    }

    @Override
    public Integer getCode() {
        return LogisticsConstant.SF_LOGISTICS;
    }
}
