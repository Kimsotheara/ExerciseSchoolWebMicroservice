package com.theara.Promotion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PromotionApplication {

	public static void main(String[] args) {
		System.out.println("hello service promotion!!!....");
		SpringApplication.run(PromotionApplication.class, args);
	}

}
