package com.example.jkl.controller;

import com.example.jkl.common.Const;
import com.example.jkl.common.ServerResponse;
import com.example.jkl.pojo.User;
import com.example.jkl.request.AddGoodsRequest;
import com.example.jkl.request.UpdateGoodsRequest;
import com.example.jkl.request.UpdateGoodsStatusRequest;
import com.example.jkl.response.FindGoodsResponse;
import com.example.jkl.service.GoodsService;
import com.example.jkl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@EnableSwagger2
public class GoodsController {
    @Autowired
    GoodsService goodsService;
    @Autowired
    UserService userService;
    //添加商品
    @PostMapping(value = "add/goods")
    public ServerResponse addGoods(@RequestBody AddGoodsRequest addGoodsRequest, HttpSession session){
        User currentUser = (User)session.getAttribute(Const.CURRENT_USER);
        if (null == currentUser) {
            return ServerResponse.createByNeedLogin();
        }
        if (!userService.checkRole(currentUser.getRole(), Const.Role.ROLE_SELLER)) {
            return ServerResponse.createByErrorMessage("您不是商家，无法添加商品");
        }
     return goodsService.addGoods(addGoodsRequest);
    }
    //删出商品
    @DeleteMapping(value = "delete/goods/by/id")
    public  ServerResponse deleteGoods(@RequestBody Integer id){
        return goodsService.deleteGoods(id);
    }
    //更新商品
    @PutMapping(value ="update/goods")
    public ServerResponse updateGoods(@RequestBody UpdateGoodsRequest updateGoodsRequest, HttpSession session){
        User currentUser = (User)session.getAttribute(Const.CURRENT_USER);
        if (null == currentUser) {
            return ServerResponse.createByNeedLogin();
        }
        if (!userService.checkRole(currentUser.getRole(), Const.Role.ROLE_SELLER)) {
            return ServerResponse.createByErrorMessage("您不是商家，修改商品");
        }
        if (null == updateGoodsRequest) {
            return ServerResponse.createByIllegalArgument();
        }
        return goodsService.updateGoods(updateGoodsRequest);
    }


    //封页查找商品
    @GetMapping(value = "select/goods/by/name")
    public List<FindGoodsResponse> findGoodsByGName(@RequestBody String gName, Integer pageNumber, Integer pageSize){
        return goodsService.findGoodsByGName(gName,pageNumber,pageSize);
    }
    @PutMapping(value = "update/goods/status")
    public ServerResponse setStatus(UpdateGoodsStatusRequest updateGoodsStatusRequest,HttpSession session){
        User currentUser = (User)session.getAttribute(Const.CURRENT_USER);
        if (null == currentUser) {
            return ServerResponse.createByNeedLogin();
        }
        if (!userService.checkRole(currentUser.getRole(), Const.Role.ROLE_SELLER)) {
            return ServerResponse.createByErrorMessage("您不是商家，不能更改商品状态");
        }
        if (null == updateGoodsStatusRequest.getStatus()|| null == updateGoodsStatusRequest.getGoodsId()) {
            return ServerResponse.createByIllegalArgument();
        }
        return goodsService.setStatus(updateGoodsStatusRequest);
    }


    //批量删除
    @DeleteMapping(value = "delete/goods/by/list")
    public ServerResponse deleteGoodsList(@RequestBody List<Integer> ids){
        return goodsService.deleteGoodsList(ids);
    }
    //通过店名去找
    @GetMapping(value = "select/goods/by/storeName")
    public ServerResponse searchByStoreName(@RequestBody String storeName,@RequestBody Integer pageNumber,@RequestBody Integer pageSize){
        return goodsService.searchByStoreName(storeName,pageNumber,pageSize);
    }
    @RequestMapping("upload")
    public ServerResponse upload(@RequestParam(value = "upload_file", required = false) MultipartFile file,
                                 HttpSession session, HttpServletRequest request) {
        User currentUser = (User)session.getAttribute(Const.CURRENT_USER);
        if (null == currentUser) {
            return ServerResponse.createByNeedLogin();
        }
        if (!userService.checkRole(currentUser.getRole(), Const.Role.ROLE_SELLER)) {
            return ServerResponse.createByErrorMessage("您不是商家，不具有上传文件权限");
        }
        String uploadDir = request.getSession().getServletContext().getRealPath("upload");
        return goodsService.upload(file, uploadDir);
    }


    @RequestMapping("product-image-upload")
    public ServerResponse productImageUpload(@RequestParam(value = "productId", required = false)Integer productId,
                                             @RequestParam(value = "mainImage", required = false)MultipartFile mainImage,
                                             @RequestParam(value = "subImage", required = false) List<MultipartFile> subImage,
                                             HttpSession session, HttpServletRequest request) {
        User currentUser = (User)session.getAttribute(Const.CURRENT_USER);
        if (null == currentUser) {
            return ServerResponse.createByNeedLogin();
        }
        if (!userService.checkRole(currentUser.getRole(), Const.Role.ROLE_SELLER)) {
            return ServerResponse.createByErrorMessage("您不是商家，不具有上传文件权限");
        }
        String uploadDir = request.getSession().getServletContext().getRealPath("upload");
        return goodsService.productImageUpload(productId, mainImage, subImage,uploadDir);
    }




    @RequestMapping("rich_text_upload")
    public ServerResponse richTextUpload(@RequestParam(value = "upload_file", required = false)MultipartFile file,
                                         HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        User currentUser = (User)session.getAttribute(Const.CURRENT_USER);
        if (null == currentUser) {
            return ServerResponse.createByNeedLogin();
        }
        if (!userService.checkRole(currentUser.getRole(), Const.Role.ROLE_SELLER)) {
            return ServerResponse.createByErrorMessage("您不是商家，不具有上传文件权限");
        }
        String uploadDir = request.getSession().getServletContext().getRealPath("upload");
        response.addHeader("Access-Control-Allow-Headers","X-File-Name");
        return  goodsService.upload(file, uploadDir);
    }

}
