package com.example.jkl.request;

import lombok.Data;

@Data
public class AddGoodsRequest {


    //名称
    private String name;
    //数量
    private Integer stock;
    //单价
    private Double price;
    private String mainImageUrl;
    private String subImagesUrl;
   //简要
    private String brief;
    //上下架
    private Short status;
    //地址
    private String gAddress;
}
