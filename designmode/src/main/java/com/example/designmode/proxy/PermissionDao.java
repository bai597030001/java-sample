package com.example.designmode.proxy;

import java.util.List;

/**
 * @program: java-sample
 * @description: permissionDao
 * @author: baijd-a
 * @create: 2020-08-04 16:02
 **/
public class PermissionDao implements IPermissionDao{
    @Override
    public List<String> getPermission() {
        System.out.println("-----------getPermission()----------------");
        return null;
    }

    @Override
    public boolean addPermission(String s) {
        System.out.println("-----------addPermission(String s)----------------");
        return true;
    }

    @Override
    public boolean deletePermission(String s) {
        System.out.println("-----------deletePermission(String s)----------------");
        return true;
    }
}
