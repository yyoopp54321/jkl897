package com.example.jkl.service;

import com.example.jkl.common.ServerResponse;
import com.example.jkl.pojo.User;
import com.example.jkl.request.RegisterUserRequest;
import com.example.jkl.request.UpdateUserLastMoneyRequest;
import com.example.jkl.request.UpdateUserPasswordRequest;
import com.example.jkl.request.UpdateUserRequest;

import java.util.List;

public interface UserService {
    //微信登陆

    //注册
    ServerResponse register(RegisterUserRequest registerUserRequest);
    //登陆
  ServerResponse login(String username, String password);
    //权限
    boolean checkRole(int userRoleType, int roleType);
    //更新用户信息
    ServerResponse updateUser(UpdateUserRequest UpdateUserRequest);
    //修改密码
    ServerResponse updateUserPassword(UpdateUserPasswordRequest updateUserPasswordRequest);
    //查看用户信息
    ServerResponse getUserInformation1(String openId);
  ServerResponse getUserInformation(Integer id);
    //安全码
    String randomSafetyCode();
    //通过用户名查找用户
    List<User> findUserByName(String username);
    //设置余额
    Integer updateUserLastMoney(UpdateUserLastMoneyRequest updateUserLastMoneyRequest,String username);
    User getByToken(String token);

  User findUserByOpenId(String openId );
  Integer saveOrUpdate(User user);
}
