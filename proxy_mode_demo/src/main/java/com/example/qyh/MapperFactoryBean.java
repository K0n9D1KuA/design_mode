package com.example.qyh;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


/**
 * @author K0n9D1KuA
 * @version 1.0
 * @description: 工厂方法 这种注入对象的方式
 * 较为灵活 可以设置对象的属性 各种操作
 * @date 2022/11/27 14:09
 */

@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class MapperFactoryBean<T> implements FactoryBean<T> {
    //接口信息
    private Class<T> mapperInterface;


    /*
     * @author: K0n9D1KuA
     * @description: 返回mapper接口的代理对象
     * @param: null
     * @return:
     * @date: 2022/11/27 13:31
     */

    @Override
    public T getObject() throws Exception {
        InvocationHandler invocationHandler = (proxy, method, args) -> {
            //获得接口方法上面的注解
            Select annotation = method.getAnnotation(Select.class);
            //获得自定义sql
            String sql = annotation.value();
            //模拟sql语句解析
            //把占位符换成实参
            sql = sql.replace("#{id}", args[0].toString());
            log.info("本次执行的sql语句 --> {} ", sql);
            //模拟sql查询操作
            return args[0].toString();
        };
        //创建代理对象
        return (T) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{mapperInterface}, invocationHandler);
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }
}
