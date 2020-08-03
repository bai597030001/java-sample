package com.example.designmode.adapter;

/**
 * @program: java-sample
 * @description: 适配器模式
 * @author: baijd-a
 * @create: 2020-07-28 19:44
 **/
// 客户端使用的接口
interface Target {
    /*
     * 客户端请求处理的方法
     */
    void request();
}

// 被适配的对象
class Adaptee {
    /*
     * 原本存在的方法
     */
    public void specificRequest(){
    }
}

// 适配器实现
class Adapter implements Target{
    /*
     * 持有需要被适配的接口对象
     */
    private Adaptee adaptee;
    /*
     * 构造方法，传入需要被适配的对象
     * @param adaptee 需要被适配的对象
     */
    public Adapter(Adaptee adaptee){
        this.adaptee = adaptee;
    }
    @Override
    public void request() {
        adaptee.specificRequest();
    }
}

/*
 * 使用适配器的客户端
 */
public class AdapterClient {
    public static void main(String[] args){
        //创建需要被适配的对象
        Adaptee adaptee = new Adaptee();
        //创建客户端需要调用的接口对象
        Target target = new Adapter(adaptee);
        //请求处理
        target.request();
    }
}
