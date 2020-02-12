package com.example.jkl.mapper;

import com.example.jkl.pojo.Order;
import org.apache.ibatis.annotations.Mapper;
import tk.mybatis.mapper.entity.Example;

@Mapper
public interface OrderMapper extends CommonMapper<Order> {
    void updateByExample(Example example);
}