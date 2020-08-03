package com.example.designmode.singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EnumSingletonInstance {
    //私有化构造函数
    private EnumSingletonInstance() {
    }

    static enum SingletonEnum {
        //创建一个枚举对象，该对象先天为单例
        INSTANCE;
        private EnumSingletonInstance singletion;

        // 私有化枚举的构造函数
        // JVM保证这个方法绝对只调用一次
        private SingletonEnum() {
            singletion = new EnumSingletonInstance();
        }

        public EnumSingletonInstance getInstnce() {
            return singletion;
        }
    }

    //对外提供一个获取Singleton对象的静态方法
    public static EnumSingletonInstance getInstance() {
        return SingletonEnum.INSTANCE.getInstnce();
    }

    public static void main(String[] args) {
        System.out.println(EnumSingletonInstance.getInstance());
        System.out.println(EnumSingletonInstance.getInstance());
        System.out.println(EnumSingletonInstance.getInstance() == EnumSingletonInstance.getInstance());
    }
}

class ReflectEnumSingletonInstanceTest {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        EnumSingletonInstance singleton1 = EnumSingletonInstance.getInstance();
        EnumSingletonInstance singleton2 = EnumSingletonInstance.getInstance();
        assertEquals(singleton1, singleton2);
        Class<EnumSingletonInstance> clz = EnumSingletonInstance.class;
        Constructor<EnumSingletonInstance> c = clz.getDeclaredConstructor(null);
        c.setAccessible(true);
        EnumSingletonInstance singletonInstance = c.newInstance();
        assertEquals(singleton1, singletonInstance);
    }
}
