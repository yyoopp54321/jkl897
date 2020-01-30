package com.example.jkl.service.impl;

import com.example.jkl.dao.CarDao;
import com.example.jkl.pojo.Car;
import com.example.jkl.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    @Autowired
    CarDao carDao;
    @Override
    public Integer addCar(Car car) {
        return carDao.addCar(car);
    }

    @Override
    public Integer deleteCar(Integer id) {
        return carDao.deleteCar(id);
    }

    @Override
    public List<Car> findAllCar() {
        return carDao.findAllCar();
    }

    @Override
    public Integer deleteCarList(List<Integer> ids) {
        return carDao.deleteCarList(ids);
    }
}
