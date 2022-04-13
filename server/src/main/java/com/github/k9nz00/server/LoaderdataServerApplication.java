package com.github.k9nz00.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class LoaderdataServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoaderdataServerApplication.class, args);
	}
}
