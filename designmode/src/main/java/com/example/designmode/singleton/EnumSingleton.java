package com.example.designmode.singleton;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public enum EnumSingleton {
    INSTANCE;

    public static EnumSingleton getInstance() {
        return INSTANCE;
    }

    public void func1() {
        System.out.println("func1");
    }
}

// 反射：没有构造函数，会抛出异常。(就算你在枚举里加了构造函数，也是一样的)
class ReflectEnumSingletonTest {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        EnumSingleton singleton1 = EnumSingleton.getInstance();
        EnumSingleton singleton2 = EnumSingleton.getInstance();
        assertEquals(singleton1, singleton2);

        Class clz = singleton1.getClass();
        EnumSingleton enumSingleton1 = (EnumSingleton) clz.newInstance();
        assertEquals(singleton1, enumSingleton1);

        Constructor enumConstructor = EnumSingleton.class.getConstructor();
        enumConstructor.setAccessible(true);
        EnumSingleton enumSingleton2 = (EnumSingleton) enumConstructor.newInstance();
        assertEquals(singleton1, enumSingleton2);
    }
}

// 序列化：支持
class SerializableEnumSingleTest {
    public static void main(String[] args) {
        EnumSingleton singleton = EnumSingleton.getInstance();
        File file = new File("EnumSingleton.txt");
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
            EnumSingleton rSingleton = (EnumSingleton) ois.readObject();
            fis.close();
            ois.close();
            System.out.println(rSingleton.hashCode());
            assertEquals(singleton, rSingleton);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

// 克隆：枚举无法克隆，没有这样的方法。
class CloneEnumSingleTest {
    public static void main(String[] args) {
        EnumSingleton singleton = EnumSingleton.getInstance();
        // EnumSingleton singletonClone = (EnumSingleton) singleton.clone();
    }
}
