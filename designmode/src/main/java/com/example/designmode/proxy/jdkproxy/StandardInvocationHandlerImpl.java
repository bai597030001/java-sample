package com.example.designmode.proxy.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @program: java-sample
 * @description: invocationhandler
 * @author: baijd-a
 * @create: 2020-08-05 09:51
 **/
public class StandardInvocationHandlerImpl implements InvocationHandler {

    //需要被代理的类对象
    private Object object = null;

    public StandardInvocationHandlerImpl() {
    }

    public StandardInvocationHandlerImpl(Object object) {
        this.object = object;
    }

    // 绑定不同的对象，代理类不变，对象改变
    public Object bind(Object object) {

        this.object = object;

        /**
         * 参数1：类加载器，采用target（被代理的类）本身的类加载器
         * 参数2：生成的动态代理对象挂在那个接口下，采用target（被代理的类）实现的接口下，即Woker接口。
         * 参数3：定义实现方法逻辑的代理类，this表示当前对象，它必须实现InvocationHandler的invoke方法
         *
         * @return 代理类*/
        return Proxy.newProxyInstance(object.getClass().getClassLoader(), object.getClass().getInterfaces(), this);
    }

    /**
     * 参数解释
     *
     * @param proxy 代理类的实例对象
     * @param method 要调用的method
     * @param args method参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("日志：打印信息");
        //通过反射调用 被代理类的方法
        return method.invoke(object, args);
    }
}
