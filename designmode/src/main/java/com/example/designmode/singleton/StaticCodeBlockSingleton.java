package com.example.designmode.singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/*
 * @Author baijd
 * @Description 单例模式 静态代码块
 * @Date
 * 集合{父类static，子类static，父类大括号，父类构造函数，子类大括号，子类构造函数}的一个子集。
 **/
public class StaticCodeBlockSingleton {

    private static StaticCodeBlockSingleton instance;

    private StaticCodeBlockSingleton() {
        if (instance != null) {
            throw new RuntimeException("实例已经存在，请通过 getInstance()方法获取");
        }
    }

    // 类似饿汉式 通过静态代码块来预先创建
    static {
        instance = new StaticCodeBlockSingleton();
    }

    public static StaticCodeBlockSingleton getInstance() {
        return instance;
    }
}

/*
 * @Author baijd
 * @Description 示例 反射破坏单例模式
 * @Date
 **/
class StaticCodeBlockSingletonTest {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        StaticCodeBlockSingleton singleton1 = StaticCodeBlockSingleton.getInstance();
        StaticCodeBlockSingleton singleton2 = StaticCodeBlockSingleton.getInstance();
        assertEquals(singleton1, singleton2);

        Class<StaticCodeBlockSingleton> clz = StaticCodeBlockSingleton.class;
        Constructor<StaticCodeBlockSingleton> c = clz.getDeclaredConstructor(null);
        c.setAccessible(true);
        StaticCodeBlockSingleton lazySingleton = c.newInstance();
        assertNotEquals(singleton1, lazySingleton);
    }
}
