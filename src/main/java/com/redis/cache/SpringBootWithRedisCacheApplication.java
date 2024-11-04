package com.redis.cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootWithRedisCacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootWithRedisCacheApplication.class, args);
		System.out.println("Application is up and running");
	}

}
