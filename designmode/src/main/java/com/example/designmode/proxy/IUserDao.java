package com.example.designmode.proxy;

import java.util.List;

public interface IUserDao {
    boolean save(String s);

    boolean delete(String s);

    boolean modify(String s);

    List<String> query();
}
