package com.example.designmode.decorator;

/**
 * @program: java-sample
 * @description: 装饰(Decorator)角色：持有一个构件(Component)对象的实例，并定义一个与抽象构件接口一致的接口。
 * @author: baijd-a
 * @create: 2020-07-28 19:32
 **/
public class Decorator implements Component{
    private Component component;

    public Decorator(Component component){
        this.component = component;
    }

    @Override
    public void sampleOperation() {
        // 委派给构件
        component.sampleOperation();
    }
}
