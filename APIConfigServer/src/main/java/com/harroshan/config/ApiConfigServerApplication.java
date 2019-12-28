package com.harroshan.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ApiConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiConfigServerApplication.class, args);
	}

}
