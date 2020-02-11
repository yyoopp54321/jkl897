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
public class SellerAddressController {
    @Autowired
    private UserService userService;
    @Autowired
    private AddressService addressService;

    @PostMapping(value = "add/seller/address")
    public ServerResponse add(@RequestBody Address address, HttpSession session) {
        User currentUser = (User)session.getAttribute(Const.CURRENT_USER);
        if (null == currentUser) {
            return ServerResponse.createByNeedLogin();
        }
        if (!userService.checkRole(currentUser.getRole(), Const.Role.ROLE_SELLER)) {
            return ServerResponse.createByErrorMessage("不是商家用户,不能在商家端添加地址");
        }
        address.setUserId(currentUser.getId());
        address.setRole(currentUser.getRole());
        return addressService.addAddress(address);
    }

   @GetMapping(value = "select/seller/address")
    public ServerResponse select(@RequestBody Integer addressId, HttpSession session) {
        User currentUser = (User)session.getAttribute(Const.CURRENT_USER);
        if (null == currentUser) {
            return ServerResponse.createByNeedLogin();
        }
        if (!userService.checkRole(currentUser.getRole(), Const.Role.ROLE_SELLER)) {
            return ServerResponse.createByErrorMessage("不是商家用户,不能查看商家地址");
        }
        return addressService.selectAddress(addressId,currentUser.getId());
    }



    @PutMapping(value = "update/seller/address")
    public ServerResponse update(@RequestBody Address address, HttpSession session) {
        User currentUser = (User)session.getAttribute(Const.CURRENT_USER);
        if (null == currentUser) {
            return ServerResponse.createByNeedLogin();
        }
        if (!userService.checkRole(currentUser.getRole(), Const.Role.ROLE_SELLER)) {
            return ServerResponse.createByErrorMessage("不是商家用户,不能更新商家地址");
        }
        address.setUserId(currentUser.getId());
        address.setRole(currentUser.getRole());
        return addressService.updateAddress(address);
    }

    @DeleteMapping(value = "delete/seller/address")
    public ServerResponse delete(@RequestBody Integer addressId, HttpSession session) {
        User currentUser = (User)session.getAttribute(Const.CURRENT_USER);
        if (null == currentUser) {
            return ServerResponse.createByNeedLogin();
        }
        if (!userService.checkRole(currentUser.getRole(), Const.Role.ROLE_SELLER)) {
            return ServerResponse.createByErrorMessage("不是商家用户,不能删除商家地址");
        }
        return addressService.deleteAddress(addressId, currentUser.getRole(), currentUser.getId());
    }
}
