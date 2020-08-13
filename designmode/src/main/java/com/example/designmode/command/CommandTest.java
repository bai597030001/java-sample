package com.example.designmode.command;

/**
 * @program: java-sample
 * @description: 命令模式
 * @author: baijd-a
 * @create: 2020-08-07 17:06
 **/
public class CommandTest {
    public static void main(String[] args) {
        //创建接收者
        Receiver receiver = new Receiver();
        //创建命令对象，设定其接收者
        Command command = new ConcreteCommand(receiver);
        //创建请求者，把命令对象设置进去
        Invoker invoker = new Invoker(command);
        //执行方法
        invoker.action();
    }
}

class Receiver {
    /**
     * 真正执行命令相应的操作
     */
    public void action() {
        System.out.println("执行操作");
    }
}

interface Command {
    /**
     * 执行方法
     */
    void execute();
}

class ConcreteCommand implements Command {
    /**
     * 持有相应的接收者对象
     */
    private Receiver receiver = null;

    /**
     * 构造方法
     *
     * @param receiver
     */
    public ConcreteCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        //通常会转调接收者的形影方法，让接收者来真正执行功能
        receiver.action();
    }
}

class Invoker {
    /**
     * 持有命令对象
     */
    private Command command = null;

    /**
     * 构造方法
     *
     * @param command
     */
    public Invoker(Command command) {
        this.command = command;
    }

    /**
     * 行动方法
     */
    public void action() {
        command.execute();
    }
}
