package com.easy.easyproduct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class EasyProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(EasyProductApplication.class, args);
	}

}
