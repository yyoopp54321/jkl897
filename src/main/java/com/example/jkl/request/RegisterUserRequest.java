package com.example.jkl.request;

import lombok.Data;

@Data
public class RegisterUserRequest {
    private String username;
    private String password;
    private String nickName;
    private Integer phone;
    private Short role;





}
