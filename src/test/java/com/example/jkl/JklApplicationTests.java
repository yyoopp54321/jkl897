package com.example.jkl;


import com.example.jkl.dao.UserDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JklApplicationTests {
@Autowired
    UserDao userDao;
    @Test
    void contextLoads() {

        }
        
    }


