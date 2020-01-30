package com.example.jkl.dao;

import com.example.jkl.mapper.UserCarShipMapper;
import com.example.jkl.pojo.Car;
import com.example.jkl.pojo.UserCarShip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Repository
public class UserCarShipDao {
    @Autowired
    UserCarShipMapper userCarShipMapper;
    public Integer addCarRecord(UserCarShip userCarShip){
        return userCarShipMapper.insert(userCarShip);
    }
    public Integer deleteCarRecord(Integer id){
        return userCarShipMapper.deleteByPrimaryKey(id);
    }
    public Integer deleteCarListRecord(List<Integer> ids){
        Example example =new Example(Car.class);
        example.createCriteria().andIn("id",ids);
        return userCarShipMapper.deleteByExample(example);


    }
}
