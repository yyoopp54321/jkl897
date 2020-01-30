package com.example.jkl.service;

import com.example.jkl.pojo.User;

import java.util.List;

public interface UserService {
    Integer register(User user);
    User findUserById(Integer id);
    List<User> findAllUser();
    User loginUser(String username, String password);
    Integer deleteUser(Integer id);
    Integer updateUser(User user);

}
