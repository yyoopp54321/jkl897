package com.example.jkl.controller;

import com.example.jkl.common.Const;
import com.example.jkl.common.ServerResponse;
import com.example.jkl.pojo.User;
import com.example.jkl.request.RegisterUserRequest;
import com.example.jkl.request.UpdateUserRequest;
import com.example.jkl.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.http.HttpSession;

@RestController
@EnableSwagger2
public class SellerController {

    @Autowired
    private UserService userService;


    /**
     * 商家用户登陆
     * @param username
     * @param password
     * @param session
     * @return
     */
    @GetMapping(value = "login/seller")
    public ServerResponse login(@RequestBody String username,@RequestBody String password, HttpSession session){
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)){
            return ServerResponse.createByIllegalArgument();
        }
        ServerResponse response = userService.login(username, password);
        if (!response.isSuccess()) {
            return response;
        }
        User user = (User)response.getData();
        if (userService.checkRole(user.getRole(), Const.Role.ROLE_SELLER)) {
            // 用户登陆成功，将其信息放入session中
            session.setAttribute(Const.CURRENT_USER, user);
            return response;
        }
        return ServerResponse.createByErrorMessage("非商家用户");
    }

    /**
     * 注册商家用户
     * @param registerUserRequest
     * @return
     */
    @PostMapping(value = "register/seller")
    public ServerResponse register(@RequestBody RegisterUserRequest registerUserRequest){
        if (null == registerUserRequest) {
            return ServerResponse.createByIllegalArgument();
        }
        registerUserRequest.setRole((short) Const.Role.ROLE_SELLER);
        return userService.register(registerUserRequest);
    }

    /**
     * 更新个人信息
     * @param updateUserRequest
     * @param session
     * @return
     */
    @PutMapping(value = "update/seller")
    public ServerResponse update(@RequestBody UpdateUserRequest updateUserRequest,HttpSession session){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (null == user) {
            return ServerResponse.createByNeedLogin("用户未登录,请先登陆");
        }
        if (!userService.checkRole(user.getRole(),Const.Role.ROLE_SELLER)) {
            return ServerResponse.createByErrorMessage("不是商家，无法修改商家信息");
        }

        ServerResponse response = userService.updateUser(updateUserRequest);
        if (!response.isSuccess()) {
            return response;
        }
        // 更新session中存储的个人信息
        User updateUser = (User)response.getData();
        updateUser.setUsername(user.getUsername());
        updateUser.setRole(user.getRole());
        session.setAttribute(Const.CURRENT_USER, updateUser);
        return response;
    }

    /**
     * 获取商家个人信息
     * @param session
     * @return
     */
    @GetMapping(value = "select/seller/information")
    public ServerResponse getUserInformation( HttpSession session) {
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (null == currentUser) {
            return ServerResponse.createByNeedLogin("用户未登录,请先登陆");
        }
        return userService.getUserInformation(currentUser.getId());
    }

}
