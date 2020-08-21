package com.example.designmode.decorator;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

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

        String s = "hello, world";
        new DataInputStream(new BufferedInputStream(new ByteArrayInputStream(s.getBytes())))
    }
}
