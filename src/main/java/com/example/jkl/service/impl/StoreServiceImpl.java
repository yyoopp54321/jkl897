package com.example.jkl.service.impl;

import com.example.jkl.common.ServerResponse;
import com.example.jkl.dao.StoreDao;
import com.example.jkl.pojo.Store;
import com.example.jkl.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
@Service
public class StoreServiceImpl implements StoreService {
    @Autowired
    StoreDao storeDao;
    @Override
    public ServerResponse addStore(String storeName, String detail, Integer sellerId) {
        Store store = new Store();
        store.setSellerId(sellerId);
        store.setStoreName(storeName);
        store.setStatus((short) 1);
        store.setDetail(detail);
        int rowCount =storeDao.add(store);
        if (rowCount > 0) {
            return ServerResponse.createBySuccessMessage("开店成功,好运连连，祝你生意兴隆");
        }
        return ServerResponse.createByErrorMessage("开店失败");
    }

    @Override
    public ServerResponse updateStoreName(Integer storeId, String storeName, Integer sellerId) {
        // 检验一下店铺是否存在
        Store store = storeDao.findStoreById(storeId);
        if (null == store) {
            return ServerResponse.createByErrorMessage("店铺不存在");
        }
        // 检查店铺的属主是不是当前用户
        if (sellerId != store.getSellerId()) {
            return ServerResponse.createByErrorMessage("不是您的用户,不具有修改权限");
        }
        store.setStoreName(storeName);
        int rowCount = storeDao.update(store);
        if (rowCount > 0) {
            return ServerResponse.createBySuccessMessage("店铺名修改成功");
        }
        return ServerResponse.createByErrorMessage("店铺名修改失败");
    }


    @Override
    public ServerResponse closeStore(Integer storeId, Integer sellerId) {
        // 检验一下店铺是否存在
        Store store = storeDao.findStoreById(storeId);
        if (null == store) {
            return ServerResponse.createByErrorMessage("店铺不存在");
        }
        // 检查店铺的属主是不是当前用户
        if (sellerId != store.getSellerId()) {
            return ServerResponse.createByErrorMessage("不是您的店铺,不具有修改权限");
        }
        store.setStatus((short) 0);
        int rowCount = storeDao.update(store);
        if (rowCount > 0) {
            return ServerResponse.createBySuccessMessage("店铺关闭成功");
        }
        return ServerResponse.createByErrorMessage("店铺关闭失败");
    }


    @Override
    public ServerResponse findStoreDetail(Integer storeId, Integer sellerId) {
        // 检验一下店铺是否存在
        Store store = storeDao.findStoreById(storeId);
        if (null == store) {
            return ServerResponse.createByErrorMessage("店铺不存在");
        }
        // 检查店铺的属主是不是当前用户
        if (sellerId != store.getSellerId()) {
            return ServerResponse.createByErrorMessage("不是您的店铺,不具有查阅权限");
        }
        return ServerResponse.createBySuccessData(store);
    }


    @Override
    public ServerResponse findListStore(Integer sellerId) {
        List<Store> storeList = storeDao.select(sellerId);
        if (!CollectionUtils.isEmpty(storeList)) {
            return ServerResponse.createBySuccessData(storeList);
        }
        return ServerResponse.createBySuccessData("未找到");
    }
}
