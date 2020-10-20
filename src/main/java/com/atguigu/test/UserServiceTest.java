package com.atguigu.test;

import com.atguigu.pojo.User;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;

/**
 * @author CubeMonkey
 * @create 2020-07-13 22:28
 */
public class UserServiceTest {
    UserService userService = new UserServiceImpl();
    @Test
    public void registUser() {
        userService.registUser(new User(null, "bbj168", "666666", "bbj168@qq.com"));
        userService.registUser(new User(null, "abc168", "666666", "abc168@qq.com"));
    }

    @Test
    public void login() {
        if(userService.login(new User(null, "wzg168", "123456", null)) != null ){
            System.out.println("登录成功");
        }else{
            System.out.println("登录失败");
        }
    }

    @Test
    public void existUsername() {
        if (userService.existUsername("wzg16888")){
            System.out.println("用户名已存在");
        }else{
            System.out.println("用户名可用");
        }
    }
}