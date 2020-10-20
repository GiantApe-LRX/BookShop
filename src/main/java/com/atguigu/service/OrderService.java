package com.atguigu.service;

import com.atguigu.pojo.Cart;

/**
 * @author CubeMonkey
 * @create 2020-08-05 16:12
 */
public interface OrderService {
    public String createOrder(Cart cart, Integer userId);
}
