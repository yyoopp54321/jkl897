package com.example.jkl.service;

import com.example.jkl.common.ServerResponse;
import com.example.jkl.pojo.Address;

public interface AddressService {
    ServerResponse addAddress(Address address);
    ServerResponse deleteAddress(Integer addressId, Short role, Integer userId );
    ServerResponse  updateAddress(Address address);
    ServerResponse selectAddress(Integer addressId, Integer userId);
}
