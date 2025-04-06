package com.extia.easyshopper.infrastructure.config.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("EasyShopper product catalog API.")
                        .contact(new Contact()
                                .name("Jullie Paixão")
                                .email("euujullie@gmail.com")
                                .url("https://www.jullie.dev"))
                        .description("API developed to manage product catalog.")
                        .version("1.0.0")
                );
    }
}
