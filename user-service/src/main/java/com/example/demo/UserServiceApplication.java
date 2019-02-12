package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * @EnableEurekaClient和@EnableDiscoveryClient注解都可以用于注册发现
 * @EnableEurekaClient只适用于Eureka注册中心，@EnableDiscoveryClient可以适用于其他注册中心
 */
@SpringBootApplication
@EnableDiscoveryClient
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

}

