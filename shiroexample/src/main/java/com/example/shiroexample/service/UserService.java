package com.example.shiroexample.service;

import com.example.shiroexample.dao.UserDAO;
import com.example.shiroexample.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    public boolean addUser(UserModel userModel) {
        UserModel user = userDAO.getUserByEmail(userModel.getEmail());
        return user == null && userDAO.addUser(userModel);
    }

    public String checkPassword(String userName, String passwd) {
        UserModel user = userDAO.getUserByUserNameAndPasswd(userName, passwd);
        if (user == null) {
            return "登录失败！账号不存在或密码错误";
        }
        return "登录成功！index页面欢迎您";
    }

    public boolean checkUserExists(UserModel userModel) {
        return userDAO.checkUserExists(userModel);
    }
}
