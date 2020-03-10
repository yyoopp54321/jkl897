package com.example.jkl.controller;

import com.example.jkl.common.Const;
import com.example.jkl.common.ServerResponse;
import com.example.jkl.pojo.User;
import com.example.jkl.request.AddCarRequest;
import com.example.jkl.request.UpdateCarRequest;
import com.example.jkl.response.FindCarResponse;
import com.example.jkl.service.CarService;
import com.example.jkl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@EnableSwagger2
public class CarController {
    @Autowired
    CarService carService;
    @Autowired
    UserService userService;
    //查看购物车所有记录
    @GetMapping(value = "find/car")
    public List<FindCarResponse> findCarByUserId(Integer userId){
        return carService.findCarByUserId(userId);
    }
    //删除购物车记录
    @DeleteMapping(value = "delete/car/by/id")
    public ServerResponse deleteCar(@RequestBody Integer id){
        return carService.deleteCar(id);
    }
    //添加到购物车
   @PostMapping(value = "add/car")
    public ServerResponse addCar(@RequestBody AddCarRequest addCarRequest, HttpSession session){
       User currentUser = (User)session.getAttribute(Const.CURRENT_USER);
       if (null == currentUser) {
           return ServerResponse.createByNeedLogin();
       }

       if (null == addCarRequest.getGoodsId() || null == addCarRequest.getCount()) {
           return ServerResponse.createByIllegalArgument();
       }
       return carService.addCar(addCarRequest);
   }


   //批量删除
    @DeleteMapping(value = "delete/car/carList")
   public ServerResponse deleteCarList(@RequestBody List<Integer> ids){
        Integer integer = carService.deleteCarList(ids);
        if (integer == null) {
            return ServerResponse.createByErrorMessage("删除失败");
        }else {
            return ServerResponse.createBySuccessData("删除成功");}
    }
    //修改商品数量
    @PutMapping(value = "update/car/GoodsCount")
    public Integer updateCarGoodsCount(UpdateCarRequest updateCarRequest){
        return carService.updateCarGoodsCount(updateCarRequest);
    }
}

