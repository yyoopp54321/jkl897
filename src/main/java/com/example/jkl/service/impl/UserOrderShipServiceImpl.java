package com.example.jkl.service.impl;
import com.example.jkl.dao.UserOrderShipDao;
import com.example.jkl.pojo.UserOrderShip;
import com.example.jkl.service.UserOrderShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserOrderShipServiceImpl implements UserOrderShipService {
    @Autowired
    UserOrderShipDao userOrderShipDao;
    @Override
    public Integer addOrderRecord(UserOrderShip userOrderShip) {
        return userOrderShipDao.addOrderRecord(userOrderShip);
    }

    @Override
    public Integer deleteOderRecord(Integer id) {
        return userOrderShipDao.deleteOderRecord(id);
    }

    @Override
    public Integer deleteOrderListRecord(List<Integer> ids) {
        return userOrderShipDao.deleteOrderListRecord(ids);
    }
}
