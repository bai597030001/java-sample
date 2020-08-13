package com.example.designmode.proxy.cglibproxy;

/**
 * @program: java-sample
 * @description: cglib工厂
 * @author: baijd-a
 * @create: 2020-08-04 17:15
 **/

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Cglib子类代理工厂
 * 对UserDao对象在内存中动态构建出一个子类对象
 */
public class CglibProxyFactory implements MethodInterceptor {
    //维护目标对象
    private Object target;

    public CglibProxyFactory(Object target) {
        this.target = target;
    }

    //获取目标对象的代理对象
    public Object getProxyInstance() {
        //1. 实例化工具类
        Enhancer en = new Enhancer();
        //2. 设置父类对象
        en.setSuperclass(this.target.getClass());
        //3. 设置回调函数
        en.setCallback(this);
        //4. 创建子类，也就是代理对象
        return en.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("Begin Transaction");
        //执行目标对象的方法
        Object returnValue = method.invoke(target, objects);
        System.out.println("End Transaction");
        return returnValue;
    }
}
