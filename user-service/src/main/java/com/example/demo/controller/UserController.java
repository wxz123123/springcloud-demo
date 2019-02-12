package com.example.demo.controller;

import com.example.demo.pojo.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2019/2/11.
 */
@RestController
public class UserController {
    /**
     * 从配置中心中获取配置
     */
    @Value("${name}")
    private String name;
    @Value("${age}")
    private Integer age;
    @Value("${system.password}")
    private String password;

    @GetMapping("/getUser")
    public User getUser(@RequestParam("name")String name){
        User user=new User();
        user.setName(name);
        user.setAge(18);
        return user ;
    }

    @GetMapping("/getUserByRepo")
    public User getUserByRepo(){
        User user=new User();
        user.setName(name);
        user.setAge(age);
        user.setPassword(password);
        return user ;
    }
}
