package com.example.jkl.dao;

import com.example.jkl.mapper.GoodsMapper;
import com.example.jkl.mapper.OrderEntityMapper;
import com.example.jkl.mapper.OrderMapper;
import com.example.jkl.mapper.UserMapper;
import com.example.jkl.pojo.Order;
import com.example.jkl.pojo.OrderEntity;
import com.example.jkl.pojo.User;
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
    OrderEntityMapper orderEntityMapper;
  @Autowired
  UserMapper userMapper;
  @Autowired
  GoodsMapper goodsMapper;

    //添加订单
    public Integer addOrderEntity(OrderEntity orderEntity){

     return orderEntityMapper.insert(orderEntity);
    }
    public Integer addorder(Order order){
        return orderMapper.insert(order);
    }
    //删除订单
    public Integer deleteOrderById(Integer id){

        return orderMapper.deleteByPrimaryKey(id);
    }
    //批量删除订单
    public  Integer deleteOrderList(List<Integer> ids ){
        Example example =new Example(Order.class);
        example.createCriteria().andIn("id",ids);


    return orderMapper.deleteByExample(example);
    }
    //支付订单

  public Integer updateOrder(Order order){
        return orderMapper.updateByPrimaryKeySelective(order);
  }
  //退单
  public Integer backOrder(OrderEntity orderEntity){
      Order order = orderMapper.selectByPrimaryKey(orderEntity.getId());
        if (order.getStatus()==1){
          //已支付可以退单
            User user = userMapper.selectByPrimaryKey(order.getBuyerId());
            user.setLastMoney(user.getLastMoney()+orderEntity.getPrice()*orderEntity.getCount());
            userMapper.updateByPrimaryKeySelective(user);
            //订单状态设为——1表示退单
            order.setStatus((short) -1);
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

    public List<Order> findOrderByOrderNo(Integer orderNo){
         Example example =new Example(Order.class);
         example.createCriteria().andEqualTo("orderNo",orderNo);
        return orderMapper.selectByExample(example);
    }
    public Integer update(Order order){
        return  orderMapper.updateByPrimaryKey(order);
    }
    public List<OrderEntity> findOrderByStoreId(Integer storeId){
        Example example=new Example(OrderEntity.class);
        example.createCriteria().andEqualTo("storeId",storeId);
      return   orderEntityMapper.selectByExample(example);
    }
    public Order finOrderById(Integer buyer_id){
        Example example=new Example(Order.class);
        example.createCriteria().andEqualTo("buyer_id",buyer_id);

        return (Order) orderMapper.selectByExample(example);
    }
    public Order findOrderByPrimaryKey(Integer id){
        return orderMapper.selectByPrimaryKey(id);
    }

}
