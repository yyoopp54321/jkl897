package com.example.jkl.service.impl;

import com.example.jkl.common.ServerResponse;
import com.example.jkl.dao.AddressDao;
import com.example.jkl.dao.UserDao;
import com.example.jkl.pojo.Address;
import com.example.jkl.request.AddAddressRequest;
import com.example.jkl.request.UpdateAddressRequest;
import com.example.jkl.response.FindAddressResponse;
import com.example.jkl.service.AddressService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    AddressDao addressDao;
    @Autowired
    UserDao userDao;
    @Override
    public ServerResponse addAddress(AddAddressRequest addAddressRequest) {
        Address address = new Address();
        address.setProvice(addAddressRequest.getProvice());
        address.setDistrict(addAddressRequest.getDistrict());
        address.setCity(addAddressRequest.getCity());
        address.setStreet(addAddressRequest.getStreet());
        address.setPostcode(addAddressRequest.getPostcode());
        Integer add = addressDao.add(address);
        if (add>0){
            return ServerResponse.createBySuccessData("添加收货地址成功");
        }else {
            return ServerResponse.createByErrorMessage("添加收货地址失败");
        }
    }

    @Override
    public ServerResponse deleteAddress(Integer addressId,  Integer userId) {

        List<Address> delete = addressDao.delete(addressId, userId);
        if (delete !=null) {
            return ServerResponse.createBySuccessMessage("删除地址成功");
        }
        return ServerResponse.createByErrorMessage("删除地址失败");
    }


    @Override
    public ServerResponse updateAddress(UpdateAddressRequest updateAddressRequest) {
        Address address = new Address();
        address.setProvice(updateAddressRequest.getProvice());
        address.setDistrict(updateAddressRequest.getDistrict());
        address.setCity(updateAddressRequest.getCity());
        address.setStreet(updateAddressRequest.getStreet());
        address.setPostcode(updateAddressRequest.getPostcode());
        Integer add = addressDao.add(address);

        Integer update = addressDao.update(address);
        if (update>0){
            return ServerResponse.createBySuccessData("更新收货地址成功");
        }else {
            return ServerResponse.createByErrorMessage("更新收货地址失败");
        }
    }

    @Override
    public ServerResponse selectAddress( Integer userId) {
        List<Address> select = addressDao.select(userId);
        List<FindAddressResponse> findAddressResponseList=new ArrayList<>();
        for (Address address:select){
            FindAddressResponse findAddressResponse = new FindAddressResponse();
            BeanUtils.copyProperties(address,findAddressResponse);
            findAddressResponseList.add(findAddressResponse);
        }
        if (null == select) {
            return ServerResponse.createByErrorMessage("未查询到与该用户对应的地址");
        }
        return ServerResponse.createBySuccessData(findAddressResponseList);
    }

    }

