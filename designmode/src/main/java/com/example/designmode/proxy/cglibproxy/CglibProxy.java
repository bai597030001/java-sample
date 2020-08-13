package com.example.designmode.proxy.cglibproxy;

/**
 * @program: java-sample
 * @description: cglib代理
 * @author: baijd-a
 * @create: 2020-08-04 16:22
 **/
public class CglibProxy {
    public static void main(String[] args) {
        //目标对象
        RoleDao roleDao = new RoleDao();
        //生成代理对象
        RoleDao roleDaoProxy = (RoleDao) new CglibProxyFactory(roleDao).getProxyInstance();
        //调用对象方法
        roleDaoProxy.addRole("root");
        roleDaoProxy.getRoles();
    }
}

