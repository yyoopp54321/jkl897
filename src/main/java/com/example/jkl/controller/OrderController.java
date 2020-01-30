package com.example.jkl.controller;

import com.example.jkl.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@RestController
@EnableSwagger2
public class OrderController {
  @Autowired
    OrderService orderService;
  @PostMapping(value = "add/order",produces ="text/html;charset=utf-8")
      public Integer addOrder(Order order){
      return orderService.addOrder(order);
  }
}
