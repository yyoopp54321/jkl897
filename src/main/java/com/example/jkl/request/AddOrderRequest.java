package com.example.jkl.request;

import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

@Data
public class AddOrderRequest {
    @Column(name = "order_no")
    private Integer orderNo;

    @Column(name = "buyer_id")
    private Integer buyerId;

    @Column(name = "store_id")
    private Integer storeId;

    @Column(name = "goods_id")
    private Integer goodsId;

    @Column(name = "goods_name")
    private String goodsName;

    @Column(name = "goods_image")
    private String goodsImage;

    private Double price;

    private Integer count;

    @Column(name = "total_price")
    private Double totalPrice;

    @Column(name = "create_time")
    private Date createTime;
}
