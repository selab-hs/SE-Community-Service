package com.core.service.config.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
                .title("SE Community Service")
                .version("v0.0.1")
                .description("SE Community Web Service API 구현");

        return new OpenAPI()
                .info(info);
    }
}