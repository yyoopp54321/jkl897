package com.example.jkl.controller;

import com.example.jkl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@RestController
@EnableSwagger2
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping(value = "add/user",produces ="text/html;charset=utf-8")
    public Integer  register(User user){
        return userService.register(user);
    }
}
