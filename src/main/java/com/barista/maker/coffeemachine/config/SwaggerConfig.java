package com.barista.maker.coffeemachine.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Coffee Machine API")
                        .version("1.0.0")
                        .description("API Documentation for Coffee Machine application")
                        .contact(new Contact()
                                .name("Arslan Marat")
                                .email("maratarslan0@gmail.com")));
    }
}
