package com.example.designmode.eventlisten;

import java.util.Vector;

/**
 * @program: java-sample
 * @description: 事件监听模式
 * @author: baijd-a
 * @create: 2020-08-18 19:11
 **/
public class JDKEventListenner {
    public static void main(String[] args) {
        MyEventSource source = new MyEventSource();

        MyEventListenerImpl listener1 = new MyEventListenerImpl();
        MyEventListenerImpl listener2 = new MyEventListenerImpl();
        MyEventListenerImpl listener3 = new MyEventListenerImpl();

        source.registerListener(listener1);
        source.registerListener(listener2);
        source.registerListener(listener3);

        source.attachEvent();
    }
}

class MyEventSource {
    Vector<MyEventListener> listeners;

    public MyEventSource() {
        this.listeners = new Vector<>();
    }

    public Vector<MyEventListener> getListeners() {
        return listeners;
    }

    public void setListeners(Vector<MyEventListener> listeners) {
        this.listeners = listeners;
    }

    public void registerListener(MyEventListener listener) {
        listeners.add(listener);
    }

    public void removeListener(MyEventListener listener) {
        listeners.remove(listener);
    }

    /**
     * @Author baijd-a
     * @Description 事件源触发事件，向事件监听器传播事件
     **/
    public void attachEvent() {
        for (MyEventListener listener : listeners) {
            listener.handleEvent(new MyEvent(this));
        }
    }
}

class MyEvent extends java.util.EventObject {
    public MyEvent(Object source) {
        super(source);
    }

    @Override
    public MyEventSource getSource() {
        return (MyEventSource) source;
    }

    /**
     * @Author baijd-a
     * @Description 事件回调
     **/
    public void doEvent(){
        System.out.println("通知一个事件源 source :"+ this.getSource());
    }
}

/**
 * 所有事件监听器接口都必须扩展 EventListener 接口。
 * 回调接口类
 */
interface MyEventListener extends java.util.EventListener {
    /**
     * @Author baijd-a
     * @Description 事件监听器接收到事件后处理事件方法
     *              回调函数接口
     **/
    void handleEvent(MyEvent var1);
}


/**
 * 事件监听器实现类
 */
class MyEventListenerImpl implements MyEventListener {
    @Override
    public void handleEvent(MyEvent var1) {
        var1.doEvent();
    }
}
