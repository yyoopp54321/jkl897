package com.example.jkl.controller;

import com.example.jkl.common.Const;
import com.example.jkl.common.ServerResponse;
import com.example.jkl.pojo.User;
import com.example.jkl.service.StoreService;
import com.example.jkl.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.http.HttpSession;

@RestController
@EnableSwagger2
public class StoreController {
    @Autowired
    UserService userService;
    @Autowired
    StoreService storeService;
    @PostMapping (value = "add/store")
    public ServerResponse add(@RequestBody String storeName,@RequestBody String detail, HttpSession session){
        User currentUser = (User)session.getAttribute(Const.CURRENT_USER);
        if (null == currentUser) {
            return ServerResponse.createByNeedLogin();
        }
        if (!userService.checkRole(currentUser.getRole(), Const.Role.ROLE_SELLER)) {
            return ServerResponse.createByErrorMessage("非商家用户,不能开店");
        }
        return storeService.addStore(storeName, detail, currentUser.getId());
    }
    @GetMapping(value = "select/storeDetail")
    public ServerResponse getStoreDetail(@RequestParam Integer storeId, HttpSession session){
        User currentUser = (User)session.getAttribute(Const.CURRENT_USER);
        if (null == currentUser) {
            return ServerResponse.createByNeedLogin();
        }
        if (!userService.checkRole(currentUser.getRole(), Const.Role.ROLE_SELLER)) {
            return ServerResponse.createByErrorMessage("非商家用户,不具有查看权限");
        }
        if (null == storeId) {
            return ServerResponse.createByIllegalArgument();
        }
        return storeService.findStoreDetail(storeId, currentUser.getId());
    }
    @PutMapping(value = "update/storeName")
    public ServerResponse setStoreName(@RequestBody Integer storeId, @RequestBody String storeName, HttpSession session) {
        User currentUser = (User)session.getAttribute(Const.CURRENT_USER);
        if (null == currentUser) {
            return ServerResponse.createByNeedLogin();
        }
        if (!userService.checkRole(currentUser.getRole(), Const.Role.ROLE_SELLER)) {
            return ServerResponse.createByErrorMessage("非商家用户,不具有修改权限");
        }
        if (null == storeId || StringUtils.isBlank(storeName)) {
            return ServerResponse.createByIllegalArgument();
        }
        return storeService.updateStoreName(storeId, storeName, currentUser.getId());
    }
    @PutMapping(value = "close/store")
    public ServerResponse closeStore(@RequestBody Integer storeId, HttpSession session) {
        User currentUser = (User)session.getAttribute(Const.CURRENT_USER);
        if (null == currentUser) {
            return ServerResponse.createByNeedLogin();
        }
        if (!userService.checkRole(currentUser.getRole(), Const.Role.ROLE_SELLER)) {
            return ServerResponse.createByErrorMessage("非商家用户,不能关闭店铺");
        }
        if (null == storeId) {
            return ServerResponse.createByIllegalArgument();
        }
        return storeService.closeStore(storeId, currentUser.getId());
    }
    @GetMapping(value = "select/listStore")
    public ServerResponse listStore( HttpSession session){
        User currentUser = (User)session.getAttribute(Const.CURRENT_USER);
        if (null == currentUser) {
            return ServerResponse.createByNeedLogin();
        }
        if (!userService.checkRole(currentUser.getRole(), Const.Role.ROLE_SELLER)) {
            return ServerResponse.createByErrorMessage("非商家用户,不具有查看权限");
        }
        return storeService.findListStore(currentUser.getId());
    }
}
