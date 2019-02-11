package com.example.demo.controller;

import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2019/2/11.
 */
@RestController
public class FeignController {
    @Autowired
    private UserService userService;
    @GetMapping("testFeign")
    public String testFeign(@RequestParam("name")String name){
        return userService.getUser(name);
    }
}
