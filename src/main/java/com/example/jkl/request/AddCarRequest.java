package com.example.jkl.request;

import lombok.Data;

import javax.persistence.Column;

@Data
public class AddCarRequest {
    private Integer buyerId;
    private Integer storeId;
    private Integer goodsId;
    private Double price;
    private Integer count;
    @Column(name = "main_image_url")
    private String mainImageUrl;

    @Column(name = "g_name")
    private String gName;
}
