package com.example.jkl.service;

import com.example.jkl.common.ServerResponse;
import com.example.jkl.request.AddAddressRequest;
import com.example.jkl.request.UpdateAddressRequest;

public interface AddressService {
    ServerResponse addAddress(AddAddressRequest addAddressRequest);
    ServerResponse deleteAddress(Integer addressId, Integer userId );
    ServerResponse  updateAddress(UpdateAddressRequest updateAddressRequest);
    ServerResponse selectAddress(Integer userId);
}
