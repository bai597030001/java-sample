package com.example.designmode.decorator;

/**
 * @program: java-sample
 * @description: 具体构件(ConcreteComponent)角色：定义一个将要接收附加责任的类
 * @author: baijd-a
 * @create: 2020-07-28 19:31
 **/
public class ConcreteComponent implements Component {
    @Override
    public void sampleOperation() {
        System.out.println("ConcreteComponent: sampleOperation()");
        // 写相关的业务代码
    }
}
