package com.example.designmode.singleton;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/*
 * @Author baijd
 * @Description 过静态内部类的方式实现单例模式是线程安全的，同时静态内部类不会在Singleton类加载时就加载，
 *              而是在调用getInstance()方法时才进行加载，达到了懒加载的效果。
 *              缺点：无法传参
 * @Date 2020/6/28
 **/
public class StaticInnerClassSingleton implements Serializable {
    //静态内部类
    private static class SingletonHandler {
        private static StaticInnerClassSingleton instance = new StaticInnerClassSingleton();
    }

    private StaticInnerClassSingleton() {
    }

    /* 加载一个类时，其内部类不会同时被加载；
    当且仅当内部类的某个静态成员（静态域、构造器、静态方法等）被调用时，一个内部类才会被加载 */
    public static StaticInnerClassSingleton getInstance() {
        return SingletonHandler.instance;
    }

    //防止序列化破坏单例模式
    public Object readResolve() {
        return SingletonHandler.instance;
    }
}

/*
 * @Author baijd-a
 * @Description 通过序列化破坏单例
 * @Date 17:31 2020/6/28
 **/
class SerializableStaticInnerClassSingletonTest {

    public static void main(String[] args) {
        StaticInnerClassSingleton singleton = StaticInnerClassSingleton.getInstance();
        File file = new File("MySingleton.txt");
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
            StaticInnerClassSingleton rSingleton = (StaticInnerClassSingleton) ois.readObject();
            fis.close();
            ois.close();
            System.out.println(rSingleton.hashCode());
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
class ReflectStaticInnerClassSingletonTest {

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<StaticInnerClassSingleton> clz = StaticInnerClassSingleton.class;
        Constructor<StaticInnerClassSingleton> c = clz.getDeclaredConstructor(null);
        c.setAccessible(true);
        StaticInnerClassSingleton classSingleton1 = c.newInstance();
        StaticInnerClassSingleton classSingleton2 = StaticInnerClassSingleton.getInstance();
        assertNotEquals(classSingleton1, classSingleton2);
    }
}
