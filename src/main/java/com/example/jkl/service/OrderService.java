package com.example.jkl.service;

import com.example.jkl.common.ServerResponse;
import com.example.jkl.pojo.Car;
import com.example.jkl.pojo.Order;
import com.example.jkl.pojo.OrderEntity;
import com.example.jkl.request.PayOrderRequest;

import java.util.List;

public interface OrderService {
    ServerResponse addOrderEntity(OrderEntity orderEntity);
    ServerResponse deleteOrder(Integer id);
    ServerResponse payOrder(PayOrderRequest payOrderRequest);
    ServerResponse backOrder(Order order);
    Integer deleteOrderList(List<Integer> ids );
    ServerResponse findOrderByOrderNo(Integer orderNo);
    ServerResponse getOrderEntityList(List<Car> carList);
    ServerResponse setOrderStatusByOrderNo(Integer orderNo,Short status);
    List<OrderEntity> findOrderByStoreId(Integer storeId);
    Order finOrderById(Integer buyerId);

}
