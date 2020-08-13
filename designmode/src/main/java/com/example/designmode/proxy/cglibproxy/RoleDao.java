package com.example.designmode.proxy.cglibproxy;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: java-sample
 * @description: 角色表
 * @author: baijd-a
 * @create: 2020-08-04 17:16
 **/
public class RoleDao {
    public List<String> getRoles() {
        System.out.println("getRoles()");
        return new ArrayList<String>() {{
            add("test");
            add("user");
            add("admin");
        }};
    }

    public boolean addRole(String role) {
        System.out.println("addRole(String role)");
        return true;
    }
}
