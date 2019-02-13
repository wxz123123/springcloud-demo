package com.example.demo.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description 通过@ FeignClient（“服务名”），来指定调用哪个服务
 * @Author wxz
 * @Date 2019/2/13 11:51
 */
@Service
@FeignClient(value = "user-service",fallback = UserServiceHystrix.class)//fallback表示断路器方法类，服务调用异常时会进入这个类中对于断路方法
public interface UserService {
    @GetMapping("getUser")
    String getUser(@RequestParam("name")String name);
}
