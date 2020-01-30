package com.example.jkl.dao;

import com.example.jkl.mapper.UserMapper;
import com.example.jkl.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Repository
public class UserDao {
    @Autowired
    UserMapper userMapper;
 /*   public User loginUser(String userName, String password){
        Example example = new Example(User.class);
        return userMapper.loginUser(userName, password);}*/
    //注册(用户表所有字段)
    public Integer register( User user){
        return userMapper.insert(user);
    }
    //查看用户信息
    public User findUser(Integer id){
        return userMapper.selectByPrimaryKey(id);
    }
    //登陆(username password)
    public User loginUser(String username, String password){
        Example example=new Example(User.class);
        example.createCriteria()
                .andEqualTo("username",username)
                .andEqualTo("password",password);
        return  userMapper.selectByPrimaryKey(example);
    }
    //查看所有用户信息
    public List<User> findAllUser(){
        return userMapper.selectAll();
    }
    //删除用户信息
    public Integer deleteUser(Integer id){
        return userMapper.deleteByPrimaryKey(id);
    }
    //更新用户信息
    public Integer updateUser(User user){
        return userMapper.updateByPrimaryKey(user);
    }
}
