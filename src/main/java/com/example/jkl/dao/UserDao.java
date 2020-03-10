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

    public Integer addUser(User user){
        return userMapper.insert(user);
    }

    public User findUser(Integer id){
        return userMapper.selectByPrimaryKey(id);
    }
    //登陆(username password)
    public User loginUser(String username, String password){
        Example example=new Example(User.class);
        example.createCriteria()
                .andEqualTo("username",username)
                .andEqualTo("password",password);
        return  userMapper.selectOneByExample(example);
    }

    //删除用户信息
    public Integer deleteUser(Integer id){
        return userMapper.deleteByPrimaryKey(id);
    }
    //查找用户通过username
    public List<User> findUserByName(String username){
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("username",username);
        return userMapper.selectByExample(example);
    }
    //更新用户通过用户名信息
    public Integer updateUserByUsername(User user,String username){

        return userMapper.updateByExampleSelective(user,username);
    }
    public Integer updateUser(User user){

        return userMapper.updateByPrimaryKeySelective(user);
    }
    public User findUserByOpenId(String openId){
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("openId",openId);
        return userMapper.selectOneByExample(example);
    }
    public User getByToken(String token){
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("token",token);
        return userMapper.selectOneByExample(example);
    }
}
