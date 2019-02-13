package com.example.demo.controller;

import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description TODO
 * @Author wxz
 * @Date 2019/2/13 11:51
 */
@RestController
public class RibbonController {
    @Autowired
    private UserService userService;
    @GetMapping("ribbonTest")
    public String ribbonTest(@RequestParam("name")String name){
        /**
         * 通过ribbon调用微服务接口
         * ribbon默认已经做了负载均衡
         */
        return userService.getUser(name);
    }
}
