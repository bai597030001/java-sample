package com.example.shiroexample.controller;

import com.example.shiroexample.model.UserModel;
import com.example.shiroexample.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@CrossOrigin(allowCredentials = "true")
public class loginController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index() {
        return "register";
    }

    @GetMapping("error")
    public String error() {
        return "error";
    }

    @PostMapping(value = "register")
    public String register(String username, String password, String email, Model model) {
        UserModel user = new UserModel();
        user.setUserName(username);
        user.setPasswd(password);
        user.setEmail(email);
        //用户已注册
        if (userService.checkUserExists(user)) {
            model.addAttribute("result", "注册失败（该用户名或邮箱已注册）！        请尝试重新注册！");
            return "index";
        }
        // 插入数据库
        if (userService.addUser(user)) {
            //注册成功 发送message到mq
            model.addAttribute("result", "注册成功！         欢迎您成为广联达的一员");
        } else {
            //注册失败
            model.addAttribute("result", "注册失败(数据库错误)！        请尝试重新注册！");
        }
        return "index";
    }

    @GetMapping("login")
    public String login(@RequestParam("login_username") String userName, @RequestParam String passwd, Model model) {
        String checkResult = userService.checkPassword(userName, passwd);
        model.addAttribute("result", checkResult);
        return "index";
    }

    @GetMapping("user")
    public String user(Model model) {
        model.addAttribute("result", "user");
        return "index";
    }

    @GetMapping("admin")
    public String admin(Model model) {
        model.addAttribute("result", "admin");
        return "index";
    }
}
