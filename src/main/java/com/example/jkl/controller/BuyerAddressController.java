package com.example.jkl.controller;

import com.example.jkl.common.Const;
import com.example.jkl.common.ServerResponse;
import com.example.jkl.pojo.Address;
import com.example.jkl.pojo.User;
import com.example.jkl.service.AddressService;
import com.example.jkl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.http.HttpSession;

@RestController
@EnableSwagger2
public class BuyerAddressController {

    @Autowired
    private UserService userService;
    @Autowired
    private AddressService addressService;

    @PostMapping(value = "add/buyer/address")
    public ServerResponse add(@RequestBody Address address, HttpSession session) {
        User currentUser = (User)session.getAttribute(Const.CURRENT_USER);
        if (null == currentUser) {
            return ServerResponse.createByNeedLogin();
        }
        if (!userService.checkRole(currentUser.getRole(), Const.Role.ROLE_BUYER)) {
            return ServerResponse.createByErrorMessage("不是买家用户,不能在买家端添加地址");
        }
        address.setUserId(currentUser.getId());
        address.setRole(currentUser.getRole());
        return addressService.addAddress(address);
    }

    @GetMapping(value = "select/buyer/address")
    public ServerResponse select(@RequestBody Integer addressId, HttpSession session) {
        User currentUser = (User)session.getAttribute(Const.CURRENT_USER);
        if (null == currentUser) {
            return ServerResponse.createByNeedLogin();
        }
        if (!userService.checkRole(currentUser.getRole(), Const.Role.ROLE_BUYER)) {
            return ServerResponse.createByErrorMessage("不是买家用户,不能查看买家地址");
        }
        return addressService.selectAddress(addressId,currentUser.getId());
    }

    @PutMapping(value = "update/buyer/address")
    public ServerResponse update(@RequestBody Address address, HttpSession session) {
        User currentUser = (User)session.getAttribute(Const.CURRENT_USER);
        if (null == currentUser) {
            return ServerResponse.createByNeedLogin();
        }
        if (!userService.checkRole(currentUser.getRole(), Const.Role.ROLE_BUYER)) {
            return ServerResponse.createByErrorMessage("不是买家用户,不能更新买家地址");
        }
        address.setUserId(currentUser.getId());
        address.setRole(currentUser.getRole());
        return addressService.updateAddress(address);
    }

    @DeleteMapping(value = "delete/buyer/address")
    public ServerResponse delete(@RequestBody Integer addressId, HttpSession session) {
        User currentUser = (User)session.getAttribute(Const.CURRENT_USER);
        if (null == currentUser) {
            return ServerResponse.createByNeedLogin();
        }
        if (!userService.checkRole(currentUser.getRole(), Const.Role.ROLE_BUYER)) {
            return ServerResponse.createByErrorMessage("不是买家用户,不能删除买家地址");
        }
        return addressService.deleteAddress(addressId, currentUser.getRole(), currentUser.getId());
    }




}
