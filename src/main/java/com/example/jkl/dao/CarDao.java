package com.example.jkl.dao;

import com.example.jkl.mapper.CarMapper;
import com.example.jkl.pojo.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Repository
public class CarDao {
    @Autowired
    private CarMapper carMapper;

    //批量删除订单(购物车)删除购物车的所有记录
    public Integer deleteCarList(List<Integer> ids){
        Example example=new Example(Car.class);
        example.createCriteria().andIn("id",ids);
        return carMapper.deleteByExample(example);
    }
    //添加到购物车
    public Integer addCar(Car car){
        return carMapper.insert(car);
    }
    //逐条删除购物车记录
    public Integer deleteCar(Integer id){


        return carMapper.deleteByPrimaryKey(id);


    }
    public List<Car> selectCarByBuyerId(Integer BuyerId){
        Example example=new Example(Car.class);
        example.createCriteria().andEqualTo("buyerId",BuyerId);
       return carMapper.selectByExample(example);

    }
   public Integer update(Car car){
        return carMapper.updateByPrimaryKey(car);
   }
   public Car findCarById(Integer id){
        return carMapper.selectByPrimaryKey(id);
   }

    //查看所有记录
     public List<Car> findAllCar(){
         return carMapper.selectAll();
     }

}
