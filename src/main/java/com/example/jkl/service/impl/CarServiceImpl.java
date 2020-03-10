package com.example.jkl.service.impl;

import com.example.jkl.common.ServerResponse;
import com.example.jkl.dao.CarDao;
import com.example.jkl.dao.GoodsDao;
import com.example.jkl.dao.UserDao;
import com.example.jkl.pojo.Car;
import com.example.jkl.request.AddCarRequest;
import com.example.jkl.request.UpdateCarRequest;
import com.example.jkl.response.FindCarResponse;
import com.example.jkl.service.CarService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    @Autowired
    CarDao carDao;
    @Autowired
    GoodsDao goodsDao;
    @Autowired
    UserDao userDao;

    @Override
    public ServerResponse addCar(AddCarRequest addCarRequest) {
        Car car = new Car();
        car.setBuyerId(addCarRequest.getBuyerId());
        car.setPrice(addCarRequest.getPrice());
        car.setStoreId(addCarRequest.getStoreId());
        car.setGoodsId(addCarRequest.getGoodsId());
        car.setCount(addCarRequest.getCount());
        Integer integer = carDao.addCar(car);
        if (integer == null) {
            return ServerResponse.createByErrorMessage("添加失败");
        }else {
        return ServerResponse.createBySuccessData("添加成功");}
    }


        @Override
    public ServerResponse deleteCar(Integer id) {
            Integer integer = carDao.deleteCar(id);
            if (integer == null) {
                return ServerResponse.createByErrorMessage("删除失败");
            }else {
                return ServerResponse.createBySuccessData("删除成功");}
        }

    private List<FindCarResponse> showFindAllCar(List<Car> carList){
        List<FindCarResponse> list=new ArrayList<>();
        for (Car car:carList){
            FindCarResponse findCarResponse = new FindCarResponse();
            BeanUtils.copyProperties(car,findCarResponse);
            list.add(findCarResponse);
        }
        return list;
    }
    @Override
    public List<FindCarResponse> findCarByUserId(Integer buyerId){

        List<Car> list = carDao.selectCarByBuyerId(buyerId);
        return  showFindAllCar(list);
    }
    public Integer updateCarGoodsCount(UpdateCarRequest updateCarRequest){
        Car car = carDao.findCarById(updateCarRequest.getId());
        car.setCount(updateCarRequest.getCount());
        return carDao.update(car);
    }
    @Override
    public Integer deleteCarList(List<Integer> ids) {
        return carDao.deleteCarList(ids);
    }
}
