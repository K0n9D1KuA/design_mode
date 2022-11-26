package com.qyh.demo;

import com.alibaba.fastjson.JSON;
import com.qyh.demo.annotation.MyDoor;
import com.qyh.demo.config.WhiteListVerificationService;
import org.apache.commons.beanutils.BeanUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author K0n9D1KuA
 * @version 1.0
 * @description: 切面类
 * @date 2022/11/27 1:09
 */

@Aspect
@Component
public class DoJoinPoint {

    private Logger logger = LoggerFactory.getLogger(DoJoinPoint.class);

    @Autowired
    private WhiteListVerificationService starterService;
    private static String[] types = {
            "java.lang.Integer", "java.lang.Double",
            "java.lang.Float", "java.lang.Long", "java.lang.Short",
            "java.lang.Byte", "java.lang.Boolean", "java.lang.Char",
            "java.lang.String", "int", "double", "long", "short", "byte",
            "boolean", "char", "float"};

    private static HashMap<String, Class> map = new HashMap<String, Class>() {
        {
            put("java.lang.Integer", int.class);
            put("java.lang.Double", double.class);
            put("java.lang.Float", float.class);
            put("java.lang.Long", long.class);
            put("java.lang.Short", short.class);
            put("java.lang.Boolean", boolean.class);
            put("java.lang.Char", char.class);
        }
    };


    // 切点 添加了@MyDoor注解的方法会进行aop
    @Pointcut("@annotation(com.qyh.demo.annotation.MyDoor)")
    public void aopPoint() {
    }


    //环绕通知
    @Around("aopPoint()")
    public Object doRouter(ProceedingJoinPoint jp) throws Throwable {
        //获得方法元对象
        Method method = getMethod(jp);
        //获得方法上的myDoor注解
        MyDoor myDoor = method.getAnnotation(MyDoor.class);
        //获得白名单的字段 userId
        String key = myDoor.key();
        //需要校验的userId
        String keyValue = null;
        //获得所有的参数名字
        List<String> fieldsName = getFieldsName(jp);
        System.out.println("所有参数名称");
        fieldsName.stream().forEach(System.out::println);
        //获得所有参数值
        List<String> paramValue = getParamValue(jp);
        System.out.println("所有参数值");
        paramValue.stream().forEach(System.out::println);
        //先通过key 从参数名list中找到他所对应的下标 然后再用这个下标就可以获得值
        keyValue = paramValue.get(fieldsName.indexOf(key));
        System.out.println("最后的结果是:" + key + "=" + keyValue);
        //获得所有白名单里面的内容
        String[] split = starterService.split(",");
        //白名单过滤
        for (String str : split) {
            if (keyValue.equals(str)) {
                //匹配上了 就可以执行方法
                return jp.proceed();
            }
        }
        //没有匹配上 返回json字符串
        return returnObject(myDoor, method);
    }

    //获得添加了注解的接口的方法元对象
    private Method getMethod(JoinPoint jp) throws NoSuchMethodException {
        Signature sig = jp.getSignature();
        MethodSignature methodSignature = (MethodSignature) sig;
        return getClass(jp).getMethod(methodSignature.getName(), methodSignature.getParameterTypes());
    }

    private Class<? extends Object> getClass(JoinPoint jp) throws NoSuchMethodException {
        return jp.getTarget().getClass();
    }

    //返回对象
    private Object returnObject(MyDoor doGate, Method method) throws IllegalAccessException, InstantiationException {
        //获得方法返回类型
        Class<?> returnType = method.getReturnType();
        //从注解中拿到返回值
        String returnJson = doGate.returnJson();
        if ("".equals(returnJson)) {
            return returnType.newInstance();
        }
        return JSON.parseObject(returnJson, returnType);
    }

    //获得所有的参数值 并且以{'111','222'}的形式返回
    public static List<String> getParamValue(JoinPoint joinPoint) {
        StringBuilder sb = new StringBuilder();
        //获取所有的参数
        Object[] args = joinPoint.getArgs();
        for (int k = 0; k < args.length; k++) {
            Object arg = args[k];
            // 获取对象类型
            String typeName = arg.getClass().getTypeName();
            for (String t : types) {
                //1 判断是否是基础类型
                if (t.equals(typeName)) {
                    sb.append(arg + "; ");
                } else {
                    //2 通过反射获取实体类属性
                    sb.append(getFieldsValue(arg));
                }
            }
        }
        return Arrays.asList(sb.toString().split(";"));
    }

    //解析实体类，获取实体类中的属性
    public static String getFieldsValue(Object obj) {
        //通过反射获取所有的字段，getFileds()获取public的修饰的字段
        //getDeclaredFields获取private protected public修饰的字段
        Field[] fields = obj.getClass().getDeclaredFields();
        String typeName = obj.getClass().getTypeName();
        for (String t : types) {
            if (t.equals(typeName)) {
                return "";
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (Field f : fields) {
            //在反射时能访问私有变量
            f.setAccessible(true);
            try {
                for (String str : types) {
                    //这边会有问题，如果实体类里面继续包含实体类，这边就没法获取。
                    //其实，我们可以通递归的方式去处理实体类包含实体类的问题。
                    if (f.getType().getName().equals(str)) {
                        sb.append(f.getName() + " : " + f.get(obj) + ", ");
                    }
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        sb.append("}");
        return sb.toString();
    }

    //返回方法的参数名 并且以{"sdadas","dsajj"} 的形式返回
    private static List<String> getFieldsName(JoinPoint joinPoint) throws ClassNotFoundException, NoSuchMethodException {
        String classType = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        Class<?>[] classes = new Class[args.length];
        for (int k = 0; k < args.length; k++) {
            if (!args[k].getClass().isPrimitive()) {
                //获取的是封装类型而不是基础类型
                String result = args[k].getClass().getName();
                Class s = map.get(result);
                classes[k] = s == null ? args[k].getClass() : s;
            }
        }
        ParameterNameDiscoverer pnd = new DefaultParameterNameDiscoverer();
        //获取指定的方法，第二个参数可以不传，但是为了防止有重载的现象，还是需要传入参数的类型
        Method method = Class.forName(classType).getMethod(methodName, classes);
        String[] parameterNames = pnd.getParameterNames(method);
        return Arrays.asList(parameterNames);
    }
}
