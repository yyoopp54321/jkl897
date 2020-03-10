package com.example.jkl.response;

import lombok.Data;

@Data
public class FindOrderResponse  {
    private Integer storeId;
    private String gName;
    private Double gPrice;
    private Integer gCount;
    private Double totalMoney;
}
