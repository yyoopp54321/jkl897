package com.example.jkl.dao;

import com.example.jkl.mapper.AddressMapper;
import com.example.jkl.pojo.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
@Repository
public class AddressDao {
    @Autowired
    AddressMapper addressMapper;
    public List<Address> findAddressByUserId(Integer userId){
        Example example=new Example(Address.class);
        example.createCriteria().andEqualTo("userId",userId);
       return addressMapper.selectByExample(example);
    }
    public Integer add(Address address){
        return addressMapper.insert(address);
    }
    public List<Address> delete(Integer addressId, Short role, Integer userId){
        Example example=new Example(Address.class);
        example.createCriteria()
                .andEqualTo("id",addressId)
                .andEqualTo("role",role)
                .andEqualTo("userId",userId);
        return  addressMapper.selectByExample(example);
    }
    public Integer  update(Address address){
        return  addressMapper.updateByPrimaryKey(address);
    }
    public  List<Address> select(Integer addressId, Integer userId){
        Example example=new Example(Address.class);
        example.createCriteria()
                .andEqualTo("id",addressId)
                .andEqualTo("userId",userId);
        return addressMapper.selectByExample(example);
    }

}
