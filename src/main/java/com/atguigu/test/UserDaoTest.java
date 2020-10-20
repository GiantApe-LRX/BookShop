package com.atguigu.test;

import com.atguigu.dao.UserDao;
import com.atguigu.dao.impl.UserDaoImpl;
import com.atguigu.pojo.User;
import org.junit.jupiter.api.Test;

/**
 * @author CubeMonkey
 * @create 2020-07-13 22:12
 */
public class UserDaoTest {

    @Test
    public void queryUserByUsername() {
        UserDao userDao = new UserDaoImpl();
        if (userDao.queryUserByUsername("admin") == null){
            System.out.println("用户名可用");
        }else{
            System.out.println("用户名已存在");
        }
    }

    @Test
    public void queryUserByUsernameAndPassword() {
        UserDao userDao = new UserDaoImpl();
        if (userDao.queryUserByUsernameAndPassword("admin", "admin") == null){
            System.out.println("用户名或密码错误");
        }else{
            System.out.println("登录成功");
        }
    }

    @Test
    public void saveUser() {
        System.out.println(new UserDaoImpl().saveUser(new User(null, "wzg168", "123456", "wzg168@qq.com")));
    }
}