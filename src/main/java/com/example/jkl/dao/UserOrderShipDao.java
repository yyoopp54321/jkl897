package com.example.jkl.dao;



import com.example.jkl.mapper.UserOrderShipMapper;
import com.example.jkl.pojo.Order;
import com.example.jkl.pojo.UserOrderShip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Repository
public class UserOrderShipDao {

    @Autowired
    UserOrderShipMapper userOrderShipMapper;
    public Integer addOrderRecord(UserOrderShip userOrderShip){
      return userOrderShipMapper.insert(userOrderShip);
    }
    public Integer deleteOderRecord(Integer id){
        return userOrderShipMapper.deleteByPrimaryKey(id);
    }
    public Integer deleteOrderListRecord(List<Integer> ids){
        Example example= new Example(Order.class);
        example.createCriteria().andIn("id",ids);
        return userOrderShipMapper.deleteByExample(ids);
    }
}
