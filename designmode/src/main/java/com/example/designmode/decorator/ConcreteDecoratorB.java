package com.example.designmode.decorator;

/**
 * @program: java-sample
 * @description: 具体装饰(ConcreteDecorator)角色：负责给构件对象“贴上”附加的责任
 * @author: baijd-a
 * @create: 2020-07-28 19:33
 **/
public class ConcreteDecoratorB extends Decorator {

    public ConcreteDecoratorB(Component component) {
        super(component);
    }

    @Override
    public void sampleOperation() {
        // 写相关的业务代码
        super.sampleOperation();
        // 写相关的业务代码
        System.out.println("ConcreteDecoratorB: sampleOperation()");
    }
}
