package com.example.demo.service;

import org.springframework.stereotype.Component;

/**
 * 断路器类
 * feign调用微服务接口时出现异常，就会进入这个类中对于异常方法
 * Created by Administrator on 2019/2/11.
 */
@Component
public class UserServiceHystrix implements UserService{
    @Override
    public String getUser(String name) {
        return "服务器调用异常";
    }
}
