package com.example.jkl.dao;

import com.example.jkl.mapper.GoodsMapper;
import com.example.jkl.pojo.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Repository
public class GoodsDao {
    @Autowired
    GoodsMapper goodsMapper;

    //添加商品
    public Integer addGoods(Goods goods) {
        return goodsMapper.insert(goods);
    }

    //批量删除
    public Integer deleteGoodsList(List<Integer> ids){
        Example example=new Example(Goods.class);
        example.createCriteria().andIn("id",ids);
        return  goodsMapper.deleteByExample(ids);
    }

    //删除商品
    public Integer deleteGoods(Integer id) {
        return goodsMapper.deleteByPrimaryKey(id);
    }

    //更新商品
    public Integer updateGoods(Goods goods) {
        return goodsMapper.updateByPrimaryKeySelective(goods);
    }

    //查找商品(通过商品名称模糊查找)
    public List<Goods> findGoodsByGName(String gName) {
        Example example = new Example(Goods.class);
        example.createCriteria().andLike("name", "%" + gName + "%");
        return goodsMapper.selectByExample(example);
    }
    //通过编号查找商品
    public List<Goods> findAllGoods(){
        return goodsMapper.selectAll();

    }
    public Integer findGoodsCount(String gName){
        Example example = new Example(Goods.class);
        example.createCriteria().andLike("name", "%" + gName + "%");
        return goodsMapper.selectCountByExample(example);
    }
    public Goods findGoodsById(Integer id){
        return goodsMapper.selectByPrimaryKey(id);
    }

    public List<Goods> findGoodsByStoreId(Integer storeId){
        Example example =new Example(Goods.class);
        example.createCriteria().andEqualTo(storeId);
        return goodsMapper.selectByExample(example);
    }
}