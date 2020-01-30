package com.example.jkl.service.impl;


import com.example.jkl.config.MybatisConfig;
import com.example.jkl.dao.GoodsDao;
import com.example.jkl.pojo.Goods;
import com.example.jkl.service.GoodsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Slf4j
@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    GoodsDao goodsDao;
    @Autowired
    MybatisConfig mybatisConfig;

    @Override
    public Integer addGoods(Goods goods) {
        return goodsDao.addGoods(goods);
    }

    @Override
    public Integer deleteGoods(Integer id) {
        return goodsDao.deleteGoods(id);
    }

    @Override
    public Integer updateGoods(Goods goods) {
        return goodsDao.updateGoods(goods);
    }

    @Override
    public List<Goods> findGoodsByGName(String gName, Integer pageNumber, Integer pageSize) {
        PageHelper.startPage(pageNumber, pageSize);
        List<Goods> all = goodsDao.findGoodsByGName(gName);
        PageInfo<Goods> pageInfo = new PageInfo<>(all);
        log.info("all-{}", all);
        log.info("pageInfo.getList()-{}", pageInfo.getList());
        log.info("pageNumber-{},pageSize-{}", pageNumber, pageSize);
        return pageInfo.getList();
    }

    @Override
    public List<Goods> findAllGoods(Integer pageNumber, Integer pageSize) {
        //开始分页必须写在上面
        PageHelper.startPage(pageNumber, pageSize);
        List<Goods> all = goodsDao.findAllGoods();
        PageInfo<Goods> pageInfo = new PageInfo<>(all);
        log.info("all-{}", all);
        log.info("pageInfo.getList()-{}", pageInfo.getList());
        log.info("pageNumber-{},pageSize-{}", pageNumber, pageSize);
        return pageInfo.getList();
    }

    @Override
    public Integer addGoodsList(List<Goods> goodsList) {
        return goodsDao.addGoodsList(goodsList);
    }

    @Override
    public Integer deleteGoodsList(List<Integer> ids) {
        return goodsDao.deleteGoodsList(ids);
    }

}