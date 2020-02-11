package com.example.jkl.dao;

import com.example.jkl.mapper.StoreMapper;
import com.example.jkl.pojo.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
@Repository
public class StoreDao {

    @Autowired
    StoreMapper storeMapper;

    public List<Store> findStoreByName(String storeName) {
        Example example = new Example(Store.class);
        example.createCriteria().andLike("storeName", storeName);
        return storeMapper.selectByExample(example);
    }

    public Store findStoreById(Integer id) {
        return storeMapper.selectByPrimaryKey(id);
    }

    public Integer add(Store store) {
        return storeMapper.insert(store);
    }

    public Integer update(Store store) {
        return storeMapper.updateByPrimaryKey(store);
    }
    public List<Store> select(Integer sellerId){
        Example example=new Example(Store.class);
        example.createCriteria().andEqualTo("sellerId",sellerId);
        return storeMapper.selectByExample(sellerId);
    }
}