package com.example.jkl.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@RestController

public class TestController {

    public
    @GetMapping
    public String getHello(){
        return "hello tk.mybatis";
    }
}
