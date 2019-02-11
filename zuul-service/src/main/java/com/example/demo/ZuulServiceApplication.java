package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * zuul简介：
 * zuul功能上类似于nginx
 * zuul主要功能是路由转发和过滤器
 * zuul默认和ribbon结合实现负载均衡的功能
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy//开启zuul的功能
public class ZuulServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuulServiceApplication.class, args);
	}

}

