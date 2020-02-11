package com.example.jkl.service.impl;

import com.example.jkl.common.Const;
import com.example.jkl.common.ServerResponse;
import com.example.jkl.dao.*;
import com.example.jkl.pojo.*;
import com.example.jkl.response.FindOrderResponse;
import com.example.jkl.service.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
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
    StoreDao storeDao;
    @Override
    public ServerResponse addOrder(OrderEntity orderEntity ) {
        List<Address> addressByUserId = addressDao.findAddressByUserId(orderEntity.getBuyerId());
        if (null == addressByUserId) {
            return ServerResponse.createByErrorMessage("订单创建失败,未找到对应地址");
        }
        List<Car> carList = carDao.selectCarByBuyerId(orderEntity.getBuyerId());

        if (CollectionUtils.isEmpty(carList)) {
            return ServerResponse.createByErrorMessage("购物车中没有任何选中的商品");
        }
        Integer integer = orderDao.addOrder(orderEntity);
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
    public ServerResponse payOrder(OrderEntity orderEntity) {
        Integer integer = orderDao.payOrder(orderEntity);
        if (integer > 0) {
            return ServerResponse.createBySuccessMessage("支付成功");
        }
        return ServerResponse.createByErrorMessage("支付失败");
    }

    @Override
    public ServerResponse backOrder(OrderEntity orderEntity) {
        Integer integer = orderDao.backOrder(orderEntity);
        if (integer > 0) {
            return ServerResponse.createBySuccessMessage("退单成功");
        }
        return ServerResponse.createByErrorMessage("退单失败");
    }

    @Override
    public List<FindOrderResponse> findAllOrder() {
        List<Order> allOrder = orderDao.findAllOrder();
        return showFIndAllOrder(allOrder);
    }
    private List<FindOrderResponse> showFIndAllOrder(List<Order> orderList){
        List<FindOrderResponse> list=new ArrayList<>();
        for (Order order:orderList){
            FindOrderResponse findOrderResponse = new FindOrderResponse();
            BeanUtils.copyProperties(order,findOrderResponse);
            findOrderResponse.setTotalMoney(findOrderResponse.getGPrice()*findOrderResponse.getGCount());
            list.add(findOrderResponse);

        }
        return list;
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
                return ServerResponse.createByErrorMessage("商品" + goods.getName() +"库存不足,只剩下：" + goods.getStock());
            }

            OrderEntity orderEntity = new OrderEntity();
            orderEntity.setBuyerId(car.getBuyerId());
            orderEntity.setStoreId(goods.getStoreId());
            // 通过店铺获取商家Id
            Store store = storeDao.findStoreById(goods.getStoreId());
            orderEntity.setSellerId(store.getSellerId());
            orderEntity.setGoodsId(goods.getId());
            orderEntity.setGoodsName(goods.getName());
            orderEntity.setGoodsImage(goods.getMainImageUrl());
            orderEntity.setPrice(goods.getPrice());
            orderEntity.setCount(car.getCount());
            orderEntity.setTotalPrice(goods.getPrice()*car.getCount());
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

}
