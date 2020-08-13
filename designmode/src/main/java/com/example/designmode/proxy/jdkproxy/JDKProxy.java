package com.example.designmode.proxy.jdkproxy;

import com.example.designmode.proxy.IPermissionDao;
import com.example.designmode.proxy.IUserDao;
import com.example.designmode.proxy.PermissionDao;
import com.example.designmode.proxy.UserDao;

/**
 * @program: java-sample
 * @description: JDK动态代理
 * @author: baijd-a
 * @create: 2020-08-04 14:34
 **/
public class JDKProxy {
    public static void main(String[] args) {
        /**
         * 这一句是生成代理类的class文件，前提是你需要在工程根目录下创建com/sun/proxy目录，不然会报找不到路径的io异常
         * -Dsun.misc.ProxyGenerator.saveGeneratedFiles=true
         **/
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        proxyFactoryTest();
        proxyAdviancedFactoryTest();
        proxyInvocationHandlerTest();
    }

    public static void proxyFactoryTest() {
        //目标对象
        IUserDao userDao = new UserDao();
        //原始类型 class com.example.designmode.proxy.UserDao
        System.out.println(userDao.getClass());

        //给定目标对象，动态创建代理对象
        IUserDao proxy = (IUserDao) new ProxyFactory(userDao).getProxyInstance();
        //代理对象类型 class com.sun.proxy.$Proxy0
        System.out.println(proxy.getClass());

        proxy.save("abc");
        proxy.modify("def");
        proxy.delete("abc");
        proxy.query();
    }

    public static void proxyAdviancedFactoryTest() {
        ProxyAdvancedFactory proxyAdvancedFactory = new ProxyAdvancedFactory();
        //目标对象
        UserDao userDao = new UserDao();
        //原始类型 class com.example.designmode.proxy.UserDao
        System.out.println(userDao.getClass());

        IUserDao proxy = (IUserDao) proxyAdvancedFactory.bind(userDao);
        //代理对象类型 class com.sun.proxy.$Proxy0
        System.out.println(proxy.getClass());

        proxy.save("abc");
        proxy.modify("def");
        proxy.delete("abc");
        proxy.query();

        PermissionDao permissionDao = new PermissionDao();
        IPermissionDao permissionDaoProxy = (IPermissionDao) proxyAdvancedFactory.bind(permissionDao);
        permissionDaoProxy.addPermission("des");
        permissionDaoProxy.getPermission();
        permissionDaoProxy.deletePermission("des");
    }

    public static void proxyInvocationHandlerTest() {
        StandardInvocationHandlerImpl invocationHandler = new StandardInvocationHandlerImpl();

        // 被代理对象 UserDao
        UserDao userDao = new UserDao();
        System.out.println(userDao.getClass());

        // 代理对象
        UserDao proxyUserDao = (UserDao) invocationHandler.bind(userDao);
        proxyUserDao.save("abc");

        // 被代理对象 PermissionDao
        PermissionDao permissionDao = new PermissionDao();
        System.out.println(userDao.getClass());

        // 代理对象
        PermissionDao proxyPermissionDao = (PermissionDao) invocationHandler.bind(permissionDao);
        proxyPermissionDao.addPermission("user");
    }
}
