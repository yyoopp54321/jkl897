package com.example.jkl.service;

import com.example.jkl.pojo.UserCarShip;

import java.util.List;

public interface UserCarShipService {
    Integer addCarRecord(UserCarShip userCarShip);
    Integer deleteCarRecord(Integer id);
    Integer deleteCarListRecord(List<Integer> ids);
}
