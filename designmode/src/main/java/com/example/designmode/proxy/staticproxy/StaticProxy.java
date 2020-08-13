package com.example.designmode.proxy.staticproxy;

import com.example.designmode.proxy.IUserDao;
import com.example.designmode.proxy.UserDao;

import java.util.List;

/**
 * @program: java-sample
 * @description: 静态代理
 * @author: baijd-a
 * @create: 2020-08-04 14:45
 **/
public class StaticProxy implements IUserDao {
    // 被代理对象
    IUserDao userDao = new UserDao();

    @Override
    public boolean save(String s) {
        System.out.println("begin save(), do something");
        boolean save = userDao.save(s);
        System.out.println("after save(), do something");
        return save;
    }

    @Override
    public boolean delete(String s) {
        System.out.println("begin delete(), do something");
        boolean delete = userDao.delete(s);
        System.out.println("after delete(), do something");
        return delete;
    }

    @Override
    public boolean modify(String s) {
        System.out.println("begin modify(), do something");
        boolean modify = userDao.modify(s);
        System.out.println("after modify(), do something");
        return modify;
    }

    @Override
    public List<String> query() {
        System.out.println("begin query(), do something");
        List<String> query = userDao.query();
        System.out.println("after query(), do something");
        return query;
    }

    public static void main(String[] args) {
        StaticProxy proxy = new StaticProxy();
        proxy.save("abc");
        proxy.modify("def");
        proxy.query();
        proxy.delete("abc");
    }
}
