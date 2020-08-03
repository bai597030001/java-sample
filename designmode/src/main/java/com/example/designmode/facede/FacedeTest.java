package com.example.designmode.facede;

/**
 * @program: java-sample
 * @description: 门面模式
 * @author: baijd-a
 * @create: 2020-07-28 19:47
 **/
/**
 * 子系统角色类
 * 电脑CPU
 */
class CPU {
    public void startUp(){
        System.out.println("cpu is  startUp...");
    }
    public void shutDown(){
        System.out.println("cpu is  shutDown...");

    }
}

/**
 * 子系统角色类
 * 电脑硬盘
 */
class Disk {
    public void startUp() {
        System.out.println("disk is  startUp...");
    }

    public void shutDown() {
        System.out.println("disk is  shutDown...");

    }
}

/**
 * 子系统角色类
 * 电脑内存
 */
class Memory {
    public void startUp() {
        System.out.println("memory is  startUp...");
    }

    public void shutDown() {
        System.out.println("memory is  shutDown...");

    }
}

/**
 * 外观角色
 * 电脑
 * 用户通过操作当前类即可达到操作所有子系统的目的
 */
class Computer {
    private CPU cpu;
    private Disk disk;
    private Memory memory;

    public Computer() {
        cpu = new CPU();
        disk = new Disk();
        memory = new Memory();
    }

    public void startUp() {
        cpu.startUp();
        disk.startUp();
        memory.startUp();
    }

    public void shutDown() {
        cpu.shutDown();
        disk.shutDown();
        memory.shutDown();
    }
}

/**
 * 外观模式 测试类
 */
public class FacedeTest {
    public static void main(String[] args) {
        Computer computer = new Computer();
        computer.startUp();
        System.out.println("------------------");
        computer.shutDown();
    }
}
