package com.theara.User;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class UserApplication {
	public static void main(String[] args) {
		System.out.println("hello service user testing!!!...");
		SpringApplication.run(UserApplication.class, args);
	}

}
