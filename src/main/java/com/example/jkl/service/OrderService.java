package com.example.jkl.service;

import com.example.jkl.common.ServerResponse;
import com.example.jkl.pojo.Car;
import com.example.jkl.pojo.OrderEntity;
import com.example.jkl.response.FindOrderResponse;

import java.util.List;

public interface OrderService {
    ServerResponse addOrder(OrderEntity orderEntity);
    ServerResponse deleteOrder(Integer id);
    ServerResponse payOrder(OrderEntity orderEntity);
    ServerResponse backOrder(OrderEntity orderEntity);
    List<FindOrderResponse> findAllOrder();
    Integer deleteOrderList(List<Integer> ids );
    ServerResponse findOrderByOrderNo(Integer orderNo);
    ServerResponse getOrderEntityList(List<Car> carList);
    ServerResponse setOrderStatusByOrderNo(Integer orderNo,Short status);

}
