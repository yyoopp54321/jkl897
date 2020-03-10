package com.example.jkl.service.impl;

import com.example.jkl.common.Const;
import com.example.jkl.common.ServerResponse;
import com.example.jkl.dao.*;
import com.example.jkl.pojo.*;
import com.example.jkl.request.PayOrderRequest;
import com.example.jkl.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderDao orderDao;
    @Autowired
    AddressDao addressDao;
    @Autowired
    CarDao carDao;
    @Autowired
    GoodsDao goodsDao;
    @Autowired
    UserDao userDao;

    @Autowired
    StoreDao storeDao;

    @Override
    public ServerResponse addOrderEntity(OrderEntity orderEntity) {
        Address address = addressDao.findAddressByUserId(orderEntity.getBuyerId());
        if (null == address) {
            return ServerResponse.createByErrorMessage("订单创建失败,未找到对应地址");
        }
        Order order = new Order();
        order.setId(orderEntity.getId());
        order.setBuyerId(orderEntity.getBuyerId());
        order.setPayment(null);
        order.setOrderNo(orderEntity.getOrderNo());
        order.setAddressId(address.getId());
        order.setStatus((short) 0);
        order.setUpdateTime(new Date());
        order.setCreateTime(new Date());

        Integer integer = orderDao.addOrderEntity(orderEntity);
        if (integer > 0) {
            return ServerResponse.createBySuccessMessage("添加成功");
        }
        return ServerResponse.createByErrorMessage("添加失败");
    }


    @Override
    public ServerResponse deleteOrder(Integer id) {
        Integer integer = orderDao.deleteOrderById(id);
        if (integer > 0) {
            return ServerResponse.createBySuccessMessage("添加成功");
        }
        return ServerResponse.createByErrorMessage("添加失败");
    }

    @Override
    public ServerResponse payOrder(PayOrderRequest payOrderRequest) {
        for (OrderEntity orderEntity: payOrderRequest.getOrderEntityList()) {
            User user = userDao.findUser(orderEntity.getBuyerId());
            if (user.getLastMoney() >= payOrderRequest.getTotalMoneys()) {
                user.setLastMoney(user.getLastMoney() - payOrderRequest.getTotalMoneys());
                Integer integer = userDao.updateUser(user);
                log.info("修改用户余额—{}", integer);
                Goods goods = goodsDao.findGoodsById(orderEntity.getGoodsId());
                goods.setStock(goods.getStock() - orderEntity.getCount());
                Integer integer1 = goodsDao.updateGoods(goods);
                log.info("减少库存数量-{}", integer1);
                Order order = orderDao.findOrderByPrimaryKey(orderEntity.getId());
                order.setStatus((short) 1);
                order.setPayment(payOrderRequest.getTotalMoneys());
                order.setUpdateTime(new Date());
                Integer update = orderDao.update(order);
                return ServerResponse.createBySuccessMessageAndData("支付成功", update);
            } else {
                return ServerResponse.createByErrorMessage("支付失败");
            }

        }
      return ServerResponse.createBySuccess();
    }

    @Override
    public ServerResponse backOrder(Order order) {
        if (order.getStatus()==1){
            //已支付可以退单
            User user = userDao.findUser(order.getBuyerId());
            user.setLastMoney(user.getLastMoney()+order.getPayment());
            userDao.updateUser(user);
            //订单状态设为——1表示退单
            order.setStatus((short) -1);
            order.setUpdateTime(new Date());
            Integer update = orderDao.update(order);
            return ServerResponse.createBySuccessMessageAndData("退单成功",update);
        }else {
            //无法退单 正常结束
            return ServerResponse.createByError();
        }
    }

    @Override
    public List<OrderEntity> findOrderByStoreId(Integer storeId) {
        return orderDao.findOrderByStoreId(storeId);
    }

    @Override
    public Integer deleteOrderList(List<Integer> ids) {
        return orderDao.deleteOrderList(ids);
    }

    @Override
    public ServerResponse findOrderByOrderNo(Integer orderNo) {
        List<Order> orderByOrderNo = orderDao.findOrderByOrderNo(orderNo);
        if (orderByOrderNo == null) {
            return ServerResponse.createByErrorMessage("不存在此订单");
        }
        return ServerResponse.createBySuccess();
    }

    public ServerResponse getOrderEntityList(List<Car> carList) {
        List<OrderEntity> orderEntityList = new ArrayList<>();
        for (Car car : carList) {
            Goods goods = goodsDao.findGoodsById(car.getGoodsId());
            if (goods.getStatus() != Const.ProductStatus.ON_SALE) {
                return ServerResponse.createByErrorMessage("商品：" + goods.getName() + "已下架");
            }
            if (goods.getStock() < car.getCount()) {
                return ServerResponse.createByErrorMessage("商品" + goods.getName() + "库存不足,只剩下：" + goods.getStock());
            }

            OrderEntity orderEntity = new OrderEntity();
            orderEntity.setBuyerId(car.getBuyerId());
            orderEntity.setStoreId(goods.getStoreId());
            // 通过店铺获取商家Id
            Store store = storeDao.findStoreById(goods.getStoreId());
            orderEntity.setStoreId(store.getId());
            orderEntity.setGoodsId(goods.getId());
            orderEntity.setGoodsName(goods.getName());
            orderEntity.setGoodsImage(goods.getMainImageUrl());
            orderEntity.setPrice(goods.getPrice());
            orderEntity.setCount(car.getCount());
            orderEntity.setTotalPrice(goods.getPrice() * car.getCount());
            orderEntity.setCreateTime(new Date());

            orderEntityList.add(orderEntity);
        }

        return ServerResponse.createBySuccessData(orderEntityList);
    }

    @Override
    public ServerResponse setOrderStatusByOrderNo(Integer orderNo, Short status) {
        Order order = (Order) orderDao.findOrderByOrderNo(orderNo);
        if (null == order) {
            return ServerResponse.createByErrorMessage("订单不存在");
        }
        order.setStatus(status);
        int rowCount = orderDao.update(order);
        if (rowCount > 0) {
            return ServerResponse.createBySuccess();
        }
        return ServerResponse.createByError();
    }

    public Order finOrderById(Integer buyerId) {

        return orderDao.finOrderById(buyerId);
    }




}