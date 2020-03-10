package com.example.jkl.controller;

import com.example.jkl.common.Const;
import com.example.jkl.common.ServerResponse;
import com.example.jkl.pojo.OrderEntity;
import com.example.jkl.pojo.User;
import com.example.jkl.service.OrderService;
import com.example.jkl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@EnableSwagger2
public class SellerOrderController {
    @Autowired
    UserService userService;
    @Autowired
    OrderService orderService;
    //设置订单编号并发货
    @GetMapping(value = "send/goods")
    public ServerResponse sendGoods(@RequestParam Integer orderNo, HttpSession session) {
        User currentUser = (User)session.getAttribute(Const.CURRENT_USER);
        if (null == currentUser) {
            return ServerResponse.createByNeedLogin();
        }
        if (null == orderNo) {
            return ServerResponse.createByIllegalArgument();
        }
        ServerResponse response = orderService.setOrderStatusByOrderNo(orderNo, (short) Const.OrderStatusEnum.SEND.getCode());
        if (response.isSuccess()) {
            return ServerResponse.createBySuccessMessage("订单发货成功");
        }
        return ServerResponse.createByErrorMessage("订单发货失败");
    }
    //通过店铺id去查看订单
    @GetMapping(value = "find/seller/order")
    public List<OrderEntity> findOrderByStoreId(@RequestParam Integer storeId){
        return orderService.findOrderByStoreId(storeId);
    }


}
