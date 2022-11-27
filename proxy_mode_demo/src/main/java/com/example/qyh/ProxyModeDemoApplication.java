package com.example.qyh;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@Slf4j
public class ProxyModeDemoApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(ProxyModeDemoApplication.class, args);
        ConfigurableListableBeanFactory beanFactory = configurableApplicationContext.getBeanFactory();
        IUserDao userDao = beanFactory.getBean("userDao", IUserDao.class);
        String s = userDao.queryUserInfo("1");
        log.info("执行的结果是---->{}", s);

    }

}
