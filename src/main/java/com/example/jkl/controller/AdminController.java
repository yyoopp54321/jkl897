package com.example.jkl.controller;


import com.example.jkl.common.Const;
import com.example.jkl.common.ServerResponse;
import com.example.jkl.pojo.User;
import com.example.jkl.request.RegisterUserRequest;
import com.example.jkl.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.http.HttpSession;

@RestController
@EnableSwagger2
public class AdminController {
    @Autowired
    UserService userService;
    @GetMapping(value = "login/admin")
    public ServerResponse login(@RequestBody String username, @RequestBody String password, HttpSession session){
        if(StringUtils.isBlank(username) || StringUtils.isBlank(password)){
            return ServerResponse.createByIllegalArgument();
        }
        ServerResponse response = userService.login(username, password);
        if (!response.isSuccess()) {
            return response;
        }
        User user = (User)response.getData();
        if (userService.checkRole(user.getRole(), Const.Role.ROLE_ADMIN)) {
            // 用户登陆成功，将其信息放入session中
            session.setAttribute(Const.CURRENT_USER, user);
            return response;
        }
        return ServerResponse.createByErrorMessage("非管理员用户");
    }

    @PostMapping(value = "register/admin")
    public ServerResponse register(@RequestBody RegisterUserRequest registerUserRequest){
        if (null == registerUserRequest) {
            return ServerResponse.createByIllegalArgument();
        }
        registerUserRequest.setRole((short) Const.Role.ROLE_ADMIN);
        ServerResponse register;
        register = userService.register(registerUserRequest);
        return ServerResponse.createBySuccessData(register);
    }
}
