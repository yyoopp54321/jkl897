package com.example.jkl.service;

import com.example.jkl.common.ServerResponse;
import com.example.jkl.pojo.Goods;
import com.example.jkl.request.AddGoodsRequest;
import com.example.jkl.request.UpdateGoodsRequest;
import com.example.jkl.request.UpdateGoodsStatusRequest;
import com.example.jkl.response.GetAllGoodsResponse;

import java.util.List;

public interface GoodsService {
    ServerResponse addGoods(AddGoodsRequest addGoodsRequest);
    ServerResponse deleteGoods(Integer id);
    ServerResponse setStatus(UpdateGoodsStatusRequest updateGoodsStatusRequest);
    ServerResponse updateGoods(UpdateGoodsRequest updateGoodsRequest);
    GetAllGoodsResponse findGoodsByGName(String gName, Integer pageNumber, Integer pageSize);
    ServerResponse  getGoodsInfo(Integer id );
    GetAllGoodsResponse findAllGoods(Integer pageNumber, Integer pageSize);
    ServerResponse deleteGoodsList(List<Integer> ids);
    ServerResponse searchByStoreName(String storeName,Integer pageNumber, Integer pageSize);
    Integer selectCount(Goods goods);
 /*   ServerResponse MainImageUpload(Integer goodsId, MultipartFile mainImage,  String uploadPath);
    ServerResponse SubImageUpload(Integer goodsId,List<MultipartFile> subImage, String uploadPath);*/
}
