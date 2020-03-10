package com.example.jkl.service;


import com.example.jkl.common.ServerResponse;
import com.example.jkl.request.AddCarRequest;
import com.example.jkl.request.UpdateCarRequest;
import com.example.jkl.response.FindCarResponse;

import java.util.List;

public interface CarService {
    ServerResponse addCar(AddCarRequest addCarRequest);
    ServerResponse deleteCar(Integer id);
    Integer deleteCarList(List<Integer> ids);
    List<FindCarResponse> findCarByUserId(Integer buyerId);
    Integer updateCarGoodsCount(UpdateCarRequest updateCarRequest);
}
