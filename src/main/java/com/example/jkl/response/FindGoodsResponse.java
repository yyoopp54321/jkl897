package com.example.jkl.response;

import lombok.Data;

@Data
public class FindGoodsResponse {
    private Integer id;
    private String name;
    private Integer stock;
    private Double price;
    private String mainImageUrl;
    private String gAddress;

}
