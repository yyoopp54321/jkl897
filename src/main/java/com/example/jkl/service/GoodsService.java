package com.example.jkl.service;

import com.example.jkl.pojo.Goods;

import java.util.List;

public interface GoodsService {
    Integer addGoods(Goods goods);
    Integer deleteGoods(Integer id);
    Integer updateGoods(Goods goods);
    List<Goods> findGoodsByGName(String gName,Integer pageNumber,Integer pageSize);
    List<Goods> findAllGoods(Integer pageNumber,Integer pageSize);
    Integer addGoodsList(List<Goods> goodsList);
    Integer deleteGoodsList(List<Integer> ids);

}
