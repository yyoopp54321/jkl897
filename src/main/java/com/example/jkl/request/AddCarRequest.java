package com.example.jkl.request;

import lombok.Data;

@Data
public class AddCarRequest {
    private Integer buyerId;
    private Integer storeId;
    private Integer goodsId;
    private Double price;
    private Integer count;
}
