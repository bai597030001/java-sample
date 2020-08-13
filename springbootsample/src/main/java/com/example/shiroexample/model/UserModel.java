package com.example.shiroexample.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {

    private long id;

    private String userName;

    private transient String passwd;

    private String email;

    private String createTime;

    private String updateTime;
}
