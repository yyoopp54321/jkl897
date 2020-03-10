package com.example.jkl.controller;

import com.example.jkl.common.Const;
import com.example.jkl.common.ServerResponse;
import com.example.jkl.pojo.Car;
import com.example.jkl.pojo.Order;
import com.example.jkl.pojo.OrderEntity;
import com.example.jkl.pojo.User;
import com.example.jkl.request.PayOrderRequest;
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
  @PostMapping(value = "add/orderEntity",produces ="text/html;charset=utf-8")
  public ServerResponse addOrderEntity(@RequestBody OrderEntity orderEntity, HttpSession session){
    User currentUser = (User)session.getAttribute(Const.CURRENT_USER);
    if (null == currentUser) {
      return ServerResponse.createByNeedLogin();
    }
      return orderService.addOrderEntity(orderEntity);
  }
  //取消未支付的订单
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
    public ServerResponse payOrder(@RequestBody PayOrderRequest payOrderRequest){
      return orderService.payOrder(payOrderRequest);    }
    //退订单
    @PutMapping(value = "back/order")
    public ServerResponse backOrder(@RequestBody Order order){
      return orderService.backOrder(order);
    }
    //查看所有订单
    @GetMapping(value = "select/order/by/userId")
    public Order finOrderById(Integer buyerId){
      return orderService.finOrderById(buyerId);
    }
    //查看订单号
  @GetMapping(value = "find/order/by/orderNo")
     public ServerResponse findOrderByOrderNo(@RequestParam Integer orderNo){
    return orderService.findOrderByOrderNo(orderNo);
     }
     //查看订单的实体列从购物车获取
     @GetMapping(value = "find/orderEntityList")
     public ServerResponse getOrderEntityList(@RequestParam List<Car> carList){
    return orderService.getOrderEntityList(carList);
     }
}
