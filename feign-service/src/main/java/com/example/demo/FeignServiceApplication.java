package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * feign简介：
 * feign和ribbon都是springcloud微服务调用方式之一
 * feign默认集成ribbon，具有负载均衡的能力
 * feign采用基于注解方式调用微服务，只要创建一个接口并采用注解就可以调用微服务
 * feign自带断路器Hystrix,但是熔断器默认是关闭状态，需要在配置中开启它
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients//@EnableFeignClients注解开启Feign的功能
public class FeignServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeignServiceApplication.class, args);
	}

}

