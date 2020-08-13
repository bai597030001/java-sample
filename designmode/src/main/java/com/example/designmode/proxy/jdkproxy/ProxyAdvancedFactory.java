package com.example.designmode.proxy.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @program: java-sample
 * @description: 动态绑定对象
 * @author: baijd-a
 * @create: 2020-08-04 17:11
 **/
public class ProxyAdvancedFactory {
    //维护一个目标对象
    private Object target;

    public ProxyAdvancedFactory() {
    }

    // 绑定不同的对象，代理类不变，对象改变
    public Object bind(Object object) {
        this.target = object;
        /**
         * 参数1：类加载器，采用target（被代理的类）本身的类加载器
         * 参数2：生成的动态代理对象挂在那个接口下，采用target（被代理的类）实现的接口下，即Woker接口。
         * 参数3：定义实现方法逻辑的代理类，this表示当前对象，，
         *
         * 它必须实现InvocationHandler的invoke方法
         *
         * @return 代理类*/
        return Proxy.newProxyInstance(
                object.getClass().getClassLoader(),
                object.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("Before invoke method");
                        //执行目标对象方法
                        Object returnValue = method.invoke(target, args);
                        System.out.println("After invoke method");
                        return returnValue;
                    }
                });
    }
}
