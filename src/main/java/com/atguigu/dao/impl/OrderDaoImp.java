package com.atguigu.dao.impl;


import com.atguigu.dao.OrderDao;
import com.atguigu.pojo.Order;

/**
 * @author CubeMonkey
 * @create 2020-08-05 15:56
 */
public class OrderDaoImp extends BaseDao implements OrderDao {
    @Override
    public int saveOrder(Order order) {

        String sql = "insert into t_order(`order_id`, `create_time`, `price`, `status`, `user_id`) values(?,?,?,?,?)";
        return updata(sql, order.getOrderId(), order.getCreateTime(), order.getPrice(), order.getStatus(), order.getUserId());
    }
}
