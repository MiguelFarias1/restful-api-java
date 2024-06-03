package com.miguel.project.specialist.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Person API")
                        .version("V1")
                        .description("Project to study")
                        .contact(new Contact()
                                .name("MiguelFarias")
                                .email("miguelfariasb8@outlook.com"))
                );
    }
}
