package com.atguigu.test;

import com.atguigu.dao.OrderItemDao;
import com.atguigu.dao.impl.OrderItemDaoImp;
import com.atguigu.pojo.OrderItem;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;


/**
 * @author CubeMonkey
 * @create 2020-08-05 16:06
 */
public class OrderItemDaoImpTest {

    @Test
    public void saveOrderItem() {
        OrderItemDao orderItemDao = new OrderItemDaoImp();
        orderItemDao.saveOrderItem(new OrderItem(null, "java从入门到精通", 1, new BigDecimal(100), new BigDecimal(100), "1234567890"));
        orderItemDao.saveOrderItem(new OrderItem(null, "java从入门到精通", 2, new BigDecimal(100), new BigDecimal(200), "1234567890"));
        orderItemDao.saveOrderItem(new OrderItem(null, "Netty入门", 2, new BigDecimal(100), new BigDecimal(200), "1234567890"));

    }
}