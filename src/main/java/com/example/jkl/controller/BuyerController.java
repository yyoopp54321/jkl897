package com.example.jkl.controller;

import com.example.jkl.common.Const;
import com.example.jkl.common.ServerResponse;
import com.example.jkl.pojo.User;
import com.example.jkl.request.RegisterUserRequest;
import com.example.jkl.request.UpdateUserLastMoneyRequest;
import com.example.jkl.request.UpdateUserPasswordRequest;
import com.example.jkl.request.UpdateUserRequest;
import com.example.jkl.service.UserService;
import org.apache.commons.lang3.StringUtils;
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
    //注册
    @PostMapping(value = "add/buyer",produces ="text/html;charset=utf-8")
    public ServerResponse register(@RequestBody RegisterUserRequest registerUserRequest){

        ServerResponse register = userService.register(registerUserRequest);
        return ServerResponse.createBySuccessData(register);

    }
    //登陆
    @GetMapping(value = "login/buyer")
    public ServerResponse loginUser(@RequestBody String username, @RequestBody String password,HttpSession session){
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)){
            return ServerResponse.createByIllegalArgument();
        }
        ServerResponse response = userService.login(username, password);
        if (!response.isSuccess()) {
            return response;
        }
        User user = (User)response.getData();
        if (userService.checkRole(user.getRole(), Const.Role.ROLE_BUYER)) {
            // 用户登陆成功，将其信息放入session中
            session.setAttribute(Const.CURRENT_USER, user);
            return response;
        }
        return ServerResponse.createByErrorMessage("非买家用户");
    }
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
    public ServerResponse getUserInformation(@RequestBody Integer id,HttpSession session){
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (null == currentUser) {
            return ServerResponse.createByNeedLogin("用户未登录,请先登陆");
        }
        return userService.getUserInformation(id);
    }
    //查找单个用户
    @GetMapping(value = "select/buyer/by/username")
    public List<User> findUserByName(@RequestBody String username){
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
