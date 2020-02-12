package com.example.jkl.request;

import lombok.Data;

@Data
public class UpdateGoodsRequest {
    private String name;
    private Double price;
    private Integer stock;
    private  String brief;
    private String subImagesUrl;
    private String mainImageUrl;
    private String gAddress;
}
