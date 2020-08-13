package com.example.shiroexample.dao;

import com.example.shiroexample.model.UserModel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDAO {

    /**
     * 新增用户
     *
     * @param userModel 用户模型
     * @return true: 成功, false:失败
     */
    @Insert("INSERT INTO user_info(username,passwd,email) VALUES(#{userName}, #{passwd}, #{email})")
    boolean addUser(UserModel userModel);

    /**
     * 删除用户
     *
     * @param id 用户对应的id
     * @return true: 成功, false:失败
     */
    @Delete("DELETE FROM user_info WHERE id =#{id}")
    boolean deleteUser(long id);

    /**
     * 修改用户信息
     *
     * @param userModel 用户信息
     * @return true: 成功, false:失败
     */
    @Update("UPDATE user_info SET username=#{userName},passwd=#{passwd},email=#{email} WHERE id =#{id}")
    boolean updateUser(UserModel userModel);

    /**
     * 根据主键获取用户
     *
     * @param id 主键
     * @return 用户信息
     */
    @Select("SELECT * FROM user_info WHERE id = #{id}")
    @Results({
            @Result(property = "userName",  column = "username"),
            @Result(property = "createTime", column = "createtime"),
            @Result(property = "updateTime", column = "updatetime")
    })
    UserModel getUserById(long id);

    /**
     * 根据邮箱获取用户信息
     *
     * @param email 邮箱
     * @return 用户信息
     */
    @Select("SELECT * FROM user_info WHERE email = #{email}")
    @Results({
            @Result(property = "userName",  column = "username"),
            @Result(property = "createTime", column = "createtime"),
            @Result(property = "updateTime", column = "updatetime")
    })
    UserModel getUserByEmail(String email);

    /**
     * 根据用户名获取用户信息
     *
     * @param userName 用户名
     * @return 用户信息
     */
    @Select("SELECT * FROM user_info WHERE username = #{userName}")
    @Results({
            @Result(property = "userName",  column = "username"),
            @Result(property = "createTime", column = "createtime"),
            @Result(property = "updateTime", column = "updatetime")
    })
    UserModel getUserByUserName(String userName);

    /**
     * 根据账号密码进行查询
     *
     * @param userName 账号
     * @param passwd 密码
     * @return 用户信息
     */
    @Select("SELECT * FROM user_info WHERE username = #{userName} AND passwd = #{passwd}")
    @Results({
            @Result(property = "userName",  column = "username"),
            @Result(property = "createTime", column = "createtime"),
            @Result(property = "updateTime", column = "updatetime")
    })
    UserModel getUserByUserNameAndPasswd(@Param("userName") String userName, @Param("passwd") String passwd);

    /**
     * 获取所有用户信息
     * @return 用户信息列表
     */
    @Select("SELECT * FROM user_info")
    @Results({
            @Result(property = "userName",  column = "username"),
            @Result(property = "createTime", column = "createtime"),
            @Result(property = "updateTime", column = "updatetime")
    })
    List<UserModel> getAllUsers();

    @Select({"<script>",
            "SELECT COUNT(*) FROM user_info",
            "WHERE 1=1",
            "<when test='userName!=null'>",
            "AND username = #{userName}",
            "</when>",
            "<when test='email!=null'>",
            "OR email = #{email}",
            "</when>",
            "</script>"})
    boolean checkUserExists(UserModel userModel);
}
