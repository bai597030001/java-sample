package com.example.designmode.singleton;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/*
 * @Author baijd-a
 * @Description CAS实现单例
 * @Date 17:43 2020/6/28
 **/
public class CasSingleton implements Serializable {
    private static final AtomicReference<CasSingleton> INSTANCE = new AtomicReference<CasSingleton>();

    private CasSingleton() {
    }

    /**
     * 用CAS确保线程安全
     */
    public static final CasSingleton getInstance() {
        for (; ; ) {
            CasSingleton current = INSTANCE.get();
            if (current != null) {
                return current;
            }
            current = new CasSingleton();
            if (INSTANCE.compareAndSet(null, current)) {
                return current;
            }
        }
    }
}

class ReflectCasSingletonTest {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        CasSingleton singleton1 = CasSingleton.getInstance();
        CasSingleton singleton2 = CasSingleton.getInstance();
        assertEquals(singleton1, singleton2);

        Class<CasSingleton> clz = CasSingleton.class;
        Constructor<CasSingleton> c = clz.getDeclaredConstructor(null);
        c.setAccessible(true);
        CasSingleton hungrySingleton1 = c.newInstance();
        assertNotEquals(singleton1, hungrySingleton1);
    }
}

/*
 * @Author baijd-a
 * @Description 通过序列化破坏单例
 * @Date 17:31 2020/6/28
 **/
class SerializableCASSingletonTest {

    public static void main(String[] args) {
        CasSingleton singleton = CasSingleton.getInstance();
        File file = new File("CASSingleton.txt");
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
            CasSingleton rSingleton = (CasSingleton) ois.readObject();
            fis.close();
            ois.close();
            System.out.println(rSingleton.hashCode());
            // assertNotEquals(singleton, rSingleton);
            assertNotEquals(singleton, rSingleton);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
