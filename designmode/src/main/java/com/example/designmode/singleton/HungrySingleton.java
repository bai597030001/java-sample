package com.example.designmode.singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/*
 * @Author baijd
 * @Description 单例模式 饿汉式
 * @Date  2020/6/28
 **/
public class HungrySingleton {
    // 1.必须是 static
    private static HungrySingleton instance = new HungrySingleton();

    // 必须是私有构造
    private HungrySingleton() {
        if (instance != null) {
            throw new RuntimeException("实例已经存在，请通过 getInstance()方法获取");
        }
    }

    public static HungrySingleton getInstance() {
        return instance;
    }
}

/*
 * @Author baijd
 * @Description 饿汉式测试用例，通过反射可以破坏单例
 * @Date  2020/6/28
 **/
class HungrySingletonTest {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        HungrySingleton singleton1 = HungrySingleton.getInstance();
        HungrySingleton singleton2 = HungrySingleton.getInstance();
        assertEquals(singleton1, singleton2);

        Class<HungrySingleton> clz = HungrySingleton.class;
        Constructor<HungrySingleton> c = clz.getDeclaredConstructor(null);
        c.setAccessible(true);
        HungrySingleton hungrySingleton1 = c.newInstance();
        assertNotEquals(singleton1, hungrySingleton1);
    }
}
