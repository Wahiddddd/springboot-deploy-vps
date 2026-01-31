package com.dibimbing.deployment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
@EnableCaching
public class DeploymentApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeploymentApplication.class, args);
	}

	@PostConstruct
	public void checkEnv() {
		System.out.println("DB_HOST = " + System.getenv("DB_HOST"));
	}


}
