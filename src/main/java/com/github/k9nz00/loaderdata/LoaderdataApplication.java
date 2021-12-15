package com.github.k9nz00.loaderdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class LoaderdataApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoaderdataApplication.class, args);
	}
}
