package com.example.jkl.controller;

import com.example.jkl.common.Const;
import com.example.jkl.common.ServerResponse;
import com.example.jkl.pojo.User;
import com.example.jkl.request.AddAddressRequest;
import com.example.jkl.request.UpdateAddressRequest;
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
    public ServerResponse add(@RequestBody AddAddressRequest addAddressRequest, HttpSession session) {
        User currentUser = (User)session.getAttribute(Const.CURRENT_USER);
        if (null == currentUser) {
            return ServerResponse.createByNeedLogin();
        }
        return addressService.addAddress(addAddressRequest);
    }

   @GetMapping(value = "select/seller/address")
    public ServerResponse select(@RequestParam Integer userId, HttpSession session) {
        User currentUser = (User)session.getAttribute(Const.CURRENT_USER);
        if (null == currentUser) {
            return ServerResponse.createByNeedLogin();
        }

        return addressService.selectAddress(userId);
    }



    @PutMapping(value = "update/seller/address")
    public ServerResponse update(@RequestBody UpdateAddressRequest updateAddressRequest, HttpSession session) {
        User currentUser = (User)session.getAttribute(Const.CURRENT_USER);
        if (null == currentUser) {
            return ServerResponse.createByNeedLogin();
        }

        return addressService.updateAddress(updateAddressRequest);
    }

    @DeleteMapping(value = "delete/seller/address")
    public ServerResponse delete(@RequestBody Integer addressId, HttpSession session) {
        User currentUser = (User)session.getAttribute(Const.CURRENT_USER);
        if (null == currentUser) {
            return ServerResponse.createByNeedLogin();
        }
        return addressService.deleteAddress(addressId, currentUser.getId());
    }
}
