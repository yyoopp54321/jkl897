package com.example.jkl.service;

import com.example.jkl.common.ServerResponse;
import com.example.jkl.request.AddGoodsRequest;
import com.example.jkl.request.UpdateGoodsRequest;
import com.example.jkl.request.UpdateGoodsStatusRequest;
import com.example.jkl.response.FindGoodsResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface GoodsService {
    ServerResponse addGoods(AddGoodsRequest addGoodsRequest);
    ServerResponse deleteGoods(Integer id);
    ServerResponse setStatus(UpdateGoodsStatusRequest updateGoodsStatusRequest);
    ServerResponse updateGoods(UpdateGoodsRequest updateGoodsRequest);
    List<FindGoodsResponse> findGoodsByGName(String gName, Integer pageNumber, Integer pageSize);
    /*List<Goods> findAllGoods(Integer pageNumber,Integer pageSize);*/
    ServerResponse deleteGoodsList(List<Integer> ids);
    ServerResponse searchByStoreName(String storeName,Integer pageNumber, Integer pageSize);
   ServerResponse upload(MultipartFile multipartFile, String uploadPath);

   ServerResponse productImageUpload(Integer productId, MultipartFile mainImage, List<MultipartFile> subImage, String uploadPath);
}
