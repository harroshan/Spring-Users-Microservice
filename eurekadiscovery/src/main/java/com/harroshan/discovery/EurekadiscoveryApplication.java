package com.harroshan.discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekadiscoveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekadiscoveryApplication.class, args);
	}

}
