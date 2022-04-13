package com.github.k9nz00.migration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
@ConfigurationPropertiesScan("com.github.k9nz00.migration.configuration")
public class MigrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(MigrationApplication.class, args);
	}
}
