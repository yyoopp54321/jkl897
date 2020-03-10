package com.example.jkl.request;

import lombok.Data;

@Data
public class UpdateUserRequest {
    private String uImg;
    private String nickName;
    private Long phone;
}
