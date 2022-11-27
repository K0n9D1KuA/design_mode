package com.example.qyh;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.*;
import org.springframework.stereotype.Component;


//BeanDefinitionRegistryPostProcessor功能如下：
//1、因为实现了BeanFactoryPostProcessor接口，所以可以修改bean定义中属性，
//2、还可以动态的添加bean到spring容器中 而且它的执行会优先其他自定义的实现了该接口的BeanFactoryPostProcessor类，具体参考PostProcessorRegistrationDelegate

/**
 * @author K0n9D1KuA
 * @version 1.0
 * @description: 通过扫描包 将各种mapperFactoryBean注入spring容器
 * @date 2022/11/27 14:08
 */

@Component
public class RegisterBeanFactory implements BeanDefinitionRegistryPostProcessor {
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
        GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
        genericBeanDefinition.setBeanClass(MapperFactoryBean.class);
        genericBeanDefinition.setScope("singleton");
        //获得构造器
        //真实的mybatis中 这里是包扫描  添加公众IUserDao.class
        genericBeanDefinition.getConstructorArgumentValues().addGenericArgumentValue(IUserDao.class);
        BeanDefinitionHolder definitionHolder = new BeanDefinitionHolder(genericBeanDefinition, "userDao");
        //注册
        BeanDefinitionReaderUtils.registerBeanDefinition(definitionHolder, beanDefinitionRegistry);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

    }
}
