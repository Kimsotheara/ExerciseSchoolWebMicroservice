package com.theara.CategoryAndCourse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CategoryAndCourseApplication {

	public static void main(String[] args) {
		System.out.println("hello service category and course!!!!!...");
		SpringApplication.run(CategoryAndCourseApplication.class, args);
	}

}
