package com.atguigu.test;


import java.lang.reflect.Method;

/**
 * @author CubeMonkey
 * @create 2020-07-29 14:03
 */
public class UserServletTest {
    public void login(){
        System.out.println("这是login()方法调用了");
    }
    public void regist(){
        System.out.println("regist()方法调用了");
    }
    public void updateUser(){
        System.out.println("updateUser()方法调用了");
    }

    public void updateUserPassword(){
        System.out.println("updateUserPassword()方法调用了");
    }
}
