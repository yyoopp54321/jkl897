package com.example.jkl.service;

import com.example.jkl.pojo.Order;

import java.util.List;

public interface OrderService {
    Integer addOrder(Order order);
    Integer deleteOrder(Integer id);
    Integer payOrder(Order order);
    Integer backOrder(Order order);
    List<Order> findAllOrder();
    Integer deleteOrderList(List<Integer> ids );

}
