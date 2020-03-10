package com.example.jkl.service.impl;


import com.example.jkl.common.ServerResponse;
import com.example.jkl.dao.UserDao;
import com.example.jkl.pojo.User;
import com.example.jkl.request.RegisterUserRequest;
import com.example.jkl.request.UpdateUserLastMoneyRequest;
import com.example.jkl.request.UpdateUserPasswordRequest;
import com.example.jkl.request.UpdateUserRequest;
import com.example.jkl.response.FindUserInfo;
import com.example.jkl.service.UserService;
import com.example.jkl.utils.EncryptUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.Random;
@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;


    @Override
    public ServerResponse register(RegisterUserRequest registerUserRequest) {
        List<User> list = findUserByName(registerUserRequest.getUsername());
        if (!list.isEmpty()){
            return ServerResponse.createByErrorMessage("用户名已存在");
        }
        User user = new User();
        user.setUsername(registerUserRequest.getUsername());
        user.setPassword(registerUserRequest.getPassword());
        user.setNickName(registerUserRequest.getNickName());
        user.setPhone(registerUserRequest.getPhone());
        user.setRole(registerUserRequest.getRole());
        int rowCount = userDao.addUser(user);
        if(rowCount > 0) {
            return    ServerResponse.createBySuccessMessage("注册成功");

        }
        return    ServerResponse.createByErrorMessage("注册失败，请检查");
    }
    @Override
    public ServerResponse login(String username, String password) {

        User user = userDao.loginUser(username, password);
        if (user==null){
            return  ServerResponse.createByErrorMessage("用户名或密码错误");
        }

        return  ServerResponse.createBySuccessMessageAndData("登陆成功",user);
    }

    @Override
     public boolean checkRole(int userRoleType, int roleType){
        return userRoleType == roleType;
    }

    @Override
    public ServerResponse updateUser(UpdateUserRequest updateUserRequest) {
        User user = new User();
        user.setNickName(updateUserRequest.getNickName());
        user.setuImg(updateUserRequest.getUImg());
        user.setPhone(updateUserRequest.getPhone());
        Integer integer = userDao.updateUser(user);
        if (integer>0){
            return ServerResponse.createBySuccessData("修改信息成功");
        }
        return ServerResponse.createByErrorMessage("个人信息修改失败");
    }

    @Override
    public List<User> findUserByName(String username) {
        return userDao.findUserByName(username);
    }

    @Override
    public ServerResponse updateUserPassword(UpdateUserPasswordRequest updateUserPasswordRequest) {
        List<User> userByName = userDao.findUserByName(updateUserPasswordRequest.getUsername());
        User user = new User();
        if (!CollectionUtils.isEmpty(userByName)) {
            user = userByName.get(0);
        }else {
            return ServerResponse.createByErrorMessage("无法找到当前用户的个人信息");
        }
        String encryptPassword = EncryptUtil.encoderUTF8(updateUserPasswordRequest.getPassword());
        if (!StringUtils.equals(encryptPassword,user.getPassword())) {
            return ServerResponse.createByErrorMessage("原密码错误");
        }
        if (updateUserPasswordRequest.getPassword().equals(user.getPassword())) {
            return ServerResponse.createByErrorMessage("新旧密码一致");//新旧密码一致
        } else {
           user.setPassword(updateUserPasswordRequest.getPassword());
           userDao.updateUserByUsername(user,updateUserPasswordRequest.getUsername());
           return ServerResponse.createBySuccessData("修改成功");//
        }

    }

    @Override
    public ServerResponse getUserInformation(Integer id) {
        User user = userDao.findUser(id);
        FindUserInfo findUserInfo = new FindUserInfo();
        BeanUtils.copyProperties(user,findUserInfo);
        return ServerResponse.createBySuccessData(findUserInfo);
    }
    public ServerResponse getUserInformation1(String openId) {
        User userByOpenId = userDao.findUserByOpenId(openId);
        User user = userDao.findUser(userByOpenId.getId());
        FindUserInfo findUserInfo = new FindUserInfo();
        BeanUtils.copyProperties(user,findUserInfo);
        return ServerResponse.createBySuccessData(findUserInfo);
    }
    @Override
    public Integer updateUserLastMoney(UpdateUserLastMoneyRequest updateUserLastMoneyRequest,String username) {
        User user = new User();
        user.setLastMoney(updateUserLastMoneyRequest.getLastMoney());
    return userDao.updateUserByUsername(user,username);
    }

 public User findUserByOpenId(String openId ){
        return userDao.findUserByOpenId(openId);
 }
    @Override
    public Integer saveOrUpdate(User user) {
        User userByOpenId = userDao.findUserByOpenId(user.getOpenId());
        if(userByOpenId==null){
            user.setCreateTime(new Date());
            user.setRole((short) 0);
            System.out.println(user.getOpenId());
            return userDao.addUser(user);
        }else {
            return userDao.updateUser(user);
        }
    }
    @Override
    public String randomSafetyCode(){
        char[] str = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
        int count = 0;
        int i;
        int maxNum = 62;
        StringBuffer stringBuffer = new StringBuffer();
        Random random = new Random();
        while(count < 8){
            i = Math.abs(random.nextInt(maxNum));
            if (i >= 0 && i < str.length) {
                stringBuffer.append(str[i]);
                count ++;
            }
        }
        return stringBuffer.toString();
    }

    @Override
    public User getByToken(String token) {
        return userDao.getByToken(token);
    }



}
