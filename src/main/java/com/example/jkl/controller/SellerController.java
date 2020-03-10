package com.example.jkl.controller;

import com.example.jkl.common.Const;
import com.example.jkl.common.ServerResponse;
import com.example.jkl.pojo.User;
import com.example.jkl.request.RegisterUserRequest;
import com.example.jkl.request.UpdateUserRequest;
import com.example.jkl.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@EnableSwagger2
public class SellerController {

    @Autowired
    private UserService userService;


    /**
     * 商家用户登陆
     * @param username
     * @param password
     * @param session
     * @return
     */
    @GetMapping(value = "login/seller")
    public ServerResponse login(@RequestParam String username , @RequestParam String password, HttpSession session) {
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            return ServerResponse.createByIllegalArgument();
        }
        ServerResponse login = userService.login(username, password);
        if (!login .isSuccess()){
            return login;
        }
        List<User> list = userService.findUserByName(username);
        User user = list.get(0);

        if (userService.checkRole(user.getRole(), Const.Role.ROLE_SELLER)) {
                // 用户登陆成功，将其信息放入session中
                session.setAttribute(Const.CURRENT_USER, user);
                return login;

        }
            return ServerResponse.createByErrorMessage("非商家用户,请注册商家用户");
        }


    /**
     * 注册商家用户
     * @param registerUserRequest
     * @return
     */
    @PostMapping(value = "register/seller")
    public ServerResponse register(@RequestBody RegisterUserRequest registerUserRequest){
        if (null == registerUserRequest) {
            return ServerResponse.createByIllegalArgument();
        }
        registerUserRequest.setRole((short) Const.Role.ROLE_SELLER);
        return userService.register(registerUserRequest);
    }

    /**
     * 更新个人信息
     * @param updateUserRequest
     * @param session
     * @return
     */
    @PutMapping(value = "update/seller")
    public ServerResponse updateSeller(@RequestBody UpdateUserRequest updateUserRequest,HttpSession session){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (null == user) {
            return ServerResponse.createByNeedLogin("用户未登录,请先登陆");
        }

        return userService.updateUser(updateUserRequest);

    }

    /**
     * 获取商家个人信息
     * @param session
     * @return
     */
    @GetMapping(value = "select/seller/information")
    public ServerResponse getUserInformation( HttpSession session) {
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (null == currentUser) {
            return ServerResponse.createByNeedLogin("用户未登录,请先登陆");
        }
        return userService.getUserInformation(currentUser.getId());
    }

}
