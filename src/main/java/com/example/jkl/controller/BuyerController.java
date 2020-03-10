package com.example.jkl.controller;

import com.example.jkl.common.Const;
import com.example.jkl.common.ServerResponse;
import com.example.jkl.pojo.User;
import com.example.jkl.request.UpdateUserLastMoneyRequest;
import com.example.jkl.request.UpdateUserPasswordRequest;
import com.example.jkl.request.UpdateUserRequest;
import com.example.jkl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@EnableSwagger2
public class BuyerController {
    @Autowired
    UserService userService;
    //更新用户信息
    @PutMapping(value = "update/buyer")
    public ServerResponse updateUser(@RequestBody UpdateUserRequest updateUserRequest,HttpSession session ){
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (null == currentUser) {
            return ServerResponse.createByNeedLogin("用户未登录,请先登陆");
        }
        return userService.updateUser(updateUserRequest);
        }
     //查看用户信息
    @GetMapping(value = "select/buyer/by/id")
    public ServerResponse getUserInformation(@RequestParam Integer id,HttpSession session){
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (null == currentUser) {
            return ServerResponse.createByNeedLogin("用户未登录,请先登陆");
        }
        return userService.getUserInformation(id);
    }
    //查找单个用户
    @GetMapping(value = "select/buyer/by/username")
    public List<User> findUserByName(@RequestParam String username){
        return userService.findUserByName(username);
    }
    //修改密码
    @PutMapping(value = "update/buyer/password/by/username")
    public ServerResponse updateUserPassword(@RequestBody UpdateUserPasswordRequest updateUserPasswordRequest, HttpSession session){
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (null == currentUser) {
            return ServerResponse.createByNeedLogin("用户未登录,请先登陆");
        }
        return userService.updateUserPassword(updateUserPasswordRequest);
    }

    //设置一个虚拟账户
    @PutMapping(value = "update/buyer/LastMoney/by/username")
  public Integer updateUserLastMoney(@RequestBody UpdateUserLastMoneyRequest updateUserLastMoneyRequest, @RequestBody String username){
        return userService.updateUserLastMoney(updateUserLastMoneyRequest,username);
  }

}
