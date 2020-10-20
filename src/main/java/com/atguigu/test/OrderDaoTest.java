package com.atguigu.test;

import com.atguigu.dao.OrderDao;
import com.atguigu.dao.impl.OrderDaoImp;
import com.atguigu.pojo.Order;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;


/**
 * @author CubeMonkey
 * @create 2020-08-05 16:02
 */
public class OrderDaoTest {

    @Test
    public void saveOrder() {
        OrderDao orderDao = new OrderDaoImp();
        orderDao.saveOrder(new Order("1234567890", new Date(), new BigDecimal(100), 0, 1));
    }
}