package com.example.designmode.singleton;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/*
 * @Author baijd-a
 * @Description 双重校验锁实现单例
 * @Date 17:35 2020/6/28
 **/
public class DoubleCheckLockSingleton implements Serializable {
    // 1.必须是 static, 保证全局唯一
    // 2.volatile 防止指令重排(内存屏障)
    private volatile static DoubleCheckLockSingleton instance;

    // 必须是私有构造
    private DoubleCheckLockSingleton() {
        if (instance != null) {
            throw new RuntimeException("实例已经存在，请通过 getInstance()方法获取");
        }
    }

    public /*synchronized*/ static DoubleCheckLockSingleton getInstance() {
        if (instance == null) {
            synchronized (DoubleCheckLockSingleton.class) {
                if (instance == null) {
                    /**
                     * 1. 为 instance 分配内存空间
                     * 2. 初始化 instance
                     * 3. 将 instance 指向分配的内存地址
                     */
                    instance = new DoubleCheckLockSingleton();
                }
            }
        }
        return instance;
    }

    //防止序列化破坏单例模式
    public Object readResolve() {
        return instance;
    }
}

/*
 * @Author baijd-a
 * @Description 通过序列化破坏单例
 * @Date 17:31 2020/6/28
 **/
class SerializableDCLClassSingletonTest {

    public static void main(String[] args) {
        DoubleCheckLockSingleton singleton = DoubleCheckLockSingleton.getInstance();
        File file = new File("DCLSingleton.txt");
        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(singleton);
            fos.close();
            oos.close();
            System.out.println(singleton.hashCode());
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            DoubleCheckLockSingleton rSingleton = (DoubleCheckLockSingleton) ois.readObject();
            fis.close();
            ois.close();
            System.out.println(rSingleton.hashCode());
            // assertNotEquals(singleton, rSingleton);
            assertEquals(singleton, rSingleton);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

/*
 * @Author baijd-a
 * @Description 通过反射破坏静态内部类实现的单例
 * @Date 17:31 2020/6/28
 **/
class ReflectDCLClassSingletonTest {

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<DoubleCheckLockSingleton> clz = DoubleCheckLockSingleton.class;
        Constructor<DoubleCheckLockSingleton> c = clz.getDeclaredConstructor(null);
        c.setAccessible(true);
        DoubleCheckLockSingleton classSingleton1 = c.newInstance();
        DoubleCheckLockSingleton classSingleton2 = DoubleCheckLockSingleton.getInstance();
        assertNotEquals(classSingleton1, classSingleton2);
    }
}
