package com.example.jkl.service;


import com.example.jkl.pojo.Car;

import java.util.List;

public interface CarService {
    Integer addCar(Car car);
    Integer deleteCar(Integer id);
    List<Car> findAllCar();
    Integer deleteCarList(List<Integer> ids);
}
