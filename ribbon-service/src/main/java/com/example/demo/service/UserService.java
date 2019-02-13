package com.example.demo.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @Description ribbon调用服务
 * @Author wxz
 * @Date 2019/2/13 11:51
 */
@Service
public class UserService {
    /**
     * 用于调用微服务
     */
    @Autowired
    private RestTemplate restTemplate;

    //加入熔断
    @HystrixCommand(fallbackMethod = "getUserError")
    public String getUser(String name){
        /**
         * 通过ribbon调用微服务接口
         * ribbon默认已经做了负载均衡
         */
        return restTemplate.getForObject("http://user-service/getUser?name="+name,String.class);
    }

    /**
     * 熔断方法，当微服务服务调用出现异常的时候会进入这个方法
     * @return
     */
    public String getUserError(String name){
        return "服务调用异常!";
    }
}
