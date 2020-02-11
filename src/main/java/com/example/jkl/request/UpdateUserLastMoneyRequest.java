package com.example.jkl.request;

import lombok.Data;

@Data
public class UpdateUserLastMoneyRequest {
    private String username;
    private Double lastMoney;

}
