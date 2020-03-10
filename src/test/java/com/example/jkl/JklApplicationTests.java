package com.example.jkl;


import com.example.jkl.controller.SellerController;
import com.example.jkl.dao.UserDao;
import com.example.jkl.service.GoodsService;
import com.example.jkl.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JklApplicationTests {
@Autowired
    UserDao userDao;
@Autowired
    GoodsService goodsService;
@Autowired
    UserService userService;
@Autowired
    SellerController sellerController;

    @Test
    void contextLoads() {
  /*      User user = new User();
        user.setUsername("520");
        user.setPassword("520");
        Integer integer = userDao.addUser(user);
        System.out.println(integer);*/
       /* List<User> list = userDao.loginUser("520", "520");
        System.out.println(list.get(0));*/

       /* ServerResponse login = userService.login("520", "520");

        User user=(User)login.getData();
        System.out.println(user.toString());*/
    }

        
    }


