package com.atguigu.dao.impl;

import com.atguigu.pojo.OrderItem;

/**
 * @author CubeMonkey
 * @create 2020-08-05 15:59
 */
public class OrderItemDaoImp extends BaseDao implements com.atguigu.dao.OrderItemDao {

    @Override
    public int saveOrderItem(OrderItem orderItem) {

        String sql = "insert into t_order_item(`name`, `count`, `price`, `total_price`, `order_id`) values(?,?,?,?,?)";
        return updata(sql, orderItem.getName(), orderItem.getCount(), orderItem.getPrice(), orderItem.getTotalPrice(), orderItem.getOrderId());
    }
}
