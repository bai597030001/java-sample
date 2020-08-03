package com.example.designmode.singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/*
 * @Author baijd
 * @Description 单例模式 懒汉式
 * @Date  2020/6/28
 **/
public class LazySingleton {
    // 1.必须是 static
    private static LazySingleton instance;

    // 必须是私有构造
    private LazySingleton() {
        if (instance !=null){
            throw new RuntimeException("实例已经存在，请通过 getInstance()方法获取");
        }
    }

    public synchronized static LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }
}

class LazySingletonTest {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        LazySingleton singleton1 = LazySingleton.getInstance();
        LazySingleton singleton2 = LazySingleton.getInstance();
        assertEquals(singleton1, singleton2);

        Class<LazySingleton> clz = LazySingleton.class;
        Constructor<LazySingleton> c = clz.getDeclaredConstructor(null);
        c.setAccessible(true);
        LazySingleton lazySingleton = c.newInstance();
        assertNotEquals(singleton1, lazySingleton);
    }
}
