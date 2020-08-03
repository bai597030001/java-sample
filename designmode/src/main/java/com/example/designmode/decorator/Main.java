package com.example.designmode.decorator;

/**
 * @program: java-sample
 * @description: main
 * @author: baijd-a
 * @create: 2020-07-28 19:34
 **/
public class Main {
    public static void main(String[] args) {
        Component component = new ConcreteComponent();
        component.sampleOperation();

        System.out.println("....................................................");

        Component dec = new ConcreteDecoratorB(new ConcreteDecoratorA(new ConcreteComponent()));
        dec.sampleOperation();
    }
}
