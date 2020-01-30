package com.example.jkl.service.impl;

import com.example.jkl.dao.OrderDao;
import com.example.jkl.pojo.Order;
import com.example.jkl.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderDao orderDao;
    @Override
    public Integer addOrder(Order order) {
        return orderDao.addOrder(order);
    }

    @Override
    public Integer deleteOrder(Integer id) {
        return orderDao.deleteOrderById(id);
    }

    @Override
    public Integer payOrder(Order order) {
        return orderDao.payOrder(order);
    }

    @Override
    public Integer backOrder(Order order) {
        return orderDao.backOrder(order);
    }

    @Override
    public List<Order> findAllOrder() {
        return orderDao.findAllOrder();
    }

    @Override
    public Integer deleteOrderList(List<Integer> ids) {
        return orderDao.deleteOrderList(ids);
    }


}
