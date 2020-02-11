package com.example.jkl.request;

import lombok.Data;

@Data
public class UpdateUserPasswordRequest {
    private String username;
    private String password;
}
