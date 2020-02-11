package com.example.jkl.service;

import com.example.jkl.common.ServerResponse;

public interface StoreService {
    ServerResponse addStore(String storeName, String detail, Integer sellerId);
    ServerResponse updateStoreName(Integer storeId, String storeName, Integer sellerId);
    ServerResponse closeStore(Integer storeId, Integer sellerId);
    ServerResponse findStoreDetail(Integer storeId, Integer sellerId);
    ServerResponse findListStore( Integer sellerId);
}
