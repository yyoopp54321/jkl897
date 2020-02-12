package com.example.jkl.request;

import lombok.Data;

@Data
public class UpdateGoodsStatusRequest {
    private Integer goodsId;
    private Short  status;
    private  Integer sellerId;

}
