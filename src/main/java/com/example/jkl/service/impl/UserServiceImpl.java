package com.example.jkl.service.impl;

import com.example.jkl.dao.UserDao;
import com.example.jkl.pojo.User;
import com.example.jkl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;
    @Override
    public Integer register(User user) {
        return userDao.register(user) ;
    }

    @Override
    public User findUserById(Integer id) {
        return userDao.findUser(id);
    }

    @Override
    public List<User> findAllUser() {
        return userDao.findAllUser();
    }

    @Override
    public User loginUser(String username, String password) {
        return userDao.loginUser(username,password);
    }

    @Override
    public Integer deleteUser(Integer id) {
        return userDao.deleteUser(id);
    }

    @Override
    public Integer updateUser(User user) {
        return userDao.updateUser(user);
    }
}
