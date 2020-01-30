package com.example.jkl.service;

import com.example.jkl.pojo.UserOrderShip;

import java.util.List;

public interface UserOrderShipService {
    Integer addOrderRecord(UserOrderShip userOrderShip);
    Integer deleteOderRecord(Integer id);
    Integer deleteOrderListRecord(List<Integer> ids);
}
