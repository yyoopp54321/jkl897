package com.example.jkl.controller;

import com.example.jkl.common.ServerResponse;
import com.example.jkl.pojo.Car;
import com.example.jkl.pojo.OrderEntity;
import com.example.jkl.response.FindOrderResponse;
import com.example.jkl.service.OrderService;
import com.example.jkl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@EnableSwagger2
public class BuyerOrderController {
  @Autowired
    OrderService orderService;
  @Autowired
  UserService userService;
  //添加订单
  @PostMapping(value = "add/order",produces ="text/html;charset=utf-8")
  public ServerResponse addOrder(@RequestBody OrderEntity orderEntity, HttpSession session){

      return orderService.addOrder( orderEntity);
  }
  //删除订单
  @DeleteMapping (value = "delete/order/by/id")
    public ServerResponse deleteOrder(@RequestBody Integer id){
      return orderService.deleteOrder(id);
  }
  //批量删除订单
    @DeleteMapping(value = "delete/order/by/list")
    public Integer deleteOrderList(@RequestBody List<Integer> ids ){
      return orderService.deleteOrderList(ids);
    }
    //支付订单
    @PutMapping(value = "pay/order")
    public ServerResponse payOrder(@RequestBody OrderEntity orderEntity){
      return orderService.payOrder(orderEntity);    }
    //退订单
    @PutMapping(value = "back/order")
    public ServerResponse backOrder(@RequestBody OrderEntity orderEntity){
      return orderService.backOrder(orderEntity);
    }
    //查看所有订单
    @GetMapping(value = "select/order/by/list")
    public List<FindOrderResponse> findAllOrder(){
      return orderService.findAllOrder();
    }
    //查看订单号
  @GetMapping(value = "find/order/by/orderNo")
     public ServerResponse findOrderByOrderNo(@RequestBody Integer orderNo){
    return orderService.findOrderByOrderNo(orderNo);
     }
     //查看订单的实体列
     @GetMapping(value = "find/orderEntityList")
     public ServerResponse getOrderEntityList(@RequestBody List<Car> carList){
    return orderService.getOrderEntityList(carList);
     }
}
