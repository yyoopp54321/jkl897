package com.example.jkl.service.impl;


import com.example.jkl.common.ServerResponse;
import com.example.jkl.config.MybatisConfig;
import com.example.jkl.dao.GoodsDao;
import com.example.jkl.dao.StoreDao;
import com.example.jkl.dao.UserDao;
import com.example.jkl.pojo.Goods;
import com.example.jkl.pojo.Store;
import com.example.jkl.request.AddGoodsRequest;
import com.example.jkl.request.UpdateGoodsRequest;
import com.example.jkl.request.UpdateGoodsStatusRequest;
import com.example.jkl.response.FindGoodsInfoResponse;
import com.example.jkl.response.FindGoodsResponse;
import com.example.jkl.response.GetAllGoodsResponse;
import com.example.jkl.service.GoodsService;
import com.example.jkl.service.UploadService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    GoodsDao goodsDao;
    @Autowired
    MybatisConfig mybatisConfig;
    @Autowired
    UserDao userDao;
    @Autowired
    StoreDao storeDao;
    @Autowired
    UploadService uploadService;

    @Override
    public ServerResponse addGoods(AddGoodsRequest addGoodsRequest) {
         Goods goods=new Goods();
         BeanUtils.copyProperties(addGoodsRequest,goods);
         String [] UrlArray =addGoodsRequest.getSubImagesUrl().split(",");
         goods.setSubImagesUrl(UrlArray[0]);
        Integer integer = goodsDao.addGoods(goods);
        if (integer>0){
            return ServerResponse.createBySuccessData("添加商品成功");
        }else {
            return ServerResponse.createByErrorMessage("添加商品失败");
        }

    }
    @Override
    public ServerResponse deleteGoods(Integer id) {
        Integer integer = goodsDao.deleteGoods(id);
        if (integer>0){
            return ServerResponse.createBySuccessData("删除商品成功");
        }else {
            return ServerResponse.createByErrorMessage("删除商品失败");
        }
    }
    @Override
   public ServerResponse setStatus(UpdateGoodsStatusRequest updateGoodsStatusRequest){
        Goods goodsById = goodsDao.findGoodsById(updateGoodsStatusRequest.getGoodsId());
        if (goodsById==null){
            return ServerResponse.createByErrorMessage("要修改的商品不存在");
        }
        Goods goods = new Goods();
        goods.setStatus(updateGoodsStatusRequest.getStatus());
        Integer integer = goodsDao.updateGoods(goods);
        if (integer > 0) {
            return ServerResponse.createBySuccessMessage("商品状态修改成功");
        }
        return ServerResponse.createByErrorMessage("商品状态修改失败");
    }


    @Override
    public ServerResponse updateGoods(UpdateGoodsRequest updateGoodsRequest) {
        Goods goods = new Goods();
        goods.setMainImageUrl(updateGoodsRequest.getMainImageUrl());
        String [] UrlArray =goods.getSubImagesUrl().split(",");
        goods.setSubImagesUrl(UrlArray[0]);
        goods.setSubImagesUrl(updateGoodsRequest.getSubImagesUrl());
        goods.setgAddress(updateGoodsRequest.getGAddress());
        goods.setPrice(updateGoodsRequest.getPrice());
        goods.setBrief(updateGoodsRequest.getBrief());
        goods.setStock(updateGoodsRequest.getStock());
        goods.setName(updateGoodsRequest.getName());

        Integer integer = goodsDao.updateGoods(goods);
        if (integer > 0) {
            return ServerResponse.createBySuccessMessage("商品修改成功");
        }
        return ServerResponse.createByErrorMessage("商品修改失败");
    }


    @Override
    public GetAllGoodsResponse findGoodsByGName(String gName, Integer pageNumber, Integer pageSize) {
        PageHelper.startPage(pageNumber, pageSize);
        List<Goods> all = goodsDao.findGoodsByGName(gName);
        List<FindGoodsResponse> goodsResponseList=new ArrayList<>();
        for (Goods goods:all){
            FindGoodsResponse findGoodsResponse=new FindGoodsResponse();
            BeanUtils.copyProperties(goods,findGoodsResponse);
            goodsResponseList.add(findGoodsResponse);
        }
        PageInfo<FindGoodsResponse> pageInfo = new PageInfo<>(goodsResponseList);
        GetAllGoodsResponse getAllGoodsResponse = new GetAllGoodsResponse();
        getAllGoodsResponse.setTotal( pageInfo.getTotal());
        getAllGoodsResponse.setFindGoodsResponseList(pageInfo.getList());
        return getAllGoodsResponse;
    }

   


    @Override
    public  ServerResponse getGoodsInfo(Integer id) {

        Goods goods= goodsDao.findGoodsById(id);
        FindGoodsInfoResponse findGoodsInfoResponse = new FindGoodsInfoResponse();
        BeanUtils.copyProperties(goods,findGoodsInfoResponse );
        return ServerResponse.createBySuccessData(findGoodsInfoResponse);

    }


    @Override
    public GetAllGoodsResponse findAllGoods(Integer pageNumber, Integer pageSize) {

        //开始分页必须写在上面
        PageHelper.startPage(pageNumber, pageSize);
        List<Goods> all = goodsDao.findAllGoods();
        List<FindGoodsResponse> goodsResponseList=new ArrayList<>();
        for (Goods goods:all){
            FindGoodsResponse findGoodsResponse=new FindGoodsResponse();
            BeanUtils.copyProperties(goods,findGoodsResponse);
            goodsResponseList.add(findGoodsResponse);
        }
        PageInfo<FindGoodsResponse> pageInfo = new PageInfo<>(goodsResponseList);
        GetAllGoodsResponse getAllGoodsResponse = new GetAllGoodsResponse();
        getAllGoodsResponse.setTotal( pageInfo.getTotal());
        getAllGoodsResponse.setFindGoodsResponseList(pageInfo.getList());
        return getAllGoodsResponse;
    }



    @Override
    public ServerResponse deleteGoodsList(List<Integer> ids) {
        Integer integer = goodsDao.deleteGoodsList(ids);
        if (integer > 0) {
            return ServerResponse.createBySuccessMessage("商品状态修改成功");
        }
        return ServerResponse.createByErrorMessage("商品状态修改失败");

    }
    @Override
    public ServerResponse searchByStoreName(String storeName,Integer pageNumber, Integer pageSize) {

        List<Store> storeList = storeDao.findStoreByName(storeName);
        if (storeList==null){
            return ServerResponse.createByErrorMessage("不存在该用户");
        }
        Store store = storeList.get(0);
        PageHelper.startPage(pageNumber, pageSize);
        List<Goods> all = goodsDao.findGoodsByStoreId(store.getId());
        List<FindGoodsResponse> goodsResponseList=new ArrayList<>();
        for (Goods goods:all){
            FindGoodsResponse findGoodsResponse=new FindGoodsResponse();
            BeanUtils.copyProperties(goods,findGoodsResponse);
            goodsResponseList.add(findGoodsResponse);
        }
        PageInfo<FindGoodsResponse> pageInfo = new PageInfo<>(goodsResponseList);
        GetAllGoodsResponse getAllGoodsResponse = new GetAllGoodsResponse();
        getAllGoodsResponse.setTotal( pageInfo.getTotal());
        getAllGoodsResponse.setFindGoodsResponseList(pageInfo.getList());
        return ServerResponse.createBySuccessData(getAllGoodsResponse);
    }

    @Override
    public Integer selectCount(Goods goods) {
        return selectCount(goods);
    }
    /*@Override
    public ServerResponse MainImageUpload(Integer id, MultipartFile mainImage,String uploadPath) {
        Goods goods = goodsDao.findGoodsById(id);
        if (goods == null) {
            return ServerResponse.createByErrorMessage("此productId对应的商品不存在");
        }

        String mainImageUrl = String.valueOf(uploadService.fileUpload(mainImage, uploadPath));
        if (StringUtils.isBlank( mainImageUrl)) {
            return ServerResponse.createByErrorMessage("上传商品图片失败");
        }

        goods.setMainImageUrl(mainImageUrl);

        goodsDao.updateGoods(goods);
        return ServerResponse.createBySuccessMessage("上传商品图片成功");
    }
   public ServerResponse SubImageUpload(Integer id, List<MultipartFile> subImage, String uploadPath){
       Goods goods = goodsDao.findGoodsById(id);
       if (goods == null) {
           return ServerResponse.createByErrorMessage("此productId对应的商品不存在");
       }
       String subImageUrl = "";

       for (int i=0;i<subImage.size();i++) {
           MultipartFile image = subImage.get(i);
           String imageUrl = String.valueOf(uploadService.fileUpload(image, uploadPath));
           if (StringUtils.isBlank(imageUrl)) {
               return ServerResponse.createByErrorMessage("上传商品图片失败");
           }
           if (i == 0) {
               subImageUrl += imageUrl;
           } else {
               subImageUrl += "," + imageUrl;
           }

       }
       goods.setSubImagesUrl(subImageUrl);
       goodsDao.updateGoods(goods);
       return ServerResponse.createBySuccessMessage("上传商品图片成功");
   }*/
}