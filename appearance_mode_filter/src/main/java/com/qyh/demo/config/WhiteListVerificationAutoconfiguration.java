package com.qyh.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author K0n9D1KuA
 * @version 1.0
 * @description: 配置类
 * @date 2022/11/27 0:50
 */

@Configuration
//通俗的说 就是springboot工程引用了这个包 才会构建这个bean
@ConditionalOnClass(WhiteListVerificationService.class)
@EnableConfigurationProperties(WhiteListVerificationProperties.class)

public class WhiteListVerificationAutoconfiguration {

    @Autowired
    private WhiteListVerificationProperties properties;

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "kongdekua.door", value = "enabled", havingValue = "true")
    WhiteListVerificationService starterService() {
        return new WhiteListVerificationService(properties.getUserStr());
    }
    //@ConditionalOnBean（仅仅在当前上下文中存在某个对象时，才会实例化一个Bean）
    //@ConditionalOnClass（某个class位于类路径上，才会实例化一个Bean）
    //@ConditionalOnExpression（当表达式为true的时候，才会实例化一个Bean）
    //@ConditionalOnMissingBean（仅仅在当前上下文中不存在某个对象时，才会实例化一个Bean）
    //@ConditionalOnMissingClass（某个class类路径上不存在的时候，才会实例化一个Bean）
    //@ConditionalOnNotWebApplication（不是web应用）
    //@ConditionalOnProperty实现是通过havingValue与配置文件中的值对比,返回为true则配置类生效,反之失效.


}
