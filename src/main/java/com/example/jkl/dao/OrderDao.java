package com.example.jkl.dao;

import com.example.jkl.mapper.GoodsMapper;
import com.example.jkl.mapper.OrderMapper;
import com.example.jkl.mapper.UserMapper;
import com.example.jkl.pojo.Goods;
import com.example.jkl.pojo.Order;
import com.example.jkl.pojo.User;
import com.example.jkl.pojo.UserOrderShip;
import com.example.jkl.service.UserOrderShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Repository
public class OrderDao {
  @Autowired
  OrderMapper orderMapper;
  @Autowired
  UserMapper userMapper;
  @Autowired
  GoodsMapper goodsMapper;
  @Autowired
    UserOrderShipService userOrderShipService;
    //添加订单
    public Integer addOrder(Order order){
        UserOrderShip userOrderShip = new UserOrderShip();
        userOrderShip.setId(order.getId());
        userOrderShip.setoId(order.getId());
        userOrderShip.setuId(order.getuId());
        userOrderShip.setCreateTime(new Date());
        userOrderShip.setUpdateTime(new Date());
     Integer s= userOrderShipService.addOrderRecord(userOrderShip);
        System.out.println(s);
     return orderMapper.insert(order);
    }
    //删除订单
    public Integer deleteOrderById(Integer id){
        Integer integer = userOrderShipService.deleteOderRecord(id);
        System.out.println(integer);
        return orderMapper.deleteByPrimaryKey(id);
    }
    //批量删除订单
    public  Integer deleteOrderList(List<Integer> ids ){
        Example example =new Example(Order.class);
        example.createCriteria().andIn("id",ids);
       Integer x= userOrderShipService.deleteOrderListRecord(ids);
        System.out.println(x);
    return orderMapper.deleteByExample(example);
    }
    //支付订单
  public Integer payOrder(Order order){

       User user=userMapper.selectByPrimaryKey(order.getuId());
       Goods goods=goodsMapper.selectByPrimaryKey(order.getgId());
      if (user.getLastMoney()>=order.getgPrice()*order.getgCount()){
          user.setLastMoney(user.getLastMoney()-goods.getgPrice()*order.getgCount());
          order.setoState((short) 1);
          order.setUpdateTime(new Date());
          return orderMapper.updateByPrimaryKeySelective(order);
      }else {
          return 0;//正常结束
      }
  }
  //退单
  public Integer backOrder(Order order){
        if (order.getoState()==1){
          //已支付可以退单
            User user = userMapper.selectByPrimaryKey(order.getuId());
            user.setLastMoney(user.getLastMoney()+order.getgPrice()*order.getgCount());
            userMapper.updateByPrimaryKeySelective(user);
            //订单状态设为——1表示退单
            order.setoState((short) -1);
            order.setUpdateTime(new Date());
        return orderMapper.updateByPrimaryKeySelective(order);
        }else {
          //无法退单 正常结束
          return 0;
      }

  }
    //查看所有订单
    public List<Order> findAllOrder(){
        return orderMapper.selectAll();
    }

}
