package com.example.designmode.proxy;

import java.util.List;

public interface IPermissionDao {
    List<String> getPermission();
    boolean addPermission(String s);
    boolean deletePermission(String s);
}
