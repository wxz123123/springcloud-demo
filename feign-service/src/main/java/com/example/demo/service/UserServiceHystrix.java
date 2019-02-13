package com.example.demo.service;

import org.springframework.stereotype.Component;

/**
 * @Description 断路器类 feign调用微服务接口时出现异常，就会进入这个类中对于异常方法
 * @Author wxz
 * @Date 2019/2/13 11:51
 */
@Component
public class UserServiceHystrix implements UserService{
    @Override
    public String getUser(String name) {
        return "服务器调用异常";
    }
}
