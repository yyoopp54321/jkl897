package com.example.jkl.response;

import lombok.Data;

@Data
public class FindAddressResponse {
    private Integer id;

    private String name;

    private String provice;

    private String district;

    private String city;

    private String street;

    private Integer postcode;
}
