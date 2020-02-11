package com.example.jkl.service.impl;

import com.example.jkl.common.ServerResponse;
import com.example.jkl.dao.AddressDao;
import com.example.jkl.pojo.Address;
import com.example.jkl.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    AddressDao addressDao;
    @Override
    public ServerResponse addAddress(Address address) {
        Integer add = addressDao.add(address);
        if (add>0){
            return ServerResponse.createBySuccessData("添加收货地址成功");
        }else {
            return ServerResponse.createByErrorMessage("添加收货地址失败");
        }
    }

    @Override
    public ServerResponse deleteAddress(Integer addressId, Short role, Integer userId) {
        List<Address> delete = addressDao.delete(addressId, role, userId);
        if (delete !=null) {
            return ServerResponse.createBySuccessMessage("删除地址成功");
        }
        return ServerResponse.createByErrorMessage("删除地址失败");
    }


    @Override
    public ServerResponse updateAddress(Address address) {
        Integer update = addressDao.update(address);
        if (update>0){
            return ServerResponse.createBySuccessData("更新收货地址成功");
        }else {
            return ServerResponse.createByErrorMessage("更新收货地址失败");
        }
    }

    @Override
    public ServerResponse selectAddress(Integer addressId, Integer userId) {
        List<Address> select = addressDao.select(addressId, userId);
        if (null == select) {
            return ServerResponse.createByErrorMessage("未查询到与该用户对应的地址");
        }
        return ServerResponse.createBySuccessData(select);
    }
    }

