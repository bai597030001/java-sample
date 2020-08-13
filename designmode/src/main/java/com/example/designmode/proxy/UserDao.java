package com.example.designmode.proxy;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: java-sample
 * @description: userDao
 * @author: baijd-a
 * @create: 2020-08-04 15:41
 **/
public class UserDao implements IUserDao{
    @Override
    public boolean save(String s) {
        System.out.println("---------已经保存数据 " + s + "----------");
        return true;
    }

    @Override
    public boolean delete(String s) {
        System.out.println("---------已经删除数据 " + s + "----------");
        return true;
    }

    @Override
    public boolean modify(String s) {
        System.out.println("---------已经修改数据 " + s + "----------");
        return true;
    }

    @Override
    public List<String> query() {
        System.out.println("---------已经获取数据----------");
        return new ArrayList<String>() {{
            add("query1");
            add("query2");
            add("query3");
        }};
    }

}
