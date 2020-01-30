package com.example.jkl.service.impl;

import com.example.jkl.dao.UserCarShipDao;
import com.example.jkl.pojo.UserCarShip;
import com.example.jkl.service.UserCarShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserCarShipServiceImpl implements UserCarShipService {
    @Autowired
    UserCarShipDao userCarShipDao;
    @Override
    public Integer addCarRecord(UserCarShip userCarShip) {
        return userCarShipDao.addCarRecord(userCarShip);
    }

    @Override
    public Integer deleteCarRecord(Integer id) {
        return userCarShipDao.deleteCarRecord(id);
    }

    @Override
    public Integer deleteCarListRecord(List<Integer> ids) {

        return userCarShipDao.deleteCarListRecord(ids);
    }
}
