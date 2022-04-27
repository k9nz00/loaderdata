package com.github.k9nz00.server.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {

    @Bean
    public OpenAPI customOpenAPI(
            @Value("${spring.application.description}") String description) {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Loaderdata API")
                                .version("1.0.0")
                                .description(description)
                                .contact(
                                        new Contact()
                                                .email("k9nz00@yandex.ru")
                                                .name("Semka Andrey")
                                )
                );
    }
}
