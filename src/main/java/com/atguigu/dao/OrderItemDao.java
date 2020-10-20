package com.atguigu.dao;

import com.atguigu.pojo.Order;
import com.atguigu.pojo.OrderItem;

/**
 * @author CubeMonkey
 * @create 2020-08-05 15:55
 */
public interface OrderItemDao {
    public int saveOrderItem(OrderItem orderItem);

}
