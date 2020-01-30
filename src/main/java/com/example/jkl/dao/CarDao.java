package com.example.jkl.dao;

import com.example.jkl.mapper.CarMapper;
import com.example.jkl.pojo.Car;
import com.example.jkl.pojo.UserCarShip;
import com.example.jkl.service.UserCarShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Repository
public class CarDao {
    @Autowired
    private CarMapper carMapper;
    @Autowired
    UserCarShipService userCarShipService;
    //批量删除订单(购物车)删除购物车的所有记录
    public Integer deleteCarList(List<Integer> ids){
        Example example=new Example(Car.class);
        example.createCriteria().andIn("id",ids);
        Integer x=userCarShipService.deleteCarListRecord(ids);
        System.out.println(x);
        return carMapper.deleteByExample(example);
    }
    //添加到购物车
    public Integer addCar(Car car){
        UserCarShip userCarShip = new UserCarShip();
        userCarShip.setId(car.getId());
        userCarShip.setuId(car.getuId());
        userCarShip.setcId(car.getId());
        userCarShip.setCreateTime(new Date());
        userCarShip.setUpdataTime(new Date());
        Integer integer = userCarShipService.addCarRecord(userCarShip);
        System.out.println(integer);
        return carMapper.insert(car);
    }
    //逐条删除购物车记录
    public Integer deleteCar(Integer id){
        Integer integer = userCarShipService.deleteCarRecord(id);
        System.out.println(integer);

        return carMapper.deleteByPrimaryKey(id);


    }

    //查看所有记录
     public List<Car> findAllCar(){
         return carMapper.selectAll();
     }

}
