package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * ribbon简介：
 * springcloud有两种微服务调用方式：reibbon+restTemplate /  feign
 * ribbon是一个负载均衡客户端
 * ribbon可以整合断路器hystrix(ribbon不带有hystrix，需要用hystrix时要添加jar包；feign自带hystix)
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrix//@EnableHystrix开启断路器
public class RibbonServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RibbonServiceApplication.class, args);
	}
	@Bean
	@LoadBalanced//注解表明这个restRemplate开启负载均衡的功能
	RestTemplate restTemplate(){
		return new RestTemplate();
	}

}

