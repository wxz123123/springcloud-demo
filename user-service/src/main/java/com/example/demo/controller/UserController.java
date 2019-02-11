package com.example.demo.controller;

import com.example.demo.pojo.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2019/2/11.
 */
@RestController
public class UserController {
    @GetMapping("/getUser")
    public User getUser(@RequestParam("name")String name){
        User user=new User();
        user.setName(name);
        user.setAge(18);
        return user ;
    }
}
