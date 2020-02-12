package com.example.jkl.mapper;

import com.example.jkl.pojo.Car;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CarMapper extends CommonMapper<Car> {
}